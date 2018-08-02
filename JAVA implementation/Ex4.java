import java.util.concurrent.*;

public class Ex4 {
    private final static int QUEUE_CAPACITY = 16;

    private static void CheckURLsFromFile(String filename, int numberOfWorkers) {

        CheckResults results = new CheckResults();
        
        // Initialize a BlockingQueue implementation with limited capacity (QUEUE_CAPACITY)
        ArrayBlockingQueue<String> arrayBlockingQ = new ArrayBlockingQueue<String>(QUEUE_CAPACITY);
        // Start a URLFileReader thread
        Thread URLFileReaderThread = new Thread(new URLFileReader(filename, arrayBlockingQ, numberOfWorkers));
        URLFileReaderThread.start();
        // Start numberOfWorkers URLChecker threads
        Thread[] URLCheckerThreads = new Thread[numberOfWorkers];
        for(int i=0;i<numberOfWorkers;i++)
        {
        	URLCheckerThreads[i] = new Thread(new URLChecker(arrayBlockingQ, results));
        	URLCheckerThreads[i].start();
        }
        // Join URLFileReader thread
        try {
			URLFileReaderThread.join();
		} catch (InterruptedException e) 
		{
			System.err.println(e.getLocalizedMessage());
		}
        // Join URLChecker threads
        for(int i=0;i<numberOfWorkers;i++)
        {
        	try {
        		URLCheckerThreads[i].join();
    		} catch (InterruptedException e) 
    		{
    			System.err.println(e.getLocalizedMessage());
    		}
        }

        results.print();
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("usage: java Ex4 URLS_FILENAME NUMBER_OF_WORKERS");
        } else {
            String filename = args[0];
            int numberOfWorkers = Integer.valueOf(args[1]);

            CheckURLsFromFile(filename, numberOfWorkers);
        }
    }
}
