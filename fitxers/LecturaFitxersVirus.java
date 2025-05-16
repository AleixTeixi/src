package fitxers;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

public abstract class LecturaFitxersVirus {

    public static void llegirFitxer(File fitxer, List<InfoFamilia> families, List<InfoVirus> virus){
    // Pre: el fitxer existeix
    // post: families i virus omplertes amb la informació continguda al fitxer

        Scanner s = null; // Declarem un scanner

        try{ // Intentem obrir el fitxer
        s = new Scanner(fitxer); // Obrim l' scanner
        } catch (FileNotFoundException e){ // Per si volguéssim fer servir l'exception que llança si no hi ha el fitxer per alguna cosa
            System.out.println("No s'ha trobat el fitxer"); // Scanner ens demana que pensem que fer si per alguna cosa no trobem el fitxer
        }

        List<String> liniesDelFitxer = new ArrayList<>(); // Guardarem totes les línies del fitxer en un array
        while(s.hasNextLine()){
            liniesDelFitxer.add(s.nextLine()); // Emplenem el vector de línies del fitxer amb el contingut del fitxer
        }

        int i = 0; // Comptador pels bucles
        Scanner t = null; // Declarem un nou scanner

        omplirFamilies(families, t, i, liniesDelFitxer);
        omplirVirus(virus, t, i, liniesDelFitxer);

    }

    private static void omplirFamilies(List<InfoFamilia> f, Scanner t, int counter, List<String> linies){
    // Pre: linies amb el contingut del fitxer
    // Post: f amb la informació de les famílies de virus a f

        // Variables que usarem per a l'estructura InfoFamilia
        String nom = null;
        float p = 0;
        float tcp = 0;

        boolean acabat = false; // Per saber quan ja no tenim informació de les famílies

        do{

            t = new Scanner(linies.get(counter)); // Inspeccionem la primera línia del fitxer
            String comparador = t.next();
            switch(comparador){
                case "#": // comentari, no fem res
                    break;
                case "families": // indica que la informació que trobarem a continuació és de les families, no fem res
                    break;
                case "nom":
                    nom = t.next();
                    break;
                case "prob_mut_coincidencia":
                    p = t.nextFloat();
                    break;
                case "tpc_maxim_variacio":
                    tcp = t.nextFloat();
                    break;
                case "virus":
                    acabat = true;
                    break;
                default: // hem trobat *
                    f.add(new InfoFamilia(nom, p, tcp));
                    break;

            }

            counter ++;

        }while(counter < linies.size() && !acabat); // Fins que el fitxer s'acabés o hem acabat d'omplir tota la informació de les families

    }

    private static void omplirVirus(List<InfoVirus> v, Scanner t, int counter, List<String> linies){
    // Pre: linies amb la informació del fitxer
    // Post: v amb la informació relacionada amb virus del fitxer

        // variables que usarem per l'estructura InfoVirus
        String nomVir = null;
        String tipus = null;
        String fam = null;
        float pMal = 0;
        float inc = 0;
        float lat = 0;
        float dCon = 0;
        float dImm = 0;
        float mort = 0;
        float tCon = 0;
        float pCop = 0;

        while(counter < linies.size()) {

            t = new Scanner(linies.get(counter)); // Inspeccionem la primera línia del fitxer
            String comparador = t.next();

            switch(comparador){
                case "#": // comentari, no fem res
                    break;
                case "nom":
                    nomVir = t.next();
                    break;
                case "tipus":
                    tipus = t.next();
                    break;
                case "familia":
                    fam = t.next();
                    break;
                case "prob_malaltia":
                    pMal = t.nextFloat();
                    break;
                case "incubacio":
                    inc = t.nextFloat();
                    break;
                case "latencia":
                    lat = t.nextFloat();
                    break;
                case "durada_contagi":
                    dCon = t.nextFloat();
                    break;
                case "durada_immunitat":
                    dImm = t.nextFloat();
                    break;
                case "mortalitat":
                    mort = t.nextFloat();
                    break;
                case "taxa_contagi":
                    tCon = t.nextFloat();
                    break;
                case "prob_mutacio_copia":
                    pCop = t.nextFloat();
                    break;
                default: // Trobem un *, un separador entre virus
                    v.add(new InfoVirus(nomVir, tipus, fam, pMal, inc, lat, dCon, dImm, mort, tCon, pCop));
                    break;
            }

            counter ++;
        }

    }

}
