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
//Importa Model → un oggetto che ci permette di passare dati (tipo variabili) dal controller alla pagina HTML
import org.springframework.ui.Model;



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
        model.addAttribute("name", "Bruno");

        /*Restituiamo la "view" chiamata index.html (che si trova nella cartella templates)
        Spring lo interpreterà come: "carica il file templates/index.html"*/
        return "index";

    }
    
    
}
