package mainclasses;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Utente {

    List<Task> taskList = new ArrayList<Task>();

    
    
    public Utente() throws IOException{
        taskList = Task.getTasks();
    }   
    
    public void salvaTasks() throws IOException{                //Sovrascrive il file con la lista
        FileWriter file = new FileWriter("Task.csv");
        file.write("");
        Task t;
        for(int i = 0; i < taskList.size(); i++){               //Chiama il metodo salvaTask per ogni task della taskList
            t = taskList.get(i);
            t.salvaTask();
        }
    }
    
    public void runCmd(String command){                         //Esegue i comandi inseriti nella CLI
        try{
            String[] cmd = command.split(" ");
            switch (cmd[0]) {
                case "mktask":
                        try{
                            taskList.add(new Task(cmd[1],   Task.ora(cmd[2]),
                                    Task.giorno(cmd[2]),
                                    Task.mese(cmd[2]),
                                    Task.anno(cmd[2]),
                                    cmd[3]));
                        }catch (ArrayIndexOutOfBoundsException ae){
                            try {
                                taskList.add(new Task(cmd[1],   Task.ora(cmd[2]),
                                        Task.giorno(cmd[2]),
                                        Task.mese(cmd[2]),
                                        Task.anno(cmd[2]),
                                        "00/00/00/0000"));
                            }catch (Exception e){
                                System.out.println("\nInvalid command or params...\nType help for more.");
                            }
                        }
                        break;



                case "show":
                    System.out.println(this.toString());
                    break;
                case "edit":
                    switch (cmd[1]){
                            case "desc":
                                taskList.set(Integer.parseInt(cmd[2])-1,
                                        new Task(  cmd[3],
                                        Task.ora(taskList.get(Integer.parseInt(cmd[2])-1).getDataOra()),
                                        Task.giorno(taskList.get(Integer.parseInt(cmd[2])-1).getDataOra()),
                                        Task.mese(taskList.get(Integer.parseInt(cmd[2])-1).getDataOra()),
                                        Task.anno(taskList.get(Integer.parseInt(cmd[2])-1).getDataOra()),
                                        taskList.get(Integer.parseInt(cmd[2])-1).getPeriodicita()));
                                break;
                            case "date":
                                taskList.set(Integer.parseInt(cmd[2])-1,
                                        new Task( taskList.get(Integer.parseInt(cmd[2])-1).getDescrizione(),
                                        Task.ora(cmd[3]),
                                        Task.giorno(cmd[3]),
                                        Task.mese(cmd[3]),
                                        Task.anno(cmd[3]),
                                        taskList.get(Integer.parseInt(cmd[2])-1).getPeriodicita()));
                                break;
                            case "per":
                                int a = 0;
                                taskList.set(Integer.parseInt(cmd[2])-1,
                                        new Task( taskList.get(Integer.parseInt(cmd[2])-1).getDescrizione(),
                                                Task.ora(taskList.get(Integer.parseInt(cmd[2])-1).getDataOra()),
                                                Task.giorno(taskList.get(Integer.parseInt(cmd[2])-1).getDataOra()),
                                                Task.mese(taskList.get(Integer.parseInt(cmd[2])-1).getDataOra()),
                                                Task.anno(taskList.get(Integer.parseInt(cmd[2])-1).getDataOra()),
                                                cmd[3]));
                                break;
                            default:
                                System.out.println("\nInvalid command or params...\nType help for more.");
                    }
                    break;
                case "complete":
                    Task t = taskList.get(Integer.parseInt(cmd[1])-1);
                    t.completata();
                    taskList.set(Integer.parseInt(cmd[1])-1, t);
                    System.out.println("Completed Task N: " + cmd[1]);
                    break;
                case "help":
                    System.out.println("INFO COMMANDS:\n"
                            + "help\t\t" +        "Info comandi.\n"
                            + "show\t\t" +        "Stampa la lista di tutte le task salvate.\n"
                            + "mktask\t\t" +      "Crea task >NON INSERIRE SPAZI< es singola:(mktask s desc hh/gg/mm/aaaa) periodica:(mktask desc hh/gg/mm/aaaa ph/pg/pm/pa).\n"
                            + "edit\t\t" +        "Modifica task N (show) parametri [desc: descrizione][date: dataOra hh/gg/mm/aaa][per: periodicitá hh/gg/mm/aaa]\n"
                            + "complete\t" +      "Completare/eseguire la task N (show)\n"
                            + "remove\t\t" +     "Elimina la task N (show)\n");
                    break;
                case "remove":
                    taskList.remove(Integer.parseInt(cmd[1])-1);
                    System.out.println("Deleted Task N: " + cmd[1]);
                    break;
                case "":
                    break;
                default:
                    System.out.println("\nInvalid command or params...\nType help for more.");
                

            }
        }catch(Exception e){
            System.err.println(e + "\nInvalid command or params...\nType help for more.");
        }
        
    }


    public String toString(){                                                   //Esegue il toString delle tasks suddividendo gli attributi
        StringBuilder s = new StringBuilder();                                  //Viene usato nel comando "show"
        int i = 1;
        s.append(" N       TITOLO\t\t\t\tDATA\t\t\tPERIODICITÀ\t\tCOMPLETATA\n");
        for (Task task : taskList){
            s.append(((i < 10) ? " " + i +") " : i +") ") + task.toString() + '\n');
            i++;
        }
        return s.toString();
    }
    public String toStringGUI(){                                                   //Esegue il toString delle tasks suddividendo gli attributi
        StringBuilder s = new StringBuilder();                                  //Viene usato nel comando "show"
        int i = 1;
        s.append(" N        TITOLO\t\t\t\tDATA\t\tPERIODICITÀ\t\t\tCOMPLETATA\n");
        for (Task task : taskList){
            s.append(((i < 10) ? " " + i +") " : i +") ") + task.toStringGUI() + '\n');
            i++;
        }
        return s.toString();
    }
    
}
