package Grafica;

import mainclasses.Utente;

import javax.swing.*;


public class FinestraData extends JFrame {
    Utente utente;

    //frame
    public void usaFrameData(Utente ute){
        this.utente = ute;

        JFrame frameData = new JFrame("Data");
        frameData.setSize(300,300);
        frameData.setResizable(false);
        frameData.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameData.setTitle("Data");
        frameData.setVisible(true);

        ImageIcon image = new ImageIcon("Grafica\\Immagini\\TodoTest.png");                                      // Selezione file dell'icona dall'applicazione
        frameData.setIconImage(image.getImage());

    }

}
