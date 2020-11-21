/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import CustomExceptions.ArchivosExceptions;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 *
 * @author rociomera
 */
public class ListadoVisitas {
    private Map<Agente,Set<Tienda>> visitas;
    
    public ListadoVisitas() throws ArchivosExceptions{
        Agente[] ag;
        ag = Agente.cargarAgentesArchivo("agentes.txt").toArray(new Agente[10]);
        visitas = new HashMap<>();
        HashSet<Tienda> tt = new HashSet<>();
        tt.add(new Tienda("Entregado",new Ubicacion(200,300)));
        tt.add(new Tienda("Entregado",new Ubicacion(100,200)));
        tt.add(new Tienda("Por Entregar",new Ubicacion(450,100)));
        tt.add(new Tienda("Por Entregar",new Ubicacion(470,400)));
        tt.add(new Tienda("Por Entregar",new Ubicacion(300,450)));
        visitas.put(ag[0], tt);
        HashSet<Tienda> tt2 = new HashSet<>();
        tt2.add(new Tienda("Maria",new Ubicacion(350,200)));
        tt2.add(new Tienda("Luisito",new Ubicacion(400,100)));
        tt2.add(new Tienda("De Todo",new Ubicacion(200,230)));
        visitas.put(ag[1], tt2);
    }
    /**
     * Devuelve la lista de tiendas que tienen que ser visitas por
     * un agente
     * @param agente
     * @return 
     */
    public Set<Tienda>getTiendasAgente(Agente agente){
        
        visitas.forEach( (a,t) -> {
            
            
        });
        
        
        
        return visitas.get(agente);
    }
    
    /**
     * Devuelve una lista con la tiendas en el orden de visita
     * del agente pasado como parametro
     * la ejecucion de este metodo puede tardar mucho tiempo, tiempo
     * en el cual la interfaz grafica estaria unresponsive. Por lo tanto
     * la llamada a este metodo debe hacerse dentro de un hilo
     * @return List<Tienda>
     */
    public List<Tienda>calcularRuta(Agente agente){
        //1) encontrar la tienda mas cercana
        //2) actualizar la posicion del agente a esa posicion
        //3) remover la tienda mas cercana del listado de tiendas que tiene que vistar
        //4) volver al punto 1 hasta que no haya mas tienda
        
        //tiene la lista de las tiendas que voy a visitar
        List<Tienda> ordenTiendas = new ArrayList<>();
        Set<Tienda> tiendas_agente = new HashSet(getTiendasAgente(agente));
        int tam = tiendas_agente.size();
        for(int i=0;i<tam;i++){
            TreeMap<Double,Tienda> distancia_tiendas = new TreeMap<>();
            tiendas_agente.forEach((t) -> {
                double distancia = agente.getUbicacion_actual().
                        calcularDistancia(t.getUbicacion());
                distancia_tiendas.put(distancia,t);
            });
            double key = distancia_tiendas.firstKey();
            //actualizar la posicion del agente
            agente.setUbicacion_actual(distancia_tiendas.get(key).getUbicacion());
            //agrego la tienda cercana a la lista
            ordenTiendas.add(distancia_tiendas.get(key));
            //remover la tienda que se visito
            tiendas_agente.remove(distancia_tiendas.get(key));
        }
        System.out.println(ordenTiendas);
        return ordenTiendas;
    }
    
}
