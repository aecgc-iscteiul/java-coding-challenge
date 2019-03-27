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
	private Label languageLabel;

	@FXML
	private ComboBox<String> languageComboBox;

	@FXML
	private Button submitButton;

	@FXML
	private Label outputLabel;

	@FXML
	private Label gridTitleBar;

	@FXML
	private GridPane outputGrid;


	private int rowCounter = 0;


	private GeneralController controller;
	private ObservableList<String> availableLanguages;


	@FXML
	void onButtonClicked(ActionEvent event) {
		String originalText = inputTextArea.getText();
		if(!originalText.trim().isEmpty()) {
			int index = languageComboBox.getSelectionModel().getSelectedIndex();
			LanguagePair langPair = controller.getAvailableTranslations().get(index);
			Translation request = new Translation(langPair, originalText);
			addRow(request);
		}
	}
	
	
	private void addRow(Translation request) {
		int collumnCounter = 0;
		controller.requestTranslation(request);
		// Languages
		outputGrid.add(new Label(request.getLangPair().getSource()), collumnCounter++, rowCounter);
		outputGrid.add(new Label(request.getLangPair().getTarget()), collumnCounter++, rowCounter);
		// Original text
		TextArea original = new TextArea(request.getOriginalText());
		original.setWrapText(true);
		original.setEditable(false);
		outputGrid.add(original, collumnCounter++, rowCounter);
		// Status
		Label status = new Label(request.getStatus().name().toLowerCase());
		outputGrid.add(status, collumnCounter++, rowCounter);
		request.setStatusLabel(status);
		// Translated text
		TextArea translated = new TextArea(request.getTranslatedText());
		translated.setWrapText(true);
		translated.setEditable(false);
		outputGrid.add(translated, collumnCounter, rowCounter);
		request.setTranslatedTextField(translated);
		rowCounter++;
	}


	public void initialize(URL location, ResourceBundle resources) {
		controller = new GeneralController();
		availableLanguages = controller.getLanguagesAvaliable();
		languageComboBox.setValue(availableLanguages.get(0));
		languageComboBox.setItems(availableLanguages);
	}


}
