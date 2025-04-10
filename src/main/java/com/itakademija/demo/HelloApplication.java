package com.itakademija.demo;

import com.itakademija.demo.dao.person.Gender;
import com.itakademija.demo.dao.person.Person;
import com.itakademija.demo.dao.person.PersonDao;
import com.itakademija.demo.gui.PersonAdapterDao;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;

/**
 * Imamo Stage stage i na tom stageu mi formiramo Scene scenu.<p></p>
 * Scene će sadržavati nekako naš glavni kontejner sa svim user interace kontrola.
 */
public class HelloApplication extends Application {


    private final TextField nameTextField = new TextField();
    private final TextField surnameTextField = new TextField();
    private final DatePicker bithdayDatePicker = new DatePicker();
    private final RadioButton maleRadioButton = new RadioButton("Male");
    private final RadioButton femaleRadioButton = new RadioButton("Female");
    private final Button addPersonButton = new Button("Add Person");

    private final PersonAdapterDao personDao = new PersonAdapterDao();
    private ObservableList<Person> personObservableList;
    private final TableView<Person> personTableView = new TableView<>();

    @Override
    public void start(Stage stage) {
        stage.setTitle("Sport HR: Persons");


        //povezuje moj TableView<Person> sa podacima
        this.personObservableList = personDao.findAll();
        this.personTableView.setItems(this.personObservableList);
        //ovaj ispod dio će povezati moju tabelu s kolonama koje želim prikazati
        TableColumn<Person, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Person, String> nameColumn = new TableColumn<>("First Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Person, String> surnameColumn = new TableColumn<>("Last Name");
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        TableColumn<Person, LocalDate> birthdayColumn = new TableColumn<>("Birthday");
        birthdayColumn.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        TableColumn<Person, Gender> genderColumn = new TableColumn<>("Gender");
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        personTableView.getColumns().addAll(idColumn, nameColumn, surnameColumn, birthdayColumn, genderColumn);

        //FORMU
        HBox formHBox = new HBox(10);
        nameTextField.setPromptText("Enter name...");
        surnameTextField.setPromptText("Enter surname...");
        ToggleGroup group = new ToggleGroup();
        maleRadioButton.setToggleGroup(group);
        femaleRadioButton.setToggleGroup(group);
        addPersonButton.setOnAction(this::onAddPersonButtonClick);
        ObservableList<Node> formHBoxChildren = formHBox.getChildren();
        formHBoxChildren.addAll(
                nameTextField,
                surnameTextField,
                bithdayDatePicker,
                maleRadioButton,
                femaleRadioButton,
                addPersonButton);


        VBox vBox = new VBox(10);
        vBox.setPadding(new Insets(10, 10, 10, 10));
        vBox.getChildren().addAll(formHBox, personTableView);
        Scene scene = new Scene(vBox);
        stage.setMinWidth(800);
        stage.setScene(scene);
        stage.show();
    }

    private void onAddPersonButtonClick(ActionEvent event) {
        Person person = new Person();
        person.setName(nameTextField.getText());
        person.setSurname(surnameTextField.getText());
        person.setBirthday(bithdayDatePicker.getValue());
        person.setGender(maleRadioButton.isSelected() ? Gender.MALE : Gender.FEMALE);
        personDao.save(person);
        personObservableList.add(person);
        clearInputs();
    }

    private void clearInputs(){
        nameTextField.clear();
        surnameTextField.clear();
        bithdayDatePicker.setValue(LocalDate.now());
        maleRadioButton.setSelected(true);
    }


    public static void main(String[] args) {
        launch();
    }
}

/***************/


//@Override
//public void start(Stage stage) {
//    stage.setTitle("JavaFX");
//
//    HBox topMenu = new HBox(20);
//    Button hamburgerButton = new Button("Hamburger");
//    hamburgerButton.getStyleClass().add("button-icon");
//
//    Button newProjectButton = new Button("New Project");
//    Button viewButton = new Button("View");
//    topMenu.getChildren().addAll(hamburgerButton, newProjectButton, viewButton);
//
//    VBox leftMenu = new VBox(10);
//    Button fileButton = new Button("File");
//    Button gitButton = new Button("Git");
//    Button buildButton = new Button("Build");
//    leftMenu.getChildren().addAll(fileButton, gitButton, buildButton);
//
//    BorderPane borderPane = new BorderPane();
//    borderPane.setTop(topMenu);
//    borderPane.setLeft(leftMenu);
//    borderPane.setPadding(new Insets(10, 10, 10, 10));
//
//    Scene scene = new Scene(borderPane, 800, 600);
//    URL resource = HelloApplication.class.getResource("/styles/Intellij.css");
//    if (resource != null)
//        scene.getStylesheets().add(resource.toExternalForm());
//
//    stage.setScene(scene);
//    stage.show();
//
//}
//
//
//public static void main(String[] args) {
//    launch();
//}

/*************/

//private final Label label = new Label("");
//
//@Override
//public void start(Stage stage) {
//    stage.setTitle("Hello World");
//    Button button = new Button("Choose Option");
//    button.setOnAction(this::onOptionChoose);
//
//    VBox vbox = new VBox(20);
//    vbox.getChildren().addAll(button, label);
//    vbox.setPadding(new Insets(10, 10, 10, 10));
//
//    Scene scene = new Scene(vbox, 300, 250);
//    stage.setScene(scene);
//    stage.setOnCloseRequest(e -> exit(stage, e));
//    stage.show();
//}
//
//private void exit(Stage stage, WindowEvent e) {
//    e.consume();//pusti nas da dalje mi preuzmemo kontrolu
//    closeProgram(stage);
//}
//
//private void closeProgram(Stage stage) {
//    ConfirmationDialog confirmationDialog = new ConfirmationDialog();
//    boolean answer = confirmationDialog.showConfirmationDialog("Close Program", "Are you sure you want to exit?");
//    if (answer) {
//        stage.close();
//    }
//}
//
//private void onOptionChoose(ActionEvent e) {
//    ConfirmationDialog confirmationDialog = new ConfirmationDialog();
//    boolean answer = confirmationDialog.showConfirmationDialog("Confirm", "Are you sure you want to continue with programming?");
//    label.setText(answer ? "Yes, I am sure. I want to become programmer" : "No, I am not so sure.");
//}
//
//
//public static void main(String[] args) {
//    launch();
//}

/**************/
//@Override
//public void start(Stage stage) {
//    stage.setTitle("Hello World");
//    Button button = new Button("Open Alert");
//    button.setOnAction(event -> AlertBox.display("Upozorenje", "Popili ste previše kafe.."));
//
//    StackPane stackPane = new StackPane();
//    stackPane.getChildren().add(button);
//
//    Scene scene = new Scene(stackPane, 300, 250);
//    stage.setScene(scene);
//    stage.show();
//}
/***************/
//private Stage stage;
//private Scene scene1;
//private Scene scene2;
//
//@Override
//public void start(Stage stage) {
//    this.stage = stage;
//    /**
//     * Kreirati Scene 1
//     */
//    Label label1 = new Label("Welcome to the first scene: ");
//    Button button1 = new Button("Go to Scene 2");
//    button1.setOnAction(e -> stage.setScene(scene2));
//    VBox vbox1 = new VBox(20);
//    ObservableList<Node> scene1VboxControls = vbox1.getChildren();
//    scene1VboxControls.addAll(label1, button1);
//    scene1 = new Scene(vbox1, 200, 200);
//
//    /**
//     * Kreirati Scene 2
//     */
//    Label label2 = new Label("Welcome to the second scene: ");
//    Button button2 = new Button("Back to Scene 1");
//    button2.setOnAction(e -> stage.setScene(scene1));
//    StackPane stackPane2 = new StackPane();
//    ObservableList<Node> observableList2 = stackPane2.getChildren();
//    observableList2.addAll(label2, button2);
//    scene2 = new Scene(stackPane2, 600, 300);
//
//    stage.setScene(scene1);
//    stage.setTitle("Switch Between Scenes");
//    stage.show();
//
//}
//
//
//public static void main(String[] args) {
//    launch();
//}

/**********/

//private final Button button = new Button("Click Me");
//
//        stage.setTitle("Naslov Prozora");
////Anonymous inner type
////Stil 1.7 Lambda nije postojala
////        EventHandler<ActionEvent> eventHandler = new EventHandler<>() {
////            @Override
////            public void handle(ActionEvent event) {
////                System.out.println("Neko na me klikno...");
////            }
////        };
////Stil 1.8 + Lambda već postoji
//EventHandler<ActionEvent> eventHandler = e -> System.out.println("Click Me...from LAMBDA");
//        button.setOnAction(eventHandler);
//// stackPane ; stackPaneChildren  -> dvije povezane varijable
//// observer: stackPane      observable: stackPaneChildren
//StackPane stackPane = new StackPane();
//ObservableList<Node> stackPaneChildren = stackPane.getChildren();
//// kad se desi izmjena na stackPaneChildren -> stackPane kao njen posmatrač će znati da treba
//// ažurirati GUI
//        stackPaneChildren.add(button);
//
//Scene scene = new Scene(stackPane, 300, 250);
//        stage.setScene(scene);
//        stage.show();

//private final Text loginButtonText = new Text();
//Blok koda start metode - JavaFX termin 7
//        stage.setTitle("Login Page");
//GridPane gridPane = new GridPane();
//        gridPane.setAlignment(Pos.CENTER);
//        gridPane.setHgap(10);
//        gridPane.setVgap(10);
//        gridPane.setPadding(new Insets(25, 25, 25, 25));
//
/***************************************/

//private final Text loginButtonText = new Text();
//
//@Override
//public void start(Stage stage) throws Exception {
//    //Blok koda start metode - JavaFX termin 7
//    stage.setTitle("Login Page");
//    GridPane gridPane = new GridPane();
//    gridPane.setAlignment(Pos.CENTER);
//    gridPane.setHgap(10);
//    gridPane.setVgap(10);
//    gridPane.setPadding(new Insets(25, 25, 25, 25));
//
//    Label welcomeLabel = new Label("Welcome");
////
////        welcomeLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
//    welcomeLabel.setId("welcome-text");
//    gridPane.add(welcomeLabel, 0, 0, 2, 1);
//
//    Label usernameLabel = new Label("Username:");
//    gridPane.add(usernameLabel, 0, 1);
//    TextField usernameTextField = new TextField();
//    gridPane.add(usernameTextField, 1, 1);
//
//    Label passwordLabel = new Label("Password:");
//    gridPane.add(passwordLabel, 0, 2);
//    PasswordField passwordField = new PasswordField();
//    gridPane.add(passwordField, 1, 2);
//
//    Button loginButton = new Button("Sign In");
//    loginButton.setOnAction(this::onLoginButtonClicked);
//    HBox hBox = new HBox(10);
//    hBox.setAlignment(Pos.BOTTOM_RIGHT);
//    hBox.getChildren().add(loginButton);
//    gridPane.add(hBox, 1, 4);
//
//    loginButtonText.setId("login-text");
//    gridPane.add(loginButtonText, 1, 6);
//
//    Scene scene = new Scene(gridPane, 300, 280);
//    URL resource = HelloApplication.class.getResource("/styles/Login.css");
//    if (resource != null)
//        scene.getStylesheets().add(resource.toExternalForm());
//    stage.setScene(scene);
//    stage.show();
//}
//
//// standalone METODA
//private void onLoginButtonClicked(ActionEvent event) {
//    loginButtonText.setFill(Color.FIREBRICK);
//    loginButtonText.setText("Sign In button pressed");
//}