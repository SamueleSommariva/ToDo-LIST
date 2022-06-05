package ToDoList;
import java.io.IOException;
import java.util.Scanner;

import Grafica.Calendario;
import Grafica.Finestra;
import mainclasses.Utente;

//Import Grafica


public class ToDoList {
    public static void main(String[] args) throws IOException {


        Utente utente = new Utente();
        //Finestra                                                                                                      //Creata classe finestra con all'interno tutta la parte grafica
        Finestra fin = new Finestra();

        //Parte funzionale
        Scanner sc = new Scanner(System.in);
        String cmd = "";

        fin.usaFrame(utente);

        while (true){
            System.out.print(">>> ");
            cmd = sc.nextLine();
            switch(cmd){
                case "q":
                    utente.salvaTasks();
                    System.exit(1);
                default:
                    utente.runCmd(cmd);
            }
        }







    }

}
