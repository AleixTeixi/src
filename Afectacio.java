// Creada per Aleix Teixidor 28/3/2025

public class Afectacio {
// Classe que funciona com un registre guardant tota 
// la informació de un virus dins una regió

    private Regio _regio;
    private Virus _virus;
    private int _nMorts;
    // etc, falta afegir info
    
    public void sumarMorts(int nousMorts) {
    // Pre: nombre de morts nous
    // Post: nombre de morts de la regió actualitzats
	    _nMorts += nousMorts;
    }

    public Afectacio() {
    // Pre: --
    // Post: Afectacio iniciat
        _regio = null;
        _virus = null;
        _nMorts = 0;
    }

    public Afectatio(Regio r, Virus v, int nMorts) {
    // Pre: r iniciat, v iniciat i nMorts > 0
    // Post: Afectacio iniciat
        _regio = r;
        _virus = v;
        _nMorts = nMorts;
    }
}
