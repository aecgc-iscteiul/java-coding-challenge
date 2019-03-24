package unbabel_jcc;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import unbabel_jcc.resources.Messages;

public class ApiController {
	
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
