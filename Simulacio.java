// Creada per Arnau K. Deprez al 21/03/25

public class Simulacio {

    // Simulació necessita guardar, per cada parell Virus-Regió informació.

    private Virus[] _virus;         // LListat de virus
    private Regio[] _regions;       // Llistat de regions
    private int _diesSimulacio;     // Counter de dies de simulació
    // Encara em falta guardar una llista de parelles Regio-Virus

    public Simulacio(Virus[] virus, Regio[] regions, int diesSimulacio, Interaccio interaccio) {
    // Pre: dades llegides del fitxer
    // Post: Simulacio amb paràmetres del fitxer
        this._virus = virus;
        this._regions = regions;
        this._diesSimulacio = 0;
    }

    public Simulacio() {
        this._virus = null;
        this._regions = null;
        this._diesSimulacio = 0;
    }

    public void stepDia() {
    // Pre: cert
    // Post: avança la simulació 1 dia
        // Càlculs adients
        System.out.println("Dia avançat!");
    }
}
