
import java.util.ArrayList;

public class Docente{
    //ATTRIBUTI
    String nome;
    String materia;
    String mail;
    //---------
    Docente(String nome, String materia, String mail){
        this.nome = nome;
        this.materia = materia;
        this.mail = mail;
    }

    static int indiceClasseDocente; //l'indice della classe nel quale si trova un docente

    /**
     * Override del metodo toString per stampare l'oggetto in un formato specifico
     * 
     * @param void
     * @return La stringa con il formato voluto
     */
    @Override
    public String toString(){
        return "[Nome: " + this.nome + " | Materia: " + this.materia + " | Email: " + this.mail + "]";
    }

    /**
     * funzione che permette la ricerca di un docente
     * 
     * @param void
     * @return l'indice del docente (-1 se non e' stato trovato)
     */
    public static int cercaDocente(){
        int indice = Classe.cercaClasse();
        if (indice != -1){
            String nomeDocente = Classe.inputString("Inserisci il nome del docente");
            Object oggettoCorrente;
            Classe oggettoClasse = Classe.scuola.get(indice);
            if (oggettoClasse.classe != null){
                for (int i = 0; i < oggettoClasse.classe.size(); i++) {
                    oggettoCorrente = oggettoClasse.classe.get(i);
                    if (oggettoCorrente instanceof Docente docente){ //verifica se l'oggetto corrente e' di tipo Docente e lo assegna a una nuova variabile
                        if (nomeDocente.equals(docente.nome)){
                            return i;
                        }
                    }
                }
            }
        }
        return -1;
    }

    /**
     * Funzione che elimina un docente da una specifica classe
     * 
     * @param void
     * @return true o false se la rimozione e' avvenuta correttamente o meno
     */
    public static boolean rimuoviDocente(){
        int indice = cercaDocente();
        if (indice != -1){
            Classe oggettoClasse = Classe.scuola.get(indiceClasseDocente);
            oggettoClasse.classe.remove(indice);
            return true;
        }
        return false;
    }

    /**
     * Funzione che permette la modifica di un docente
     * 
     * @param void
     * @return true o false se la modifica e' avvenuta correttamente o meno
     */
    public static boolean modificaDocente(){
        int indice = cercaDocente();
        if (indice != -1){
            Classe classeCorrente = Classe.scuola.get(indiceClasseDocente);
            Object oggettoCorrente = classeCorrente.classe.get(indice);
            if (oggettoCorrente instanceof Docente docenteDaModificare){ //verifica se l'oggetto corrente e' di tipo Docente e lo assegna a una nuova variabile
                String input;
                input = Classe.inputString("Inserisci il nuovo nome del docente (premi invio se non lo vuoi modificare)");
                if (!input.equals("")){
                    docenteDaModificare.nome = input;
                }
                input = Classe.inputString("Inserisci la materia del docente (premi invio se non la vuoi modificare)");
                if (!input.equals("")){
                    docenteDaModificare.materia = input;
                }
                input = Classe.inputString("Inserisci l'indirizzo email del docente (premi invio se non lo vuoi modificare)");
                if (!input.equals("")){
                    docenteDaModificare.mail = input;
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Funzione che inserisce un docente all'interno di una classe specifica
     * 
     * @param docente l'oggetto del docente
     * @return true o false se l'inserimento e' avvenuto correttamente o meno
     */
    public static boolean inserisciDocente(Docente docente){
        int indice = Classe.cercaClasse();
        if (indice != -1){
            Classe oggettoClasse = Classe.scuola.get(indice);
            if (oggettoClasse.classe == null) {
                oggettoClasse.classe = new ArrayList<>(); //se l'arrayList non e' inizializzato lo inizializza
            }
            if (oggettoClasse.classe.size() < Classe.MAX_POSTI){
                docente.nome = Classe.inputString("Inserisci il nome del docente");
                docente.materia = Classe.inputString("Inserisci la materia del docente");
                docente.mail = Classe.inputString("Inserisci l'indirizzo email del docente");
                oggettoClasse.classe.add(docente);
                return true;
            }
        }
        return false;
    }

    /**
     * funzione che stampa tutti i docenti all'interno di una classe
     * 
     * @param void
     * @return true o false se la ricerca e' avvenuta correttamente o meno
     */
    public static boolean stampaDocenti(){
        int indice = Classe.cercaClasse();
        if (indice != -1){
            Classe oggettoClasse = Classe.scuola.get(indice);
            System.out.println("Docenti nella classe " + oggettoClasse.nome + "(" + oggettoClasse.aula + "):");
            boolean vuota = true;
            if (oggettoClasse.classe != null){
                for (int i = 0; i < oggettoClasse.classe.size(); i++) {
                    int j = 0;
                    if (oggettoClasse.classe.get(i) instanceof Docente docenteDaStampare){ //verifica se l'oggetto corrente e' di tipo Docente e lo assegna a una nuova variabile
                        j++;
                        System.out.println(j + ") " + docenteDaStampare);
                        vuota = false;
                    }
                }
            }
            if (vuota){ System.out.println("LA CLASSE NON HA DOCENTI"); }
            return true;
        }
        return false;
    }
}