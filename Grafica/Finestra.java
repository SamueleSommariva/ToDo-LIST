package Grafica;



import com.formdev.flatlaf.FlatDarkLaf;
import mainclasses.Utente;


import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Finestra extends JFrame{                                                                                                 // Creazione classe finestra per la creazione del frame

    Utente utente;


    public void usaFrame(Utente ute){
        this.utente = ute;
        JFrame frame = new JFrame ();                                                                                   // Viene creato un nuovo frame "JFrame"
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);                                                           // Si imposta la funzione che al click della crocetta dell'applicazione, si fermerà l'intero programma
        frame.setSize (1200, 800);                                                                           // Set della grandezza del frame
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

        JPanel posizionePulsanti = new JPanel();                                       // Creazione dei pulsanti
        JButton bottoneTask = new JButton("Crea Task");                                                             // Creazione bottone "Crea Task"
        JButton bottoneModificaTask = new JButton("Modifica Task");                                                 // Creazione bottone "Modifica Task"
        posizionePulsanti.add(bottoneTask);                                                                             // Posizionamento dei bottoni al frame
        posizionePulsanti.add(bottoneModificaTask);
        posizionePulsanti.add(aggiorna);

        // Impostazione del campo modificabile
        JLabel promtTitolo = new JLabel("Inserisci il titolo della Task qui...");
        JTextField titoloTask = new JTextField(25);
        JTextArea taskField = new JTextArea(ute.toStringGUI(),25,25);
        Font arial = new Font("Arial", Font.BOLD, 16);
        taskField.setFont(arial);
        taskField.setForeground(Color.black);
        taskField.setBackground(Color.GRAY);
        taskField.setEditable(false);




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
