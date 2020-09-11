package ru.otus.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.mybatis.dao.AddressDao;
import ru.otus.mybatis.dao.PersonDao;
import ru.otus.mybatis.dao.SampleMapperInterface;
import ru.otus.mybatis.model.Address;
import ru.otus.mybatis.model.Person;
import ru.otus.mybatis.model.Sample;

public class BatisTests {
  private static SqlSessionFactory sqlSessionFactory;

  @BeforeAll
  public static void beforeAll() throws IOException, SQLException {
    String resource = "mybatis-config.xml";
    InputStream inputStream = Resources.getResourceAsStream(resource);

    sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

    // Можно сконфигурировать и в коде:
    // sqlSessionFactory.getConfiguration().addMapper(Sample.class);
    // sqlSessionFactory.getConfiguration().addCache();
    // sqlSessionFactory.getConfiguration()...
  }

  @BeforeEach
  void beforEach() throws SQLException {
      BatisStarter demo = new BatisStarter();
      demo.createTables();
      demo.insertRecords();
  }

  // Выбор одного объекта
  @Test
  void selectOne() {
    try (SqlSession session = sqlSessionFactory.openSession()) {
      Sample sample = session.selectOne("testMapper.selectTestOne", 1);
      System.out.println("selected: " + sample);
    }
  }

  // Выбор списка объектов, несколько параметров
  @Test
  void selectAll() {
    try (SqlSession session = sqlSessionFactory.openSession()) {
      Map<String, Integer> params = new HashMap<>();
      params.put("minId", 50);
      params.put("maxId", 55);
      List<Sample> samples = session.selectList("testMapper.selectTestAll", params);
      System.out.println("selected: " + samples);
    }
  }

  // like, шаблоны
  @Test
  void selectLike() {
    try (SqlSession session = sqlSessionFactory.openSession()) {
      Map<String, String> params = new HashMap<>();
      params.put("nameParam", "%2%");
      List<Sample> samples = session.selectList("testMapper.selectTestLike", params);
      System.out.println("selected like with nameParam: " + samples);

      samples = session.selectList("testMapper.selectTestLike");
      System.out.println("selected like without nameParam: " + samples);
    }
  }

  // in -- select * from Test where id in (1,2,3,4)
  @Test
  void selectForEach() {
    try (SqlSession session = sqlSessionFactory.openSession()) {
      List<Integer> params = Arrays.asList(1, 2, 3, 4, 5);
      List<Sample> samples = session.selectList("testMapper.selectTestForEach", params);
      System.out.println("selectedForEach: " + samples);
    }
  }

  @Test
  void insert() {
    try (SqlSession session = sqlSessionFactory.openSession()) {
      Map<String, String> params = new HashMap<>();
      params.put("id", "500");
      params.put("name", "TestInsertovich");
      int rowCount = session.insert("testMapper.insert", params);
      System.out.println("inserted: " + rowCount);

      Sample sample = session.selectOne("testMapper.selectTestOne", 500);
      System.out.println("selected: " + sample);
    }
  }

  // Маппинг через интерфейсы
  @Test
  void selectOneInterface() {
    //Optional добавили в 3.5.0 (только для "интерфейсов")

    try (SqlSession session = sqlSessionFactory.openSession()) {
      SampleMapperInterface mapper = session.getMapper(SampleMapperInterface.class);
      Optional<Sample> sample = mapper.findOne(1);
      System.out.println("selected: " + sample);

      Optional<Sample> testNotExists = mapper.findOne(-1);
      System.out.println("selected: " + testNotExists);
    }
  }

  @Test
  void selectCached() {
    try (SqlSession session = sqlSessionFactory.openSession()) {
      Sample sample = session.selectOne("testMapper.selectTestOne", 1);
      System.out.println("1 selected: " + sample);

      session.selectOne("testMapper.selectTestOne", 1);
      System.out.println("2 selected: " + sample);

      session.selectOne("testMapper.selectTestOne", 1);
      System.out.println("3 selected: " + sample);

      session.selectOne("testMapper.selectTestOne", 4);
      System.out.println("4 selected: " + sample);
    }

    // Настройка кэша в TestMapper.xml и ehcache.xml
  }

  @Test
  public void selectPersonByCity() {
    try (SqlSession session = sqlSessionFactory.openSession()) {
      PersonDao personDao = session.getMapper(PersonDao.class);
      AddressDao addressDao = session.getMapper(AddressDao.class);

      Person person = new Person();
      person.setId(1);
      person.setFirstName("John");
      person.setLastName("Black");

      Person person2 = new Person();
      person2.setId(2);
      person2.setFirstName("Ivan");
      person2.setLastName("Ivanov");

      Address address = new Address();
      address.setId(1);
      address.setPersonId(2);
      address.setCity("Moscow");

      personDao.insert(person);
      personDao.insert(person2);
      addressDao.insert(address);

      List<Person> loadedPerson = personDao.selectByCity("Moscow");
      System.out.println(loadedPerson);
    }
  }
}
