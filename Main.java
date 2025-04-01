// Creada per Arnau K. Deprez al 21/03/25

public class Main {

    // Rutes dels arxius: són static per poder accedir-hi sense haver de fer una instància
    private static String _rutaVirus = "virus.txt";
    private static String _rutaRegio = "regio.txt";
    private static String _rutaInici = "inici.txt";

    public static void main(String[] args) {

        // TODO: Aquí me falta saber com arriba la informació dels fitxers (Guillem)
        
        //Virus[] virus = LectorVirus.llegirVirus();
        //Regio[] regions = LectorRegions.llegirRegions();
        //LectorGeneral.llegirEstatInicial();

        Interaccio interaccio = new Interaccio();

        // Demanar a l'usuari que entri la informació dels fitxers
        String nomFitxerVirus = null;
        String nomFitxerRegions = null;
        String nomFitxerEstatInit = null;

        String nomFitxerEstatInit = "test.txt";

        EstatInicialLlegit estatInicia = LecturaFitxersEstatInicial.llegirFitxer(nomFitxerEstatInit);


        Simulacio simulacio = new Simulacio();

        simulacio.run();
    }
}
