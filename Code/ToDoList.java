package Code;
import java.io.IOException;
import java.util.Scanner;

import Code.Grafica.Finestra;
import Code.mainclasses.Utente;

//Import Code.Grafica


public class ToDoList {
    public static void main(String[] args) throws IOException {


        //Finestra                                                                                                      //Creata classe finestra con all'interno tutta la parte grafica
        Finestra fin = new Finestra();
        fin.usaFrame();





        //Parte funzionale
        Utente utente = new Utente();
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
