/**
 * 
 */
package application;

import java.util.List;

/**
 * @author EliAsher & MihirKhatri
 *
 */
public interface SocialNetworkADT {

  /**
   * 
   * Creates a new account to be added to the social network
   * 
   * @param userName - String for the account
   * @throws DuplicateNameException
   */
  public void createNewAccount(String userName) throws DuplicateNameException;

  /**
   * 
   * @param user
   */
  public List<String> getFriendList(String user);

  /**
   * Creates a friendship between two users
   * 
   * @param user1
   * @param user2
   */
  public void addFriend(String user1, String user2);

  public void removeFriend(String user1, String user2);

}
