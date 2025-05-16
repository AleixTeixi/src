package fitxers;

//Autor: Guillem Bouzas

public class InfoFamilia { // Representació simplificada d'una família de virus

    public String nomFamilia;
    public float probMutCoincidencia;
    public float tcpMaxVar;

    public InfoFamilia(String nom, float p, float t){ // Constructor
        nomFamilia = nom;
        probMutCoincidencia = p;
        tcpMaxVar = t;
    }
}
