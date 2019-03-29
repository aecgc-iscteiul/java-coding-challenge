package unbabel_jcc;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class GuiController implements Initializable {


	@FXML
	private TextArea inputTextArea;
	@FXML
	private ComboBox<String> languageComboBox;
	@FXML
	private GridPane outputGrid;
    @FXML
    private HBox loadingView;
    @FXML
    private ScrollPane normalView;

	@FXML
	private VBox vBox;


	private int rowCounter = 0;


	private GeneralController controller;


	public void initialize(URL location, ResourceBundle resources) {
		controller = new GeneralController(this);
		controller.getLanguagesAvaliable();
		normalView.setVisible(false);
		loadingView.setVisible(true);
	}


	@FXML
	void onButtonClicked(ActionEvent event) {
		String originalText = inputTextArea.getText();
		if(!originalText.trim().isEmpty()) {
			int index = languageComboBox.getSelectionModel().getSelectedIndex();
			LanguagePair langPair = controller.getAvailableTranslations().get(index);
			TranslationRequest request = new TranslationRequest(langPair, originalText);
			addRow(request);
		}
	}


	private void addRow(TranslationRequest request) {
		int collumnCounter = 0;
		controller.requestTranslation(request);
		// Languages From
		outputGrid.add(new Label(request.getLangPair().getSource()), collumnCounter++, rowCounter);
		// Original text
		TextArea original = new TextArea(request.getOriginalText());
		original.setWrapText(true);
		original.setEditable(false);
		outputGrid.add(original, collumnCounter++, rowCounter);
		// Language To
		outputGrid.add(new Label(request.getLangPair().getTarget()), collumnCounter++, rowCounter);
		// Translated text
		TextArea translated = new TextArea(request.getTranslatedText());
		translated.setWrapText(true);
		translated.setEditable(false);
		outputGrid.add(translated, collumnCounter++, rowCounter);
		request.setTranslatedTextField(translated);
		// Status
		Label status = new Label(request.getStatusString());
		outputGrid.add(status, collumnCounter, rowCounter);
		request.setStatusLabel(status);
		rowCounter++;
	}


	public void setTranslationLanguagesAvailable(ObservableList<String> availableLanguages) {
		languageComboBox.setValue(availableLanguages.get(0));
		languageComboBox.setItems(availableLanguages);
		normalView.setVisible(true);
		loadingView.setVisible(false);
	}

}
