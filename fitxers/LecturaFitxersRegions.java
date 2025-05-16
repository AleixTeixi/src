package fitxers;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

public abstract class LecturaFitxersRegions {

    public static List<InfoRegio> llegirFitxer(File fitxer){    
    //Pre: el fitxer existeix; 
    //Post: retornem una llista amb la informació continguda en el fitxer

        List<InfoRegio> regions = new ArrayList<>(); // Vector on anirem guardant la informació de les regions

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

        inicialitzarInfoRegions(regions, t, i, liniesDelFitxer); // Per recollir la informació de cada regió
        omplirMobilitat(regions, t, i, liniesDelFitxer); // Per recollir la informació de les regions limítrofes

        return regions;
    }

    private static void inicialitzarInfoRegions(List<InfoRegio> reg, Scanner t, int comptador, List<String> linies){
    // Pre: no tenim informació de les regions i linies conté informació del fitxer;
    // Post: tenim reg omplert amb la informació pròpia de cada regió omplerta i la resta de paràmetres preparats per a llegir la informació de mobilitat
            
        // Declarem les variables per els primers atributs de les regions
        String nom = null;
        int nHab = 0;
        float ratioInt = 0;
        
        boolean acabat = false; // Per saber quan hem acabat la inicialització

        do{ 
            
            t = new Scanner(linies.get(comptador)); // Inspeccionem la primera línia del fitxer
            String comparador = t.next(); // Aconseguim el primer String de la línia
            
            switch(comparador){ // Opcions que tenim segons el primer element de la línia
                case "#": //Comentari, no fem res
                    break;
                case "nom": 
                    nom = t.next();
                    break;
                case "habitants":
                    nHab = t.nextInt();
                    break;
                case "mob_interna":
                    ratioInt = t.nextFloat();
                    break;
                case "limits_i_mobilitat":
                    acabat = true;
                    break;
                default: // Hem trobat un *
                    reg.add(new InfoRegio(nom, nHab, ratioInt)); // Afegim una regió
                    break;
            }

            comptador ++;

        }while(comptador < linies.size() && !acabat);

    }

    private static void omplirMobilitat(List<InfoRegio> reg, Scanner t, int comptador, List<String> linies){
    // Pre: Tenim reg omplert amb la informació basica de cadascuna de les regions i linies conté la informació del fitxer
    // Post: cada regió té informació de com es relaciona amb les regions veïnes

        // Variables que usarem en aquesta part
        String veina = null;
        float ratioExt = 0;
        
        while(comptador < linies.size()){ // Anirem fins al final del document
        
            t = new Scanner(linies.get(comptador)); // Obtinc la regió actual
            String regioActual = t.next();
            int pos = cercarRegio(reg, regioActual); // Busco on està

            comptador ++; // Comencem a mirar les regions veïnes de la regió actual
            t = new Scanner(linies.get(comptador));
            String comparador = t.next();

            while(!comparador.equals("*") && comptador < linies.size()){ // Si no estem en un salt entre regions o acabem el fitxer
                
                veina = comparador;
                ratioExt = t.nextFloat();
                reg.get(pos).afegirMobilitat(veina, ratioExt);
                comptador ++;
                t = new Scanner(linies.get(comptador));
                comparador = t.next();

            }

            comptador ++;

        }

    }

    private static int cercarRegio(List<InfoRegio> reg, String nomRegio) {
    // Pre: reg omplerta amb la informació bàsica de les regions, una regio amb nomRegio existeix dins reg
    // Post: obtenim la posició de la regio amb nomRegio dins la llista
        
        int pos = 0;

        for(int i = 0; i < reg.size(); i++){
            if(reg.get(i).nomRegio.equals(nomRegio)){
                pos = i;
                break;
            }
        }

        return pos;

    }
}
