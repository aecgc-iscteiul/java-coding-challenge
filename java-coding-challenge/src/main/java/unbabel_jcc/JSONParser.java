package unbabel_jcc;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSONParser {

	
	public List<LanguagePair> parseLanguageRequest(String json) {
		List<LanguagePair> availableTranslations = new ArrayList<LanguagePair>();
		JSONObject jsonObject = new JSONObject(json);
		JSONArray languagePairs = jsonObject.getJSONArray("objects");
		for (int i = 0; i < languagePairs.length(); i++) {
			JSONObject langPairA = languagePairs.getJSONObject(i);
			JSONObject langPair = langPairA.getJSONObject("lang_pair");
			JSONObject sourceLanguage = langPair.getJSONObject("source_language");
			String sourceName = sourceLanguage.getString("name");
			String sourceShortName = sourceLanguage.getString("shortname");
			JSONObject targetLanguage = langPair.getJSONObject("target_language");
			String targetName = targetLanguage.getString("name");
			String targetShortName = targetLanguage.getString("shortname");
			LanguagePair lp = new LanguagePair(sourceName, sourceShortName, targetName, targetShortName);
			availableTranslations.add(lp);
		}
		return availableTranslations;
	}
	
	
	public String parseTranslationRequest(String json) {
		String uid = "";		
		JSONObject jsonObject = new JSONObject(json);
		uid = jsonObject.getString("uid");
		return uid;
	}
	
	
	public String parseCheckITranslationRequest(TranslationRequest request, String json) {		
		JSONObject jsonObject = new JSONObject(json);
		String translatedText = "";
		String status = jsonObject.getString("status");
		if(status.equals("completed")) {
			translatedText = jsonObject.getString("translatedText");
		} 
		return translatedText;
	}
	
	
}