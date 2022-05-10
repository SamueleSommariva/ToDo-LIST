package mainclasses;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Utente {
    
    List<Task> taskList = new ArrayList<Task>();

    
    
    public Utente() throws IOException{
        Task task;
        for (int i = 0; Task.altreTask(); i++){
            taskList.add(new Task());
        }
    }   
    
    public void salvaTasks() throws IOException{
        for(int i = taskList.size(); i > 0; i--){
            taskList.get(taskList.size()-1).salvaTask();
        }
    }
    
    public void runCmd(String command){
        try{
            String[] cmd = command.split(" ");
            switch (cmd[0]) {
                case "mktask":
                    taskList.add(new Task(cmd[1],   Task.ora(cmd[2]), 
                                                        Task.giorno(cmd[2]), 
                                                        Task.mese(cmd[2]), 
                                                        Task.anno(cmd[2])));
                    break;
                case "show":
                    System.out.println(this.toString());
                    break;
                case "edit":
                    switch (cmd[1]){
                            case "desc":
                                taskList.set(new Integer(cmd[1].toString()), 
                                        new Task(  cmd[2],
                                        Task.ora(taskList.get(new Integer(cmd[1].toString())).getDataOra()),
                                        Task.giorno(taskList.get(new Integer(cmd[1].toString())).getDataOra()),
                                        Task.mese(taskList.get(new Integer(cmd[1].toString())).getDataOra()),
                                        Task.anno(taskList.get(new Integer(cmd[1].toString())).getDataOra())));
                                break;
                            case "date":
                                taskList.set(new Integer(cmd[1].toString()), 
                                        new Task( taskList.get(new Integer(cmd[1].toString())).getDescrizione(),
                                        Task.ora(cmd[2]),
                                        Task.giorno(cmd[2]),
                                        Task.mese(cmd[2]),
                                        Task.anno(cmd[2])));
                                break;
                            default:
                                System.out.println("\nInvalid command or params...\nType help for more.");
                    }
                    break;
                case "complete":
                    Task t = taskList.get(new Integer(cmd[1].toString())-1);
                    t.completata();
                    taskList.set(new Integer(cmd[1].toString())-1, t);
                    System.out.println("Completed Task N: " + cmd[1]);
                    break;
                case "help":
                    System.out.println("INFO COMMANDS:\n"
                            + "help\t\t" +        "Info comandi.\n"
                            + "show\t\t" +        "Stampa la lista di tutte le task salvate.\n"
                            + "mktask\t\t" +      "Crea task es (mktask desc hh/gg/mm/aaaa).\n"
                            + "edit\t\t" +        "Modifica task N (show) parametri [desc: descrizione][date: dataOra hh/gg/mm/aaa]\n"
                            + "completed\t" +     "Completare/eseguire la task N (show)\n"
                            + "rm\t\t" +            "Elimina la task N (show)\n");
                    break;
                case "rm":
                    taskList.remove(new Integer(cmd[1].toString())-1);
                    System.out.println("Deleted Task N: " + cmd[1]);
                    break;
                default:
                    System.out.println("\nInvalid command or params...\nType help for more.");
                

            }
        }catch(Exception e){
            System.err.println(e + "\nInvalid command or params...\nType help for more.");
        }
        
    }
    
    
    public String toString(){
        StringBuilder s = new StringBuilder();
        int i = 1;
        s.append("N  DESC.\tDATE\t\tCOMPLETE\n");
        for (Task task : taskList){
            s.append(i +") " + task.toString() + '\n');
            i++;
        }
        return s.toString();
    }
    
}