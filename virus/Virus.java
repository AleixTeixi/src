package virus;
// Modificacions a càrrec de Guillem Bouzas

public abstract class Virus {
// Aquesta classe encapsula les dades de la malaltia i determina com es propaga

    // Atributs 
    protected FamiliaVirus _familia;
    protected String _nom;
    protected float _probMalaltia;	// Prob de q una persona infectada amb el virus desenvolupi la malaltia
    protected float _tempsIncub;		// Temps d'incubació: temps mig entre contreure el virus i començar els símptomes
    protected float _tempsLatencia;	// Temps de latència: temps entre agafar el virus i poder contagiar (t_lat <= t_inc)
    protected float _taxaMort;		// Taxa de mortalitat
    protected float _tempsContagi;	// Temps contagi: durada mitjana durant la qual una persona pot contagiar
    protected float _probContagi;		// Taxa de contagi: probabilitat de contagiar una persona sana
    protected float _tempsImmune;		// Temps mig en que una persona és immune al virus
    
    public Virus(String nom, float pMal, float tInc, float tLat, float pMor, float tCon, float pCon, float tImm, FamiliaVirus fam) {
        this._nom = nom;
        this._probMalaltia = pMal;
        this._tempsIncub = tInc;
        this._tempsLatencia = tLat;
        this._taxaMort = pMor;
        this._tempsContagi = tCon;
        this._probContagi = pCon;
        this._tempsImmune = tImm;
        this._familia = fam;
    }
    
    public boolean esFamilia (Virus b) {
    // Pre: el virus actual i un altre virus, b
    // Post: retorna cert si els dos virus són de la mateixa família i fals altrament
        String familiaA = this._familia.toString();
        String familiaB = b._familia.toString();

        return familiaA.equals(familiaB);
    }

    public String familia() {
    //Pre: ---; Post: retorna el nom de la família a la qual pertany el virus
        return this._familia.toString();
    }
    
    public float obtenirProbabilitatContagi() {
        //Pre:--
        //Post: ens retorna la probabilitat de contagi del virus
        return this._probContagi;
    }

    public float obtenirTempsLatencia() {
        //Pre:--
        //Post: ens retorna el temps de latència del virus
        return this._tempsLatencia;
    }
    
    public float obtenirTempsContagi() {
        //Pre:--
        //Post: ens retorna el temps de contagi del virus
        return this._tempsContagi;
    }
    
    public float obtenirProbabilitatMalaltia() {
        //Pre:--
        //Post: ens retorna la probabilitat de malaltia del virus
        return this._probMalaltia;
    }
    
    public float obtenirTempsIncubacio() {
        //Pre:--
        //Post: ens retorna el temps d'incubació del virus
        return this._tempsIncub;
    }

    public float obtenirTaxaMort() {
        //Pre:--
        //Post: ens retorna la taxa de mortalitat del virus
        return this._taxaMort;
    }

    @Override
    public String toString(){
    //Pre:-- 
    //Post: converteix l'obejecte en string
        return this._nom;
    }

    public boolean mesFort(Virus vir){
    // Pre: --;
    // Post: retorna cert si this és un virus més fort que vir, altrament fals
        boolean fort = false;
        if(this._probContagi == vir._probContagi){
            if(this._probMalaltia == vir._probMalaltia){
                if(this._nom.compareTo(vir._nom) < 0) // comparació per ordre alfabètic (si tenen la mateixa taxa de contagi i prob Malaltia)
                    fort = true; // this va primer en ordre alfabètic
            }
            else
                fort = this._probMalaltia > vir._probMalaltia; // comparació per prob Malaltia (si tenen la mateix taxa de contagi)
        }
        else
            fort = this._probContagi > vir._probContagi; // comparació per taxa de contagi

        return fort;
    }
}
