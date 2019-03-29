package unbabel_jcc;

import org.junit.Test;

import unbabel_jcc.resources.Status;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;

public class TranslationTest {

	
	private static String SOURCE = "SourceLanguage";
	private static String SOURCE_SHORT = "sc";
	private static String TARGET = "TargetLanguage";
	private static String TARGET_SHORT = "tl";
	private static String UID = "uid";
	private static String TRANSLATED_TEXT = "TranslatedText";
	private static LanguagePair LANG_PAIR = new LanguagePair(
			SOURCE, SOURCE_SHORT, TARGET, TARGET_SHORT);
	private static String ORIGINAL_TEXT = "OriginalText";
	
	private static TranslationRequest TRANSLATION;
	
	@BeforeClass
	public static void initTests() {
		TRANSLATION = new TranslationRequest(LANG_PAIR, ORIGINAL_TEXT);
	}
	
	
	@Test
	public void correctConstructorTest() {
		TRANSLATION = new TranslationRequest(LANG_PAIR, ORIGINAL_TEXT);
		assertNotNull("Translation null", TRANSLATION);
		assertEquals("Language Pair attribute", LANG_PAIR, TRANSLATION.getLangPair());
		assertEquals("Original text attribute", ORIGINAL_TEXT, TRANSLATION.getOriginalText());
		assertEquals("Status attribute initializing on constructor", Status.PROCESSING, TRANSLATION.getStatus());
		assertTrue("Translation text attribute empty on constructor", TRANSLATION.getTranslatedText().isEmpty());
		assertTrue("Uid attribute empty on constructor", TRANSLATION.getUid().isEmpty());
	}
	
	
	@Test
	public void requestMadeTest() {
		TRANSLATION.setAsRequested(UID);
		assertEquals("Status changes to requested", Status.REQUESTED, TRANSLATION.getStatus());
		assertEquals("Status string as expected - Requested", "Requested", TRANSLATION.getStatusString());
	}
	
	
	@Test
	public void requestCompletedTest() {
		TRANSLATION.setAsCompleted(TRANSLATED_TEXT);
		assertEquals("Status changes to completed", Status.COMPLETED, TRANSLATION.getStatus());
		assertEquals("Status string as expected - Completed", "Completed", TRANSLATION.getStatusString());
	}
	
	
	@Test
	public void requestErrorTest() {
		TRANSLATION.setAsError();
		assertEquals("Status changes to error", Status.ERROR, TRANSLATION.getStatus());
		assertEquals("Status string as expected - Error", "Error", TRANSLATION.getStatusString());
	}
	
	
	@AfterClass
	public static void cleanUp() {
		// If there is no reference to the object, it will be deleted by the garbage collector
		TRANSLATION = null;
		SOURCE = null;
		SOURCE_SHORT = null;
		TARGET = null;
		TARGET_SHORT = null;
		UID = null;
		TRANSLATED_TEXT = null;
		LANG_PAIR = null;
		ORIGINAL_TEXT = null;
	}
	
	
}
