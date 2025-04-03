// Creada per Aleix Teixidor 28/3/2025

import java.util.Map;
import java.util.HashMap;
public class Afectacio {
// Classe que funciona com un registre guardant tota 
// la informació de un virus dins una regió

    private Regio _regio;
    private Virus _virus;
    private int _nMorts;
    private int _nInfectats;
    private int _nMalalts;
    private int _nImmunes;
    private int _nContagiosos;
    private int _totalInfectats;
    private int _totalMorts;
    private int _totalMalalts;

// Map que el podriem utilitzar com a histograma per seguir l'evolució temporal? Bona idea?
    private Map<Integer, Integer> _histograma; //Primer integer=dia, Segon integer=nombre del qual volem consultar

// MÈTODES CONSTRUCTORS

    public Afectacio() {
    // Pre: --
    // Post: Afectacio iniciat
        _regio = null;
        _virus = null;
        _nMorts = 0;
        _nInfectats=0;
        _nMalalts=0;
        _nImmunes=0;
        _nContagiosos=0;
        _totalMalalts=0;
        _totalInfectats=0;
        _totalMorts=0;
        _histograma=new HashMap<>();
    }

    public Afectacio(Regio r, Virus v, int nMorts, int nInfectats, int nMalalts) {
    // Pre: r iniciat, v iniciat i nMorts > 0
    // Post: Afectacio iniciat
        _regio = r;
        _virus = v;
        _nMorts = nMorts;
        _totalMorts=nMorts;
        _nInfectats = nInfectats;
        _totalInfectats=nInfectats;
        _nMalalts = nMalalts;

    }

//MÈTODES MODIFICADORS
   
    public void sumarMorts(int nousMorts) {
        // Pre: nombre de morts nous
        // Post: nombre de morts de la regió actualitzats
            _nMorts += nousMorts;
        }
    
    public void abansMalaltAraImmune(){ //Podriem fer un mètode que mires si hi ha persones que ja no estan malaltes i ara son immunes per restar el numero de malalts i sumar el d'immununes

    }
    
}

//MÈTODES CONSULTORS
    public int TotalMorts(){
        //Pre:--
        //Post: retorna el total nombre de morts
        return _totalMorts
    }

    public int TotalInfectats(){
        //Pre:--
        //Post: retorna el total nombre d'infectats
        return _totalInfectats;
    }

    public int TotalMalalts(){
        //Pre:--
        //Post: retorna el total nombre de malalts
        return _totalMalalts;
    }


