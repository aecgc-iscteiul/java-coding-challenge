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
		HttpURLConnection con;
		BufferedReader in;
		String inputLine, jsonString = null;
		try {
			URL url = new URL(LANGUAGE_REQUEST);
			con = (HttpURLConnection) url.openConnection();
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("Authorization", AUTENTICATION);
			con.setRequestMethod("GET");
			in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			StringBuffer content = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				content.append(inputLine);
			}
			in.close();
			con.disconnect();
			jsonString = content.toString();
		} catch (Exception e) {
			//TODO
			e.printStackTrace();
		} 
		return jsonString;
	}


	public String requestTranslation(TranslationRequest request) {
		HttpURLConnection con;
		BufferedReader in;
		String inputLine, jsonString = null;
		try {
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
			StringBuffer content = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				content.append(inputLine);
			}
			in.close();
			con.disconnect();
			jsonString = content.toString();
		} catch (Exception e) {
			request.setAsError();
		} 
		return jsonString;
	}


	public String checkTranslationRequest(TranslationRequest request) {
		HttpURLConnection con;
		BufferedReader in;
		String inputLine, jsonString = null;
		try {
			if((request.getUid() != null) && (!request.getUid().isEmpty())) {
				String completeUrl = CHECK_TRANSLATION_REQUEST + request.getUid() + "/";
				URL url = new URL(completeUrl);
				con = (HttpURLConnection) url.openConnection();
				con.setRequestProperty("Content-Type", "application/json");
				con.setRequestProperty("Authorization", AUTENTICATION);

				in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				StringBuffer content = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					content.append(inputLine);
				}
				in.close();
				con.disconnect();
				jsonString = content.toString();
			}
		} catch (Exception e) {
			request.setAsError();
		}
		return jsonString;
	}

}
