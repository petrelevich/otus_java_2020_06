package ru.otus.l11.encapsulation;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

class CompanyUnmodifiableDepartments {
  private Set<Department> departments = new HashSet<>();

  public Set<Department> getDepartments() {
    return Collections.unmodifiableSet(departments);
  }
}

class CompanyIterableDepartments {
  private Set<Department> departments = new HashSet<>();

  public Iterable<Department> getDepartments() {
    return departments;
  }

  public void addDepartment(Department department) {
    // ... какая-то логика
    departments.add(department);
    // ... какая-то логика
  }
}

public class EncapsusationDemo2 {

  public static void main(String[] args) {
    var company1 = new CompanyUnmodifiableDepartments();
    var departments1 = company1.getDepartments();
    departments1.add(new Department());

    var company2 = new CompanyIterableDepartments();
    company2.addDepartment(new Department());
    company2.addDepartment(new Department());
    var departments2 = company1.getDepartments();

    for(Department department : departments2){
      System.out.println(department);
    }
  }

}
