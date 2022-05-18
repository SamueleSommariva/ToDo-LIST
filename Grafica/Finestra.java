package Grafica;

import javax.swing.*;
import java.awt.*;

public class Finestra {

    public void usaFrame(){
        //Codice Grafica

        //Frame
        JFrame frame  = new JFrame();                                                                                   //Crea il frame
        frame.setTitle("ToDo_List");                                                                                    //Nome frame
        frame.setVisible(true);                                                                                         //Rendo il frame visibile

        //Frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                                                           //Quando chiudo il frame con la X la chiudo definitivamente (Non la nascondo)
        frame.setResizable(false);                                                                                      //Non permetto di ridimensionare il frame
        frame.setSize(600,600);                                                                             //Imposta la dimesione del frame
        frame.getContentPane().setBackground(Color.darkGray);




        //Icona
        ImageIcon image = new ImageIcon("ImmaginiToDo\\ToDo_LIst-Logo.png");                                              //Creo immagine             //Trovare nuova immagine 250x250px
        frame.setIconImage(image.getImage());
    }

}
