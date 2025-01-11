public class Tortue implements Runnable {
    
    private int position = 0;
    private String nom;
    private int disTotal;
  
    String red = "\u001B[31m";
    String vert = "\u001B[32m";
    String blue = "\u001B[34m";
    String RESET = "\u001B[0m";

    public Tortue(String nom, int dis) 
    {
        this.nom = nom;
	this.disTotal = dis;
    }

    public void run() {
        while (position < disTotal)
	{
            position ++;
            System.out.println(nom + " est à Destance parcourue " +vert + position + RESET);
          try
	  {
		  Thread.sleep(100);
	  }
	  catch (InterruptedException ex)
	  {
		  return ;
	  }
	  if (position >= disTotal) 
	   {
                System.out.println( blue + nom + " A GAGNÉ LA COURSE!");
                System.exit(0);
            }
        }
    }
}
