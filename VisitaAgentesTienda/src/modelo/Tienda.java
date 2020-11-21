/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Objects;

/**
 *
 * @author rociomera
 */
public class Tienda {
    private String nombre;
    private Ubicacion ubicacion;
    public Tienda(String nombre, Ubicacion posicion){
        this.nombre = nombre;
        this.ubicacion = posicion;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Ubicacion getUbicacion() {
        return ubicacion;
    }
    public void setUbicacion(Ubicacion posicion) {
        this.ubicacion = posicion;
    }
    //equals para poder eliminar la tienda del set de tiendas
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
        final Tienda other = (Tienda) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.ubicacion, other.ubicacion)) {
            return false;
        }
        return true;
    }
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.nombre);
        hash = 67 * hash + Objects.hashCode(this.ubicacion);
        return hash;
    }

    @Override
    public String toString() {
        return "Tienda{" + "nombre=" + nombre + ", ubicacion=" + ubicacion + '}';
    }
    
    
}
