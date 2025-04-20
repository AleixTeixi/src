package fitxers;
// Autor: Guillem Bouzas

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import virus.*;

public abstract class LecturaFitxersVirus {

    public static InfoFitxerVirus llegirFitxer(File fitxer){
    //Pre: el fitxer existeix; Post: retornem una llista amb la informació continguda en el fitxer
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

        List<FamiliaVirus> f = new ArrayList<>(); // vectors on anirem guardant les informacions
        List<VirusADN> vd = new ArrayList<>();
        List<VirusARN> vr = new ArrayList<>();

        String nomFamilia = null; // Declarem dades de la família
        float probMutCoincidencia = 0;
        float tpcMaximVariacio = 0;

        String nomVirus = null; // Decclarem les dades de virus
        FamiliaVirus fVirus = null;
        float probMalaltia = 0;
        float latencia = 0;
        float incubacio = 0;
        float duaradaContagi = 0;
        float duaradaImmunitat = 0;
        float mortalitat = 0;
        float taxaContagi = 0;
        float probMutCopia = 0;

        boolean esInfoFamilia = false; // Per saber si la informació que vindrà serà d'una família o un virus
        boolean esArn = false; // Per saber si la informació que vindrà serà d'un virus ARN o ADN

        while(i < liniesDelFitxer.size()){
            t = new Scanner(liniesDelFitxer.get(i)); // Inspeccionem la primera línia del fitxer
            String element = t.next();
            switch(element){
                case "#": // És un comentari, no fem res
                    break;
                case "*": // Ens separa la informació entre families/virus, aprofitarem per crear un virus o una famiília i afegir-la al seu corresponent vector.
                    if(esInfoFamilia) f.add(new FamiliaVirus(nomFamilia, tpcMaximVariacio, probMutCoincidencia));
                    else{if(esArn) vr.add(new VirusARN(nomVirus, probMalaltia, incubacio, latencia, mortalitat, duaradaContagi, taxaContagi, duaradaImmunitat, fVirus, probMutCopia));
                         else vd.add(new VirusADN(nomVirus, probMalaltia, incubacio, latencia, mortalitat, duaradaContagi, taxaContagi, duaradaImmunitat, fVirus));
                    }
                    break;
                case "families": // La informacio de les següents línies va referida a famílies
                    esInfoFamilia = true;
                    break;
                case "nom": // Guardem el nom de la família o el virus
                    if(esInfoFamilia) nomFamilia = t.next();
                    else nomVirus = t.next();
                    break;
                case "prob_mut_coincidencia": // Guardem la probabilitat de mutació per coincidencia
                    probMutCoincidencia = t.nextFloat();
                    break;
                case "tpc_maxim_variacio": // Guardem el tcp màxim de variació
                    tpcMaximVariacio = t.nextFloat();
                    break;
                case "virus": // Indiquem que la informació que vindrà ara correspondrà a virus i no famílies
                    esInfoFamilia = false;
                    break;
                case "tipus": // Indiquem si informació que ens arribarà correspon a un virus ARN o ADN
                    if(t.next().equals("ARN")) esArn = true;
                    else esArn = false;
                    break;
                case "familia": // Busquem la família a la que pertany el virus (l'haurem creat prèviament)
                    for(int j=0; j < f.size(); j++){
                        FamiliaVirus familia = f.get(j);
                        if(familia.toString().equals(t.next())){fVirus = familia; break;}
                    }
                    break;
                case "prob_malaltia": // Guardem la resta de dades referides a un virus
                    probMalaltia = t.nextFloat();
                    break;
                case "incubacio":
                    incubacio = t.nextFloat();
                    break;
                case "latencia":
                    latencia = t.nextFloat();
                    break;
                case "durada_contagi":
                    duaradaContagi = t.nextFloat();
                    break;
                case "durada_immunitat":
                    duaradaImmunitat = t.nextFloat();
                    break;
                case "mortalitat":
                    mortalitat = t.nextFloat();
                    break;
                case "taxa_contagi":
                    taxaContagi = t.nextFloat();
                    break;
                default:
                    probMutCopia = t.nextFloat();
            }
            i++; // Augmentem el comptador
        }

        // Abans de retornar, completarem la informació de les famílies de virus afegint els virus que pertanyen a cada família.
        for(int k=0; k < f.size(); k++){ // Anem recorrent totes les famílies
            String familiaPerComparar = f.get(k).toString(); // La família que estem comprovant
            for(int m=0; m < vd.size(); m++){ // Recorrem el vector de virus ADN
                if(familiaPerComparar.equals(vd.get(m).familia())){ // Si el virus pertany a la família
                    f.get(k).afegirVirus(vd.get(m)); // Afegim el virus a la família
                }
            }
            for(int n=0; n < vr.size(); n++){ // el mateix però amb virus ARN
                if(familiaPerComparar.equals(vr.get(n).familia())){
                    f.get(k).afegirVirus(vr.get(n));
                }
            } 
        }

        return new InfoFitxerVirus(f, vd, vr);
    }

}
