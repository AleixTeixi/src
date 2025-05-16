package fitxers;

public class InfoVirus { // Representació de la informació continguda en un fitxer sobre els virus

    public String nomVirus;
    public String tipus;
    public String familia;
    public float probMalaltia;
    public float incubacio;
    public float latencia;
    public float duradaContagi;
    public float duradaImmunitat;
    public float mortalitat;
    public float taxaContagi;
    public float probMutacioCopia;

    public InfoVirus(String n, String t, String f, float pMal, float i, float l, // Constructor
                        float dCon, float dImm, float m, float tCon, float pMut){
        nomVirus = n;
        tipus = t;
        familia = f;
        probMalaltia = pMal;
        incubacio = i;
        latencia = l;
        duradaContagi  = dCon;
        duradaImmunitat = dImm;
        mortalitat = m;
        taxaContagi = tCon;
        probMutacioCopia = pMut;

    }

}
