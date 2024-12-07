public static void main(String[] args)
{  
    Banque banque = new Banque(100, 1000);

    for (int depuis = 0; depuis < 100; depuis ++)
    { 
        Runnable r = new Transfert(banque,depuis,1000); 
        new Thread(r).start();  
    } 
}