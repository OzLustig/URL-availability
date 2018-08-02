import java.util.concurrent.atomic.AtomicInteger;

class CheckResults 
{
    
	public void updateCheckResults(URLStatus url)
	{
		switch (url) 
		{
		case ERROR:
			atError.getAndIncrement();
			break;
		case OK:
			atOk.getAndIncrement();
			break;
		case UNKNOWN:
			atUnknown.getAndIncrement();
			break;
		}
	}
	
    private static AtomicInteger atOk = new AtomicInteger(0);
    private static AtomicInteger atError = new AtomicInteger(0);
    private static AtomicInteger atUnknown = new AtomicInteger(0);


    void print() {
        System.out.printf("%d OK, %d Error, %d Unknown\n",
        		atOk.get(),
                atError.get(),
                atUnknown.get());
    }
}
