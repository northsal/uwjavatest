package com.tedneward.example;

import java.beans.*;
import java.util.*;

public class Person implements Comparable<Person>{
  private int age;
  private String name;
  private double salary;
  private String ssn;
  private boolean propertyChangeFired = false;
  
  public Person() {
    this("", 0, 0.0d);
  }
  
  public Person(String n, int a, double s) {
    name = n;
    age = a;
    salary = s;
    ssn = "";
  }

  public static class AgeComparator implements Comparator<Person> {

    @Override
    public int compare(Person p1, Person p2) {
      int age1 = p1.getAge();
      int age2 = p2.getAge();
      return age1 - age2;
    }
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    if(age < 0) {
      throw new IllegalArgumentException();
    }
    this.age = age;
  }
  
  public String getName() {
    return name;
  }

  public void setName(String name) {
    if(name == null) {
      throw new IllegalArgumentException();
    }
    this.name = name;
  }
  
  public double getSalary() {
    return salary;
  }

  public void setSalary(double salary) {
    this.salary = salary;
  }
  
  public String getSSN() {
    return ssn;
  }

  public void setSSN(String value) {
    String old = ssn;
    ssn = value;
    
    this.pcs.firePropertyChange("ssn", old, value);
    propertyChangeFired = true;
  }
  public boolean getPropertyChangeFired() {
    return propertyChangeFired;
  }

  public double calculateBonus() {
    return salary * 1.10;
  }
  
  public String becomeJudge() {
    return "The Honorable " + name;
  }
  
  public int timeWarp() {
    return age + 10;
  }
  
  public boolean equals(Object other) {
    if(this == other) return true;

    if(other ==null || (this.getClass() != other.getClass())) return false;

    Person p = (Person) other;
    return (name.equals(p.getName()) && age == p.getAge());

  }

  public String toString() {
    return "[Person name:" + this.name + " age:" + this.age +  " salary:" + this.salary + "]";
  }

  public static ArrayList<Person> getNewardFamily() {
    ArrayList<Person> list = new ArrayList<Person>();
    list.add( new Person("Ted", 41, 250000) );
    list.add( new Person("Charlotte", 43, 150000) );
    list.add( new Person("Michael", 22, 10000) );
    list.add( new Person("Matthew", 15, 0) );

    return list;
  }

  public int compareTo (Person other) {
    double salary2 = other.getSalary();
    if(this.salary < salary2) {
      return 1;
    }
    if(this.salary > salary2) {
      return -1;
    }
    return 0;
  }
  // PropertyChangeListener support; you shouldn't need to change any of
  // these two methods or the field
  //
  private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
  public void addPropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.addPropertyChangeListener(listener);
  }
  public void removePropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.removePropertyChangeListener(listener);
  }
}
