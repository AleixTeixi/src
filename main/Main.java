package main;
// Creada per Arnau K. Deprez al 21/03/25

import javafx.application.Application;
import javafx.stage.Stage;
import simulacio.Simulacio;

import java.io.File; // Provisionalmen
import java.util.List;

import ui.Interaccio;

import simulacio.Regio;

import fitxers.LecturaFitxersEstatInicial;  // Per llegir el fitxer de l'estat inicial
import fitxers.EstatInicial;
import fitxers.LecturaFitxersVirus;         // Per llegir el fitxer dels virus
import fitxers.InfoFitxerVirus;
import fitxers.LecturaFitxersRegions;       // Per llegir el fitxer de regions
import fitxers.InfoRegio;

public class Main extends Application { // Necessari per treballar amb JavaFX
    // Classe principal. Coordina la lectura de fitxers i inicialització de la
    // simulació i GUI.

    // Atributs
    private Interaccio _interaccio;
    private Simulacio _sim;
    // Específic del portàtil de n'Arnau, només per testejar que funcioni
    private String _fitxerEstatInicial = "~/Uni/3r/ProPro/projecte-f1/fitxers/estatInicial.txt";
    private String _fitxerVirus = "~/Uni/3r/ProPro/projecte-f1/fitxers/virus.txt";
    private String _fitxerRegions = "~/Uni/3r/ProPro/projecte-f1/fitxers/regions.txt";

    // Atributs x guardar info dels fitxers
    private List<EstatInicial>  _estatsInicials;    // Llista de tuples. Cada tupla té info d'una afectació
    private InfoFitxerVirus     _infoFitxerVirus;   // Tupla que guarda 3 llistes: viruADN, virusARN i famílies
    private List<InfoRegio>     _infoRegions;

    private List<Regio>         _regions;           // Llista de regions per passar a la simulació
    private List<VirusADN>      _virusAND;          // Llista de regions per passar a la simulació
    private List<VirusARN>      _virusARN;          // Llista de regions per passar a la simulació
    private List<FamiliaVirus>  _families;          // Llista de regions per passar a la simulació

    public static void main(String[] args) {
        launch(args); // Inicia la UI de JavaFX
    }

    @Override
    public void start(Stage primaryStage) { // aquest mètode és el punt d'entrada de les aplicacions JavaFX
        // Pre: primaryStage (és la finestra principal, creada per JavaFX quan fem
        // launch)
        // Post: inicia la simulació, l'interacció i la GUI
        // _interaccio = new Interaccio();
        // _interaccio.iniciarGUI(primaryStage);

        // llegir fitxers
        llegirFitxers();

        // Passar les dades del format que les retorna la funció de llegir
        // al format en el qual les guardem a dintre de la simulació.
        traduirFitxers();

        // crear la simulació amb les dades dels fitxers
        _sim = crearSimulacio();

        // informar a Interacció de la simulació creada
        _interaccio.setSimulacio(_sim);
    }

    public void llegirFitxers() {
        // Pre: --
        // Post: inicia i carrega tot segons el contingut dels fitxers
        _estatsInicials = LecturaFitxersEstatInicial.llegirFitxer(new File(_fitxerEstatInicial));
        _infoFitxerVirus = LecturaFitxersVirus.llegirFitxer(new File(_fitxerVirus));
        _regions = LecturaFitxersRegions.llegirFitxer(new File(_fitxerRegions));
    }

    public void traduirFitxers() {
        // Pre: els tres fitxers llegits i carregats
        // Post: extreu informació dels fitxers i els carrega als atributs adequats
        
        // REGIONS
        traduirRegio();
        

        // VIRUS
    }

    public void traduirRegio() {
    // Pre: fitxer de regions llegit
    // Post: inicia vector de regions a partir de la info del fitxer

    // InfoRegio:
        // public String nomRegio;
        // public int habitantsRegio;
        // public float mobInterna;
        // public List<String> regionsLimitrofes;
        // public List<Float> mobExterna;

    // Regió:
        // // Atributs
        // private String nomRegio;
        // private int nombreHabitants;
        // private double ratioInternContactesActual;
        // private double ratioInternContactesInicial;
        // // Mapa per poder guardar la taxa de contacte on tenim la clau de nom virus
        // // guardada com a string i la taxa com a float
        // private Map<Virus, Afectacio> afectacions;
        //
        // // Mapa per poder guardar la ratio externa de contactes on la clau de nom regio
        // // guardada com a sting i com a valor la ratio de contacte extern
        // private Map<String, Float> ratioExternaContactesInicial;
        // private Map<String, Float> ratioExternaContactesActual; // String OK (no cal Regio)
        //
        // class LlistatRegions {
        //     public String nomRegio;
        //     public boolean confinada; // true si està confinada
        // }
        //
        // private ArrayList<LlistatRegions> regionsVeines;
        //
        // // MÈTODES CONSTRUCTORS
        //
        // // Constructor de regio
        // public Regio(String nomRegio, double ratioInternContactesInicial, int nombreHabitants) {
        //     this.nomRegio = nomRegio;
        //     this.nombreHabitants = nombreHabitants;
        //     this.ratioInternContactesInicial = ratioInternContactesInicial;
        //     this.regionsVeines = new ArrayList<>();
        //     this.ratioExternaContactesInicial = new HashMap<>();
        //     this.ratioExternaContactesActual = new HashMap<>();
        // }
        for (int i = 0; i < _infoRegions.size(); i++) {
            _regions.append(new Regio( ... ));
        }
    }

    public Simulacio crearSimulacio() {
        // Pre: --
        // Post: --

        // Creem una simulació amb els paràmetres passats pels fitxers
        Simulacio tempSim = new Simulacio(
                _interaccio,
                _infoFitxerVirus.families,
                _infoFitxerVirus.virusAdn,
                _infoFitxerVirus.virusArn,
                _regions, // Això no podria ser una List<Regio>? sembla que guarden casi el mateix (Regió
                          // == InfoRegio)
                0 // 0 per simplificar coses, no té gaire importància
        );

        // Ara carreguem la simulació amb l'estat inicial llegit per fitxer
        tempSim.carregarEstatInicial(_estatInicial); // TODO: carregar estat inicial a Simulacio

        return tempSim;
    }

}
