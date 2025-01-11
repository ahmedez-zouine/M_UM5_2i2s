import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.lang.Thread; 

public class SanjiEtNamijob implements Runnable
{
	CompteBanque cmp;
	int sold;
	String name;


	private Lock lock = new ReentrantLock();
	public SanjiEtNamijob(int sold, String name)
	{
		this.sold = sold;
		this.name = name;
	}


	void commandRetirer(int sold)
	{
		if (sold > 0 )
		{
			try {
	 			Thread.sleep(1000);
			} catch (InterruptedException ex)
			{

			}
			cmp.retirer(sold);
		}
	}

	public void run()
	{	
		lock.lock();
        	try
		{
			commandRetirer(sold);
		} finally 
		{
            		lock.unlock();
		}
        }
}
