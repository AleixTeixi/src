package main;
// Creada per Arnau K. Deprez al 21/03/25

import javafx.application.Application;
import javafx.stage.Stage;

import ui.Interaccio;

// vull saber si funciona el commit desde IntelliJ

public class Main extends Application {     // Necessari per treballar amb JavaFX
// Classe principal. Coordina la lectura de fitxers i inicialització de la simulació i GUI.

    private Interaccio _interaccio;

    public static void main(String[] args) {
        launch(args); // Inicia la UI de JavaFX
    }

    @Override
    public void start(Stage primaryStage) {     // aquest mètode és el punt d'entrada de les aplicacions JavaFX
    // Pre: primaryStage (és la finestra principal, creada per JavaFX quan fem launch)
    // Post: inicia la simulació, l'interacció i la GUI
        _interaccio = new Interaccio();
        _interaccio.iniciarGUI(primaryStage);

        // llegir fitxers

        // carregar les estructures de dades adequades

        // crear la simulació amb les dades dels fitxers
        // _simulacio = new Simulacio();

        // informar a Interacció de la simulació creada
        //_interaccio.setSimulacio(simulacio);
    }
}
