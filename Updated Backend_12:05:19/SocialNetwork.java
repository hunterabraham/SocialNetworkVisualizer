import java.util.List;
import application.DuplicateNameException;
import application.Graph;
import application.SocialNetworkADT;

public class SocialNetwork implements SocialNetworkADT {
  private Graph graph;

  public SocialNetwork() {
    graph = new Graph();
  }

  @Override
  public void createNewAccount(String userName) throws DuplicateNameException {
    graph.addUser(userName);
  }

  @Override
  public List<String> getFriendList(String user) {
    return graph.getFriendsOf(user);
  }

  @Override
  public void addFriend(String user1, String user2) {
    graph.addFriend(user1, user2);
  }

  @Override
  public void removeFriend(String user1, String user2) {
    graph.removeFriend(user1, user2);
  }
}
