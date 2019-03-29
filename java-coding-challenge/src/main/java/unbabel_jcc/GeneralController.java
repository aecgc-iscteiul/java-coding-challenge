package unbabel_jcc;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GeneralController {


	private JSONParser parser;
	private ApiController api;
	private GuiController gui;
	private List<TranslationRequest> requests;
	private List<LanguagePair> availableTranslations;

	public GeneralController(GuiController gui) {
		this.gui = gui;
		parser = new JSONParser();
		api = new ApiController();
		requests = new ArrayList<TranslationRequest>();
		availableTranslations = new ArrayList<LanguagePair>();
		RequestChecker requestChecker = new RequestChecker(this);
		requestChecker.start();
	}

	public JSONParser getParser() {
		return parser;
	}

	public ApiController getApi() {
		return api;
	}


	public List<TranslationRequest> getUids() {
		return requests;
	}

	public List<LanguagePair> getAvailableTranslations() {
		return availableTranslations;
	}


	public void getLanguagesAvaliable() {
		Thread t = new Thread() {
			public void run() {
				final ObservableList<String> languages = FXCollections.observableArrayList();
				String jsonRequest = api.requestLanguages();
				if(jsonRequest != null) {
					availableTranslations = parser.parseLanguageRequest(jsonRequest);
					for(LanguagePair lp: availableTranslations) {
						languages.add(lp.getLanguagePairAsString());
					}
				}
				Platform.runLater(new Runnable() {
					public void run() {
						gui.setTranslationLanguagesAvailable(languages);
					}
				});
			}
		};
		t.start();
	}


	public void requestTranslation(final TranslationRequest request) {
		Thread t = new Thread() {
			public void run() {
				requests.add(request);
				String jsonRequest = api.requestTranslation(request);
				if(jsonRequest != null) {
					String uid = parser.parseTranslationRequest(jsonRequest);
					if(!uid.isEmpty()) {
						request.setAsRequested(uid);
						return;
					}
				}
				request.setAsError();
			}
		};
		t.start();
	}

}
