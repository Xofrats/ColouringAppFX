import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Group root = new Group();

        //Making the start screen

        ColourPalette startPalette = new ColourPalette(3);

        Rectangle firstColour = new Rectangle(50, 50, 50, 50);
        firstColour.setStroke(Color.BLACK);

        Rectangle secondColour = new Rectangle(150, 50, 50, 50);
        secondColour.setStroke(Color.BLACK);

        Rectangle thirdColour = new Rectangle(250, 50, 50, 50);
        thirdColour.setStroke(Color.BLACK);

        root.getChildren().add(firstColour);
        root.getChildren().add(secondColour);
        root.getChildren().add(thirdColour);


        // error text

        Text error = new Text();
        error.setX(50);
        error.setY(450);

        root.getChildren().add(error);

        //Adding a colour interface

        TextField colourRed = new TextField();
        colourRed.setLayoutX(150);
        colourRed.setLayoutY(120);

        Label forRed = new Label("Enter red value");
        forRed.setLayoutX(50);
        forRed.setLayoutY(120);

        TextField colourGreen = new TextField();
        colourGreen.setLayoutX(150);
        colourGreen.setLayoutY(150);

        Label forGreen = new Label("Enter green value");
        forGreen.setLayoutX(50);
        forGreen.setLayoutY(150);

        TextField colourBlue = new TextField();
        colourBlue.setLayoutX(150);
        colourBlue.setLayoutY(180);

        Label forBlue = new Label("Enter blue value");
        forBlue.setLayoutX(50);
        forBlue.setLayoutY(180);

        Button addColorbtn = new Button("Add a color");
        addColorbtn.setLayoutX(50);
        addColorbtn.setLayoutY(250);

        //adding to root
        root.getChildren().add(colourRed);
        root.getChildren().add(colourGreen);
        root.getChildren().add(colourBlue);
        root.getChildren().add(addColorbtn);
        root.getChildren().add(forRed);
        root.getChildren().add(forGreen);
        root.getChildren().add(forBlue);


        EventHandler<MouseEvent> eventAddColor = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                int numberOfColours = startPalette.getSizeOfPalette();
                try {
                    startPalette.add(new Colour(Integer.parseInt(colourRed.getText()), Integer.parseInt(colourGreen.getText()), Integer.parseInt(colourBlue.getText())));
                    colourRed.clear();
                    colourGreen.clear();
                    colourBlue.clear();

                    if (numberOfColours == 0) {
                        Colour one = startPalette.get(0);
                        firstColour.setFill(Color.rgb(one.getRed(), one.getGreen(), one.getBlue()));
                        numberOfColours++;
                    } else if (numberOfColours == 1) {
                        Colour one = startPalette.get(1);
                        secondColour.setFill(Color.rgb(one.getRed(), one.getGreen(), one.getBlue()));
                        numberOfColours++;
                    } else if (numberOfColours == 2) {
                        Colour one = startPalette.get(2);
                        thirdColour.setFill(Color.rgb(one.getRed(), one.getGreen(), one.getBlue()));
                        numberOfColours++;
                    }

                    error.setText("");

                } catch (Exception e) {
                    colourRed.clear();
                    colourGreen.clear();
                    colourBlue.clear();
                    error.setText("Error: Could not add the color");
                }
            }
        };

        addColorbtn.addEventFilter(MouseEvent.MOUSE_CLICKED, eventAddColor);

        //Mixing two colours

        Text boxToMix = new Text(55, 335, "Box to mix");
        Text redValue = new Text(355, 315, "Red Value");
        Text greenValue = new Text(355, 365, "Green Value");
        Text blueValue = new Text(355, 415, "Blue Value");

        root.getChildren().add(boxToMix);
        root.getChildren().add(redValue);
        root.getChildren().add(greenValue);
        root.getChildren().add(blueValue);

        //textfield for the userinput

        TextField colourToGetMixed = new TextField("box to mix");
        colourToGetMixed.setLayoutX(50);
        colourToGetMixed.setLayoutY(350);

        TextField mixedRed = new TextField("Red value");
        mixedRed.setLayoutX(200);
        mixedRed.setLayoutY(300);

        TextField mixedGreen = new TextField("Green value");
        mixedGreen.setLayoutX(200);
        mixedGreen.setLayoutY(350);

        TextField mixedBlue = new TextField("Blue value");
        mixedBlue.setLayoutX(200);
        mixedBlue.setLayoutY(400);

        Button mixColourbtn = new Button("mix colours");
        mixColourbtn.setLayoutX(50);
        mixColourbtn.setLayoutY(400);

        root.getChildren().add(mixedBlue);
        root.getChildren().add(mixedGreen);
        root.getChildren().add(mixedRed);
        root.getChildren().add(colourToGetMixed);
        root.getChildren().add(mixColourbtn);

        //Making the button event

        EventHandler<MouseEvent> eventMixingColour = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Colour colour1 = new Colour(0, 0, 0);
                try {
                    if (Integer.parseInt(colourToGetMixed.getText()) == 1) {
                        colour1 = startPalette.get(0);
                    } else if (Integer.parseInt(colourToGetMixed.getText()) == 2) {
                        colour1 = startPalette.get(1);
                    } else if (Integer.parseInt(colourToGetMixed.getText()) == 3) {
                        colour1 = startPalette.get(2);
                    }

                    error.setText("");
                } catch (Exception e) {
                    error.setText("Error: Type which box you want to mix");
                }

                try {
                    Colour colour2 = new Colour(Integer.parseInt(mixedRed.getText()), Integer.parseInt(mixedGreen.getText()), Integer.parseInt(mixedBlue.getText()));
                    colour1.mixWith(colour2);
                    firstColour.setFill(Color.rgb(colour1.getRed(), colour1.getGreen(), colour1.getBlue()));
                    error.setText("");
                } catch (Exception e) {
                    error.setText("Error: Colour 2 contains something off");
                }

            }
        };

        mixColourbtn.addEventFilter(MouseEvent.MOUSE_CLICKED, eventMixingColour);



        //setting up the scene
Scene scene = new Scene(root, 500, 600);
scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setTitle("Colouring App");
        primaryStage.setScene(scene);

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
