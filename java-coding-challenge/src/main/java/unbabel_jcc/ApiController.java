package unbabel_jcc;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import unbabel_jcc.resources.Messages;

public class ApiController {
	
	// LANGUAGE
	// Authorization: ApiKey $UNBABEL_USERNAME:$UNBABEL_API_
	// "Content-Type: application/json" 
	// GET https://api.unbabel.com/tapi/v2/language_pair/
	
	// TRANSLATE
	// "Authorization: ApiKey $UNBABEL_USERNAME:$UNBABEL_API_KEY" \
	// "Content-Type: application/json" \
	// POST https://api.unbabel.com/tapi/v2/translation/ \
	//'{
//    "text": "Text to be translated",
//    "source_language": "en",
//    "target_language": "pt"
//}'
	
	public ApiController() {
		// TODO Auto-generated constructor stub
	}
	
	public ObservableList<String> getLanguagesAvaliable() {
		ObservableList<String> languages = FXCollections.
				observableArrayList(Messages.getString("ApiController.Lang0"),
						Messages.getString("ApiController.Lang1"), 
						Messages.getString("ApiController.Lang2"));
		return languages;
	}
	
	
	
	public String translateInput(String input) {
		return input + " TRANSLATED";
	}

}
