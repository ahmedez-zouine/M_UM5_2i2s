public class MainNormal {
    public static void main(String[] args) {
        System.out.println("Démarrage de la version non synchronisée");
        Banque banque = new Banque(100, 1000);
        
        System.out.println("Solde total initial: " + banque.soldeTotal() + " Dhs");
        
        for (int compte = 0; compte < 100; compte++) {
            Runnable r = new Transfert(banque, compte, 1000);
            new Thread(r).start();
        }
        
        while (true) {
            try {
                Thread.sleep(2000);
                System.out.println("Solde total actuel: " + banque.soldeTotal() + " Dhs");
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}