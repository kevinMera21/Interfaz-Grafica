/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visitaagentestienda;

import CustomExceptions.ArchivosExceptions;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import modelo.Agente;
import modelo.ListadoVisitas;
import modelo.Tienda;

/**
 *
 * @author rociomera
 */
public class PaneOrganizer {
    public BorderPane _root;
    public Pane mapaPane;
    public VBox rightPane;
    public ListadoVisitas listado;
    
    public PaneOrganizer() throws ArchivosExceptions{
        listado = new ListadoVisitas();

        _root = new BorderPane();
        crearTop();
        crearCenter();
        cargarAgentes();
    }
    public Pane getRoot(){
        return _root;
    }
    private void crearTop(){
        HBox pt= new HBox();
        pt.getChildren().add(new Label("Visualizador Rutas"));
        pt.setAlignment(Pos.CENTER);
        pt.setPadding(new Insets(10,10,10,10));
        _root.setTop(pt);
    }
    private void crearCenter(){
        mapaPane = new Pane();
        mapaPane.setPrefSize(600, 600);
        mapaPane.setStyle("-fx-background-image: url('mapa.png');"
                + "-fx-background-repeat: stretch;"
                + "-fx-background-size: 600 600;"
                + "-fx-background-position: center center;");
        _root.setCenter(mapaPane);
    }
    private void cargarAgentes() throws ArchivosExceptions{
        //comboBox con la info de los agentes
        //se debe mostrar en orden alfabetico
        rightPane = new VBox();
        /*ComboBox cagente = new ComboBox();
        try {
            Set<Agente> agentes=Agente.cargarAgentesArchivo("agentes.txt");
            cagente.setItems(FXCollections.observableArrayList(agentes));
            //FIJAR ACCION AL COMBO BOX
            cagente.setOnAction(e ->{
                resetAgentSelection();
                createAgentPane((Agente)cagente.getSelectionModel().getSelectedItem());
            });
        } catch (ArchivosExceptions ex) {

        }*/
        
        for(Agente ag : Agente.cargarAgentesArchivo("agentes.txt")){
            HBox agenteInfoPane = new HBox();
            ImageView iv= new ImageView(new Image("/imagenes/"+ag.getImage_path(),60,60,true,true));
            VBox dataAgentPane = new VBox();
            dataAgentPane.getChildren().addAll(new Label(ag.getNombre()),
                                                new Label(ag.getCedula()));
            dataAgentPane.setPadding(new Insets(30,30,30,30));
            dataAgentPane.setSpacing(10);
            agenteInfoPane.setAlignment(Pos.CENTER);
            agenteInfoPane.getChildren().addAll(iv,dataAgentPane);
            rightPane.getChildren().add(agenteInfoPane);
            
            agenteInfoPane.setOnMouseClicked((e)->{
                resetAgentSelection();
                cargarTiendasMapa(ag);
            });
        }
        
        rightPane.setStyle("-fx-padding: 5;" + 
                      "-fx-border-style: solid inside;" + 
                      "-fx-border-width: 1;" +
                      "-fx-border-insets: 5;"+ 
                      "-fx-border-color: black;");
        _root.setLeft(rightPane);
    }
    private void resetAgentSelection(){
        mapaPane.getChildren().clear();
        //agentSection.getChildren().clear();
    }
    private void cargarTiendasMapa(Agente ag){
        
        //anade la imagen del agente al mapa
        /*ImageView iv2= new ImageView(new Image("/imagenes/"+ag.getImage_path(),50,50,true,true));
        System.out.println(ag.getUbicacion_actual());
        iv2.setLayoutX(ag.getUbicacion_actual().getX());
        iv2.setLayoutY(ag.getUbicacion_actual().getY());
        mapaPane.getChildren().add(iv2);*/
        
        //ubicar las tiendas en el mapa que le agente debe visitar
        //sale un circulo con el orden de visita y abajo el nombre
        int i = 1;
        for(Tienda t:listado.calcularRuta(ag)){
            //crear un stackpane con un circulo y la posicion en la 
            //que visito la tienda
            StackPane stack = new StackPane();
            Text texto = new Text(String.valueOf(i));
            Circle c = new Circle(30);
            c.setFill(Color.RED);
            stack.getChildren().addAll(c,texto);
            //crear VBox con el StackPane y el nombre de la tienda
            VBox paneTienda = new VBox(stack,new Label(t.getNombre()));
            paneTienda.setAlignment(Pos.CENTER);
            //fijar la posicion de la tienda y agregarla al mapa
            paneTienda.setLayoutX(t.getUbicacion().getX());
            paneTienda.setLayoutY(t.getUbicacion().getY());
            mapaPane.getChildren().add(paneTienda);
            i++;
        }
    }
    
    
}

