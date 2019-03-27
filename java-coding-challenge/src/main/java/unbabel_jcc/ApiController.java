package unbabel_jcc;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiController {

	
	static String USERNAME = "fullstack-challenge";
	static String APIKEY = "9db71b322d43a6ac0f681784ebdcc6409bb83359";
	static String AUTENTICATION = "ApiKey " + USERNAME + ":" + APIKEY;
	
	static String LANGUAGE_REQUEST = "https://sandbox.unbabel.com/tapi/v2/language_pair/";
	static String TRANSLATE_REQUEST = "https://sandbox.unbabel.com/tapi/v2/translation/";
	static String CHECK_TRANSLATION_REQUEST = "https://sandbox.unbabel.com/tapi/v2/translation/";
	
	public String requestLanguages() {
		try {
			HttpURLConnection con;
			BufferedReader in;
			URL url = new URL(LANGUAGE_REQUEST);
			con = (HttpURLConnection) url.openConnection();
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("Authorization", AUTENTICATION);
			con.setRequestMethod("GET");
			in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer content = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				content.append(inputLine);
			}
			in.close();
			con.disconnect();
			return content.toString();
		} catch (Exception e) {
			//TODO
			e.printStackTrace();
		} 
		return null;
	}
	
	
	public String requestTranslation(Translation request) {
		try {
			HttpURLConnection con;
			BufferedReader in;
			LanguagePair langPair = request.getLangPair();
			String originalText = request.getOriginalText();
			URL url = new URL(TRANSLATE_REQUEST);
			con = (HttpURLConnection) url.openConnection();
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("Authorization", AUTENTICATION);
			con.setRequestMethod("POST");
			String postData = "{\"text\" : \"" + originalText +
					"\", \"source_language\" : \"" + langPair.getSourceShort() +
					"\", \"target_language\" : \"" + langPair.getTargetShort() + 
					"\", \"text_format\" : \"text\"}";
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(postData);
			wr.flush();	wr.close();
			in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer content = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				content.append(inputLine);
			}
			in.close();
			con.disconnect();
			return content.toString();
		} catch (Exception e) {
			//TODO
			e.printStackTrace();
		} 
		return null;
	}
	
	
	public String checkTranslationRequest(Translation request) {
		try {
			HttpURLConnection con;
			BufferedReader in;
			if((request.getUid() != null) && (!request.getUid().isEmpty())) {
				String completeUrl = CHECK_TRANSLATION_REQUEST + request.getUid() + "/";
				URL url = new URL(completeUrl);
				con = (HttpURLConnection) url.openConnection();
				con.setRequestProperty("Content-Type", "application/json");
				con.setRequestProperty("Authorization", AUTENTICATION);
				
				in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuffer content = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					content.append(inputLine);
				}
				in.close();
				con.disconnect();
				return content.toString();
			}
		} catch (Exception e) {
			//TODO
			e.printStackTrace();
		} 
		return null;
	}
	
}
