// Creada per Arnau K. Deprez al 21/03/25

// fonts consultades:
//  - https://www.geeksforgeeks.org/javafx-tutorial/
//  - https://fxdocs.github.io/docs/html5/
//  - https://openjfx.io/javadoc/24/

import java.util.Scanner;
import java.util.List;

import java.io.File;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

public class Interaccio {
// Classe que maneja l'interacció amb l'usuari i mostra la GUI (més endavant)

    private Scanner _scanner;   // Per rebre input de l'usuari per CLI
    private Stage _stage;       // Guarda la finestra principal de JavaFX
    private Label _statsLabel;

    public Interaccio() {
    // Pre: --
    // Post: objecte iniciat
        this._scanner = new Scanner(System.in);
    }

    public void iniciarGUI(Stage stage) {
    // Pre: stage iniciada al Main
    // Post: inicia la GUI
        this._stage = stage;
        this._stage.setTitle("infekTopia - Grup f1");       // Canviem el nom de la finestra

        Button stepDiaButton = new Button("Avançar un dia");
        //stepDiaButton.setOnAction(e -> ); // Configurar una acció pel botó

        _statsLabel = new Label("Dia: 0\nStats aquí...");

        VBox layout = new VBox(10, _statsLabel, stepDiaButton);

        Scene scene = new Scene(layout, 400, 300);

        this._stage.setScene(scene);
        this._stage.show();
    }

    public List<File> seleccionarArxius() {
    // Pre: --
    // Post: retorna uns llista amb els fitxers seleccionats
        return SelectorFitxers.seleccionar(this._stage);
    }

    public void mostrarText(String text){
    // Pre: String no buit
    // Post: mostra string a l'usuari
        System.out.println(text);   // Per ara x terminal
    }

    public void mostrarResultats(int dia, Virus[] virus, Regio[] regions) {
    // Pre: dades d'un dia
    // Post: mostra "stats" a l'usuari
        System.out.println("Dia X:");
        // Mostra els resultats de la simulació per a cada dia
        // ...
    }

    public String rebreInput(String missatge) {
    // Pre: String amb el missatge a mostrar a l'usuari per demanar input (pot ser buit)
    // Post: retorna l'input de l'usuari (String)
        System.out.print(missatge);
        return _scanner.nextLine();
    }
}



//https://jenkov.com/tutorials/javafx/filechooser.html
abstract class SelectorFitxers {
    
    static List<File> seleccionar(Stage stage) {
        final FileChooser _fileChooser = new FileChooser(); 
        List<File> llistaFitxers = _fileChooser.showOpenMultipleDialog(stage);

        return llistaFitxers;
    }
}