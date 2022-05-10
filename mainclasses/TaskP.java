package mainclasses;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TaskP extends Task{

    double periodicita;

    public TaskP(String descrizione, int h, int g, int m, int a, double periodicita) {
        super(descrizione, h, g, m, a);
        this.periodicita = periodicita;
    }

    public static List<Task> getTasks() throws IOException {
        String fileContent = Files.readString(Path.of("TaskP.csv"));
        String[] fileLines = fileContent.split("\n");
        String[] fileCamps = fileContent.split(",|\n");
        List<Task> taskList = new ArrayList<Task>();

        for (int l = 1, c = 1; l <= fileLines.length-1; l ++, c += 3){
            taskList.add(new TaskP(fileCamps[c], Task.ora(fileCamps[c+1]), Task.giorno(fileCamps[c+1]), Task.mese(fileCamps[c+1]), Task.anno(fileCamps[c+1]), Double.parseDouble(fileCamps[c+2])));
        }

        return taskList;
    }

    @Override
    public void salvaTask() throws IOException {
        if (!getCompletata()){
            try{
                FileWriter file = new FileWriter("TaskP.csv", true);
                file.write("\n" + this.descrizione + "," + this.dataOra + "," + this.periodicita + "," + this.completata);
                file.close();

            } catch (FileNotFoundException e) {
                System.err.println("FileNotFoundException");
            }
        }
    }
    public String toString() {
        return  descrizione + (descrizione.length() < 4 ? "\t\t\t\t\t\t\t\t\t" :
                (descrizione.length() < 8 ?"\t\t\t\t\t\t\t\t" :
                        (descrizione.length() < 12 ? "\t\t\t\t\t\t\t" :
                                (descrizione.length() < 16 ? "\t\t\t\t\t\t" :
                                        (descrizione.length() < 20 ? "\t\t\t\t\t" :
                                                (descrizione.length() < 24 ? "\t\t\t\t" :
                                                        (descrizione.length() < 28 ? "\t\t\t" :
                                                                (descrizione.length() < 32 ? "\t\t" : "\t")))))))) +
                dataOra + "\t" +
                periodicita + "\t"+
                completata;
    }

}
