
//Creada per Aleix Teixidor el 27/03/25
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
public class Regio {

    // Atributs
    private String nomRegio; 
    private int nombreHabitants; 
    private double ratioInternContactesActual;
    private double ratioInternContactesInicial;
    //Mapa per poder guardar la taxa de contacte on tenim la clau de nom virus guardada com a string i la taxa com a float
    private Map<Virus, Afectacio> afectacions;

    //Mapa per poder guardar la ratio externa de contactes on la clau de nom regio guardada com a sting i com a valor la ratio de contacte extern
    private Map<String, Float> ratioExternaContactesInicial;
    private Map<String, Float> ratioExternaContactesActual;
    class LlistatRegions{
        public String nomRegio;
        public boolean confinada; // true si està confinada
    }

    private ArrayList<LlistatRegions> regionsVeines;    
    

    // Mètodes

    //Constructor de regio
    public Regio(String nomRegio,double ratioInternContactesInicial, int nombreHabitants){
        this.nomRegio=nomRegio;
        this.nombreHabitants=nombreHabitants;
        this.ratioInternContactesInicial=ratioInternContactesInicial;
        this.regionsVeines=new ArrayList<>();
        this.ratioExternaContactesInicial=new HashMap<>();
        this.ratioExternaContactesActual=new HashMap<>();
    }

    public void tancar(String nomRegio){
    //Pre: Regio r amb regions veines
    //Post: Les regions veïnes queden confinades i ratios externes son 0
        for(LlistatRegions i:regionsVeines){
            if(i.nomRegio.equals(nomRegio)){
                i.confinada=true;  
                //Aqui faltaria guardar la ratio externa inicial per potser després restaurar-la
                ratioExternaContactesActual.put(nomRegio,0.0f);
            }
        }
    }

    public void confinamentTou() {
    //Pre: regio r i regio R són veïnes i tancades
    //Post:ratios externes de contactes a zero
        for(LlistatRegions i:regionsVeines){
            tancar(i.nomRegio);
        }
    }

    public void confinamentDur (float ratioInternContactesNova) {
    //Pre: regio r i regio R són veïnes i tancades
    //Post: ratios externes de contactes a zero i ratio interna de contactes modificada
        ratioInternContactesInicial=ratioInternContactesActual;
        ratioInternContactesActual=ratioInternContactesNova;
        for(LlistatRegions i:regionsVeines)
            tancar(i.nomRegio);
    }

    public void desconfinar(String nomRegio){
    //Pre: this.nomRegio ha de ser veïna i confinada
    //Post: this.nomRegio ara esta desconfinada i la ratio externa es recupera
        for(LlistatRegions i:regionsVeines){
            if(i.nomRegio.equals(nomRegio)){
                i.confinada=false;
                ratioExternaContactesActual.put(nomRegio,//nomRegioRatioExterna(metode que ens dona el nom i no fem servir geters)
            }
        }
    }

    public void desconfinarDur() {
    //Pre: la regio esta en confinament dur
    //Post: regio desconfinada i es recupera la ratio interna inicial
        this.ratioInternContactesActual=ratioInternContactesInicial;


    }

    public boolean esVeina(String nomRegio){
    // Pre: regio existeix amb nomRegio
    // Post: retorna true si el nom de la regio passada és veina, false altrament
        for(LlistatRegions i:regionsVeines){
            if(i.nomRegio.equals(nomRegio)){
                return true;
            }
        }
        return false;
    }

    public boolean esConfinada(String nomRegio){
    // Pre: regio existeix amb nomRegio
    // Post: retorna true si el nom de la regió passada com a paràmetre és veina i confinada, fals altrament
        for(LlistatRegions i:regionsVeines){
            if(i.nomRegio.equals(nomRegio) && i.confinada){
                return true;
            }
        }
        return false;
    }
}   

