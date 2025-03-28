// Modificacions a càrrec de Guillem Bouzas

public abstract class Virus {
// Aquesta classe encapsula les dades de la malaltia i determina com es propaga

    // Atributs 
    FamiliaVirus _familia;
    String _nom;
    float _probMalaltia;	// Prob de q una persona infectada amb el virus desenvolupi la malaltia
    float _tempsIncub;		// Temps d'incubació: temps mig entre contreure el virus i començar els símptomes
    float _tempsLatencia;	// Temps de latència: temps entre agafar el virus i poder contagiar (t_lat <= t_inc)
    float _taxaMort;		// Taxa de mortalitat
    float _tempsContagi;	// Temps contagi: durada mitjana durant la qual una persona pot contagiar
    float _probContagi;		// Taxa de contagi: probabilitat de contagiar una persona sana
    float _tempsImmune;		// Temps mig en que una persona és immune al virus
    
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
}
