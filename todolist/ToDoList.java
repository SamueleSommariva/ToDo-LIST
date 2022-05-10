package todolist;
import java.io.IOException;
import java.util.Scanner;
import mainclasses.Utente;

public class ToDoList {
    public static void main(String[] args) throws IOException {
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
