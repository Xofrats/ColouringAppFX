import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
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

        Rectangle firstColour = new Rectangle(100, 50, 50, 50);
        firstColour.setStroke(Color.BLACK);

        ColorPicker firstPicker = new ColorPicker();
        firstPicker.setLayoutX(150);
        firstPicker.setLayoutY(160);

        Rectangle secondColour = new Rectangle(200, 50, 50, 50);
        secondColour.setStroke(Color.BLACK);

        Rectangle thirdColour = new Rectangle(300, 50, 50, 50);
        thirdColour.setStroke(Color.BLACK);

        root.getChildren().add(firstColour);
        root.getChildren().add(secondColour);
        root.getChildren().add(thirdColour);
        root.getChildren().add(firstPicker);


        // error text

        Text error = new Text();
        error.setX(50);
        error.setY(450);

        root.getChildren().add(error);

        //Adding a colour interface

        Text buttonHeader = new Text("Pick a color");
        buttonHeader.setLayoutX(150);
        buttonHeader.setLayoutY(140);



        Button addColorbtn = new Button("Add a color");
        addColorbtn.setLayoutX(150);
        addColorbtn.setLayoutY(200);


        root.getChildren().add(addColorbtn);
       root.getChildren().add(buttonHeader);


        EventHandler<MouseEvent> eventAddColor = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                int numberOfColours = startPalette.getSizeOfPalette();
                try {
                  //  startPalette.add(new Colour(Integer.parseInt(colourRed.getText()), Integer.parseInt(colourGreen.getText()), Integer.parseInt(colourBlue.getText())));
                    String hex2 = "#" + Integer.toHexString(firstPicker.getValue().hashCode());



                    int redInt = Integer.valueOf( hex2.substring( 1, 3 ), 16 );

                    int greenInt = Integer.valueOf( hex2.substring( 3, 5 ), 16 );
                    int blueInt = Integer.valueOf( hex2.substring( 5, 7 ), 16 );
                    startPalette.add(new Colour(redInt, greenInt,blueInt));


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

                    error.setText("Error: Could not add the color");
                }
            }
        };

        addColorbtn.addEventFilter(MouseEvent.MOUSE_CLICKED, eventAddColor);

        //Mixing two colours

        Text boxToMix = new Text(55, 335, "Box to mix");
       ColorPicker mixPicker = new ColorPicker();
       mixPicker.setLayoutX(250);
       mixPicker.setLayoutY(350);

        Text mixWith = new Text(250, 335, "Pick a color to mix with");

        root.getChildren().add(boxToMix);
        root.getChildren().add(mixPicker);
        root.getChildren().add(mixWith);


        //textfield for the userinput

        TextField colourToGetMixed = new TextField("enter number");
        colourToGetMixed.setLayoutX(50);
        colourToGetMixed.setLayoutY(350);




        Button mixColourbtn = new Button("mix colours");
        mixColourbtn.setLayoutX(175);
        mixColourbtn.setLayoutY(400);


        root.getChildren().add(colourToGetMixed);
        root.getChildren().add(mixColourbtn);

        //Making the button event

        EventHandler<MouseEvent> eventMixingColour = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Colour colour1 = new Colour(0, 0, 0);
                Rectangle mixStart = new Rectangle();
                try {
                    if (Integer.parseInt(colourToGetMixed.getText()) == 1) {
                        colour1 = startPalette.get(0);
                        mixStart = firstColour;
                    } else if (Integer.parseInt(colourToGetMixed.getText()) == 2) {
                        colour1 = startPalette.get(1);
                        mixStart = secondColour;
                    } else if (Integer.parseInt(colourToGetMixed.getText()) == 3) {
                        colour1 = startPalette.get(2);
                        mixStart = thirdColour;
                    }

                    error.setText("");
                } catch (Exception e) {
                    error.setText("Error: Type which box you want to mix");
                }

                try {
                    String hex2 = "#" + Integer.toHexString(mixPicker.getValue().hashCode());
                    int redInt = Integer.valueOf( hex2.substring( 1, 3 ), 16 );
                    int greenInt = Integer.valueOf( hex2.substring( 3, 5 ), 16 );
                    int blueInt = Integer.valueOf( hex2.substring( 5, 7 ), 16 );

                    Colour colour2 = new Colour(redInt, greenInt,blueInt);
                    colour1.mixWith(colour2);
                    mixStart.setFill(Color.rgb(colour1.getRed(), colour1.getGreen(), colour1.getBlue()));
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
