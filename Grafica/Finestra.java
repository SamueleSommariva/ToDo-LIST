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


public class Finestra {

    public void usaFrame(){
        JFrame frame = new JFrame ();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize (600, 600);
        frame.setLocationRelativeTo (null);
        frame.setTitle("ToDo_List");
        frame.setResizable(false);
        frame.setFont(new Font("Arial Black", Font.BOLD, 20));

        ImageIcon image = new ImageIcon("Grafica\\Immagini\\TodoTest.png");
        frame.setIconImage(image.getImage());

        frame.setUndecorated ( true );
        frame.getRootPane ().setWindowDecorationStyle
                (
                        JRootPane.FRAME
                );

        JPanel panel = new JPanel ();
        frame.setContentPane ( panel );

        try
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


        frame.setVisible (true);



        JPanel posizionePulsanti = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel posizioneInserimentoTesto = new JPanel();
        JButton bottoneTask = new JButton("Crea Task");
        JButton bottoneModificaTask = new JButton("Modifica Task");
        posizionePulsanti.add(bottoneTask);
        posizionePulsanti.add(bottoneModificaTask);
        frame.add(posizionePulsanti, BorderLayout.WEST);

        JTextArea titoloTask = new JTextArea("Inserisci il titolo della task");
        posizioneInserimentoTesto.add(titoloTask);
        titoloTask.setEditable(true);
        frame.add(posizioneInserimentoTesto);
    }

}
