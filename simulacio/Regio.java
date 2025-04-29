package simulacio;

import virus.*;

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

    private static class LlistatRegions{
        public String nomRegio;
        public boolean confinada; // true si està confinada

        public LlistatRegions(String nomRegio, boolean confinada) {
            this.nomRegio = nomRegio;
            this.confinada = confinada;
        }
    }

    private ArrayList<LlistatRegions> regionsVeines;    
    

    //MÈTODES CONSTRUCTORS

    //Constructor de regio
    public Regio(String nomRegio, double ratioInternContactesInicial, int nombreHabitants) {
        this.nomRegio = nomRegio;
        this.nombreHabitants = nombreHabitants;
        this.ratioInternContactesInicial = ratioInternContactesInicial;
        this.ratioInternContactesActual = ratioInternContactesInicial;
        this.regionsVeines = new ArrayList<>();
        this.ratioExternaContactesInicial = new HashMap<>();
        this.ratioExternaContactesActual = new HashMap<>();
        this.afectacions = new HashMap<>();
    }

    //MÈTODES MODIFICADORS

    public void afegirVeina(String nomRegio, float ratioexternaentrada){
        //Pre: nomRegio no existeix com a veina
        //Post: afegim la regio com a veina i la ratio externa de contactes inicial
        regionsVeines.add(new LlistatRegions(nomRegio, false)); //False perque considerem que la regioVeina no esta confinada inicialment
        ratioExternaContactesInicial.put(nomRegio, ratioexternaentrada);
        ratioExternaContactesActual.put(nomRegio, ratioexternaentrada);
    }

    public void tancar(String nomRegio){
    //Pre: Regio r amb regions veines
    //Post: Les regions veïnes queden confinades i ratios externes son 0
        for(LlistatRegions i:regionsVeines){
            if(i.nomRegio.equals(nomRegio)){
                i.confinada = true;
                ratioExternaContactesActual.put(nomRegio, 0.0f);
                break;
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
        this.ratioInternContactesInicial=this.ratioInternContactesActual;
        this.ratioInternContactesActual = ratioInternContactesNova;
        confinamentTou();
    }

    public void desconfinar(String nomRegio){
    //Pre: this.nomRegio ha de ser veïna i confinada
    //Post: this.nomRegio ara esta desconfinada i la ratio externa es recupera
    for (LlistatRegions i : regionsVeines) {
        if (i.nomRegio.equals(nomRegio) && i.confinada) {
            i.confinada = false;
            float ratioExternaOriginal = ratioExternaContactesInicial.getOrDefault(nomRegio, 0.0f);
            ratioExternaContactesActual.put(nomRegio, ratioExternaOriginal);
            break;
            }
        }
    }

    public void desconfinarDur() {
        //Pre: la regio esta en confinament dur
        //Post: regio desconfinada i es recupera la ratio interna inicial
        if(estaConfinamentDur()){
            this.ratioInternContactesActual = this.ratioInternContactesInicial;
            for (LlistatRegions i : regionsVeines) {
                if (i.confinada) {
                    i.confinada = false;
                    ratioExternaContactesActual.put(i.nomRegio, ratioExternaContactesInicial.getOrDefault(i.nomRegio, 0.0f));
                }
            }
        }
    }

    //MÈTODES CONSULTORS

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

    public boolean esConfinada(String nomRegio) {
        // Pre: regio existeix amb nomRegio
        // Post: retorna true si el nom de la regió passada com a paràmetre és veina i confinada, fals altrament
        for (LlistatRegions i : regionsVeines) {
            if (i.nomRegio.equals(nomRegio) && i.confinada) {
                return true;
            }
        }
        return false;
    }
    
    public int obtenirPoblacio() {
        //Pre:--
        //Post: retornem el nombre d'habitants de la regio
        return this.nombreHabitants;
    }

    public double obtenirRatioInternContactesActual() {
        //Pre:--
        //Post: es retorna la ratio interna de contactes actual de la regio
        return this.ratioInternContactesActual;
    }

    public float obtenirRatioExternaContactesActual(String nomRegio) {
        //Pre: nomRegio es tracta d'una regio veina R'
        //Post: retorna la ratio externa de contactes actual de la regio R
        if (!esVeina(nomRegio)) {
            return 0.0f;
        } else {
            return this.ratioExternaContactesActual.getOrDefault(nomRegio, 0.0f);
        }
    }
    
    public String obtenirNomRegio() {
        //Pre:--
        //Post: es retorna el nom de la regio
        return this.nomRegio;
    }

    private boolean estaConfinamentDur() {
        //Pre: --
        //Post: retorna true en el cas que la regio estigui en confinament dur, false altrament
        if (this.ratioInternContactesActual == this.ratioInternContactesInicial) {
            return false;
        }
        for (LlistatRegions i : regionsVeines) {
            if (!i.confinada || ratioExternaContactesActual.getOrDefault(i.nomRegio, 1.0f) != 0.0f) {
                return false;
            }
        }
        return true;
    }

}   

