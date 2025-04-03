// Creada per Arnau K. Deprez al 21/03/25

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {     // Necessari per treballar amb JavaFX

    public static void main(String[] args) {
        launch(args); // Inicia la UI de JavaFX
    }

    @Override
    public void start(Stage primaryStage) {     // aquest mètode és el punt d'entrada de les aplicacions JavaFX
    // Pre: primaryStage (és la finestra principal, creada per JavaFX quan fem launch)
    // Post: inicia la simulació, l'interacció i la GUI
        Interaccio interaccio = new Interaccio();

        String rutaVirus = interaccio.rebreInput("Entra la ruta del fitxer de virus: ");
        String rutaRegio = interaccio.rebreInput("Entra la ruta del fitxer de regions: ");
        String rutaEstat = interaccio.rebreInput("Entra la ruta del fitxer d'estat inicial: ");


        // TODO: En Guillem m'ha de passar info dels fitxers x carregar la simulació
        Virus[] virus = {};
        Regio[] regions = {}; 

        Simulacio simulacio = new Simulacio(virus, regions, 0, interaccio);
        
        interaccio.iniciarGUI(primaryStage);
        interaccio.setSimulacio(simulacio);
    }
}
