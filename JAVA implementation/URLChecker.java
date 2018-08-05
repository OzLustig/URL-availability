import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.BlockingQueue;

public class URLChecker implements Runnable {
    private final static int CONNECTION_TIMEOUT = 2000;
    private final static int READ_TIMEOUT = 2000;

    private final BlockingQueue<String> urlQueue;
    private final CheckResults globalResults;

    URLChecker(BlockingQueue<String> urlQueue, CheckResults globalResults) {
        this.urlQueue = urlQueue;
        this.globalResults = globalResults;
    }

    @Override
    public void run() {
        
        //loop: pop URL from the urlQueue, if not empty- check it, otherwise break from the loop
        //make sure to update globalResults (you decide when and how)
    	String url = null;
		try {
			url = urlQueue.take();
		} catch (InterruptedException e) {
			System.err.println(e.getLocalizedMessage());
		}
        while(url!="")
        {
        	globalResults.updateCheckResults(checkURL(url));
        	try {
				url = urlQueue.take();
			} catch (InterruptedException e) {
				System.err.println(e.getLocalizedMessage());
			}
        }
    }

    private static URLStatus checkURL(String url) {
        URLStatus status;

        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setConnectTimeout(CONNECTION_TIMEOUT);
            connection.setReadTimeout(READ_TIMEOUT);
            connection.setRequestMethod("HEAD");
            int responseCode = connection.getResponseCode();
            if (responseCode >= 200 && responseCode < 400) {
                status = URLStatus.OK;
            } else {
                status = URLStatus.ERROR;
            }
        } catch (IOException e) {
            status = URLStatus.UNKNOWN;
        }

        return status;
    }
}
