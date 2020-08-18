package ru.otus.l11.encapsulation;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

class Employee{
  Department department = new Department();

  public Department getDepartment() {
    return this.department;
  }

  public String getCompanyName() {
    return this.department.getCompany().getName();
    // return this.department.getCompanyName();
  }
}

class Department {
  Company company = new Company();

  public Company getCompany() {
    return this.company;
  }
}

class Company {
  String name;
  Set<Department> departments = new HashSet<>();

  public String getName() {
    return this.name;
  }

  public Set<Department> getDepartments() {
    return departments;
  }
}

// Employee -> Department -> Company
public class EncapsusationDemo {

  public static void main(String[] args) {
    // У нас есть сотрудник, мы хотим получить название организации
    Employee employee = new Employee();

    // Совсем криминал
    String companyName1 = employee.department.company.name;

    // Уже лучше
    String companyName2 = employee.getDepartment().getCompany().getName();

    // В идеале так
    String companyName3 = employee.getCompanyName();

    // Здесь, что может пойти не так?
    // Мы хотим добавить подразделения
    var company = new Company();
    var departments = company.getDepartments();

    departments.add(new Department());
    departments.add(new Department());
    System.out.println("departments.size() = " + departments.size());

    // см. код ниже ....





















//     company.addDepartment(department1);
//     company.removeDepartment(department1);

    // Еще какие варианты решения?
  }
}


