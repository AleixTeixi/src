// Creada per Arnau K. Deprez al 21/03/25

public class Simulacio {

    // Simulació necessita guardar, per cada parell Virus-Regió informació.
    
    private Interaccio  _interaccio;	// Per manejar l'interfície d'usuari
    private boolean     _running;		// Guarda si l'execució està en marxa o no
    private Virus[]     _virus;         // LListat de virus
    private Regio[]     _regions;       // Llistat de regions
    private int         _diesSimulacio;     // Counter de dies de simulació
    // Encara em falta guardar una llista de parelles Regio-Virus

    
    public Simulacio(Virus[] virus, Regio[] regions, int diesSimulacio, Interaccio interaccio) {
    // Pre: dades llegides del fitxer
    // Post: Simulacio amb paràmetres del fitxer
        this._virus = virus;
        this._regions = regions;
        this._diesSimulacio = 0;
	    this._running = false;
        this._interaccio = interaccio;
    }

    public Simulacio() {
    // Pre: --
    // Post: Simulació iniciada amb paràmetres buits
        this._virus = null;
        this._regions = null;
        this._diesSimulacio = 0;
    }

    private void stepDia() {
    // Pre: cert
    // Post: avança la simulació 1 dia
        // Càlculs adients
        System.out.println("Dia posterior!");

        // fer els càlculs

        // actualitzar GUI (passar nous stats a Interaccio)
    }

    public void run() {
    // Pre: --
    // Post: Simulació posada en marxa
        _running = true;

        String opcio = null;

        while (_running) {
                
            _interaccio.mostrarText("test");
                
            opcio = _interaccio.rebreInput("entra opcio: ");
            this.stepDia();

            if (opcio == "p") {	// P --> Parar; hauria de fer un 'enum' per fer-ho més net
                _running = false;
            }
        }
    }
}
