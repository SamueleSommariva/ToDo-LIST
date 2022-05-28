package Grafica;



import com.formdev.flatlaf.FlatDarkLaf;
import mainclasses.Utente;


import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Finestra extends JFrame{                                                                                                 // Creazione classe finestra per la creazione del frame

    Utente utente;


    public void usaFrame(Utente ute){
        this.utente = ute;
        JFrame frame = new JFrame ();                                                                                   // Viene creato un nuovo frame "JFrame"
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);                                                     // Si imposta la funzione che al click della crocetta dell'applicazione, si fermerà l'intero programma
        frame.addWindowListener(new WindowAdapter() {                                                                   //Cambia l'azione che avviene in chiusura
            @Override
            public void windowClosing(WindowEvent e){                                                                   //Salva e chiude
                try {
                    ute.salvaTasks();
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }catch (Exception z){
                }
            }
        });
        frame.setSize (1200, 630);                                                                           // Set della grandezza del frame
        frame.setLocationRelativeTo (null);                                                                             // Set della posizone di apertura dell'applicazione
        frame.setTitle("ToDo_List");                                                                                    // Set del titolo dell'applicazione: "ToDo_List"
        frame.setResizable(false);                                                                                      // Impostazione di non modifica della grandezza del frame                                               // Impostazione del font dell'applicazione

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

        JButton bottoneInvioTitolo = new JButton("Salva titolo");

        JButton aggiorna = new JButton("Aggiorna");
        aggiorna.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.updateComponentTreeUI(frame);
            }
        });

        SwingUtilities.updateComponentTreeUI(frame);


        /**
         * Campi dati Task
         */

        JLabel promtTitolo = new JLabel("Titolo Task");
        JLabel promtData = new JLabel("Data Task");
        JLabel promtPeriodicita = new JLabel("Periodicita Task");
        JTextField titoloTask = new JTextField(25);
        JTextField dataTask = new JTextField(10);
        JTextField periodicitaTask = new JTextField(10);




        /**
         * Pulsanti
         */

        JPanel posizionePulsanti = new JPanel();                                       // Creazione dei pulsanti
        JButton bottoneTask = new JButton("Crea Task");                                                             // Creazione bottone "Crea Task"
        JButton bottoneModificaTask = new JButton("Modifica Task");                                                 // Creazione bottone "Modifica Task"
        posizionePulsanti.add(bottoneTask);                                                                             // Posizionamento dei bottoni al frame
        posizionePulsanti.add(bottoneModificaTask);
        posizionePulsanti.add(aggiorna);


        /**
         * Lista Task
         */


        JTextArea taskField = new JTextArea(ute.toStringGUI(),25,25);
        Font arial = new Font("Arial", Font.BOLD, 16);
        taskField.setFont(arial);
        taskField.setForeground(Color.black);
        taskField.setBackground(Color.GRAY);
        taskField.setEditable(false);


        /**
         * Inserimento componenti nel Frame
         */

        JPanel pannelloInserimento = new JPanel();
        pannelloInserimento.add(promtTitolo);
        pannelloInserimento.add(titoloTask);
        pannelloInserimento.add(promtData);
        pannelloInserimento.add(dataTask);
        pannelloInserimento.add(promtPeriodicita);
        pannelloInserimento.add(periodicitaTask);




        frame.getContentPane().add(BorderLayout.NORTH, pannelloInserimento);
        frame.getContentPane().add(BorderLayout.SOUTH, posizionePulsanti);
        frame.getContentPane().add(BorderLayout.CENTER, taskField);

        frame.setVisible (true);                                                       //
                                                                                                                        // Impostazione del campo modificabile



        frame.setVisible (true);




    }
}
