package application;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
// import Graph.GraphNode;



public class Graph implements GraphADT {
  private ArrayList<Person> adjList;

  /*
   * Default no-argument constructor
   */
  public Graph() {
    adjList = new ArrayList<Person>();
  }

  @Override
  public void addUser(String userName) throws DuplicateNameException {
    if (userName == null) {
      // Do nothing
    }
    // check if User is already in graph
    for (int i = 0; i < adjList.size(); i++) {
      if (adjList.get(i).getUserName().equals(userName))
        return;
    }
    Person user = new Person(userName);
    adjList.add(user);
  }

  @Override
  public void removeUser(String user) {
    if (user == null) {
      // Do nothing
    }
    for (int i = 0; i < adjList.size(); i++) {
      if (adjList.get(i).getUserName().equals(user)) {
        adjList.remove(i);// removed vertex from adjLists
        for (int k = 0; k < adjList.size(); k++) {
          adjList.get(k).getDependencyList().remove(user);
        }
        break;
      }
    }
    // removed vertex and all instances of vertex in other graph nodes dependency lists
  }

  @Override
  public void addFriend(String user1, String user2) {

    if (user1 == null || user2 == null)
      return;
    else if (user1.equals(user2)) {// checks for self edge
      return;
    }
    // check if verteces are in graph
    ArrayList<String> nameHolder = new ArrayList<String>();
    for (int i = 0; i < adjList.size(); i++) {
      nameHolder.add(adjList.get(i).getUserName());
    }
    // if the list doesn't contain either v1 or v2, return
    if (!nameHolder.contains(user1) || !nameHolder.contains(user2))
      return;

    for (int i = 0; i < adjList.size(); i++) {
      // if v1 is found in the adjList, store its index
      if (adjList.get(i).getUserName().equals(user1)) {
        // check if edge already exists from v1 --> v2
        if (adjList.get(i).getDependencyList().contains(user2)) {
          return; // edge already exists
        } else {
          // edge does not exist, create edge from v1 --> v2
          ArrayList<String> temp = adjList.get(i).getDependencyList();
          temp.add(user2);
          Person tempNode = new Person(user1);
          tempNode.addToDependencyList(temp);
          adjList.set(i, tempNode);
        }
      }
    }
  }

  @Override
  public void removeFriend(String user1, String user2) {
    // TODO Auto-generated method stub
    // checks that verteces are not null
    if (user1 == null || user2 == null)
      return;
    // check if verteces are in graph
    ArrayList<String> nameHolder = new ArrayList<String>();
    for (int i = 0; i < adjList.size(); i++) {
      nameHolder.add(adjList.get(i).getUserName());
    }
    // if the list doesn't contain either v1 or v2, return
    if (!nameHolder.contains(user1) || !nameHolder.contains(user2))
      return;
    for (int i = 0; i < adjList.size(); i++) {
      // if v1 is found in the adjList, store its index
      if (adjList.get(i).getUserName().equals(user1)) {
        // check if edge already exists from v1 --> v2
        if (adjList.get(i).getDependencyList().contains(user2)) {
          adjList.get(i).getDependencyList().remove(user2);// removes v2 from dependency list of v1
        }
      }
    }
  }

  @Override
  public Set<String> getAllUsers() {
    Set<String> toReturn = new HashSet<>();
    // iterate through adjList
    for (int i = 0; i < adjList.size(); i++) {
      toReturn.add(adjList.get(i).getUserName());
    }
    return toReturn;
  }

  @Override
  public List<String> getFriendsOf(String user) {
    for (int i = 0; i < adjList.size(); i++) {
      if (adjList.get(i).getUserName().equals(user)) {
        return adjList.get(i).getDependencyList();
      }
    }
    return null;
  }

  @Override
  public int size() {
    int toReturn = 0;
    for (int i = 0; i < adjList.size(); i++) {
      toReturn += adjList.get(i).getDependencyList().size();
    }
    return toReturn;
  }

  @Override
  public int order() {
    return adjList.size();
  }



}
