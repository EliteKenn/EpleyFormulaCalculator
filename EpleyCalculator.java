package GUIReviewProject;
import javafx.application.Application;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyCode;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
public class EpleyCalculator extends Application{

	private static TextField weightLifted = new TextField();
	private static TextField repsPerformed = new TextField();
	private static TextField newMax = new TextField();
	private static Button getNewMax = new Button("Calculate");
	private static Button clearButton = new Button("Clear");
	
	public void start(Stage window) {
		//Create UI
		GridPane gPane = new GridPane();
		gPane.setVgap(5);
		gPane.setHgap(5);
		gPane.add(new Label("Weight lifted:" ),0, 0);
		gPane.add(weightLifted, 1, 0);
		gPane.add(new Label("Reps performed:"), 0, 1);
		gPane.add(repsPerformed, 1, 1);
		gPane.add(new Label("Rep Max: "), 0, 2);
		gPane.add(newMax, 1, 2);
		gPane.add(getNewMax, 1, 3);
		gPane.add(clearButton, 1,3);
		
		gPane.setStyle("-fx-border-color:black;");
		gPane.setAlignment(Pos.CENTER);
		weightLifted.setAlignment(Pos.BOTTOM_RIGHT);
		repsPerformed.setAlignment(Pos.BOTTOM_RIGHT);
		newMax.setAlignment(Pos.BOTTOM_RIGHT);
		newMax.setEditable(false);
		gPane.setHalignment(getNewMax, HPos.RIGHT);
		weightLifted.setPromptText("Enter weight lifted...");
		repsPerformed.setPromptText("Enter reps performed...");
		
	
		getNewMax.setOnAction(e -> getWeightMax());
		repsPerformed.setOnKeyPressed(e -> {
			getNewMax.setDefaultButton(true);
			if(e.getCode() == KeyCode.ENTER) {
				weightLifted.requestFocus();
			}
			
		});
		weightLifted.setOnKeyPressed(e ->{
			if(e.getCode() == KeyCode.ENTER || e.getCode() == KeyCode.TAB) {
				repsPerformed.requestFocus();
			}
		});
		clearButton.setOnAction(e -> {
			weightLifted.clear();
			repsPerformed.clear();
			newMax.clear();
			});
		
		/*If you want to prevent the user to input characters that fails the validation logic, 
		 * then rather than listening to the textProperty˙, e.i.(textProperty().addListener()
		 * you can use a TextFormatter (this TextField only accept integers)
		 */
		weightLifted.setTextFormatter(new TextFormatter<>(change ->
        (change.getControlNewText().matches("([0-9][1-9]*)?")) ? change : null));
		repsPerformed.setTextFormatter(new TextFormatter<>(change ->
        (change.getControlNewText().matches("([1-9][0-9]*)?")) ? change : null));
		weightLifted.setTextFormatter(new TextFormatter<>(change ->
        (change.getControlNewText().matches("\\d*|\\d+\\.\\d*")) ? change : null));
		//Use a TextFormatter with a filter that blocks inputs resulting in invalid text:
		
		
		Scene scene = new Scene(gPane, 400, 400);
		window.setTitle("Epley Formula Calculator");
		window.setScene(scene);
		window.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public static void getWeightMax() {
		//Get values from textfield
		try {
		double weight = Double.parseDouble(weightLifted.getText());
		int reps = Integer.parseInt(repsPerformed.getText());
		
		//Create an Epley object
		Epley epley = new Epley(weight, reps);
		
		//Display new max
		
		newMax.setText(String.format("%.2f", epley.getWeightMax()));
		}catch(NumberFormatException e) {
			e.getMessage();
		}
	}//END OF getWeightMax() METHOD
	
	
}
