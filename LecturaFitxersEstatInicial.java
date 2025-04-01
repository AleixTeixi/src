// Autor: Guillem Bouzas

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.File;

public abstract class LecturaFitxersEstatInicial {

    public static List<Afectacio> llegirFitxer(String nomFitxer){
    //Pre: el fitxer existeix; Post: retornem una llista amb la informació continguda en el fitxer

        List<Afectacio> afectacions = new ArrayList<>(); // Vector que retornarem
        Scanner s = new Scanner(new File(nomFitxer)); // Obrim un scanner
        List<String> liniesDelFitxer = new ArrayList<>(); // Guardarem totes les línies del fitxer en un array

        while(s.hasNextLine()){
            liniesDelFitxer.add(s.nextLine()); // Emplenem el vector de línies del fitxer amb el contingut del fitxer
        }

        int i = 0; // Comptador pel bucle
        while(i < liniesDelFitxer.size()){
            Scanner t = new Scanner(liniesDelFitxer.get(i)); // Inspeccionem la primera línia del fitxer
            String element = t.next();
            String nomRegio = null;
            String nomVirus = null;
            if(element.compareTo("#") == 0) // És un comentari, no fem res
            else{
                if(element.compareTo("*") == 0) // Ens separa la informació entre les  regions, no fem res
                else{
                    if(element.compareTo("regio") == 0) nomRegio = t.next(); // Guardem el nom de la regió
                    else{
                        if(element.compareTo("nom_virus") == 0) nomVirus = t.next(); // Guardem el nom del virus
                        else{
                            Afectacio a = new Afectacio(nomRegio, nomVirus, t.nextInt()); // Creem una afectació
                            afectacions.add(a); // Afegim una afectació al vector d'afectacions
                        }
                    }
                }
            }
        }
        return afectacions;
    }
}
