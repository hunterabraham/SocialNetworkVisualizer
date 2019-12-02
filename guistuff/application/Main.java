package application;

import java.util.List;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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
  private final ObservableList<Person> data = FXCollections.observableArrayList(
      new Person("Joe", "Smoe", "Joe@example.com"), new Person("Smoe", "Joe", "Smoe@example.com"));

  @Override
  public void start(Stage stage) {
    Text username = new Text("Username"); // Create username and password text
    TextField user = new TextField(); // Field to type username and password into
    user.setPromptText("Just click Submit"); // Will change these later
    Text password = new Text("Password");
    TextField pass = new TextField();
    pass.setPromptText("Just click Submit");

    Button submit = new Button("Submit");
    Button forgot = new Button("Forgot Password");

    GridPane login = new GridPane();
    login.setMinSize(250, 150); // Make grid right size

    // Set the gradient to the login page
    login.setStyle("-fx-background-color: linear-gradient(to bottom , CYAN, ROYALBLUE)");

    // Setting up the spacing and layout of the login page
    login.setVgap(5);
    login.setHgap(5);
    login.setAlignment(Pos.CENTER);

    login.add(username, 0, 0);
    login.add(user, 1, 0);
    login.add(password, 0, 1);
    login.add(pass, 1, 1);
    login.add(submit, 0, 2);
    login.add(forgot, 1, 2);

    BorderPane layout = new BorderPane(); // Main login scene
    layout.setStyle("-fx-background-color: darkblue");

    // Add logo????
    ImageView image = new ImageView();
    Image pic = new Image("eye.png");
    image.setFitHeight(40);
    image.setFitWidth(50);
    image.setImage(pic);
    layout.setAlignment(image, Pos.TOP_CENTER);
    layout.setTop(image);

    layout.setCenter(login);

    // Button on login page to create new account
    Button createAcc = new Button("Create"); // FINISH SCENE
    createAcc.setStyle("-fx-background-color: white");
    createAcc.setFont(Font.font("Arial", FontWeight.BOLD, 10));
    createAcc.setPadding(new Insets(5, 106, 5, 110));
    layout.setAlignment(createAcc, Pos.BOTTOM_CENTER);
    layout.setBottom(createAcc);



    // Login Scene
    Scene scene = new Scene(layout, 250, 250);
    stage.setTitle("TWEETER");
    stage.setScene(scene);
    stage.show();



    // New scene
    // This scene will appear if help button is pressed
    // Could be seperated and fixed

    Label label2 =
        new Label("Please Call this Number:\n 000-000-0000 \nif you have forgotten your password");
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
    Label name = new Label("Harrison Bell"); // Take username
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
    Scene scene5 = new Scene(showFriends, 430, 450);
    final Label label = new Label("Friends");

    showFriends.setAlignment(label, Pos.TOP_CENTER);
    label.setFont(Font.font("Courier", FontWeight.BOLD, 20));

    table.setEditable(true);

    // All of this will be formatted differently in order to accommodate a graph
    TableColumn firstName = new TableColumn("First Name");
    firstName.setMinWidth(100);
    firstName.setCellValueFactory(new PropertyValueFactory<Person, String>("firstName"));

    TableColumn lastName = new TableColumn("Last Name");
    lastName.setMinWidth(100);
    lastName.setCellValueFactory(new PropertyValueFactory<Person, String>("lastName"));

    TableColumn email = new TableColumn("Email");
    email.setMinWidth(200);
    email.setCellValueFactory(new PropertyValueFactory<Person, String>("email"));

    table.setItems(data);
    table.getColumns().addAll(firstName, lastName, email);

    final VBox vList = new VBox();
    vList.setSpacing(5);
    vList.setPadding(new Insets(10, 0, 0, 10));
    vList.getChildren().addAll(table);
    table.setStyle("-fx-background-color: linear-gradient(to bottom , CYAN, ROYALBLUE)");

    showFriends.setTop(label);
    showFriends.setCenter(vList);
    showFriends.setLeft(new Label("ADS")); // LOL
    showFriends.setRight(new Label("ADS"));
    showFriends.setBottom(new Label("ADS"));

    // Add a scene with a search function This could show a list of the users in the graph and let
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


    Text title2 = new Text("Recomended"); // Show a random list of people from the list that are not
                                          // already friends with the user possibly show relations
                                          // to users friends Will probably take out or drastically
                                          // change this
    title2.setFont(Font.font("Arial", FontWeight.BOLD, 14));
    title2.setFill(Color.WHITE);
    VBox.setMargin(title2, new Insets(10, 0, 0, 0));
    vbox.getChildren().add(title2);



    String f[] = {"f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f",
        "f", "f", "f"}; // Example recommended list

    // Create a combo box
    ComboBox combo = new ComboBox(FXCollections.observableArrayList(f));

    // Label to display the selected menuitem
    Label basic = new Label("No Friend Selected");
    basic.setFont(Font.font("Arial", FontWeight.BOLD, 14));
    basic.setTextFill(Color.WHITE);

    Button buttey = new Button("booty"); // Example button to add user will probably remove

    combo.getItems().add(buttey);

    // Create action event
    EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e) {
        basic.setText(combo.getValue() + ""); // Instead of print it will have button to add user
                                              // this will basically be the add user button
      }
    };
    combo.setOnAction(event);
    combo.setStyle("-fx-background-color: white");

    vbox.getChildren().add(combo);
    vbox.getChildren().add(basic);

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

    forgot.setOnAction(e -> stage.setScene(scene2));
    back.setOnAction(e -> stage.setScene(scene));
    submit.setOnAction(e -> stage.setScene(scene3));
    exit.setOnAction(e -> stage.setScene(scene));
    createAcc.setOnAction(e -> stage.setScene(scene4));
    friends.setOnAction(e -> stage.setScene(scene5));

  }

  public static void main(String args[]) {
    launch(args);
  }

  public static class Person { // This is an example Person class I don't think anymore needs to be
                               // added hear

    private final String firstName;
    private final String lastName;
    private final String email;

    private Person(String fN, String lN, String email) {
      this.firstName = (fN);
      this.lastName = (lN);
      this.email = (email);
    }

    public String getFirstName() {
      return firstName;
    }

    public String getLastName() {
      return lastName;
    }

    public String getEmail() {
      return email;
    }
  }
}

