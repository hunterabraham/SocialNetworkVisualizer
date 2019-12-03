package application;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
 * Adapted things from Java's Using JavaFX UI Controlss
 * 
 * @author
 *
 */
public class Main extends Application {
  private TableView<Person> table = new TableView<Person>();
  // This is just an
  // example for what
  // we could use but
  // we will probably
  // change it later
  // Not sure what list to use for this so I just chose the basic one
  private final ObservableList<Person> data =
      FXCollections.observableArrayList(new Person("Joe"), new Person("Smoe"), new Person("Harry"));

  @Override
  public void start(Stage stage) {
    TableView<Person> tableMain = new TableView<Person>();
    // This will eventually just read the connections of the user
    ObservableList<Person> dataMain =
        FXCollections.observableArrayList(new Person("Joe"), new Person("Smoe"));


    // Probally jsut gonna get rid of login scene
    Text username = new Text("Username"); // Create username and password text
    TextField user = new TextField(); // Field to type username and password into
    user.setPromptText("Just click Submit"); // Will change these later

    Button logout = new Button("X");
    logout.setStyle("-fx-background-color: red");
    logout.setFont(Font.font("Arial", FontWeight.BOLD, 15));
    logout.setPadding(new Insets(3, 22, 3, 20));
    logout.setOnAction(e -> stage.close());

    Button submit = new Button("Submit");
    Button forgot = new Button("  Forgot Username   ");
    forgot.setPadding(new Insets(5, 25, 5, 25));

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
    login.add(forgot, 1, 1);

    



    // Login Scene
    Scene scene = new Scene(login, 250, 250);
    stage.setTitle("TWEETER");
    stage.setScene(scene);
    stage.show();


    // New scene
    // This scene will appear if help button is pressed
    // Could be seperated and fixed

    Label label2 =
        new Label("Please Call this Number:\n 000-000-0000 \nif you have forgotten your userName");
    Button back = new Button("Back");

    BorderPane layout2 = new BorderPane();
    layout2.setCenter(label2);
    layout2.setBottom(back);
    layout2.setStyle("-fx-background-color: linear-gradient(to top , CYAN, ROYALBLUE)");
    Scene scene2 = new Scene(layout2, 200, 100);


    // Users main page scene
    // This scene will represent a users main page
    // it will likely hold a lot more information
    // ex Friend count, images, posting ability etc.

    BorderPane home = new BorderPane();
    Label name = new Label("HarrY"); // Take username
    name.setFont(Font.font("Arial", FontWeight.BOLD, 24));
    home.setAlignment(name, Pos.TOP_CENTER);
    home.setStyle("-fx-background-color: white");
    home.setTop(name);

    ImageView images = new ImageView(); // Profile pic would exist in create new account
    Image picer = new Image("me.png");
    images.setImage(picer);
    home.setCenter(images);

    VBox vbox = new VBox(); // Side panel
    vbox.setPadding(new Insets(10));
    vbox.setSpacing(8);
    vbox.setStyle("-fx-background-color: darkblue ");

    Text title = new Text("Friends");
    title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
    title.setFill(Color.WHITE);
    vbox.getChildren().add(title);

    Button friends = new Button("View Friends"); // Load a list of users friends
    friends.setStyle("-fx-background-color: white");
    friends.setFont(Font.font("Arial", FontWeight.BOLD, 12));
    VBox.setMargin(friends, new Insets(0, 0, 0, 8));
    vbox.getChildren().add(friends); // Possibly add a small friends scene to show their pages
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
    goTo(stage, scene, tableMain);

    final VBox vList = new VBox();
    vList.setSpacing(5);
    vList.setPadding(new Insets(10, 0, 0, 10));
    vList.getChildren().addAll(tableMain);
    tableMain.setStyle("-fx-background-color: linear-gradient(to bottom , CYAN, ROYALBLUE)");

    showFriends.setTop(label);
    showFriends.setCenter(vList);
    showFriends.setLeft(new Label("ADS")); // LOL
    showFriends.setRight(new Label("ADS"));
    Button X = new Button("X");
    X.setStyle("-fx-background-color: red");
    X.setFont(Font.font("Arial", FontWeight.BOLD, 12));
    showFriends.setAlignment(X, Pos.BOTTOM_LEFT);
    showFriends.setBottom(X);

    // Add a scene with a search function This could show a list of the users in the graph and let
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

    table.setEditable(true);

    // All of this will be formatted differently in order to accommodate a graph
    TableColumn userNameOther = new TableColumn("Username");
    userNameOther.setMinWidth(150);
    userNameOther.setCellValueFactory(new PropertyValueFactory<Person, String>("userName"));

    table.setItems(data);
    table.getColumns().addAll(userNameOther);
    goToAdd(stage, scene, tableMain, table);

    final VBox vList2 = new VBox();
    vList2.setSpacing(5);
    vList2.setPadding(new Insets(10, 0, 0, 10));
    vList2.getChildren().addAll(table);
    table.setStyle("-fx-background-color: linear-gradient(to bottom , CYAN, ROYALBLUE)");

    addFriends.setTop(labelOther);
    addFriends.setCenter(vList2);

    Scene scene6 = new Scene(addFriends, 450, 450);
    addFriend.setOnAction(e -> stage.setScene(scene6));

    Button removeFr = new Button("Remove Friend"); // Go to scene with list of friends
                                                   // and delete from it
    removeFr.setStyle("-fx-background-color: white");
    removeFr.setFont(Font.font("Arial", FontWeight.BOLD, 12));
    VBox.setMargin(removeFr, new Insets(0, 0, 0, 8));
    vbox.getChildren().add(removeFr); // add new scene

    Button exit = new Button("LOGOUT"); // Returns to login screen
    exit.setStyle("-fx-background-color: red; -fx-font-weight: bold");

    home.setLeft(vbox);
    home.setRight(exit);

    Scene scene3 = new Scene(home, 500, 500);

    // Scene 4
    // Account creation not sure what fields will exist yet so for now it is closed

    Label creat =
        new Label("Sorry no more accounts can be \n created go back and hit submit \n please");

    BorderPane createe = new BorderPane();
    createe.setCenter(creat);
    createe.setStyle("-fx-background-color: linear-gradient(to top , CYAN, ROYALBLUE)");

    Scene scene4 = new Scene(createe, 200, 100);


    // These are all the action events for when buttons are pressed will likely need to be changed
    // in the future

    // forgot.setOnAction(e -> stage.setScene(scene2));
    EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e) {
        previous.add(stage.getScene());
        // previous = stage.getScene();
        stage.setScene(scene2);
      }
    };
    forgot.setOnAction(event1);

    // back.setOnAction(e -> stage.setScene(scene));
    EventHandler<ActionEvent> event3 = new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e) {
        previous.add(stage.getScene());
        // previous = stage.getScene();
        stage.setScene(scene);
      }
    };
    back.setOnAction(event3);

    // submit.setOnAction(e -> stage.setScene(scene3));
    EventHandler<ActionEvent> event4 = new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e) {
        previous.add(stage.getScene());
        // previous = stage.getScene();
        stage.setScene(scene3);
      }
    };
    submit.setOnAction(event4);

    // exit.setOnAction(e -> stage.setScene(scene));
    EventHandler<ActionEvent> event5 = new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e) {
        previous.add(stage.getScene());
        // previous = stage.getScene();
        stage.setScene(scene);
      }
    };
    exit.setOnAction(event5);

    // createAcc.setOnAction(e -> stage.setScene(scene4));
    EventHandler<ActionEvent> event6 = new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e) {
        previous.add(stage.getScene());
        // previous = stage.getScene();
        stage.setScene(scene4);
      }
    };
    createAcc.setOnAction(event6);

    // friends.setOnAction(e -> stage.setScene(scene5));
    EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e) {
        previous.add(stage.getScene());
        // previous = stage.getScene();
        stage.setScene(scene5);
      }
    };
    friends.setOnAction(event);

    // X.setOnAction(e -> stage.setScene(scene3));
    EventHandler<ActionEvent> event7 = new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e) {
        previous.add(stage.getScene());
        // previous = stage.getScene();
        stage.setScene(scene3);
      }
    };
    X.setOnAction(event7);

  }

  public static void main(String args[]) {
    launch(args);
  }

  private void goTo(Stage stage, Scene main, TableView<Person> tableMain) {
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

  private void goToAdd(Stage stage, Scene main, TableView<Person> tableMain,
      TableView<Person> table) {
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
   * Ideal this will be the recursive type method to load all user data and most of the stuff from
   * start (except login) will be moved here
   * 
   * @param user
   * @param stage
   * @param main
   */
  private void loadUser(String user, Stage stage, Scene main) {
    TableView<Person> tableMain = new TableView<Person>();
    // This will eventually just find the user in the graph and read the connections
    ObservableList<Person> dataMain = FXCollections.observableArrayList(new Person("Harry"));


    BorderPane home = new BorderPane();
    Label name = new Label(user); // Take username
    name.setFont(Font.font("Arial", FontWeight.BOLD, 24));
    home.setAlignment(name, Pos.TOP_CENTER);
    home.setStyle("-fx-background-color: white");
    home.setTop(name);

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

    Button friends = new Button("View Friends"); // Load a list of users friends
    friends.setStyle("-fx-background-color: white");
    friends.setFont(Font.font("Arial", FontWeight.BOLD, 12));
    VBox.setMargin(friends, new Insets(0, 0, 0, 8));
    vbox.getChildren().add(friends); // Possibly add a small friends scene to show their pages
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
    goTo(stage, main, tableMain);

    final VBox vList = new VBox();
    vList.setSpacing(5);
    vList.setPadding(new Insets(10, 0, 0, 10));
    vList.getChildren().addAll(tableMain);
    tableMain.setStyle("-fx-background-color: linear-gradient(to bottom , CYAN, ROYALBLUE)");

    showFriends.setTop(label);
    showFriends.setCenter(vList);
    showFriends.setLeft(new Label("ADS")); // LOL
    showFriends.setRight(new Label("ADS"));
    Button X = new Button("X");
    X.setStyle("-fx-background-color: red");
    X.setFont(Font.font("Arial", FontWeight.BOLD, 12));
    showFriends.setAlignment(X, Pos.BOTTOM_LEFT);
    showFriends.setBottom(X);

    // Add a scene with a search function This could show a list of the users in the graph and
    // let
    // the main user add a connection to them
    Button addFriend = new Button("Add Friend");
    addFriend.setStyle("-fx-background-color: white");
    addFriend.setFont(Font.font("Arial", FontWeight.BOLD, 12));
    VBox.setMargin(addFriend, new Insets(0, 0, 0, 8));
    vbox.getChildren().add(addFriend); // add new scene

    Button removeFr = new Button("Remove Friend"); // Go to scene with list of friends
    // and delete from it
    removeFr.setStyle("-fx-background-color: white");
    removeFr.setFont(Font.font("Arial", FontWeight.BOLD, 12));
    VBox.setMargin(removeFr, new Insets(0, 0, 0, 8));
    vbox.getChildren().add(removeFr); // add new scene


    Button exit = new Button("X"); // Returns to login screen
    exit.setStyle("-fx-background-color: red; -fx-font-weight: bold");

    home.setLeft(vbox);
    home.setRight(exit);

    Scene userScene = new Scene(home, 500, 500);
    stage.setScene(userScene);

    // exit.setOnAction(e -> stage.setScene(previous));
    EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e) {
        stage.setScene(previous.get(previous.size() - 1));
        previous.remove(previous.size() - 1);
      }
    };

    exit.setOnAction(event1);

    // friends.setOnAction(e -> stage.setScene(scene5));
    EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e) {
        previous.add(stage.getScene());
        // previous = stage.getScene();
        stage.setScene(scene5);
      }
    };
    friends.setOnAction(event);
  }

  // Scene previous;
  ArrayList<Scene> previous = new ArrayList<Scene>();

  public static class Person { // This is an example Person class I don't think anymore needs to be
                               // added here

    private final String userName;

    private Person(String userName) {
      this.userName = (userName);
    }

    public String getUserName() {
      return userName;
    }
  }
}

