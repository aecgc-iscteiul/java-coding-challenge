package unbabel_jcc;

public class Translation {
	
	class Language {
		
		private String language;
		private String short_form;
		
		public Language(String language, String short_form) {
			super();
			this.language = language;
			this.short_form = short_form;
		}

		public String getLanguage() {
			return language;
		}

		public String getShort_form() {
			return short_form;
		}
		
	}
	
	
	private String from;
	private String to;
	private String original;
	private String translated;
	private boolean status;
	
	
	public Translation(String from, String to, String original) {
		super();
		this.from = from;
		this.to = to;
		this.original = original;
		translated = null;
		status = false;
	}


	public String getFrom() {
		return from;
	}

	public String getTo() {
		return to;
	}

	public String getOriginal() {
		return original;
	}

	public String getTranslated() {
		return translated;
	}

	public String getStatus() {
		String status = this.status ? "Completed" : "Waiting";
		return status;
	}
	
	
	public void finishTranslation(String translated) {
		this.translated = translated;
		status = true;
	}
	
	
	
	
	
}
