// Creada per Aleix Teixidor 28/3/2025
package simulacio;

import java.util.Map;
import java.util.HashMap;
import virus.*;

public class Afectacio {
    // Classe que funciona com un registre guardant tota
    // la informació de un virus dins una regió

    private Regio _regio;
    private Virus _virus;

    // Dades acumulades des de l'inici de la simulació
    private int _totalInfectats;
    private int _totalMorts;
    private int _totalMalalts;

    // Mapes per guardar el nombre de morts, infectats i malalts per cada dia.
    // Primer integer=dia, segon integer=nombre del qual volem consultar
    private Map<Integer, Integer> histogramaInfectats;
    private Map<Integer, Integer> histogramaMalalts;
    private Map<Integer, Integer> histogramaImmunes;
    private Map<Integer, Integer> histogramaMortsPrevistos;

    // MÈTODES CONSTRUCTORS

    public Afectacio(Regio r, Virus v, int nInfectats) {
        // Pre: r iniciat, v iniciat, nInfectats iniciat
        // Post: Afectacio iniciat
        _regio = r;
        _virus = v;
        histogramaInfectats = new HashMap<>();
        histogramaMalalts = new HashMap<>();
        histogramaImmunes = new HashMap<>();
        histogramaMortsPrevistos = new HashMap<>();
        histogramaInfectats.put(0, nInfectats);
        _totalInfectats = nInfectats;
        _totalMorts = 0;
        _totalMalalts = 0;
    }

    // MÈTODES MODIFICADORS

    public void sumarMorts(int nousMorts) {
        // Pre: nombre de morts nous
        // Post: nombre de morts de la regió actualitzats
        _totalMorts += nousMorts;
    }
    
    public void sumarInfectats(int nousInfectats) {
        //Pre: nombre d'infectats nous
        //Post: nombre d'infectats de la regió actualitzats
        _totalInfectats += nousInfectats;
    }

    public void sumarMalalts(int nousMalalts) {
        //Pre: nombre de malalts nous
        //Post: nombre de malalts sumats
        _totalMalalts += nousMalalts;
    }

    private void transicioInfectatsMalalts(int dia) {
        //Pre: --
        //Post: actualitzem el nombre de malalts en funció del que s'obtingui a infectats
        int diaInfeccio = dia - (int) _virus.tempsIncubacio();
        if (diaInfeccio >= 0) {
            //1r obtenim els infectats que es van infectar el dia del temps d'incubacio
            int infectatsDiaInfeccio = InfectatsDia(diaInfeccio);
            //2n mirem quans d'aquests es van convertir en immunes
            int immunesDiaInfeccio = ImmunesDia(diaInfeccio);
            //3r nomes poden emmalaltir els que no son immunes
            int possiblesMalalts = infectatsDiaInfeccio - immunesDiaInfeccio;
            int nousMalalts = (int) (possiblesMalalts * _virus.probabilitatMalaltia());
            histogramaMalalts.put(dia, nousMalalts);
            sumarMalalts(nousMalalts);
        }

    }
    
    public void processarFiContagi(int dia) {
        //Pre:
        //Post: 
        int diaInfeccio = dia - (int) _virus.tempsContagi() - (int) _virus.tempsLatencia();
        if (diaInfeccio >= 0) {
            int infectatsdiaInfeccio = InfectatsDia(diaInfeccio);
            int malaltsdiaInfeccio = histogramaMalalts.getOrDefault(diaInfeccio + (int) _virus.tempsIncubacio(), 0);
            int nousImmunes = infectatsdiaInfeccio - malaltsdiaInfeccio;
            histogramaImmunes.put(dia, nousImmunes);
        }   
    }


    public void actualitzarAfectacio(int dia) {
        //Pre: dia > 0
        //Post: actualitzem la informació de la regió de cada virus amb la regió
        
        //1r: mirem els nous contagis
        int nousContagis = determinarNousContagis(dia);
        histogramaInfectats.put(dia, nousContagis);
        sumarInfectats(nousContagis);
    
        //2n: fem el canvi a d'infectats a malalts
        transicioInfectatsMalalts(dia);

        //3r: mirem quins nous immunes tindre
        processarFiContagi(dia);

        //4t: actualitzem el nombre de morts
        int nousMorts = determinarNousMorts(dia);
        sumarMorts(nousMorts);
    }

    // MÈTODES CONSULTORS
    public int TotalMorts() {
        // Pre:--
        // Post: retorna el total nombre de morts
        return _totalMorts;
    }

    public int TotalInfectats() {
        // Pre:--
        // Post: retorna el total nombre d'infectats
        return _totalInfectats;
    }

    public int InfectatsDia(int dia) {
        // Pre:--
        // Post: retorna el nombre d'infectats d'un dia
        return histogramaInfectats.getOrDefault(dia, 0);
    }

    public int MalaltsDia(int dia) {
        // Pre:--
        // Post: retorna el nombre de malalts d'un dia
        return histogramaMalalts.getOrDefault(dia, 0);
    }
    public int ImmunesDia(int dia) {
        // Pre:--
        // Post: retorna el nombre d'immunes d'un dia
        return histogramaImmunes.getOrDefault(dia, 0);
    }

    public int TotalMalalts() {
        // Pre:--
        // Post: retorna el total nombre de malalts
        return _totalMalalts;
    }

    public int TotalImmunes() {
        // Pre:--
        // Post: retorna el totat d'immunes
        int total = 0;
        for (int i : histogramaImmunes.values()) {
            total += i;
        }
        return total;
    }

    public int PoblacioDia(int dia) {
        // Pre:--
        // Post: retorna la població de la regió viva per aquest virus un dia
        int infectats = histogramaInfectats.getOrDefault(dia, 0);
        int nombreSans = determinarSans(dia);
        int immunes = histogramaImmunes.getOrDefault(dia, 0);
        int poblacio = nombreSans + infectats + immunes;
        return poblacio;
    }

    private int determinarSans(int dia) {
        // Pre: --
        // Post: retorna el nombre de sans a la regió r
        int nombreSans = 0;
        int poblacio = _regio.Poblacio();
        int immunes = histogramaImmunes.getOrDefault(dia, 0);
        int infectats = histogramaInfectats.getOrDefault(dia, 0);
        int morts = _totalMorts;
        nombreSans = poblacio - infectats - immunes-morts;
        return Math.max(nombreSans, 0);
    }

    public int determinarContagiosos(int dia) {
        // Pre:--
        // Post: retorna el nombre d'individus d'una regio que poden contagiar un virus
        // el dia passat
        int nombreContagiosos = 0;
        float latencia = _virus.tempsLatencia();
        float contagi = _virus.tempsContagi();
        for (int diainfeccio = 0; diainfeccio <= dia; diainfeccio++) {
            int diesInfeccio = dia - diainfeccio;
            if(diesInfeccio > latencia && diesInfeccio <= latencia + contagi)
                nombreContagiosos += histogramaInfectats.getOrDefault(diainfeccio, 0);
        }
        return nombreContagiosos;
    }

    private double determinarContagisExterns(int dia) {
        // Pre:--
        // Post: retorna el nombre de contagis externs donat un dia
        double contagisExterns = 0.0;
        int diaAnterior = dia - 1;
        for (Regio.LlistatRegions veina : _regio.RegionsVeines()) {
            if (!_regio.esConfinada(veina.nomRegio)) {
                Regio regioVeina = Regio.buscarRegio(veina.nomRegio); //Obtenim regio veina del registre central
                if (regioVeina != null) { // Si regió veina existeix
                    Afectacio afectacioVeina = regioVeina.Afectacio(_virus);
                     if (afectacioVeina != null) {
                    // Calculem els components necessaris
                    int sansVeina = afectacioVeina.determinarSans(diaAnterior);
                    int contagiososVeina = afectacioVeina.determinarContagiosos(diaAnterior);
                    int poblacioVeina = afectacioVeina.PoblacioDia(diaAnterior);
                    
                    // Evitem divisió per zero
                    if (poblacioVeina > 0) {
                        double probContagi = _virus.probabilitatContagi();
                        double taxaExterna = _regio.RatioExternaContactesActual(veina.nomRegio);
                        
                        // Apliquem la fórmula de contagi extern
                        contagisExterns += sansVeina * probContagi * taxaExterna * 
                                         (contagiososVeina / (double)poblacioVeina);
                    }
                }
            }
        }
    }
    
    return contagisExterns;
}
        

    private double determinarContagisInterns(int dia) {
        // Pre:--
        // Post: retorna el nombre de contagis interns donat un dia
        int diaAnterior=dia-1;
        double contagisInterns = 0.0;
        int PodenContagiar = determinarContagiosos(diaAnterior);
        int poblacioDiaAnterior = PoblacioDia(diaAnterior);
        int sansDiaAnterior = determinarSans(diaAnterior);
        float probabilitatContagi = _virus.probabilitatContagi();
        double taxaInternContactes = _regio.RatioInternContactesActual();
        if (poblacioDiaAnterior > 0) {
            contagisInterns = sansDiaAnterior * taxaInternContactes * probabilitatContagi
                    * (PodenContagiar / (double) poblacioDiaAnterior);
        }
            
        return contagisInterns;
        }

    private int determinarNousContagis(int dia) {
        // Pre:--
        // Post: retorna el nombre de nous contagis donat un dia
        if (dia == 0)
            return 0;
        else {
            double contagisInterns = determinarContagisInterns(dia);
            double contagisExterns = determinarContagisExterns(dia);
            int sansDiaAnterior = determinarSans(dia - 1);
            int nousContagis = (int)Math.round(contagisInterns) + (int)Math.round(contagisExterns);
            return Math.min(nousContagis, sansDiaAnterior);
        }

    }

    private int determinarNousMalalts(int dia) {
        // Pre:--
        // Post: es retorna el nombre de malalts donat un dia
        int nousMalalts = 0;
        float probabilitatMalaltia = _virus.probabilitatMalaltia();
        int individusFinalIncubacio = histogramaInfectats.getOrDefault(dia - _virus.tempsIncubacio(), 0);
        nousMalalts = (int) (individusFinalIncubacio * probabilitatMalaltia);
        return nousMalalts;
    }

    private int determinarNousMorts(int dia) {
        // Pre:--
        // Post: es determinen el nombre de nous morts totals
        int nousMorts = 0;
        int nousMalaltsDia = determinarNousMalalts(dia);
        nousMorts = (int) Math.round(nousMalaltsDia * _virus.taxaMort());
        return nousMorts;
    }

    private int determinarMortsPerDia(int dia) {
        // Pre:--
        // Post: es determina el nombre de morts per dia
        int mortsPerDia = 0;
        int nousMorts = determinarNousMorts(dia);
        float duradaMalaltia = (int) _virus.tempsContagi();
        mortsPerDia = (int) Math.ceil(nousMorts / (double) duradaMalaltia);
        return mortsPerDia;
    }

    
}
