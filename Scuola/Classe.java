
import java.util.ArrayList;
import java.util.Scanner;

public class Classe{
    //ATTRIBUTI
    String nome;
    String aula;
    public ArrayList<Object> classe = new ArrayList<>(); //arrayList della singola classe contenente alunni e docenti
    //---------
    Classe(String nome, String aula, ArrayList<Object> classe){
        this.nome = nome;
        this.aula = aula;
        this.classe = classe;
    }

    public static ArrayList<Classe> scuola = new ArrayList<>(); //arrayList della scuola contenente ogni classe
    public static final int MAX_POSTI = 10; //costante che indica il numero massimo di posti in ogni classe
    static Scanner scanner = new Scanner(System.in); //oggetto scanner per prendere input

    /**
     * Funzione che prende in input una stringa
     * 
     * @param messaggio il messaggio che deve essere stampato prima di prendere dati in input
     * @return L'input inserito
     */
    public static String inputString(String messaggio){
        System.out.println(messaggio);
        return scanner.nextLine();
    }

    /**
     * funzione che permette la ricerca di una classe
     * 
     * @param void
     * @return l'indice della classe (-1 se non e' stata trovata)
     */
    public static int cercaClasse(){
        String nomeClasse = inputString("Inserisci il nome della classe");
        for (int i = 0; i < scuola.size(); i++) {
            if (nomeClasse.equals(scuola.get(i).nome)){
                return i;
            }
        }
        return -1;
    }

    /**
     * Funzione che permette la modifica di una classe
     * 
     * @param void
     * @return true o false se la modifica e' avvenuta correttamente o meno
     */
    public static boolean modificaClasse(){
        int indice = cercaClasse();
        if (indice != -1){
            Classe classeDaModificare = scuola.get(indice);
            String input;
            input = inputString("Inserisci il nuovo nome della classe (premi invio se non lo vuoi modificare)");
            if (!input.equals("")){
                classeDaModificare.nome = input;
            }
            input = inputString("Inserisci la nuova aula della classe (premi invio se non lo vuoi modificare)");
            if (!input.equals("")){
                classeDaModificare.aula = input;
            }
            return true;
        }
        return false;
    }

    /**
     * Funzione che permette l'inserimento di nuove classi nella scuola
     * 
     * @param classe l'oggetto "classe"
     * @return void
     */
    public static void inserisciClasse(Classe classe){
        classe.nome = inputString("Inserisci il nome della classe");
        classe.aula = inputString("Inserisci il nome dell'aula");
        scuola.add(classe);
    }

    public static void menuPrincipale(){
        System.out.println("Scegli una delle seguenti opzioni:");
        System.out.println("0) esci");
        System.out.println("1) inserisci (classe, alunno, docente)");
        System.out.println("2) rimuovi (alunno, docente)");
        System.out.println("3) modifica (classe, alunno, docente)");
        System.out.println("4) visualizza (alunni in una classe, docenti in una classe)");
    }

    public static void menuInserimento(){
        System.out.println("Scegli una delle seguenti opzioni:");
        System.out.println("0) esci");
        System.out.println("1) inserisci una classe");
        System.out.println("2) inserisci un alunno");
        System.out.println("3) inserisci un docente");
    }

    public static void menuRimozione(){
        System.out.println("Scegli una delle seguenti opzioni:");
        System.out.println("0) esci");
        System.out.println("1) rimuovi un alunno");
        System.out.println("2) rimuovi un docente");
    }

    public static void menuModifica(){
        System.out.println("Scegli una delle seguenti opzioni:");
        System.out.println("0) esci");
        System.out.println("1) modifica una classe");
        System.out.println("2) modifica un alunno");
        System.out.println("3) modifica un docente");
    }

    public static void menuStampa(){
        System.out.println("Scegli una delle seguenti opzioni:");
        System.out.println("0) esci");
        System.out.println("1) visualizza alunni in una classe");
        System.out.println("2) visualizza docenti in una classe");
    }
    
    public static void main(String[] args) {
        String sceltaMenu;
        String sceltaSottoMenu;
        do {
            menuPrincipale();
            sceltaMenu = scanner.nextLine();
            switch (sceltaMenu) {
                case "0" -> {
                }
                case "1" -> {
                    Classe classe = new Classe(null, null, null);
                    Alunno alunno = new Alunno(null, null, null);
                    Docente docente = new Docente(null, null, null);
                    menuInserimento();
                    sceltaSottoMenu = scanner.nextLine();
                    switch (sceltaSottoMenu) {
                        case "0" -> {
                        }
                        case "1" -> {
                            inserisciClasse(classe);
                            System.out.println("Inserimento avvenuto correttamente");
                        }
                        case "2" -> {
                            if (Alunno.inserisciAlunno(alunno)){
                                System.out.println("Inserimento avvenuto correttamente");
                            }
                            else{
                                System.out.println("Ops, qualcosa e' andato storto. Riprova");
                            }
                        }
                        case "3" -> {
                            if (Docente.inserisciDocente(docente)){
                                System.out.println("Inserimento avvenuto correttamente");
                            }
                            else{
                                System.out.println("Ops, qualcosa e' andato storto. Riprova");
                            }
                        }
                        default -> System.out.println("L'inserimento e' errato, riprova.");
                    }
                }
                case "2" -> {
                    menuRimozione();
                    sceltaSottoMenu = scanner.nextLine();
                    switch (sceltaSottoMenu) {
                        case "0" -> {
                        }
                        case "1" -> {
                            if (Alunno.rimuoviAlunno()){
                                System.out.println("Alunno rimosso con successo");
                            }
                            else{
                                System.out.println("Ops, qualcosa e' andato storto. Riprova");
                            }
                        }
                        case "2" -> {
                            if (Docente.rimuoviDocente()){
                                System.out.println("Docente rimosso con successo");
                            }
                            else{
                                System.out.println("Ops, qualcosa e' andato storto. Riprova");
                            }
                        }
                        default -> System.out.println("L'inserimento e' errato, riprova.");
                    }
                }
                case "3" -> {
                    menuModifica();
                    sceltaSottoMenu = scanner.nextLine();
                    switch (sceltaSottoMenu) {
                        case "0" -> {
                        }
                        case "1" -> {
                            if (modificaClasse()){
                                System.out.println("Modifica avvenuta correttamente");
                            }
                            else{
                                System.out.println("Ops, qualcosa e' andato storto. Riprova");
                            }
                        }
                        case "2" -> {
                            if (Alunno.modificaAlunno()){
                                System.out.println("Modifica avvenuta correttamente");
                            }
                            else{
                                System.out.println("Ops, qualcosa e' andato storto. Riprova");
                            }
                        }
                        case "3" -> {
                            if (Docente.modificaDocente()){
                                System.out.println("Modifica avvenuta correttamente");
                            }
                            else{
                                System.out.println("Ops, qualcosa e' andato storto. Riprova");
                            }
                        }
                        default -> System.out.println("L'inserimento e' errato, riprova.");
                    }
                }
                case "4" -> {
                    menuStampa();
                    sceltaSottoMenu = scanner.nextLine();
                    switch (sceltaSottoMenu) {
                        case "0" -> {
                        }
                        case "1" -> {
                            if (Alunno.stampaAlunni() == false){
                                System.out.println("Hai inserito una classe che non esiste. Riprova");
                            }
                        }
                        case "2" -> {
                            if (Docente.stampaDocenti() == false){
                                System.out.println("Hai inserito una classe che non esiste. Riprova");
                            }
                        }
                        default -> System.out.println("L'inserimento e' errato, riprova.");
                    }
                }
                default -> System.out.println("L'inserimento e' errato, riprova.");
            }
        } while (!sceltaMenu.equals("0"));
    }  
}