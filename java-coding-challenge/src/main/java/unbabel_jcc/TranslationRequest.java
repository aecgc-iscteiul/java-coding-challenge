package unbabel_jcc;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import unbabel_jcc.resources.Status;

/**
 * @author acaldeira
 *
 */
public class TranslationRequest {


	/**
	 * Object representing pair of languages to translate from (source) and to (target)
	 */
	private LanguagePair langPair;
	/**
	 * Enum representing status of request [PROCESSING, REQUESTED, COMPLETED, ERROR]
	 */
	private Status status;
	/**
	 * Original text to be translated
	 */
	private String originalText;
	/**
	 * Translated text, empty until request is completed
	 */
	private String translatedText;
	/**
	 * String with the api given uid of the request
	 */
	private String uid;
	/**
	 * TextArea with translated text to be updated on request completed
	 */
	private TextArea translatedTextArea;
	/**
	 * Label with status to be updated on request status change
	 */
	private Label statusLabel;


	/**
	 * Class construtor
	 * @param langPair Object representing pair of languages to translate from (source) and to (target)
	 * @param originalText Original text to be translated
	 */
	public TranslationRequest(LanguagePair langPair, String originalText) {
		super();
		this.langPair = langPair;
		this.originalText = originalText;
		status = Status.PROCESSING;		
		translatedText = "";
		uid = "";
		statusLabel = null;
		translatedTextArea = null;
	}


	/**
	 * Changes request internal status to requested (initially processing) and initialises uid attribute
	 * @param uid String with the api given uid of the request
	 */
	public void setAsRequested(String uid) {
		this.uid = uid;
		status = Status.REQUESTED;
		if(statusLabel != null) {
			Platform.runLater(new Runnable() {
				public void run() {
					statusLabel.setText(getStatusString());
				}
			});
		}
	}


	/**
	 * Changes request internal status to completed (from requested) and initialises translated attribute
	 * @param translated
	 */
	public void setAsCompleted(String translated) {
		this.translatedText = translated;
		status = Status.COMPLETED;
		if(statusLabel != null && translatedTextArea != null) {
			Platform.runLater(new Runnable() {
				public void run() {
					statusLabel.setText(getStatusString());
					translatedTextArea.setText(translatedText);
				}
			});
		}
	}
	
	
	/**
	 * Changes request internal status to error (from processing or requested) after error occurred in request
	 */
	public void setAsError() {
		status = Status.ERROR;
		if(statusLabel != null) {
			Platform.runLater(new Runnable() {
				public void run() {
					statusLabel.setText(getStatusString());
				}
			});
		}
	}


	/**
	 * Manipulated status string to be all lower case letters with initial in upper case
	 * @return
	 */
	public String getStatusString() {
		String statusString = status.toString().substring(0, 1).toUpperCase() + 
				status.toString().substring(1).toLowerCase();
		return statusString;
	}
	
	/**
	 * Class getter for LanguagePair attribute
	 * @return Object representing pair of languages to translate from (source) and to (target)
	 */
	public LanguagePair getLangPair() {
		return langPair;
	}


	/**
	 * Class getter for Status attribute
	 * @return Enum representing status of request [PROCESSING, REQUESTED, COMPLETED, ERROR]
	 */
	public Status getStatus() {
		return status;
	}


	/**
	 * Class getter for Original text attribute
	 * @return Original text to be translated
	 */
	public String getOriginalText() {
		return originalText;
	}


	/**
	 * Class getter for Translated text attribute
	 * @return Translated text, empty until request is completed
	 */
	public String getTranslatedText() {
		return translatedText;
	}


	/**
	 * Class getter for Uid attribute
	 * @return String with the api given uid of the request
	 */
	public String getUid() {
		return uid;
	}


	/**
	 * Class getter for Translated TextField attribute
	 * @return TextArea with translated text to be updated on request completed
	 */
	public TextArea getTranslatedTextField() {
		return translatedTextArea;
	}


	/**
	 * Class setter for Translated TextField attribute
	 * @param translatedTextArea TextArea with translated text to be updated on request completed
	 */
	public void setTranslatedTextField(TextArea translatedTextArea) {
		this.translatedTextArea = translatedTextArea;
	}


	/**
	 * Class getter for Status Label attribute
	 * @return Label with status to be updated on request status change
	 */
	public Label getStatusLabel() {
		return statusLabel;
	}


	/**
	 * Class setter for Status Label attribute
	 * @param statusLabel Label with status to be updated on request status change
	 */
	public void setStatusLabel(Label statusLabel) {
		this.statusLabel = statusLabel;
	}	

}
