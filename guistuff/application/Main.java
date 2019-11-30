package application;

import java.util.List;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Main extends Application {
  private List<String> args;

  @Override
  public void start(Stage stage) {
    Text username = new Text("Username"); // Create username and password text
    Text password = new Text("Password");

    TextField user = new TextField(); // Field to type username and password into
    TextField pass = new TextField();

    Button submit = new Button("Submit");
    Button forgot = new Button("Forgot Password");

    GridPane gridPane = new GridPane();

    gridPane.setMinSize(250, 150); // Make grid right size

    // Set the gradient to the login page
    gridPane.setStyle("-fx-background-color: linear-gradient(to bottom , CYAN, ROYALBLUE)");

    // Setting the vertical and horizontal gaps between the columns
    gridPane.setVgap(5);
    gridPane.setHgap(5);

    // Setting the alignment
    gridPane.setAlignment(Pos.CENTER);

    // Arranging all the nodes in the grid
    gridPane.add(username, 0, 0);
    gridPane.add(user, 1, 0);
    gridPane.add(password, 0, 1);
    gridPane.add(pass, 1, 1);
    gridPane.add(submit, 0, 2);
    gridPane.add(forgot, 1, 2);

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

    layout.setCenter(gridPane);

    // Button on login page to create new account
    Button createAcc = new Button("Create"); // ADD SCENE
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
    // This should eventually become its own method

    Label label2 =
        new Label("Please Call this Number:\n 000-000-0000 \nif you have forgotten your password");
    Button back = new Button("Back");

    BorderPane layout2 = new BorderPane();
    layout2.setCenter(label2);
    layout2.setBottom(back);
    layout2.setStyle("-fx-background-color: linear-gradient(to top , CYAN, ROYALBLUE)");
    Scene scene2 = new Scene(layout2, 200, 100);


    // Scene 3
    // This scene will represent a users main page
    // it will likely hold a lot more information
    // ex Friend count, images, posting ability etc.
    // and will require its own method in the future to load user information

    BorderPane home = new BorderPane();
    Label name = new Label("Harrison"); // Take user name
    name.setFont(Font.font("Arial", FontWeight.BOLD, 24));
    home.setAlignment(name, Pos.TOP_CENTER);
    home.setStyle("-fx-background-color: white");
    home.setTop(name);

    ImageView images = new ImageView();
    Image picer = new Image("me.png");
    images.setImage(picer);
    home.setCenter(images);

    VBox vbox = new VBox();
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
    vbox.getChildren().add(friends); // Possibly add a small friends scene

    Button addFriend = new Button("Add Friend"); // Add a scene with a search function
    addFriend.setStyle("-fx-background-color: white");
    addFriend.setFont(Font.font("Arial", FontWeight.BOLD, 12));
    VBox.setMargin(addFriend, new Insets(0, 0, 0, 8));
    vbox.getChildren().add(addFriend); // add new scene

    Button removeFr = new Button("Remove Friend"); // Go to scene or combo box with list of friends
                                                   // and delete from it
    removeFr.setStyle("-fx-background-color: white");
    removeFr.setFont(Font.font("Arial", FontWeight.BOLD, 12));
    VBox.setMargin(removeFr, new Insets(0, 0, 0, 8));
    vbox.getChildren().add(removeFr); // add new scene


    Text title2 = new Text("Recomended"); // Show a random list of people from the list that are not
                                          // already friends with the user possibly show relations
                                          // to users friends Will probably take out or drastically change this
    title2.setFont(Font.font("Arial", FontWeight.BOLD, 14));
    title2.setFill(Color.WHITE);
    VBox.setMargin(title2, new Insets(10, 0, 0, 0));
    vbox.getChildren().add(title2);



    String f[] = {"f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f",
        "f", "f", "f"};

    // Create a combo box
    ComboBox combo = new ComboBox(FXCollections.observableArrayList(f));

    // Label to display the selected menuitem
    Label basic = new Label("No Friend Selected");
    basic.setFont(Font.font("Arial", FontWeight.BOLD, 14));
    basic.setTextFill(Color.WHITE);

    Button buttey = new Button("booty"); // Example button to add user

    combo.getItems().add(buttey);

    // Create action event
    EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e) {
        basic.setText(combo.getValue() + ""); // Instead of print it will have button to add user
      }
    };
    combo.setOnAction(event);
    combo.setStyle("-fx-background-color: white");

    vbox.getChildren().add(combo);
    vbox.getChildren().add(basic);



    // Label firee = new Label("Add");
    // firee.setFont(Font.font("Arial", FontWeight.BOLD, 14));
    // firee.setTextFill(Color.WHITE);
    // HBox fire = new HBox();
    // fire.setMargin(firee, new Insets(0, 0, 10, 0));
    // fire.setMargin(combo, new Insets(0, 0, 0, 10));
    // fire.getChildren().add(firee);
    // fire.getChildren().add(combo);
    //
    // vbox.getChildren().add(fire);

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
    // createe.setBottom(exit);
    createe.setStyle("-fx-background-color: linear-gradient(to top , CYAN, ROYALBLUE)");

    Scene scene4 = new Scene(createe, 200, 100);


    // These are all the action events for when buttons are pressed will likely need to be changed
    // in the future

    forgot.setOnAction(e -> stage.setScene(scene2));
    back.setOnAction(e -> stage.setScene(scene));
    submit.setOnAction(e -> stage.setScene(scene3));
    exit.setOnAction(e -> stage.setScene(scene));
    createAcc.setOnAction(e -> stage.setScene(scene4));
    // addFriend.setOnAction(e -> vbox.getChildren().add(combo)):
  }

  public static void main(String args[]) {
    launch(args);
  }
}


