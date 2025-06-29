/* Questo dice al progetto dove si trova questa classe nel tuo computer.
Serve a Spring per trovare la roba nel posto giusto (una specie di etichetta GPS). */
package com.bestofyear.best_of_the_year;


/*IMPORTIAMO alcune CLASSI dal framework Spring. Sono come strumenti speciali di Spring per creare la pagina web
 



 Importa l'annotazione @Controller → dice che questa classe gestisce richieste web*/
import org.springframework.stereotype.Controller;
//Importa @RequestMapping → permette di definire un "prefisso" per tutte le rotte della classe
import org.springframework.web.bind.annotation.RequestMapping;
//Importa @RequestMapping → permette di definire un "prefisso" per tutte le rotte della classe
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//Importa Model → un oggetto che ci permette di passare dati (tipo variabili) dal controller alla pagina HTML
import org.springframework.ui.Model;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;



//L'annotazione @Controller dice a Spring che questa classe gestirà delle URL, ovvero gestisce le richieste che arrivano dal browser
@Controller
//L'annotazione @RequestMapping dice che tutte le rotte di questa classe iniziano da "/"
@RequestMapping("/")
public class MainController {

    /*Questo metodo gestisce una richiesta GET alla root "/" del sito.
    Quindi se apri il browser su http://localhost:8080/ esegue questo metodo */
    @GetMapping("/")
    public String home(Model model){

        /* Qui stiamo dicendo:
        "Aggiungi al Model una variabile chiamata 'name' con valore 'Bruno'"
        Questa variabile verrà usata nell'HTML (Thymeleaf) con ${name} */
        model.addAttribute("name", "PEPPE");

        /*Restituiamo la "view" chiamata index.html (che si trova nella cartella templates)
        Spring lo interpreterà come: "carica il file templates/index.html"*/
        return "index";

    }

    public List<Movie> getBestMovies(){
        List<Movie> bestMovies = new ArrayList<>();
        Movie matrix = new Movie(1, "The Matrix");
        bestMovies.add(matrix);
        Movie inception = new Movie(2, "Inception");
        bestMovies.add(inception);
        Movie titanic = new Movie(3, "Titanic");
        bestMovies.add(titanic);

        return bestMovies;

    }

    public List<Song> getBestSongs(){
        List<Song> bestSongs = new ArrayList<>();
        Song acquaAzzurra = new Song(1, "Acqua azzurra acqua chiara");
        bestSongs.add(acquaAzzurra);
        Song telefonando = new Song(2, "Se telefonando");
        bestSongs.add(telefonando);
        Song bellissima = new Song(3, "Bellissima");
        bestSongs.add(bellissima);

        return bestSongs;
    }
    
    /* Questo metodo costruisce la lista dei titoli dei film
    e la passa alla view index.html */
    public String titoliFilm(Model model) {

        /*  getBestMovies() restituisce una List<Movie>,
        su cui chiamiamo .stream() per lavorare "flusso per flusso" sugli elementi.
        */
        List<Movie> bestMovies = getBestMovies();

        /*  
        * bestMovies.stream():
        - trasforma la lista in una sequenza di oggetti Movie
        .map(Movie::getTitle):
        - per ogni Movie, estrae il titolo (String)
        - Movie::getTitle è la "method reference":
        un modo compatto per dire “x -> x.getTitle()”
        .collect(Collectors.joining(",")):
        - raccoglie (collect) tutti i titoli in una Singola Stringa
        - li “join-a” inserendo una virgola tra un titolo e l’altro
        */
        String titoli = bestMovies.stream()
                        .map(Movie::getTitle)
                        .collect(Collectors.joining(","));

        /*Aggiungiamo al Model:
        - nome dell'attributo: "movieItems"
        - valore: la stringa dei titoli uniti dalle virgole
        Thymeleaf potrà accedervi come ${movieItems}
        */
        model.addAttribute("movieItems", titoli);

        /* Ritorno "index":  
        indica a Spring di caricare index.html dalla cartella templates,
        dove lo user visualizzerà i dati passati.
        */
        return "index";
    }

    
    /* Questo metodo fa esattamente la stessa cosa, ma usando la lista di canzoni */
    public String titoliCanzoni(Model model) {

        /*getBestSongs() ritorna una List<Song>;
        .stream(), .map(), .collect() funzionano allo stesso modo
        */
        List<Song> bestSongs = getBestSongs();

        String titoli = bestSongs.stream()
                        .map(Song::getTitle)
                        .collect(Collectors.joining(","));

        /*Qui usiamo un nome diverso ("songItems"), così Thymeleaf
        potrà mostrarle con ${songItems}
        */
        model.addAttribute("songItems", titoli);

        return "index";  // visualizza di nuovo index.html
    }


    /*Metodo che risponde a GET /movies/{id} (es. /movies/2)
    Qui usiamo @PathVariable per leggere il valore dal path
    */
    @GetMapping("/movies/{id}")
    public String movieById(@PathVariable int id, Model model) {

        /*getBestMovies().stream():
        - creiamo lo stream di Movie
        .filter(m -> m.getId() == id):
        - manteniamo solo i Movie il cui id corrisponde a quello richiesto
        - "m -> m.getId() == id" è un'espressione lambda: m è ogni Movie
        .findFirst():
        - prendi il primo elemento che soddisfa il filtro (ce n'è al massimo uno)
        .orElse(null):
        - se non lo troviamo, ritorniamo null
        */
        Movie movie = getBestMovies().stream()
            .filter(m -> m.getId() == id)
            .findFirst()
            .orElse(null);

        /*Se abbiamo trovato un film, passiamo il suo titolo come "itemTitle"
        altrimenti un messaggio di errore.
        */
        if (movie != null) {
            model.addAttribute("itemTitle", movie.getTitle());
        } else {
            model.addAttribute("itemTitle", "Film non trovato");
        }

        /*Restituiamo la view detail.html (va creata in src/main/resources/templates)
        dove Thymeleaf mostrerà ${itemTitle}
        */
        return "detail";
    }


    /*Metodo per GET /songs/{id}, uguale al precedente ma per le canzoni
    */
    @GetMapping("/songs/{id}")
    public String songById(@PathVariable int id, Model model) {

        Song song = getBestSongs().stream()
            .filter(s -> s.getId() == id)
            .findFirst()
            .orElse(null);

        if (song != null) {
            model.addAttribute("itemTitle", song.getTitle());
        } else {
            model.addAttribute("itemTitle", "Canzone non trovata");
        }

        return "detail";
    }

}
