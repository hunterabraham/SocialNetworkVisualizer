package application;

import java.util.ArrayList;

public class Person {
  private ArrayList<String> dependencyList;// list of verteces that this node has edges to
  private final String userName;// name of this node
  private ArrayList<Person> friends;

  /**
   * Constuctor
   * 
   * @param name - name of Person
   */
  public Person(String name) {
    userName = name;
    dependencyList = new ArrayList<String>();
  }

  public String getUserName() {// name getter
    return userName;
  }

  public String toString() {
    return userName;
  }

  public void friends(Person person) {
    friends.add(person);
  }

  public ArrayList<Person> friend() {
    return friends;
  }
}
