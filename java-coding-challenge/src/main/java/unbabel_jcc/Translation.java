package unbabel_jcc;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import unbabel_jcc.resources.Status;

public class Translation {


	private LanguagePair langPair;
	private Status status;
	private String originalText;
	private String translatedText;
	private String uid;

	private TextArea translatedTextArea;
	private Label statusLabel;


	public Translation(LanguagePair langPair, String originalText) {
		super();
		this.langPair = langPair;
		this.originalText = originalText;
		status = Status.PROCESSING;		
		translatedText = "";
		uid = "";
	}


	public void setAsRequested(String uid) {
		this.uid = uid;
		status = Status.REQUESTED;
		Platform.runLater(new Runnable() {
			public void run() {
				statusLabel.setText(status.name().toLowerCase());
			}
		});
	}


	public void setAsCompleted(String translated) {
		System.out.println(translated);
		this.translatedText = translated;
		status = Status.COMPLETED;
		//System.out.println(statusLabel.toString());
		Platform.runLater(new Runnable() {
			public void run() {
				statusLabel.setText(status.name().toLowerCase());
				translatedTextArea.setText(translatedText);
			}
		});
	}


	public LanguagePair getLangPair() {
		return langPair;
	}


	public Status getStatus() {
		return status;
	}


	public String getOriginalText() {
		return originalText;
	}


	public String getTranslatedText() {
		return translatedText;
	}


	public String getUid() {
		return uid;
	}


	public TextArea getTranslatedTextField() {
		return translatedTextArea;
	}


	public void setTranslatedTextField(TextArea translatedTextArea) {
		this.translatedTextArea = translatedTextArea;
	}


	public Label getStatusLabel() {
		return statusLabel;
	}


	public void setStatusLabel(Label statusLabel) {
		this.statusLabel = statusLabel;
	}	

}
