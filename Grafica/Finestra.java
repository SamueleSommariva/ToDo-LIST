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

    static String data ="NO";
    static String dataPeriodica = "00/00/00/0000";
    Utente utente;

    static boolean periodicita;

    public Finestra() {
    }


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

        ImageIcon image = new ImageIcon("Grafica\\Immagini\\ToDo_Icon.png");                                      // Selezione file dell'icona dall'applicazione
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
            UIManager.put("JFrame.activeTitleBackground", (new Color(238,148,121)));

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
        JButton calendario = new JButton("Calendario");
        calendario.setFont(new Font("Arial", Font.BOLD, 18));
        calendario.setBackground(new Color(15,124,145));
        calendario.setForeground(Color.white);
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
        bottoneTask.setFont(new Font("Arial", Font.BOLD, 18));
        bottoneTask.setBackground(new Color(15,124,145));
        bottoneTask.setForeground(Color.white);
        bottoneTask.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {                                                                //<------------------------------------------------------------------------
                utente.runCmd("mktask " + titoloTask.getText() + " " +
                        data + " " +
                        dataPeriodica + " ");
                Finestra f = new Finestra().usaFrame(utente);
                frame.dispose();

            }
        });

        //apertura della finestra calendario
        calendario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Calendario calendario = new Calendario();
                calendario.usaCalendario(utente);
            }
        });

        JButton bottonePeriodicita = new JButton("Periodicità");                                                             // Creazione bottone "Crea Task"
        bottonePeriodicita.setFont(new Font("Arial", Font.BOLD, 18));
        bottonePeriodicita.setBackground(new Color(15,124,145));
        bottonePeriodicita.setForeground(Color.white);
        bottonePeriodicita.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                periodicita = true;
                Calendario calendario = new Calendario();
                calendario.usaCalendario(utente);
            }
        });



        JButton bottoneModificaTask = new JButton("Modifica Task");                                                // Creazione bottone "Modifica Task"
        bottoneModificaTask.setFont(new Font("Arial", Font.BOLD, 18));
        bottoneModificaTask.setBackground(new Color(15,124,145));
        bottoneModificaTask.setForeground(Color.white);
        posizionePulsanti.add(bottoneTask);                                                                             // Posizionamento dei bottoni al frame
        //posizionePulsanti.add(bottoneModificaTask);


        /**
         * Lista Task
         */

        JTextArea taskField = new JTextArea(utente.toStringGUI(),25,25);
        Font arial = new Font("Arial", Font.PLAIN, 16);
        taskField.setFont(arial);
        taskField.setBackground(new Color(238,148,121));
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
        //pannelloInserimento.add(dataTask);
        pannelloInserimento.add(calendario);                                                                          // Commento da rimuovere non appena il calendario sarà l'input della data per la task
        pannelloInserimento.add(promtPeriodicita);
        pannelloInserimento.add(bottonePeriodicita);
        //pannelloInserimento.add(periodicitaTask);


        frame.getContentPane().add(BorderLayout.NORTH, pannelloInserimento);
        frame.getContentPane().add(BorderLayout.SOUTH, posizionePulsanti);
        frame.getContentPane().add(BorderLayout.CENTER, scrollPane);

        frame.setVisible (true);


        return this;

    }
}