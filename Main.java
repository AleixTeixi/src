// Creada per Arnau K. Deprez al 21/03/25

public class Main {

    // Rutes dels arxius: són static per poder accedir-hi sense haver de fer una instància
    private static String _rutaVirus = "virus.txt";
    private static String _rutaRegio = "regio.txt";
    private static String _rutaInici = "inici.txt";

    public static void main(String[] args) {

        // TODO: Aquí me falta saber com arriba la informació dels fitxers (Guillem)
        
        // Llegir dades dels fitxers (x exemple)
        //LectorFitxer lectorInfoVirus = new LectorFitxer(_rutaVirus);
        //LectorFitxer lectorInfoRegions = new LectorFitxer(_rutaRegio);
        //LectorFitxer lectorEstatInicial = new LectorFitxer(_rutaInici);
        
        //Virus[] virus = lectorVirus.llegirVirus();
        //Regio[] regions = lectorRegions.llegirRegions();
        //lectorGeneral.llegirEstatInicial();

        //Simulacio simulacio = new Simulacio(virus, regions, diesSimulacio);
        Simulacio simulacio = new Simulacio();
        Interaccio interaccio = new Interaccio();

        String menu = "menu: ";

        while (true) {
            //simulacio.stepDia();
            interaccio.mostrarText(menu);
            interaccio.rebreInput("entra opcio: ");
            // Per ara s'ignora el string retornat x l'usuari
            simulacio.stepDia();
        }
    }
}