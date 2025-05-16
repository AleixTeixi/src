// Creada per Arnau K. Deprez al 21/03/25

package simulacio;

import java.util.List;

import ui.Interaccio;

import virus.FamiliaVirus;
import virus.VirusADN;
import virus.VirusARN;

import fitxers.EstatInicial;

public class Simulacio {

    private Interaccio _interaccio; // Per manejar l'interfície d'usuari
    private List<FamiliaVirus> _families; // Llista de families
    private List<VirusADN> _virusAdn; // " de virusAdn
    private List<VirusARN> _virusArn; // " de virusArn
    private List<Regio> _regions; // " de regions
    private int _diesSimulacio; // Comptador de dies de simulació

    public Simulacio(Interaccio interaccio, List<FamiliaVirus> families, List<VirusADN> virusAdn, List<VirusARN> virusArn, List<Regio> regions, int dies) {
    // Ctor
        this._interaccio = interaccio;
        this._families = families;
        this._virusAdn = virusAdn;
        this._virusArn = virusArn;
        this._regions = regions;
        this._diesSimulacio = 0;
    }

    public void simulaDia() {
    // Pre: --; Post: avança la simulació 1 dia

        // Extret del moodle:
        // 1. Classe **Simulador**
        // El *Simulador* és el nucli que coordina totes les altres classes. Les seves
        // funcions principals podrien ser:
        // - Instanciar objectes de *Regió* amb les seves regions limítrofes, i associar-hi diferents objectes *Virus*.
        // - Coordinar les afectacions mitjançant instàncies d'*Afectació*.
        // - Actualitzar la simulació amb un mètode `simular_dia()` que:
            // - Informa a cada regió perquè actualitzi el seu estat amb l'evolució del dia (contagis, recuperacions, etc.).
            // - Gestiona la propagació del virus entre regions limítrofes.
        _diesSimulacio++;
        System.out.println("Processant dia " + _diesSimulacio + "...");

        for (int i = 0; i < _regions.size(); i++) {
            // Cada regió ha de avançar els morts, malalts, immunes, etc un dia (als histogrames)
        }

        // Nous contagis / morts es fan dins afectació
        // hem de mostrar els casos del dia (no cal fer la dif amb el dia anterior)

        // 7. Actualitzar GUI amb les noves estadístiques
        _interaccio.mostrarInfoDia();
    }

    public void confinarRegions(Regio a, Regio b) {
        // Pre: Regions a i b; Post: es confina la regió

        // La ràtio externa de contactes d’una regió cap a l’altra passarà a ser 0.
        a.confinamentTou(b);
    }

    public void confinarRegio(Regio a) {
        a.confinamentDur(0.0);
    }
}
