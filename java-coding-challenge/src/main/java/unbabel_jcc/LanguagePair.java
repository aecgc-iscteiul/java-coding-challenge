package unbabel_jcc;

public class LanguagePair {
	
	
	private class Language {
		
		private String name;
		private String shortName;
		
		public Language(String name, String shortName) {
			this.name = name;
			this.shortName = shortName;
		}

		public String getName() {
			return name;
		}

		public String getShortName() {
			return shortName;
		}
		
	}

	private Language source;
	private Language target;

	public LanguagePair(String sourceName, String sourceShortName, 
			String targetName, String targetShortName) {
		source = new Language(sourceName, sourceShortName);
		target = new Language(targetName, targetShortName);
	}
	
	public String getSource() {
		return source.getName();
	}

	public String getTarget() {
		return target.getName();
	}


	public String getSourceShort() {
		return source.getShortName();
	}


	public String getTargetShort() {
		return target.getShortName();
	}
	
	public String getLanguagePairAsString() {
		return source.getName() + " to " + target.getName();
	}

}
