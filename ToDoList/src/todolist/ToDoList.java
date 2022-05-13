package todolist;


//Import grafica
import javax.swing.JFrame;


//Import funzionale
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import javax.swing.JFrame;
import mainclasses.Task;
import mainclasses.Utente;


public class ToDoList {
    public static void main(String[] args) throws IOException {
        
        //Grafica
        JFrame frame  = new JFrame();                                           //Crea il frame 
        frame.setTitle("ToDo_List");                                            //Nome frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                   //Quando chiudo il frame con la X la chiudo definitivamente (Non la nascondo)
        frame.setSize(600,600);                                                 //Imposta la dimesione del frame
        frame.setVisible(true);  
        
        
        //Funzionale
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
