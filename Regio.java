//Falta implementar constructors...

import java.util.ArrayList;
public class Regio {
    // Atributs

    private String nomRegio; 
    private int nombreHabitants; 

    class LlistatRegions{
        public String nomRegio;
        public boolean confinada; // true si està confinada
    }

    ArrayList<LlistatRegions> regionsVeines;

    ArrayList<String> _regionsVeines;


    // Mètodes

    public void confinamentTou() {

    }

    public void confinamentDur() {

    }

    public void desconfinar(Regio regio) {

    }
    public void desconfinarDur() {

    }

    public boolean esVeina(String nomRegio){
    // Pre: regio existeix amb nomRegio
    // Post: retorna true si el nom de la regio passada és veina, false altrament
    }

    public boolean esConfinada(String nomRegio){
    // Pre: regio existeix amb nomRegio
    // Post: retorna true si el nom de la regió passada com a paràmetre és veina i confinada, fals altrament
        for(...){
            if(regionsVeines[i]._regio.equals(nomRegio)){
                return regionsVeines[i]._confinada;
            }
        }
    }

    
}   

