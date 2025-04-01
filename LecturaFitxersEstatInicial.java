// Autor: Guillem Bouzas

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

class EstatInicial{ // Aquesta classe seria una representació simplificada d'una afectació
    String nomRegio;
    String nomVirus;
    int nInfectats;

    EstatInicial(String nom, String virus, int n){nomRegio = nom; nomVirus = virus; nInfectats = n;}
}

public abstract class LecturaFitxersEstatInicial {

    public static List<EstatInicial> llegirFitxer(File fitxer){
    //Pre: el fitxer existeix; Post: retornem una llista amb la informació continguda en el fitxer

        List<EstatInicial> estats = new ArrayList<>(); // Vector que retornarem
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

        int i = 0; // Comptador pel bucle
        Scanner t = null; // Declarem un nou scanner
        String nomRegio = null; // Declarem variables que utilitzarem dins el while
        String nomVirus = null;
        while(i < liniesDelFitxer.size()){
            t = new Scanner(liniesDelFitxer.get(i)); // Inspeccionem la primera línia del fitxer
            String element = t.next();
            switch (element) { // Bàsicament és com un if-else
                case "#": // És un comentari, no fem res
                    break;
                case "*": // Ens separa la informació entre regions, no fem res
                    break;
                case "regio":
                    nomRegio = t.next(); // Guardem el nom de la regió
                    break;
                case "nom_virus":
                    nomVirus = t.next(); // Guardem el nom del virus
                    break;  
                default: // Si no es donen cap dels casos anteriors és que hi posa "infectats"
                    EstatInicial estat = new EstatInicial(nomRegio, nomVirus, t.nextInt()); // Creem un estat inicial
                    estats.add(estat); // Afegim l'estat al vector que retornarem
            }
        }
        return estats;
    }
}
