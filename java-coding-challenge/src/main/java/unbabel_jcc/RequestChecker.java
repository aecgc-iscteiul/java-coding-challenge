package unbabel_jcc;

import java.util.List;

import unbabel_jcc.resources.Status;

public class RequestChecker extends Thread {
	
	static private int MINUTES_BETWEEN_CHECKS = 1;
	static private long MILLESECONDS_PER_MINUTE = 60000;

	private GeneralController controller;

	public RequestChecker(GeneralController controller) {
		super();
		this.controller = controller;
	}


	@Override
	public void run() {
		List<TranslationRequest> requests = controller.getUids();
		while(true) {
			try {
				sleep(MINUTES_BETWEEN_CHECKS * MILLESECONDS_PER_MINUTE);
				for(final TranslationRequest request: requests) {
					if(request.getStatus() != Status.COMPLETED && request.getStatus() != Status.ERROR) {
						Thread t = new Thread() {
							public void run() {
								String json = controller.getApi().checkTranslationRequest(request);
								if(json != null) {
									String translated = controller.getParser().parseCheckITranslationRequest(request, json);
									if(!translated.isEmpty()) { request.setAsCompleted(translated); }
								}
							}
						};
						t.start();
					}
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}



}
