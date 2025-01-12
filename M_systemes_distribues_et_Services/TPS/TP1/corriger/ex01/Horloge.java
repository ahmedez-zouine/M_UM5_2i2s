import javax.swing.*;
import java.awt.*;

public class Horloge extends JLabel {
    private Afficher afficheur;

    public Horloge() {
        this.setHorizontalAlignment(JLabel.CENTER);
        Font font = this.getFont();
        this.setFont(new Font(font.getName(), font.getStyle(), 30));
        afficheur = new Afficher(this);
        Thread thread = new Thread(afficheur);
        thread.start();
    }

    private class Afficher implements Runnable {
        private Horloge horloge;
        private int sec;
        private int min;
	private int h;

        public Afficher(Horloge horloge) {
            this.horloge = horloge;
            this.sec = 0;
            this.min = 0;
	    this.h = 0;
        }

        public void run() {
            while (true) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    return;
                }
                sec++;
                if (sec == 60) {
                    min++;
                    sec = 0;
                }
                if (min == 60) {
		    h++;
                    min = 0;
                }
                String secStr = (sec < 10 ? "0" : "") + String.valueOf(sec);
                String minStr = (min < 10 ? "0" : "") + String.valueOf(min);
                String hStr = (h < 10 ? "0" : "") + String.valueOf(h);
		horloge.setText(hStr + " H :" + minStr + " M :" + secStr + " S");
            }
        }
    }
}
