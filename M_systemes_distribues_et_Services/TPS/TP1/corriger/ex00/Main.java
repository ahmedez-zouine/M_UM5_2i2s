
public  class Main
{
	public static void main(String args[])
	{
		My_Thread th1 = new My_Thread(0);
		My_Thread th2 = new My_Thread(1);
		My_Thread th3 = new My_Thread(2);

		th1.start();
		th2.start();
		th3.start();
	
	try {
            th1.join();
            th2.join();
            th3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
   }
}
