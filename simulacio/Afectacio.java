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

    //Dades acumulades de de l'inici de la simulació
    private int _totalInfectats;
    private int _totalMorts;
    private int _totalMalalts;

    //Mapes per guardar el nombre de morts, infectats i malalts per cada dia. Primer integer=dia, segon integer=nombre del qual volem consultar
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

    //MÈTODES MODIFICADORS

    public void sumarMorts(int nousMorts) {
        // Pre: nombre de morts nous
        // Post: nombre de morts de la regió actualitzats
        _totalMorts += nousMorts;
    }

    
    //MÈTODES CONSULTORS
    public int TotalMorts() {
        //Pre:--
        //Post: retorna el total nombre de morts
        return _totalMorts;
    }

    public int TotalInfectats() {
        //Pre:--
        //Post: retorna el total nombre d'infectats
        return _totalInfectats;
    }

    public int TotalMalalts() {
        //Pre:--
        //Post: retorna el total nombre de malalts
        return _totalMalalts;
    }

    public int TotalImmunes() {
        //Pre:--
        //Post: retorna el totat d'immunes
        int total = 0;
        for (int i : histogramaImmunes.values()) {
            total += i;
        }
        return total;
    }

    public int obtenirPoblacioDia(int dia) {
        //Pre:--
        //Post: retorna la població de la regió viva per aquest virus un dia
        int infectats = histogramaInfectats.getOrDefault(dia, 0);
        int nombreSans = determinarSans(dia);
        int immunes = histogramaImmunes.getOrDefault(dia, 0);
        int poblacio = nombreSans + infectats + immunes;
        return poblacio;
    }

    private int determinarSans(int dia) {
        //Pre: --
        //Post: retorna el nombre de sans a la regió r
        int nombreSans = 0;
        int poblacio = _regio.obtenirPoblacio();
        int immunes = histogramaImmunes.getOrDefault(dia, 0);
        int infectats = histogramaInfectats.getOrDefault(dia, 0);
        int morts = _totalMorts;
        nombreSans = poblacio - infectats - immunes-morts;
        return Math.max(nombreSans,0);
    }

    public int determinarContagiosos(int dia) {
        //Pre:--
        //Post: retorna el nombre d'individus d'una regio que poden contagiar un virus el dia passat 
        int nombreContagiosos = 0;
        float latencia = _virus.obtenirTempsLatencia();
        float contagi = _virus.obtenirTempsContagi();
        int diainfeccio = 0;
        while (diainfeccio < dia) {
            int diesInfeccio = dia - diainfeccio;
            if (diesInfeccio >= latencia && diesInfeccio <= latencia + contagi)
                nombreContagiosos += histogramaInfectats.getOrDefault(diainfeccio, 0);
            diainfeccio++;
        }
        return nombreContagiosos;
    }

    private double determinarContagisExterns(int dia) {
        //Pre:--
        //Post: retorna el nombre de contagis externs donat un dia
        double contagisExterns = 0.0;
        for (Regio.LlistatRegions veina : _regio.regionsVeines) {
            if (!i.esConfinada(veina.obtenirNomRegio())) {
                int sansVeina = i.determinarSans(dia - 1);
                int contagiososVeina = i.determinarContagiosos(dia - 1);
                int poblacioVeina = i.obtenirPoblacioDia(dia - 1);
                double probabilitatContagi = _virus.obtenirProbabilitatContagi();
                double taxaExternaContactes = i.obtenirRatioExternaContactesActual(_regio.obtenirNomRegio());
                contagisExterns += sansVeina * probabilitatContagi * taxaExternaContactes
                        * (contagiososVeina / poblacioVeina);
            }
        }
        return contagisExterns;
    }

    private double determinarContagisInterns(int dia) {
        //Pre:--
        //Post: retorna el nombre de contagis interns donat un dia
        double contagisInterns = 0.0;
        int PodenContagiar = determinarContagiosos(dia - 1);
        int poblacioDiaAnterior = obtenirPoblacioDia(dia - 1);
        int sansDiaAnterior = determinarSans(dia - 1);
        float probabilitatContagi = _virus.obtenirProbabilitatContagi();
        double taxaInternContactes = _regio.obtenirRatioInternContactesActual();
        if (poblacioDiaAnterior > 0) {
            contagisInterns = sansDiaAnterior * taxaInternContactes * probabilitatContagi
                    * (PodenContagiar / (double) poblacioDiaAnterior);
        }
            
        return contagisInterns;
        }

    private int determinarNousContagis(int dia) {
        //Pre:--
        //Post: retorna el nombre de nous contagis donat un dia
        if (dia == 0)
            return 0;
        else {
            double contagisInterns = determinarContagisInterns(dia);
            double contagisExterns = determinarContagisExterns(dia);
            int nousContagis = (int) (contagisInterns + contagisExterns);
            return Math.min(nousContagis, nombreSans);
        }

    }

    private int determinarNousMalalts(int dia) {
        //Pre:--
        //Post: es retorna el nombre de malalts donat un dia
        int nousMalalts = 0;
        float probabilitatMalaltia = _virus.obtenirProbabilitatMalaltia();
        int individusFinalIncubacio = histogramaInfectats.getOrDefault(dia - _virus.obtenirTempsIncubacio(), 0);
        nousMalalts = (int) (individusFinalIncubacio * probabilitatMalaltia);
        return nousMalalts;
    }

    private int determinarNousMorts(int dia) {
        //Pre:--
        //Post: es determinen el nombre de nous morts totals
        int nousMorts = 0;
        nousMorts = (int) (determinarNousMalalts(dia) * _virus.obtenirTaxaMort());
        return nousMorts;
    }

    private int determinarMortsPerDia (int dia){
        //Pre:--
        //Post: es determina el nombre de morts per dia
        int mortsPerDia = 0;
        int nousMorts = determinarNousMorts(dia);
        float duradaMalaltia = _virus.obtenirTempsContagi();
        mortsPerDia = (int) Math.ceil(nousMorts / duradaMalaltia);
        return mortsPerDia;
    }
}