package unbabel_jcc;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.TextFlow;


public class GuiController implements Initializable {

	@FXML
    private AnchorPane anchorPane;
    @FXML
    private ScrollPane scrollPane;
	@FXML
    private Label inputLabel;
    @FXML
    private TextArea inputTextArea;
    @FXML
    private Label outputCBLabel;
    @FXML
    private ComboBox<String> outputComboBox;
    @FXML
    private Label inputCBLabel;
    @FXML
    private ComboBox<String> inputComboBox;
    @FXML
    private Button submitButton;
    @FXML
    private Label outputLabel;
    @FXML
    private Label gridTitleBar;
    @FXML
    private GridPane outputGrid;
    
    private int rowCounter = 0;
 
	
	private ApiController api;
	private ObservableList<String> availableLanguages;
	

	@FXML
	void onButtonClicked(ActionEvent event) {
		String input = inputTextArea.getText();
		if(!input.trim().isEmpty()) {
			Translation translation = new Translation(
					inputComboBox.getSelectionModel().getSelectedItem(), 
					outputComboBox.getSelectionModel().getSelectedItem(), 
					input);
			String output = api.translateInput(input);
			translation.finishTranslation(output);
			int collumnCounter = 0;
			outputGrid.add(new Label(translation.getFrom()), collumnCounter++, rowCounter);
			outputGrid.add(new Label(translation.getTo()), collumnCounter++, rowCounter);
			TextArea original = new TextArea(translation.getOriginal());
			original.setWrapText(true);
			original.setEditable(false);
		
			outputGrid.add(original, collumnCounter++, rowCounter);
			outputGrid.add(new Label(translation.getStatus()), collumnCounter++, rowCounter);
			TextArea translated = new TextArea(translation.getTranslated());
			translated.setWrapText(true);
			translated.setEditable(false);
			outputGrid.add(translated, collumnCounter, rowCounter);
			
			rowCounter++;
		}
	}


	public void initialize(URL location, ResourceBundle resources) {
		api = new ApiController();
		availableLanguages = api.getLanguagesAvaliable();
		inputComboBox.setValue(availableLanguages.get(0));
		inputComboBox.setItems(availableLanguages);
		outputComboBox.setValue(availableLanguages.get(0));
		outputComboBox.setItems(availableLanguages);
		
		//Nada disto funciona
		outputGrid.setAlignment(Pos.CENTER);
		RowConstraints rc = new RowConstraints();
		rc.setVgrow(Priority.ALWAYS);
		outputGrid.getRowConstraints().add(rc);
		
		ColumnConstraints cc = new ColumnConstraints();
		cc.setHalignment(HPos.CENTER);
		cc.fillWidthProperty();
		outputGrid.getColumnConstraints().add(cc);
		outputGrid.setSnapToPixel(false);
		
		anchorPane.setPrefSize(1920, 1080);
		scrollPane.setPrefViewportWidth(1000);
		scrollPane.setPrefViewportHeight(1000);
		
	}


}
