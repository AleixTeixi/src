// Creada per Arnau K. Deprez al 21/03/25

import java.io.File;
import java.util.List;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {     // Necessari per treballar amb JavaFX

    // Rutes dels arxius: són static per poder accedir-hi sense haver de fer una instància
    private static String _rutaVirus = "fitxers/virus.txt";
    private static String _rutaRegio = "fitdata/regio.txt";
    private static String _rutaInici = "fitdata/inici.txt";

    public static void main(String[] args) {
        launch(args); // Inicia la UI de JavaFX
    }

    @Override
    public void start(Stage primaryStage) {     // aquest mètode és el punt d'entrada de les aplicacions JavaFX
    // Pre: primaryStage (és la finestra principal, creada per JavaFX quan fem launch)
    // Post: inicia la simulació, l'interacció i la GUI
        Interaccio interaccio = new Interaccio();
        interaccio.iniciarGUI(primaryStage);

        List<File> arxius = interaccio.seleccionarArxius();

        // TODO: En Guillem m'ha de passar info dels fitxers x carregar la simulació
        Virus[] virus = {};
        Regio[] regions = {}; 



        Simulacio simulacio = new Simulacio(virus, regions, 0, interaccio);
        simulacio.run();
    }
}
