// Creada per Arnau K. Deprez al 04/04/25

package ui.scenes;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StatsScene extends Scene {
// Classe que guarda l'escena (Scene) que mostra les estadístiques de cada dia.

    public StatsScene(Stage stage) {
    // Pre: la Stage principal
    // Post: crea l'escena de mostrar estadístiques
        super(new VBox(), 400, 300);

        VBox root = (VBox) getRoot();
        root.setSpacing(10);
        root.setPadding(new Insets(10));

        Label titol = new Label("Stats d'avui:\n\tNo queda ningú viu.");
        
        root.getChildren().addAll(titol);
    }
}
