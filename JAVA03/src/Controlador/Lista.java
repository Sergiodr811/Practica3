
package Controlador;

import Modelo.*;

public class Lista <T> {

    /**
     * @return the inicio
     */
    public static Nodo getInicio() {
        return inicio;
    }

    /**
     * @param aInicio the inicio to set
     */
    public static void setInicio(Nodo aInicio) {
        inicio = aInicio;
    }

    /**
     * @return the actual
     */
    public Nodo getActual() {
        return actual;
    }

    /**
     * @param actual the actual to set
     */
    public void setActual(Nodo actual) {
        this.actual = actual;
    }
    private static Nodo inicio;
    private Nodo actual;
    
    public Lista(){
        inicio = null;
        this.actual = null;
    }
    
    public void insertar(T nd){
        Nodo n = new Nodo(nd);
        
        if(getInicio() == null){
            setInicio(n);
        }
        else{
            Nodo aux = getInicio();
            while(aux.getSiguiente() != null){
                aux = aux.getSiguiente();
            }
            n.setAnterior(aux);
            aux.setSiguiente(n);
        }
    }
}
