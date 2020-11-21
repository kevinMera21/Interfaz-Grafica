/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visitaagentestienda;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author rociomera
 */
public class VisitaAgentesTienda extends Application{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        PaneOrganizer pn = new PaneOrganizer();
        Scene sc = new Scene(pn.getRoot());
        primaryStage.setScene(sc);
        primaryStage.setTitle("Aplicacion Examen");
        primaryStage.show();
    }
    
}
