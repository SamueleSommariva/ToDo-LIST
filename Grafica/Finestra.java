package Grafica;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;


import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.text.StyledEditorKit;
import java.awt.*;
import java.awt.Color;
import java.awt.GridBagConstraints;

public class Finestra extends JFrame{                                                                                                 // Creazione classe finestra per la creazione del frame


    void impostaLimite(GridBagConstraints gbc, int gx, int gy, int gw, int gh, int wx, int wy) {

        gbc.gridx = gx;

        gbc.gridy = gy;

        gbc.gridwidth = gw;

        gbc.gridheight = gh;

        gbc.weightx = wx;

        gbc.weighty = wy;

    }


    public void usaFrame(){
        JFrame frame = new JFrame ();                                                                                   // Viene creato un nuovo frame "JFrame"
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                                                           // Si imposta la funzione che al click della crocetta dell'applicazione, si fermerà l'intero programma
        frame.setSize (600, 600);                                                                           // Set della grandezza del frame
        frame.setLocationRelativeTo (null);                                                                             // Set della posizone di apertura dell'applicazione
        frame.setTitle("ToDo_List");                                                                                    // Set del titolo dell'applicazione: "ToDo_List"
        frame.setResizable(false);                                                                                      // Impostazione di non modifica della grandezza del frame
        frame.setFont(new Font("Arial Black", Font.BOLD, 20));                                                // Impostazione del font dell'applicazione

        ImageIcon image = new ImageIcon("Grafica\\Immagini\\TodoTest.png");                                      // Selezione file dell'icona dall'applicazione
        frame.setIconImage(image.getImage());                                                                           // Set dell'icona via path

        frame.setUndecorated ( true );                                                                                  // Impostazioni di decorazione
        frame.getRootPane ().setWindowDecorationStyle
                (
                        JRootPane.FRAME
                );

        JPanel panel = new JPanel ();
        frame.setContentPane ( panel );

        try                                                                                                             // Implementazione del tema "FlatDarkLaf"
        {
            UIManager.setLookAndFeel
                    (
                            new FlatDarkLaf ()
                    );
        }
        catch (Exception e)
        {
            e.printStackTrace ();
        }

        SwingUtilities.updateComponentTreeUI (frame);


        JPanel posizionePulsanti = new JPanel();                                       // Creazione dei pulsanti
        JPanel posizioneInserimentoTesto = new JPanel();
        JButton bottoneTask = new JButton("Crea Task");                                                             // Creazione bottone "Crea Task"
        JButton bottoneModificaTask = new JButton("Modifica Task");                                                 // Creazione bottone "Modifica Task"
        posizionePulsanti.add(bottoneTask);                                                                             // Posizionamento dei bottoni al frame
        posizionePulsanti.add(bottoneModificaTask);

        JLabel etichettaInserimentoTitolo = new JLabel("Inserisci il titolo della task qui..");                    // Creazione etichetta per informare la poszione dell'insrimento del titolo
        JTextArea titoloTask = new JTextArea("");                                                                       // Creazione del campo per l'insrimento del titolo

        JPanel pannelloLayout = new JPanel();
        frame.add(pannelloLayout, titoloTask);
        JPanel titolo = new JPanel();
        frame.add(etichettaInserimentoTitolo);
        titoloTask.setEditable(true);

        GridBagLayout grigliaAvanzata = new GridBagLayout();
        GridBagConstraints limite = new GridBagConstraints();
        frame.setLayout(grigliaAvanzata);

        impostaLimite(limite, 0,10, 0,2,1, 2);
        frame.add(posizionePulsanti);
        grigliaAvanzata.setConstraints(posizionePulsanti, limite);


        impostaLimite(limite, 0,4,2,3,1,1);
        grigliaAvanzata.setConstraints(titolo,limite);



        impostaLimite(limite,0,2,2,2,1,1);
        frame.add(titoloTask);
        grigliaAvanzata.setConstraints(titoloTask,limite);



        titoloTask.setLineWrap(true);                                                                                   //
        titoloTask.setWrapStyleWord(true);                                                                              //
                                                                                                                        // Impostazione del campo modificabile


        frame.setVisible (true);

    }

}
