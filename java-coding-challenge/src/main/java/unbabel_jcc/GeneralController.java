package unbabel_jcc;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GeneralController {


	private JSONParser parser;
	private ApiController api;
	private List<Translation> requests;
	private List<LanguagePair> availableTranslations;

	public GeneralController() {
		parser = new JSONParser();
		api = new ApiController();
		requests = new ArrayList<Translation>();
		availableTranslations = new ArrayList<LanguagePair>();
		(new RequestChecker(this)).start();
	}

	public JSONParser getParser() {
		return parser;
	}

	public ApiController getApi() {
		return api;
	}


	public List<Translation> getUids() {
		return requests;
	}

	public List<LanguagePair> getAvailableTranslations() {
		return availableTranslations;
	}


	public ObservableList<String> getLanguagesAvaliable() {
		ObservableList<String> languages = FXCollections.observableArrayList();
		String jsonRequest = api.requestLanguages();
		if(jsonRequest != null) {
			availableTranslations = parser.parseLanguageRequest(jsonRequest);
			for(LanguagePair lp: availableTranslations) {
				languages.add(lp.getLanguagePairAsString());
			}
		}
		return languages;
	}


	public void requestTranslation(final Translation request) {
		Thread t = new Thread() {
			public void run() {
				requests.add(request);
				String jsonRequest = api.requestTranslation(request);
				String uid = parser.parseTranslationRequest(jsonRequest);
				request.setAsRequested(uid);
			}
		};
		t.start();
	}

}
