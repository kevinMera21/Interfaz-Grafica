/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import CustomExceptions.ArchivosExceptions;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rociomera
 */
public class Agente implements Comparable<Agente>{
    private String nombre;
    private String cedula;
    private String image_path;
    private Ubicacion posicion_actual;
    public Agente(String nombre, 
                    String cedula, 
                    String image_path,
                    Ubicacion ubicacion_actual) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.image_path = image_path;
        this.posicion_actual = new Ubicacion(ubicacion_actual.getX(),ubicacion_actual.getY());
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getCedula() {
        return cedula;
    }
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    public Ubicacion getUbicacion_actual() {
        return posicion_actual;
    }
    public void setUbicacion_actual(Ubicacion posicion_actual) {
        this.posicion_actual = posicion_actual;
    }
    public String getImage_path() {
        return image_path;
    }
    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }
    //IGUAL PARA COMPARAR POR AGENTE EN EL COMBOBOX - NO COMPARAR POR UBICACION
    @Override    
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Agente other = (Agente) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.cedula, other.cedula)) {
            return false;
        }
        return true;
    }

    //HASHCODE PORQUE SE SOBREESCRIBE EL IGUAL
    @Override
    public int hashCode() {
       
        
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.nombre);
        hash = 59 * hash + Objects.hashCode(this.cedula);
        return hash;
    }
    
    
    //PRINT PARA QUE SE MUESTRE LA INFO EN EL COMBO
    public String toString() {
        return cedula+" - "+nombre;
    }
    
    //carga los agentes que se encuentran en el archivo agentes.txt
    //que se encuentra en el paquete recursos que esta relativo al root
    //de la aplicacion
    public static Set<Agente> cargarAgentesArchivo(String nombre_archivo) 
            throws ArchivosExceptions{
        String ruta = "src/recursos/"+nombre_archivo;
        System.out.println(ruta);
        Set<Agente> agentes = new TreeSet<>();
        try(BufferedReader bf = new BufferedReader(
                                    new FileReader(ruta))){
            String linea;
            while((linea = bf.readLine())!=null){
                System.out.println(linea);
                String[] p = linea.split(",");
                String[] u = p[3].split(":");
                System.out.println(new Ubicacion(Double.valueOf(u[0]),Double.valueOf(u[1])));
                agentes.add(new Agente(p[0],p[1],p[2],
                        new Ubicacion(Double.valueOf(u[0]),Double.valueOf(u[1]))));
            }         
        }  catch (IOException ex) {
            System.out.println(ex.getMessage());
            throw new ArchivosExceptions(ruta,ex.getMessage());
        }
        return agentes;
    }

    @Override
    public int compareTo(Agente o) {
        return getNombre().compareTo(o.getNombre());
    }
}
