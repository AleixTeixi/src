// Creada per Arnau K. Deprez al 04/04/25

package ui.scenes;

import main.Main;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class InitialScene extends Scene {
// Classe que guarda l'escena (Scene) de la classe
    public InitialScene(Stage stage, Main mainRef) {
    // Pre: la Stage principal i la referència del main
    // Post: crea l'escena inicial
        super(new VBox(), 400, 300);

        VBox root = (VBox) getRoot();
        root.setSpacing(10);
        root.setPadding(new Insets(10));

        Label titulo = new Label("Simulador de Virus");
        Button seleccionarFitxers = new Button("Seleccionar fitxers");
        seleccionarFitxers.setOnAction(e -> {
            // cridem a Main per inciar la sim un cop té els fitxers
            //mainRef.iniciarSimulacio();
        });

        root.getChildren().addAll(titulo, seleccionarFitxers);
    }

    public void seleccionarFitxers(TextField virusField, TextField regioField, TextField estatField) {
    // Pre: TextField per cada fitxer
    // Post: -- (hauria de retornar els File de cada fitxer però encara no sé com)
        // No sé si aquest mètode correpon aquí
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar fitxer");

        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            virusField.setText(file.getAbsolutePath());
        }
    }
}
