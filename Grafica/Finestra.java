package Grafica;



import com.formdev.flatlaf.FlatDarkLaf;
import mainclasses.Utente;


import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Finestra extends JFrame{                                                                                                 // Creazione classe finestra per la creazione del frame

    Utente utente;


    public Finestra usaFrame(Utente utente){
        this.utente = utente;
        JFrame frame = new JFrame ();                                                                                   // Viene creato un nuovo frame "JFrame"
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);                                                     // Si imposta la funzione che al click della crocetta dell'applicazione, si fermerà l'intero programma
        frame.addWindowListener(new WindowAdapter() {                                                                   //Cambia l'azione che avviene in chiusura
            @Override
            public void windowClosing(WindowEvent e){                                                                   //Salva e chiude
                try {
                    utente.salvaTasks();
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }catch (Exception z){
                }
            }
        });
        frame.setSize (1400, 630);                                                                           // Set della grandezza del frame
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

        try{
            UIManager.setLookAndFeel(new FlatDarkLaf () );
        }
        catch (Exception e){
            e.printStackTrace ();
        }

        SwingUtilities.updateComponentTreeUI (frame);


        SwingUtilities.updateComponentTreeUI(frame);


        /**
         * Campi dati Task
         */

        JLabel promtTitolo = new JLabel("Titolo Task");
        promtTitolo.setFont(new Font("Arial", Font.BOLD, 20));
        JLabel promtData = new JLabel("Data Task");
        promtData.setFont(new Font("Arial", Font.BOLD, 20));
        JLabel promtPeriodicita = new JLabel("Periodicita Task");
        promtPeriodicita.setFont(new Font("Arial", Font.BOLD, 20));
        JTextField titoloTask = new JTextField(25);
        titoloTask.setFont(new Font("Arial", Font.PLAIN, 15));
        JTextField dataTask = new JTextField(10);
        dataTask.setFont(new Font("Arial", Font.PLAIN, 15));
        JTextField periodicitaTask = new JTextField(10);
        dataTask.setFont(new Font("Arial", Font.PLAIN, 15));




        /**
         * Pulsanti
         */

        JPanel posizionePulsanti = new JPanel();                                                                        // Creazione dei pulsanti
        JButton bottoneTask = new JButton("Crea Task");                                                             // Creazione bottone "Crea Task"
        bottoneTask.setFont(new Font("Arial", Font.PLAIN, 13));
        bottoneTask.setBackground(new Color(100,20,200));
        bottoneTask.setForeground(Color.white);
        bottoneTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                utente.runCmd("mktask " + titoloTask.getText() + " " +
                        dataTask.getText() + " " +
                        periodicitaTask.getText());
                Finestra f = new Finestra().usaFrame(utente);
                frame.dispose();

            }
        });

        JButton bottoneModificaTask = new JButton("Modifica Task");                                                // Creazione bottone "Modifica Task"
        bottoneModificaTask.setFont(new Font("Arial", Font.PLAIN, 13));
        bottoneModificaTask.setBackground(new Color(100,20,200));
        bottoneModificaTask.setForeground(Color.white);
        posizionePulsanti.add(bottoneTask);                                                                             // Posizionamento dei bottoni al frame
        posizionePulsanti.add(bottoneModificaTask);


        /**
         * Lista Task
         */


        JTextArea taskField = new JTextArea(utente.toStringGUI(),25,25);
        Font arial = new Font("Arial", Font.BOLD, 16);
        taskField.setFont(arial);
        taskField.setBackground(new Color(255,157,123));
        taskField.setForeground(Color.black);
        taskField.setEditable(false);

        /**
         * Creazione pannello scrollable
         */
        //Creazione pannello scrollable dimensioni 1100x500
        JScrollPane scrollPane = new JScrollPane(taskField);
        scrollPane.setPreferredSize(new Dimension(1100, 500));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


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
        frame.getContentPane().add(BorderLayout.CENTER, scrollPane);

        frame.setVisible (true);                                                       //
        // Impostazione del campo modificabile




        frame.setVisible (true);

        return this;


    }
}
