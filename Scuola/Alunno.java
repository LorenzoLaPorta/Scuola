
import java.util.ArrayList;

public class Alunno{
    //ATTRIBUTI
    String nome;
    String citta;
    String mail;
    //---------
    Alunno(String nome, String citta, String mail){
        this.nome = nome;
        this.citta = citta;
        this.mail = mail;
    }

    static int indiceClasseAlunno; //l'indice della classe nel quale si trova un alunno

    /**
     * Override del metodo toString per stampare l'oggetto in un formato specifico
     * 
     * @param void
     * @return La stringa con il formato voluto
     */
    @Override
    public String toString(){
        return "[Nome: " + this.nome + " | Citta': " + this.citta + " | Email: " + this.mail + "]";
    }

    /**
     * funzione che permette la ricerca di un alunno
     * 
     * @param void
     * @return l'indice dell'alunno (-1 se non e' stato trovato)
     */
    public static int cercaAlunno(){
        indiceClasseAlunno = Classe.cercaClasse();
        if (indiceClasseAlunno != -1){
            String nomeAlunno = Classe.inputString("Inserisci il nome dell'alunno");
            Object oggettoCorrente;
            Classe oggettoClasse = Classe.scuola.get(indiceClasseAlunno);
            if (oggettoClasse.classe != null){
                for (int i = 0; i < oggettoClasse.classe.size(); i++) {
                    oggettoCorrente = oggettoClasse.classe.get(i);
                    if (oggettoCorrente instanceof Alunno alunno){ //verifica se l'oggetto corrente e' di tipo Alunno e lo assegna a una nuova variabile
                        if (nomeAlunno.equals(alunno.nome)){
                            return i;
                        }
                    }
                }
            }
        }
        return -1;
    }

    /**
     * Funzione che elimina uno studente da una specifica classe
     * 
     * @param void
     * @return true o false se la rimozione e' avvenuta correttamente o meno
     */
    public static boolean rimuoviAlunno(){
        int indice = cercaAlunno();
        if (indice != -1){
            Classe oggettoClasse = Classe.scuola.get(indiceClasseAlunno);
            oggettoClasse.classe.remove(indice);
            return true;
        }
        return false;
    }

    /**
     * Funzione che permette la modifica di un alunno
     * 
     * @param void
     * @return true o false se la modifica e' avvenuta correttamente o meno
     */
    public static boolean modificaAlunno(){
        int indice = cercaAlunno();
        if (indice != -1){
            Classe classeCorrente = Classe.scuola.get(indiceClasseAlunno);
            Object oggettoCorrente = classeCorrente.classe.get(indice);
            if (oggettoCorrente instanceof Alunno alunnoDaModificare){ //verifica se l'oggetto corrente e' di tipo Alunno e lo assegna a una nuova variabile
                String input;
                input = Classe.inputString("Inserisci il nuovo nome dell'alunno (premi invio se non lo vuoi modificare)");
                if (!input.equals("")){
                    alunnoDaModificare.nome = input;
                }
                input = Classe.inputString("Inserisci la citta' dell'alunno (premi invio se non la vuoi modificare)");
                if (!input.equals("")){
                    alunnoDaModificare.citta = input;
                }
                input = Classe.inputString("Inserisci l'indirizzo email dell'alunno (premi invio se non lo vuoi modificare)");
                if (!input.equals("")){
                    alunnoDaModificare.mail = input;
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Funzione che inserisce un alunno all'interno di una classe specifica
     * 
     * @param alunno l'oggetto dell'alunno
     * @return true o false se l'inserimento e' avvenuto correttamente o meno
     */
    public static boolean inserisciAlunno(Alunno alunno){
        int indice = Classe.cercaClasse();
        if (indice != -1){
            Classe oggettoClasse = Classe.scuola.get(indice);
            if (oggettoClasse.classe == null) { //se l'arrayList non e' inizializzato lo inizializza
                oggettoClasse.classe = new ArrayList<>();
            }
            if (oggettoClasse.classe.size() < Classe.MAX_POSTI){
                alunno.nome = Classe.inputString("Inserisci il nome dell'alunno");
                alunno.citta = Classe.inputString("Inserisci la citta' dove risiede l'alunno");
                alunno.mail = Classe.inputString("Inserisci l'indirizzo email dell'alunno");
                oggettoClasse.classe.add(alunno);
                return true;
            }
        }
        return false;
    }

    /**
     * funzione che stampa tutti gli alunni all'interno di una classe
     * 
     * @param void
     * @return true o false se la ricerca e' avvenuta correttamente o meno
     */
    public static boolean stampaAlunni(){
        int indice = Classe.cercaClasse();
        if (indice != -1){
            Classe oggettoClasse = Classe.scuola.get(indice);
            System.out.println("Alunni nella classe " + oggettoClasse.nome + " (" + oggettoClasse.aula + "):");
            boolean vuota = true;
            if (oggettoClasse.classe != null){
                for (int i = 0; i < oggettoClasse.classe.size(); i++) {
                    int j = 0;
                    if (oggettoClasse.classe.get(i) instanceof Alunno alunnoDaStampare){ //verifica se l'oggetto corrente e' di tipo Alunno e lo assegna a una nuova variabile
                        j++;
                        System.out.println(j + ") " + alunnoDaStampare);
                        vuota = false;
                    }
                }
            }
            if (vuota){ System.out.println("LA CLASSE NON HA ALUNNI"); }
            return true;
        }
        return false;
    }

}