public class Main
{

	public static void main(String[] args) {
    	CompteBanque compte = new CompteBanque();
    	int sold = compte.getSold();
	System.out.println("sold now " + sold);
	compte.setSold(101);
	sold = compte.getSold();
	System.out.println("sold is " + sold);


   	SanjiEtNamijob job1 = new SanjiEtNamijob(100, "Sanji");
    	SanjiEtNamijob job2 = new SanjiEtNamijob(100, "Namij");

    	job1.cmp = compte;
    	job2.cmp = compte;

    	Thread thread1 = new Thread(job1);
    	Thread thread2 = new Thread(job2);

    	thread1.start();
    	thread2.start();

    	try {
        	thread1.join();
       	 	thread2.join();
    	} catch (InterruptedException e) {
        e.printStackTrace();
    	}

    	System.out.println("Final balance: " + compte.getSold());
	}
}
