// Creada per Arnau K. Deprez al 21/03/25

package simulacio;

import java.util.List;

import ui.Interaccio;

public class Simulacio {

    // Simulació necessita guardar, per cada parell Virus-Regió informació.
    
    private Interaccio  _interaccio;	    // Per manejar l'interfície d'usuari
    private List<FamiliaVirus> _families;
    private List<VirusADN> _virusAdn;
    private List<VirusARN> _virusArn;
    private int _diesSimulacio;     // Counter de dies de simulació

    public Simulacio(Interaccio interaccio, int dies, List<FamiliaVirus> families, List<VirusADN> virusAdn, List<VirusARN> virusArn) {
    // Pre: dades llegides del fitxer
    // Post: Simulacio amb paràmetres del fitxer
        this._virusAdn = virusAdn;
        this._virusArn = virusArn;
        this._families = families;
        this._diesSimulacio = 0;
        this._interaccio = interaccio;
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

    public void confinarRegio(Regio a, Regio b) {
    // Pre: Regió seleccionada a la GUI
    // Post: es confina la regió

        // La ràtio externa de contactes d’una regió cap a l’altra passarà a ser 0.
    }
}
