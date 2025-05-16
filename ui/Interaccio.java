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
        // Pre: -- FALTA INICIAR MAIN I STAGE
        // Post: inicia un objecte Interaccio
        // Falta posar valor als atributs de la stage i main
        // _main = ...
        _initialScene = new InitialScene(this._stage, this._main);
        _statsScene = new StatsScene(this._stage);

        // Seria millor fer directament "new Scene(this)" i passar l'objecte
        // directament?
    }

    public void setSimulacio(Simulacio simulacio) {
        // Pre: Simulació iniciada amb les dades dels fitxers
        // Post: --
        this._sim = simulacio;
        // Considero que aquest setter és imprescindible ja que:
        // Per crear la simulació necessitem els fitxers i per tenir els fitxers
        // necessitem la Interacció
        // Per tant la interacció s'haurà creat abans de la simulació. Llavors quan ja
        // s'hagi iniciat, amb el setter podem passar-li a la classe "Interaccio".
    }

    public void setMain(Main main) {
        // Pre: Main iniciat
        // Post: L'objecte actual d'Interacció guarda la ref del Main
        this._main = main;
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

    public void mostrarInfoDia() {

    }

    public void confinarRegions(String regioA, String regioB) {
        // Pre: regioA i regioB són regions existents
        // Post: Confina les regions
        if (_sim != null) {
            _sim.confinarRegio(regioA, regioB);
        } else {
            mostrarError("La simulació encara no s'ha iniciat");
        }
    }

    public void desconfinarRegions(String regioA, String regioB) {
        // Pre: regioA i regioB són regions existents
        // Post: Desconfina les regions
        if (_sim != null) {
            _sim.desconfinarRegio(regioA, regioB);
        } else {
            mostrarError("La simulació encara no s'ha iniciat");
        }
    }

    public void aplicarConfinamentDur(String regio, double novaRatio) {
        // Pre: regio existeix, novaRatio > 0
        // Post: Aplica un confinament dur a la regió
        if (_sim != null) {
            _sim.aplicarConfinamentDur(regio, novaRatio);
        } else {
            mostrarError("La simulació encara no s'ha iniciat");
        }
    }

    public void aixecarConfinamentDur(String regio) {
        // Pre: regio existeix i té un confinament dur
        // Post: Aixeca el confinament dur de la regió
        if (_sim != null) {
            _sim.aixecarConfinamentDur(regio);
        } else {
            mostrarError("La simulació encara no s'ha iniciat");
        }
    }

    public void carregarFitxers(String fitxerRegions, String fitxerVirus, String fitxerEstatInicial) {
        // Pre: Els fitxers existeixen i el main està iniciat
        // Post: Demana al Main que carregui els fitxers
        if (_main != null) {
            _main.carregarFitxers(fitxerRegions, fitxerVirus, fitxerEstatInicial);
        } else {
            mostrarError("El Main no està correctament inicialitzat");
        }
    }

    public void iniciarSimulacio() {
        // Pre: Els fitxers s'han carregat correctament i el main existeix
        // Post: Demana al Main que inicii la simulació
        if (_main != null) {
            _main.iniciarSimulacio();
            canviarAStats();
        } else {
            mostrarError("El Main no està correctament inicialitzat");
        }
    }

    private void mostrarError(String missatge) {
        // Pre: missatge no buit
        // Post: Mostra una finestra d'error amb el missatge
        System.err.println("ERROR: " + missatge);
        // Aquí es podria mostrar una finestra d'error amb JavaFX
    }
}