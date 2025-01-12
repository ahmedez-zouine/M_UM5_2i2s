public class Lievre implements Runnable 
{
    private int position = 0;
    private int disTotal;
    private String nom;
    String red = "\u001B[31m";
    String vert = "\u001B[32m";
    String blue = "\u001B[34m";
    String RESET = "\u001B[0m";

    public Lievre(String nom, int DisFinal) 
    {
        this.nom = nom;
	this.disTotal = DisFinal;
    }

    long getTimeSleep(int p)
    {
	    int dis = disTotal - p;
	    return (dis * 2000);
    }

    public void run() {
        while (position < disTotal) {
            position += 3;
            System.out.println(vert + nom +" la Distanace parcoure :" + position + RESET);
           
	   if (position >= this.disTotal / 2 && position < this.disTotal - 20)
	   {
		    System.out.println(red + nom + " Sleeping" + RESET);
                   try
		   {
			long timeSleep = getTimeSleep(position);
		    	Thread.sleep(4000);
		   }
		   catch(InterruptedException ex)
		   {
			   return ;
		   }
	    }
            else
	    {
		    try
		    {
                    	Thread.sleep(200);
		    }
	    	    catch(InterruptedException ex)
		    {
			    return ;
		    }
	    }
            if (position >= disTotal) 
	    {
                System.out.println(blue + nom + " A GAGNÉ '___'!" + RESET);
                System.exit(0);
            }
        }
    }
}
