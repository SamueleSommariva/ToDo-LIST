package mainclasses;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.String;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Task {

    String descrizione;
    String dataOra;         //      hh/gg/mm/aaaa
    boolean completata;
    String periodicita;     //      hh/gg/mm/aaaa

    public Task(String descrizione, int h, int g, int m, int a, String periodicita){
        this.descrizione = descrizione;
        if (h <= 1){                //Limita il range di ore da 1 a 24
            h = 1;
        }else if (h > 24) {
            h = 24;
        }

        if (g <= 1){                //Limita il range di giorni da 1 a 31
            g = 1;                  //***Non è presente ancora il controllo per i mesi con meno giorni***
        }else if (g > 31) {
            g = 31;
        }

        if (m <= 1){                //Limita il range di mesi da 1 a 12
            m = 1;
        }else if (m > 12) {
            m = 12;
        }

        Date d = new Date();        //Limita il range di anni dall'anno corrente al 2050
        int dc = d.getYear()+1900;
        if (a <= dc){
            a = dc;
        }else if (a > 2050) {
            a = 2050;
        }

        this.dataOra =  Integer.toString(h) + '/' +     //Crea la stringa con la formattazione hh/gg/mm/aaaa
                Integer.toString(g) + '/' +
                Integer.toString(m) + '/' +
                Integer.toString(a);
        this.periodicita = periodicita;
        this.completata = false;
    }


    public static List<Task> getTasks() throws IOException {                    //Crea una lista di task dal file Task.csv
        String fileContent = Files.readString(Path.of("Task.csv"));         //Mette tutto il contenuto del file in fileContent
        String[] fileLines = fileContent.split("\n");                      //Suddivide la stringa per righe
        String[] fileCamps = fileContent.split(",|\n");                    //Suddivide la stringa in campi
        List<Task> taskList = new ArrayList<Task>();

        for (int l = 1, c = 1; l <= fileLines.length-1; l ++, c += 4){          //Crea una sorta di "puntatore" per la riga e per il campo
            taskList.add(new Task(fileCamps[c],                                 //inizalizza la task con i campi puntati da c, c+1 e c+2 della linea l
                    Task.ora(fileCamps[c+1]),                                   //aggiunge la task alla TaskList
                    Task.giorno(fileCamps[c+1]),
                    Task.mese(fileCamps[c+1]),
                    Task.anno(fileCamps[c+1]),
                    fileCamps[c+2]));
        }

        return taskList;
    }
    
    
    

    public static int ora(String dataOra){
        StringBuilder oraS = new StringBuilder();           //Ottiene l'ora dalla stringa dataOra
        int acc = 0;                                        //***Implementarlo con lo split***
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
        StringBuilder giornoS = new StringBuilder();        //Ottiene il giorno dalla stringa dataOra
        int acc = 0;                                        //***Implementarlo con lo split***
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
        StringBuilder meseS = new StringBuilder();          //Ottiene il mese dalla stringa dataOra
        int acc = 0;                                        //***Implementarlo con lo split***
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
        StringBuilder annoS = new StringBuilder();          //Ottiene l'anno dalla stringa dataOra
        int acc = 0;                                        //***Implementarlo con lo split***
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

    
    public void completata() {
        if (this.periodicita == "00/00/00/0000") {                          //Se la periodicità è nulla ("00/00/00/0000") imposta completata a true
            this.completata = true;
        }else{
            int ora = ora(this.periodicita) + ora(this.dataOra);            //Altrimenti reimposta la data di scadenza a dataOra + periodicita
            int giorno = giorno(this.periodicita) + giorno(this.dataOra);
            int mese = mese(this.periodicita) + mese(this.dataOra);
            int anno = anno(this.periodicita) + anno(this.dataOra);
            int guardia = 0;
            while (guardia != 3){                                           //Facendo gli stessi controlli fatti nel costruttore
                guardia = 0;                                                //Ma effettuando una sorta di addizione in colonna
                if (ora > 24){                                              //Esempio     23 ore 5 periodicità 23+5=28 = 1 giorno e 4 ore
                    ora -= 24;
                    giorno++;
                }else{
                    guardia++;
                }
                if (giorno > 31){
                    giorno -= 31;
                    mese++;
                }else{
                    guardia++;
                }
                if (mese > 12){
                    mese -= 12;
                    anno++;
                }else{
                    guardia++;
                }
            }
            StringBuilder data = new StringBuilder(ora + "/" + giorno + "/" + mese + "/" + anno);       //Crea la nuova data con il solito formato
            this.dataOra = data.toString();
        }
    }

    public void salvaTask() throws IOException{                 //Fa l'append degli attributi della stringa dividendola in campi
        if (!getCompletata()){                                  //e scrivendola sul file
            try{                                                //***BUG se l'Utente inserisce nella descrizione il carattere ','***
                FileWriter file = new FileWriter("Task.csv", true);
                file.write("\n" + this.descrizione + "," + this.dataOra + "," + this.periodicita + "," + this.completata);
                file.close();
            
            } catch (FileNotFoundException e) {
                System.err.println("FileNotFoundException");
            }
        }
    }

    // get e set

    public String getPeriodicita() {
        return periodicita;
    }

    public void setPeriodicita(String periodicita) {
        this.periodicita = periodicita;
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
    public String toString() {                                                              //toString con la formattazione dell'indentazione
        return  descrizione + (descrizione.length() < 4 ? "\t\t\t\t\t\t\t\t" :
                (descrizione.length() < 8 ?"\t\t\t\t\t\t\t" :
                    (descrizione.length() < 12 ? "\t\t\t\t\t\t" :
                        (descrizione.length() < 16 ? "\t\t\t\t\t" :
                            (descrizione.length() < 20 ? "\t\t\t\t" :
                                (descrizione.length() < 24 ? "\t\t\t" :
                                    (descrizione.length() < 28 ? "\t\t" :
                                        (descrizione.length() < 32 ? "\t" : "\t")))))))) +
                dataOra + "\t" +
                (periodicita == "00/00/00/0000" ? "NO" : periodicita) + "\t\t\t\t" +
                completata;
    }
}