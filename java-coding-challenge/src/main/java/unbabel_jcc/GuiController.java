package unbabel_jcc;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;


public class GuiController implements Initializable {

	@FXML
	private Label inputLabel;
	@FXML
	private TextArea inputTextArea;
	@FXML
	private Label langLabel;
	@FXML
	private ComboBox<String> langComboBox;
	@FXML
	private Button submitButton;
    @FXML
    private Label outputLabel;
    @FXML
    private TextArea outputTextArea;
	
	private ApiController api;
	private ObservableList<String> availableLanguages;
	

	@FXML
	void onButtonClicked(ActionEvent event) {
		String input = inputTextArea.getText();
		String output = api.translateInput(input);
		outputTextArea.setText(output);
	}


	public void initialize(URL location, ResourceBundle resources) {
		api = new ApiController();
		availableLanguages = api.getLanguagesAvaliable();
		langComboBox.setValue(availableLanguages.get(0));
		langComboBox.setItems(availableLanguages);
		outputTextArea.setEditable(false);
	}


}
