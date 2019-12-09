package application;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * Adapted things from Java's Using JavaFX UI Controls
 * 
 * @author
 *
 */
public class Main extends Application {
  SocialNetwork network = new SocialNetwork(); // Main network of friends
  String centralUser = ""; // Starting user
  ArrayList<Scene> previous = new ArrayList<Scene>();// Stores previous scene

  /**
   * Start the GUI
   */
  @Override
  public void start(Stage stage) {

    Button backButton = new Button("Back");
    backButton.setStyle("-fx-background-color: red");
    backButton.setFont(Font.font("Arial", FontWeight.BOLD, 12));


    // Probally jsut gonna get rid of login scene
    Text username = new Text("Username"); // Create username and password text
    TextField user = new TextField(); // Field to type username and password into
    user.setPromptText(centralUser); // Will change these later

    Button logout = new Button("X");
    logout.setStyle("-fx-background-color: red");
    logout.setFont(Font.font("Arial", FontWeight.BOLD, 15));
    logout.setPadding(new Insets(3, 22, 3, 20));
    logout.setOnAction(e -> stage.close());

    Button submit = new Button("Submit");
    Button loadFile = new Button("   Load New File     ");
    loadFile.setPadding(new Insets(5, 28, 5, 28));

    GridPane login = new GridPane();
    login.setMinSize(250, 150); // Make grid right size

    // Set the gradient to the login page
    login.setStyle("-fx-background-color: linear-gradient(to bottom , CYAN, ROYALBLUE)");

    // Setting up the spacing and layout of the login page
    login.setVgap(5);
    login.setHgap(5);
    login.setAlignment(Pos.CENTER);

    Button createAcc = new Button("Create New Account"); // FINISH SCENE
    createAcc.setPadding(new Insets(5, 25, 5, 25));

    login.add(username, 0, 0);
    login.add(user, 1, 0);
    login.add(logout, 0, 2);
    login.add(createAcc, 1, 2);
    login.add(submit, 0, 1);
    login.add(loadFile, 1, 1);



    // Login Scene
    Scene loginScene = new Scene(login, 250, 250);
    stage.setTitle("TWEETER");


    // New scene
    // This scene will appear if help button is pressed
    // Could be seperated and fixed

    Label loadLabel = new Label("Load New File ");

    TextField loadText = new TextField();
    loadText.setPromptText("example.txt");

    loadLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
    Button submitFile = new Button("Submit");

    BorderPane layout2 = new BorderPane();
    layout2.setAlignment(loadLabel, Pos.TOP_CENTER);
    layout2.setTop(loadLabel);
    layout2.setBottom(submitFile);
    layout2.setCenter(loadText);
    layout2.setStyle("-fx-background-color: linear-gradient(to top , CYAN, ROYALBLUE)");
    Scene loadFileScene = new Scene(layout2, 200, 100);


    stage.setScene(loadFileScene);
    stage.show();

    // Scene 4
    // Account creation not sure what fields will exist yet so for now it is closed

    Text newUser = new Text("Username");
    TextField newUserTextField = new TextField("letters, digits, underscore, apostrophe");

    BorderPane createNewAccount = new BorderPane();
    createNewAccount.setCenter(newUserTextField);
    createNewAccount.setTop(newUser);
    createNewAccount.setStyle("-fx-background-color: linear-gradient(to top , CYAN, ROYALBLUE)");
    Button create = new Button("Create");
    createNewAccount.setBottom(create);

    Scene createNewACC = new Scene(createNewAccount, 200, 100);


    // These are all the action events for when buttons are pressed will likely need to be changed
    // in the future
    EventHandler<ActionEvent> loadUsers = new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e) {
        previous.add(stage.getScene());
        // previous = stage.getScene();
        stage.setScene(loadFileScene);
      }
    };
    loadFile.setOnAction(loadUsers);

    EventHandler<ActionEvent> loadFileSub = new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e) {
        previous.add(stage.getScene());
        if (!loadText.getText().equals("")) {
          loadFile(loadText.getText());
        }
        stage.setScene(loginScene);
      }
    };
    submitFile.setOnAction(loadFileSub);

    EventHandler<ActionEvent> loginSubmit = new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e) {
        if (network.string().contains(user.getText())) {
          if (user.getText().equals(centralUser)) {
            previous.add(stage.getScene());
            // previous = stage.getScene();
            loadUser(user.getText(), stage, loginScene);
          }
        }
      }
    };
    submit.setOnAction(loginSubmit);

    EventHandler<ActionEvent> createNewAcc = new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e) {
        previous.add(stage.getScene());
        // previous = stage.getScene();
        stage.setScene(createNewACC);
      }
    };
    createAcc.setOnAction(createNewAcc);

    EventHandler<ActionEvent> createNewAcco = new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e) {
        try {
          network.createNewAccount(newUserTextField.getText());
        } catch (Exception q) {
          newUserTextField.setText("name not valid");
        }
        if (centralUser.equals("")) {
          centralUser = newUserTextField.getText();
        }
        stage.setScene(previous.get(previous.size() - 1));
        previous.remove(previous.size() - 1);
      }
    };
    create.setOnAction(createNewAcco);

    EventHandler<ActionEvent> backBut = new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e) {
        stage.setScene(previous.get(previous.size() - 1));
        previous.remove(previous.size() - 1);
      }
    };
    backButton.setOnAction(backBut);

  }

  /**
   * Launches GUI
   * 
   * @param args
   */
  public static void main(String args[]) {
    launch(args);
  }

  /**
   * Adds the Goto button to the table Adapted portions from https://riptutorial.com/javafx
   * 
   * @param stage     main stage that is show
   * @param tableMain table for the user
   */
  private void goTo(Stage stage, TableView<Person> tableMain) {
    Scene scene = stage.getScene();
    TableColumn<Person, Void> goToPageCol = new TableColumn("User Page"); // This is the column that
                                                                          // holds the button to go
                                                                          // to a user page
    Callback<TableColumn<Person, Void>, TableCell<Person, Void>> cell =
        new Callback<TableColumn<Person, Void>, TableCell<Person, Void>>() {
          @Override
          public TableCell<Person, Void> call(final TableColumn<Person, Void> param) {
            final TableCell<Person, Void> cell = new TableCell<Person, Void>() {

              private final Button goToPage = new Button("Go to Page");

              {
                goToPage.setOnAction((ActionEvent event) -> {
                  Person username = getTableView().getItems().get(getIndex());
                  previous.add(stage.getScene());
                  loadUser(username.getUserName(), stage, scene);
                });
              }

              @Override
              public void updateItem(Void item, boolean emptyRow) {
                super.updateItem(item, emptyRow);
                if (emptyRow) {
                  setGraphic(null);
                } else {
                  setGraphic(goToPage);
                }
              }
            };
            return cell;
          }
        };
    goToPageCol.setCellFactory(cell);

    tableMain.getColumns().add(goToPageCol);
  }

  /**
   * Adds the Add button to the table Adapted portions from https://riptutorial.com/javafx
   * 
   * @param stage    stage that is show
   * @param table
   * @param mainUser
   */
  private void goToAdd(Stage stage, TableView<Person> table, Person mainUser) {
    Scene scene = stage.getScene();
    TableColumn<Person, Void> goToPageCol = new TableColumn("User Page"); // This is the column that
                                                                          // holds the button to go
                                                                          // to a user page
    Callback<TableColumn<Person, Void>, TableCell<Person, Void>> cell =
        new Callback<TableColumn<Person, Void>, TableCell<Person, Void>>() {
          @Override
          public TableCell<Person, Void> call(final TableColumn<Person, Void> param) {
            final TableCell<Person, Void> cell = new TableCell<Person, Void>() {

              private final Button addFriendPage = new Button("Add");

              {
                addFriendPage.setOnAction((ActionEvent event) -> {
                  Person username = getTableView().getItems().get(getIndex());
                  network.addFriend(username.getUserName(), mainUser.getUserName());
                  loadUser(mainUser.getUserName(), stage, scene);
                });
              }

              @Override
              public void updateItem(Void item, boolean emptyRow) {
                super.updateItem(item, emptyRow);
                if (emptyRow) {
                  setGraphic(null);
                } else {
                  setGraphic(addFriendPage);
                }
              }
            };
            return cell;
          }
        };
    goToPageCol.setCellFactory(cell);

    table.getColumns().add(goToPageCol);
  }

  /**
   * 
   * Recursive method to load all user data
   * 
   * @param user  user to load
   * @param stage the main stage that is show
   * @param main  the main scene
   */
  private void loadUser(String user, Stage stage, Scene main) {
    Person mainUser = null;
    for (Person user_1 : network.get()) {
      if (user_1.getUserName().equals(user)) {
        mainUser = user_1;
        break;
      }
    }
    TableView<Person> tableMain = new TableView<Person>();
    // This will eventually just find the user in the graph and read the connections
    ObservableList<Person> dataMain =
        FXCollections.observableArrayList(network.getFriendList(user));

    Button backButton = new Button("BACK");
    backButton.setStyle("-fx-background-color: red");
    backButton.setFont(Font.font("Arial", FontWeight.BOLD, 12));

    BorderPane home = new BorderPane();
    Label name = new Label(user); // Take username
    name.setFont(Font.font("Arial", FontWeight.BOLD, 24));
    home.setAlignment(name, Pos.TOP_CENTER);
    home.setStyle("-fx-background-color: white");
    home.setTop(name);
    home.setBottom(backButton);

    // ImageView images = new ImageView(); // Profile pic would exist in create new account
    // Image picer = new Image("me.png");
    // images.setImage(picer);
    // home.setCenter(images);

    VBox vbox = new VBox(); // Side panel
    vbox.setPadding(new Insets(10));
    vbox.setSpacing(8);
    vbox.setStyle("-fx-background-color: darkblue ");

    Text title = new Text("Friends");
    title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
    title.setFill(Color.WHITE);
    vbox.getChildren().add(title);

    Button viewFriends = new Button("View Friends"); // Load a list of users friends
    viewFriends.setStyle("-fx-background-color: white");
    viewFriends.setFont(Font.font("Arial", FontWeight.BOLD, 12));
    VBox.setMargin(viewFriends, new Insets(0, 0, 0, 8));
    vbox.getChildren().add(viewFriends); // Possibly add a small friends scene to show their pages
    // Also add a back button

    // Scene five to show friends
    BorderPane showFriends = new BorderPane();
    showFriends.setStyle("-fx-background-color: linear-gradient(to bottom , CYAN, ROYALBLUE)");
    Scene scene5 = new Scene(showFriends, 450, 450);
    final Label label = new Label("Friends");

    showFriends.setAlignment(label, Pos.TOP_CENTER);
    label.setFont(Font.font("Courier", FontWeight.BOLD, 20));

    tableMain.setEditable(true);

    // All of this will be formatted differently in order to accommodate a graph
    TableColumn userName = new TableColumn("Username");
    userName.setMinWidth(150);
    userName.setCellValueFactory(new PropertyValueFactory<Person, String>("userName"));

    tableMain.setItems(dataMain);
    tableMain.getColumns().addAll(userName);
    goTo(stage, tableMain);

    final VBox vList = new VBox();
    vList.setSpacing(5);
    vList.setPadding(new Insets(10, 0, 0, 10));
    vList.getChildren().addAll(tableMain);
    tableMain.setStyle("-fx-background-color: linear-gradient(to bottom , CYAN, ROYALBLUE)");

    Button backButtonOther = new Button("BACK");
    backButtonOther.setStyle("-fx-background-color: red");
    backButtonOther.setFont(Font.font("Arial", FontWeight.BOLD, 12));

    showFriends.setTop(label);
    showFriends.setCenter(vList);
    showFriends.setLeft(new Label("ADS")); // LOL
    showFriends.setRight(new Label("ADS"));
    showFriends.setBottom(backButtonOther);

    // Add a scene with a search function This could show a list of the users in the graph and
    // let
    // the main user add a connection to them
    Button addFriend = new Button("Add Friend");
    addFriend.setStyle("-fx-background-color: white");
    addFriend.setFont(Font.font("Arial", FontWeight.BOLD, 12));
    VBox.setMargin(addFriend, new Insets(0, 0, 0, 8));
    vbox.getChildren().add(addFriend); // add new scene


    BorderPane addFriends = new BorderPane();
    addFriends.setStyle("-fx-background-color: linear-gradient(to bottom , CYAN, ROYALBLUE)");
    final Label labelOther = new Label("All People");
    labelOther.setFont(Font.font("Arial", FontWeight.BOLD, 16));

    addFriends.setAlignment(labelOther, Pos.TOP_CENTER);
    label.setFont(Font.font("Courier", FontWeight.BOLD, 20));


    TableView<Person> allTable = new TableView<Person>();

    ArrayList<Person> temp = network.get();
    ArrayList<Person> temp_1 = network.getFriendList(user);
    temp.remove(network.search(user));
    temp.removeAll(temp_1);


    ObservableList<Person> data = FXCollections.observableArrayList(temp);
    allTable.setEditable(true);

    // All of this will be formatted differently in order to accommodate a graph
    TableColumn userNameOther = new TableColumn("Username");
    userNameOther.setMinWidth(150);
    userNameOther.setCellValueFactory(new PropertyValueFactory<Person, String>("userName"));

    allTable.setItems(data);
    allTable.getColumns().addAll(userNameOther);
    goToAdd(stage, allTable, mainUser);

    final VBox vList2 = new VBox();
    vList2.setSpacing(5);
    vList2.setPadding(new Insets(10, 0, 0, 10));
    vList2.getChildren().addAll(allTable);
    allTable.setStyle("-fx-background-color: linear-gradient(to bottom , CYAN, ROYALBLUE)");

    addFriends.setTop(labelOther);
    addFriends.setCenter(vList2);

    Button backButton_two = new Button("Back");
    backButton_two.setStyle("-fx-background-color: red");
    backButton_two.setFont(Font.font("Arial", FontWeight.BOLD, 12));
    addFriends.setAlignment(backButton_two, Pos.BOTTOM_LEFT);
    addFriends.setBottom(backButton_two);

    Scene addFriendsScene = new Scene(addFriends, 450, 450);



    Button removeFr = new Button("Remove Friend"); // Go to scene with list of friends
    // and delete from it
    removeFr.setStyle("-fx-background-color: white");
    removeFr.setFont(Font.font("Arial", FontWeight.BOLD, 12));
    VBox.setMargin(removeFr, new Insets(0, 0, 0, 8));
    vbox.getChildren().add(removeFr); // add new scene



    TableView<Person> tableMainRemove = new TableView<Person>();
    ObservableList<Person> dataMainRemove =
        FXCollections.observableArrayList(network.getFriendList(user));

    BorderPane remove = new BorderPane();
    remove.setStyle("-fx-background-color: linear-gradient(to bottom , CYAN, ROYALBLUE)");
    Scene removeFriendsScene = new Scene(remove, 450, 450);
    final Label label_remove = new Label("Remove Friends");

    remove.setAlignment(label, Pos.TOP_CENTER);
    label_remove.setFont(Font.font("Courier", FontWeight.BOLD, 20));

    tableMainRemove.setEditable(true);

    tableMainRemove.setItems(dataMainRemove);
    tableMainRemove.getColumns().addAll(userName);
    goToRemove(stage, tableMainRemove, mainUser);

    final VBox vList_2 = new VBox();
    vList_2.setSpacing(5);
    vList_2.setPadding(new Insets(10, 0, 0, 10));
    vList_2.getChildren().addAll(tableMainRemove);
    tableMainRemove.setStyle("-fx-background-color: linear-gradient(to bottom , CYAN, ROYALBLUE)");

    Button backButtonOtherTwo = new Button("BACK");
    backButtonOtherTwo.setStyle("-fx-background-color: red");
    backButtonOtherTwo.setFont(Font.font("Arial", FontWeight.BOLD, 12));

    remove.setTop(label_remove);
    remove.setCenter(vList_2);
    remove.setLeft(new Label("ADS")); // LOL
    remove.setRight(new Label("ADS"));
    remove.setBottom(backButtonOtherTwo);



    home.setLeft(vbox);
    home.setBottom(backButton);

    Scene userScene = new Scene(home, 500, 500);
    stage.setScene(userScene);


    EventHandler<ActionEvent> viewFriendsEvent = new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e) {
        previous.add(stage.getScene());

        stage.setScene(scene5);
      }
    };
    viewFriends.setOnAction(viewFriendsEvent);

    EventHandler<ActionEvent> backBut = new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e) {
        stage.setScene(previous.get(previous.size() - 1));
        previous.remove(previous.size() - 1);
      }
    };
    backButton.setOnAction(backBut);

    EventHandler<ActionEvent> backButt = new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e) {
        stage.setScene(previous.get(previous.size() - 1));
        previous.remove(previous.size() - 1);
      }
    };
    backButtonOther.setOnAction(backButt);

    EventHandler<ActionEvent> backButt_2 = new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e) {
        stage.setScene(previous.get(previous.size() - 1));
        previous.remove(previous.size() - 1);
      }
    };
    backButtonOtherTwo.setOnAction(backButt);

    EventHandler<ActionEvent> backButton_Two = new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e) {
        stage.setScene(previous.get(previous.size() - 1));
        previous.remove(previous.size() - 1);
      }
    };
    backButton_two.setOnAction(backButton_Two);

    EventHandler<ActionEvent> addFriendEvent = new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e) {
        previous.add(stage.getScene());
        stage.setScene(addFriendsScene);
      }
    };
    addFriend.setOnAction(addFriendEvent);

    EventHandler<ActionEvent> removeFriendEvent = new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e) {
        previous.add(stage.getScene());
        stage.setScene(removeFriendsScene);
      }
    };
    removeFr.setOnAction(removeFriendEvent);
  }

  /**
   * Adds the remove button to the table Adapted portions from https://riptutorial.com/javafx
   * 
   * @param stage
   * @param table
   * @param mainUser
   */
  private void goToRemove(Stage stage, TableView<Person> table, Person mainUser) {
    Scene scene = stage.getScene();
    TableColumn<Person, Void> goToPageCol = new TableColumn("Remove"); // This is the column that
                                                                       // holds the button to go
                                                                       // to a user page
    Callback<TableColumn<Person, Void>, TableCell<Person, Void>> cell =
        new Callback<TableColumn<Person, Void>, TableCell<Person, Void>>() {
          @Override
          public TableCell<Person, Void> call(final TableColumn<Person, Void> param) {
            final TableCell<Person, Void> cell = new TableCell<Person, Void>() {

              private final Button removeFriendPage = new Button("Remove");

              {
                removeFriendPage.setOnAction((ActionEvent event) -> {
                  Person username = getTableView().getItems().get(getIndex());
                  network.removeFriend(username.getUserName(), mainUser.getUserName());
                  loadUser(mainUser.getUserName(), stage, scene);
                });
              }

              @Override
              public void updateItem(Void item, boolean emptyRow) {
                super.updateItem(item, emptyRow);
                if (emptyRow) {
                  setGraphic(null);
                } else {
                  setGraphic(removeFriendPage);
                }
              }
            };
            return cell;
          }
        };
    goToPageCol.setCellFactory(cell);

    table.getColumns().add(goToPageCol);
  }

  /**
   * Loads a file into the network
   * 
   * @param fileName name of file to be loaded
   */
  private void loadFile(String fileName) {
    try {
      FileReader file = new FileReader(fileName);
      Scanner sc = new Scanner(file);
      String[] data = sc.nextLine().trim().split(" ");
      while (sc.hasNextLine()) {


        System.out.println(data.length);
        // check if adding of some sort will occur
        if (data[0].equals("a")) {
          if (data.length == 2) {
            network.createNewAccount(data[1]);
          } else if (data.length == 3) {// need to create a friendship (edge) between two users
            if (!network.string().contains(data[1])) {
              network.createNewAccount(data[1]);
            }
            if (!network.string().contains(data[2])) {
              network.createNewAccount(data[2]);
            }
            network.addFriend(data[1], data[2]);
          }
        } else if (data[0].equals("r")) {// will remove either friendship or user
          if (data.length == 2) {
            network.removeUser(data[1]);
          } else if (data.length == 3) {// need to remove a friendship (edge) between two users
            network.removeFriend(data[1], data[2]);
          }
        } else if (data[0].equals("r")) {
          if (data.length == 2)
            network.removeUser(data[1]);
        } else if (data.length == 3) {
          network.removeFriend(data[1], data[2]);
        } else if (data[0].equals("s")) {
          // TODO: Load the Screen of the User.
          centralUser = data[1];
        }
        data = sc.nextLine().trim().split(" ");
      }
    } catch (DuplicateNameException e1) {

      e1.printStackTrace();
    } catch (Exception e1) {

      e1.printStackTrace();
    }
  }
}

