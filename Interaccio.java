// Creada per Arnau K. Deprez al 21/03/25

// fonts consultades:
//  - https://www.geeksforgeeks.org/javafx-tutorial/
//  - https://fxdocs.github.io/docs/html5/
//  - https://openjfx.io/javadoc/24/

import java.util.Scanner;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class Interaccio {
// Classe que maneja l'interacció amb l'usuari i mostra la GUI (més endavant)

    private Scanner _scanner;   // Per rebre input de l'usuari per CLI
    private Scene _sceneInici;      // Finestra inicial mostrant noms i selecció de fitxers
    private Scene _sceneStats;      // Finestra per mostrar estadístiques i info
    private Stage _stage;           // Conté la Window on es mostraran els Stage
    private Label _statsLabel;      // Aquí vull guardar-hi les dades per anar-les actualitzant sense haver de modificar tota la Scene (encara no ho faig)
    private Simulacio _sim;     // Guarda la simulació per poder fer crides (com avançar dia)

    public Interaccio() {
    // Pre: --
    // Post: objecte iniciat
        this._scanner = new Scanner(System.in);
    }

    public void setSimulacio(Simulacio simulacio) {
    // Pre: Simulació iniciada amb les dades dels fitxers
    // Post: --
        this._sim = simulacio;
        // Considero que aquest setter és imprescindible ja que:
        //      Per crear la simulació necessitem els fitxers i per tenir els fitxers necessitem la Interacció
        //      Per tant la interacció s'haurà creat abans de la simulació. Llavors quan ja s'hagi iniciat,
        //      amb el setter podem passar-li a la classe "Interaccio".
    }

    public void mostrarText(String text){
    // Pre: String no buit
    // Post: mostra string a l'usuari
        System.out.println(text);   // Per ara x terminal
    }

    public String rebreInput(String missatge) {
    // Pre: String amb el missatge a mostrar a l'usuari per demanar input (pot ser buit)
    // Post: retorna l'input de l'usuari (String)
        System.out.print(missatge);
        return _scanner.nextLine();
    }

    public void updateStats(int diaActual) {
    // Pre: nous stats (per ara només dia)
    // Post: actualitza GUI amb els nous stats
        // No sé com fer això correctament. No vull crear-ho tot de nou, només vull actualitzar les dades dins del Label

        Button avansaDiaButton = new Button("següent dia");
        avansaDiaButton.setOnAction(e -> this._sim.stepDia());

        _statsLabel = new Label("Stats del dia " + diaActual + ":\n...");   // Aquí es mostrarien les dades del dia corresponent

        VBox layout = new VBox(20, _statsLabel, avansaDiaButton);

        _sceneStats = new Scene(layout, 400, 300);
        
        this._stage.setScene(_sceneStats);
        this._stage.show();
    }

    public void iniciarGUI(Stage stage) {
    // Pre: stage iniciada al Main
    // Post: inicia la GUI i mostra la Scene inicial

        // guardem la Stage
        this._stage = stage;
        this._stage.setTitle("infekTopia - Grup f1");       // Canviem el nom de la finestra

        // Creem la Scene inicial (_sceneInici): noms, botó per fitxers, etc)
        Button selecFitxersButton = new Button("Seleccionar fitxers");
        selecFitxersButton.setOnAction(e -> canviarScene()); // Configurar una acció pel botó

        Label caption = new Label("InfekTopia - ProPro 2025\nGrup f1 (DV): G. Bouzas, A. Deprez, A. Teixidor");

        VBox layout = new VBox(20, caption, selecFitxersButton);

        _sceneInici = new Scene(layout, 400, 300);

        // Mostrem l'escena inicial en la finestra
        this._stage.setScene(_sceneInici);
        this._stage.show();
    }

    public void canviarScene() {
    // Pre: --
    // Post: Canvia l'escena inicial i mostra l'escena amb les dades de la simulacio

        Button avansaDiaButton = new Button("següent dia");
        avansaDiaButton.setOnAction(e -> this._sim.stepDia());

        _statsLabel = new Label("Stats del dia " + 0 + ":\n...");   // Aquí es mostrarien les dades del dia corresponent

        VBox layout = new VBox(20, _statsLabel, avansaDiaButton);

        _sceneStats = new Scene(layout, 400, 300);

        this._stage.setScene(_sceneStats);
        this._stage.show();
    }
}