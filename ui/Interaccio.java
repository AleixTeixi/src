// Creada per Arnau K. Deprez al 21/03/25

// fonts consultades:
//  - https://www.geeksforgeeks.org/javafx-tutorial/
//  - https://fxdocs.github.io/docs/html5/
//  - https://openjfx.io/javadoc/24/

package ui;

import main.Main;
import simulacio.Simulacio;

import ui.scenes.InitialScene;
import ui.scenes.StatsScene;

import javafx.stage.Stage;

public class Interaccio {
// Classe que maneja l'interacció amb l'usuari i mostra la GUI
    
    private Main _main;
    private Simulacio _sim;
    private Stage _stage;
    private InitialScene _initialScene;
    private StatsScene _statsScene;

    public Interaccio() {
    // Pre: --
    // Post: inicia un objecte Interaccio
        _initialScene = new InitialScene(this._stage, this._main);
        _statsScene = new StatsScene(this._stage);
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

    public void iniciarGUI(Stage stage) {
    // Pre: main stage passat pel Main
    // Post: inicia la GUI amb la pantalla inicial
        _stage = stage;
        _stage.setTitle("infekTopia - Grup f1");
        _stage.setScene(_initialScene);
        _stage.show();
    }

    public void canviarAStats() {
    // Pre: --
    // Post: -- (canvia l'escena inicial a la de Stats)
        _stage.setScene(_statsScene);
    }
}