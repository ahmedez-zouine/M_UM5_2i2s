
public class My_Thread extends Thread
{
	int w_do;

	public My_Thread(int w_do)
	{
		this.w_do = w_do;
	}

	public void do_alph(int code)
	{
		char c;
		if (code == 0)
		{
			c = 'r'; 
			while (c <= 'z')
			{
				System.out.print(c++ + " ");
				try {
                       			 Thread.sleep(100);
                    		} catch (InterruptedException e) {
                        		e.printStackTrace();
                    		}
			}
		}else if (code == 1)
		{
			c = 'A';
			while (c >= 'Q')
			{
				System.out.print(c++ + " ");
			       	try{
				       	Thread.sleep(100);
				} catch (InterruptedException e){
					e.printStackTrace();
 				}
			}
		}
	}

	public void do_number()
	{
		int i = 0;
		while (i <= 60)
		{
			
			System.out.print(i++ + " ");
			try
			{
				Thread.sleep(100);
			}catch (InterruptedException ex){
			}
		}
	}

	public void run()
	{
		if (w_do == 0)
			do_alph(0);
		else if (w_do == 1)
			do_alph(1);
		else if (w_do == 2)
			do_number();
	}
}
