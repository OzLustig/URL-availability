// HW4, Oz Lustig, 203184858

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.BlockingQueue;

public class URLFileReader implements Runnable {
    private String filename;
    private BlockingQueue<String> urlQueue;
    private int numberOfConsumers;

    URLFileReader(String filename, BlockingQueue<String> urlQueue, int numberOfConsumers) {
        this.filename = filename;
        this.urlQueue = urlQueue;
        this.numberOfConsumers = numberOfConsumers;
    }

    @Override
    public void run() 
    {
       	//open filename, read lines and push them into urlQueue
    	FileInputStream fis;
    	try {

    		fis = new FileInputStream(filename);
    		//Construct BufferedReader from InputStreamReader
    		BufferedReader br = new BufferedReader(new InputStreamReader(fis));

    		String line = null;
    		while ((line = br.readLine()) != null) 
    		{
    			urlQueue.put(line);
    		}
    		br.close();
    	}
    	catch (FileNotFoundException e) {
    		System.err.println(e.getLocalizedMessage());
    	} catch (IOException e) {
    		System.err.println(e.getLocalizedMessage());
    	} catch (InterruptedException e) {
    		System.err.println(e.getLocalizedMessage());
		}
    		
    	// push numberOfConsumers empty strings into urlQueue
    	for(int i=0;i<numberOfConsumers;i++)
    	{
    		try {
				urlQueue.put("");
			} catch (InterruptedException e) {
				System.err.println(e.getLocalizedMessage());
			}
    	}
    }
}
