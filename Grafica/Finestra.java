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
        JButton bottoneTask = new JButton("Crea Task");                                                             // Creazione bottone "Crea Task"
        JButton bottoneModificaTask = new JButton("Modifica Task");                                                 // Creazione bottone "Modifica Task"
        posizionePulsanti.add(bottoneTask);                                                                             // Posizionamento dei bottoni al frame
        posizionePulsanti.add(bottoneModificaTask);

        // Impostazione del campo modificabile
        JLabel promtTitolo = new JLabel("Inserisci il titolo della Task qui...");
        JTextField titoloTask = new JTextField(25);
        JTextArea taskField = new JTextArea(10,25);

        JButton bottoneInvioTitolo = new JButton("Salva titolo");

        JPanel pannelloInserimento = new JPanel();
        pannelloInserimento.add(promtTitolo);
        pannelloInserimento.add(titoloTask);
        frame.getContentPane().add(BorderLayout.NORTH, pannelloInserimento);

        frame.getContentPane().add(BorderLayout.CENTER, taskField);

        frame.getContentPane().add(BorderLayout.SOUTH, posizionePulsanti);

        frame.setVisible (true);                                                       //
                                                                                                                        // Impostazione del campo modificabile


        frame.setVisible (true);

    }

}
