package ToDoList;
import java.awt.*;
import java.io.IOException;
import java.util.Scanner;

import Grafica.Finestra;
import com.formdev.flatlaf.FlatDarkLaf;
import mainclasses.Utente;

//Import Grafica
import javax.swing.*;

public class ToDoList {
    public static void main(String[] args) throws IOException {


        Utente utente = new Utente();
        //Finestra                                                                                                      //Creata classe finestra con all'interno tutta la parte grafica
        Finestra fin = new Finestra();
        fin.usaFrame(utente);

        //Parte funzionale
        Scanner sc = new Scanner(System.in);
        String cmd = "";
        while (true){
            System.out.print(">>> ");
            cmd = sc.nextLine();
            switch(cmd){
                case "q":
                    utente.salvaTasks();
                    return;
                default:
                    utente.runCmd(cmd);
            }
        }

    }

}
