// Creada per Arnau K. Deprez al 21/03/25

public class Simulacio {

    // Simulació necessita guardar, per cada parell Virus-Regió informació.
    
    private Interaccio  _interaccio;	    // Per manejar l'interfície d'usuari
    private Virus[]     _virus;             // LListat de virus
    private Regio[]     _regions;           // Llistat de regions
    private int         _diesSimulacio;     // Counter de dies de simulació

    
    public Simulacio(Virus[] virus, Regio[] regions, int diesSimulacio, Interaccio interaccio) {
    // Pre: dades llegides del fitxer
    // Post: Simulacio amb paràmetres del fitxer
        this._virus = virus;
        this._regions = regions;
        this._diesSimulacio = 0;
        this._interaccio = interaccio;
    }

    public Simulacio() {
    // Pre: --
    // Post: Simulació iniciada amb paràmetres buits
        this._virus = null;
        this._regions = null;
        this._diesSimulacio = 0;
    }

    public void stepNDies(int n) {
    // Pre: n > 0
    // Post: avança la simulació tants dies com n
        if (0 >= n) {
            System.out.println("nombre de dies invalid");
            return;
        }
    
        for (int i = 0; i < n; i++) {
            this.stepDia();
        }
    }

    public void stepDia() {
    // Pre: cert
    // Post: avança la simulació 1 dia
        // Càlculs adients
        System.out.println("Dia " + _diesSimulacio + "!");
        _diesSimulacio++;

        // fer els càlculs

        // actualitzar GUI (passar nous stats a Interaccio)
        _interaccio.updateStats(_diesSimulacio);    // falta molt, per ara només s'actualitza el dia
    }
}
