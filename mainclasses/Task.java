package mainclasses;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.String;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;

public class Task {

    String descrizione;
    String dataOra;         //      hh/gg/mm/aaaa
    boolean completata;

    public Task(String descrizione, int h, int g, int m, int a){
        this.descrizione = descrizione;
        if (h <= 1){
            h = 1;
        }else if (h > 24) {
            h = 24;
        }

        if (g <= 1){
            g = 1;
        }else if (g > 31) {
            g = 31;
        }

        if (m <= 1){
            m = 1;
        }else if (m > 12) {
            m = 12;
        }

        Date d = new Date();
        int dc = d.getYear()+1900;
        if (a <= dc){
            a = dc;
        }else if (a > 2050) {
            a = 2050;
        }

        this.dataOra =  Integer.toString(h) + '/' +
                Integer.toString(g) + '/' +
                Integer.toString(m) + '/' +
                Integer.toString(a);
        this.completata = false;
    }
    
    public Task() throws IOException{
        
        String fileContent = Files.readString(Path.of("Task.csv"));
        char[] c = fileContent.toCharArray();

        String[] params = new String[3];
        StringBuilder param = new StringBuilder();
        
        try{
            for(int i = fileContent.length()-1, cont = 0; c[i] != '\n'; i--){
                if (c[i] == ','){
                    param.reverse();
                    params[cont] = param.toString();
                    param.delete(0, param.length());
                    cont++;
                }else{
                    param.append(c[i]);
                }
            }
            param.reverse();
            params[2] = param.toString();

            this.descrizione = params[2];
            this.dataOra = params[1];
            this.completata = false;
        
        
        
            StringBuilder Tfile = new StringBuilder();
            int i = fileContent.length()-1;
            for (; c[i] != '\n'; i--){}

            Tfile.append(fileContent, 0, i);
            
        
            FileWriter file = new FileWriter("Task.csv", false);
            file.write(Tfile.toString());
            file.close();
            
        } catch (FileNotFoundException e) {
            System.err.println("FileNotFound");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Non ci sono piu Task salvate");
        }
    }
    
    
    

    public static int ora(String dataOra){
        StringBuilder oraS = new StringBuilder();
        int acc = 0;
        for (char c : dataOra.toCharArray()){
            if (c == '/'){
                acc++;
            }
            if (acc == 0){
                oraS.append(c);
            }
            if (c == '/'){
                acc++;
            }
        }
        int ora = Integer.parseInt(oraS.toString());
        return ora;
    }


    public static int giorno(String dataOra){
        StringBuilder giornoS = new StringBuilder();
        int acc = 0;
        boolean guardia = false;
        for (char c : dataOra.toCharArray()){
            if (c == '/'){
                acc++;
            }
            if (acc == 1 && guardia){
                giornoS.append(c);
            }else if(acc == 1){
                guardia = true;
            }

        }
        int giorno = new Integer(giornoS.toString());
        return giorno;
    }

    public static int mese(String dataOra){
        StringBuilder meseS = new StringBuilder();
        int acc = 0;
        boolean guardia = false;
        for (char c : dataOra.toCharArray()){
            if (c == '/'){
                acc++;
            }
            if (acc == 2 && guardia){
                meseS.append(c);
            }else if(acc == 2){
                guardia = true;
            }

        }
        int mese = new Integer(meseS.toString());
        return mese;
    }


    public static int anno(String dataOra){
        StringBuilder annoS = new StringBuilder();
        int acc = 0;
        boolean guardia = false;
        for (char c : dataOra.toCharArray()){
            if (c == '/'){
                acc++;
            }
            if (acc == 3 && guardia){
                annoS.append(c);
            }else if(acc == 3){
                guardia = true;
            }

        }
        int anno = new Integer(annoS.toString());
        return anno;
    }
    public static boolean altreTask() throws IOException{
        String fileContent = Files.readString(Path.of("Task.csv"));
        return fileContent.length() > 1;
    }

    
    public void completata() {
        this.completata = true;
    }

    public void salvaTask() throws IOException{
        if (!getCompletata()){
            try{
                FileWriter file = new FileWriter("Task.csv", true);
                file.write("\n" + this.descrizione + "," + this.dataOra + "," + this.completata);
                file.close();
            
            } catch (FileNotFoundException e) {
                System.err.println("FileNotFoundException");
            }
        }
    }

    
    public String getDescrizione() {
        return descrizione;
    }
    public String getDataOra() {
        return dataOra;
    }
    public boolean getCompletata() {
        return completata;
    }
    public void setDataOra(String dataOra) {
        this.dataOra = dataOra;
    }
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    @Override
    public String toString() {
        return  descrizione + "\t\t" +
                dataOra + "\t" +
                completata;
    }
}