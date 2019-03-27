package unbabel_jcc;

import java.util.List;

import unbabel_jcc.resources.Status;

public class RequestChecker extends Thread {

	private GeneralController controller;

	public RequestChecker(GeneralController controller) {
		super();
		this.controller = controller;
	}


	@Override
	public void run() {
		List<Translation> requests = controller.getUids();
		while(true) {
			try {
				sleep(60000);
				for(final Translation request: requests) {
					if(request.getStatus() != Status.COMPLETED) {
						Thread t = new Thread() {
							public void run() {
								String json = controller.getApi().checkTranslationRequest(request);
								String translated = controller.getParser().parseCheckITranslationRequest(request, json);
								if(!translated.isEmpty()) { request.setAsCompleted(translated); }
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
