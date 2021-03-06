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
                case "q":                                                                                                   //Se il comando inserito è q
                    utente.salvaTasks();                                                                                    //salva i cambiamenti sulle task sul file Task.csv
                    System.exit(0);                                                                               //e termina la il processo.
                default:
                    utente.runCmd(cmd);                                                                                 //Altrimenti lancia il comando con l'apposito metodo della classe Utente
            }
        }

    }

}
