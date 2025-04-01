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

    ArrayList<> _regionsVeines;
    // diu een Francesc q millor guardar un map xq també volem guardar la taxa de contacte
    // el map podria ser que la clau  sigui un string i la taxa un float
    // o podria ser la pròpia regió si definim que la igualtat de regions està en el nom (ha dit que ja ho explicarem)
    map<Virus, Afectacio>  _afectacions;    // No sé si la sintaxi és correcta.

    public Regio() {
        _regionsVeines = new Arraylsit
    }


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

