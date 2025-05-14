// Modificacions a càrrec de Guillem Bouzas
package virus;

import java.util.List;
import java.util.ArrayList;

public class FamiliaVirus {
// Aquesta classe representa una agrupació de virus té informació conjunta i porta un registre de virus de la mateixa família.

    // Atributs
    private String _nomFamilia;
    private float _maxVariacio;		// Tant per cent màxim de variació en cas de mutar
    private List<Virus> _virusFamilia;  // Llista de virus de la família
    private float _pMutacioCoincidencia; // Probabilitat de mutacions per coincidència d'aquesta família de virus

    public FamiliaVirus (String nom, float maxVar, float pMutCoinc) {
        _nomFamilia = nom;
        _maxVariacio = maxVar;
        _pMutacioCoincidencia = pMutCoinc;
        _virusFamilia = new ArrayList<>();
    }

    public float variacioMax() {
    // Pre: cert; Post: retorna la variació màxima de la família
        return _maxVariacio;
    }

    public float pMutacioPerCoincidencia(){
    // Pre: cert; Post: retorna la probabilitat de mutació per coincidència de la família
        return _pMutacioCoincidencia;
    }

    public void afegirVirus(Virus vir){
    //Pre: ---; Post: afegim un virus al vector que compta amb els virus de la família
        _virusFamilia.add(vir);
    }

    public Virus buscarVirus(String nom){
    //Pre: ---;  Post: retorna el virus amb el nom que li hem passat, si no existeix aquest virus retorna null
        boolean trobat = false;
        int i = 0;
        while(!trobat && i < this._virusFamilia.size()){
            if(nom.equals(this._virusFamilia.get(i).toString()))
                trobat = true;
            else 
                i++;
        }
        if(trobat)
            return this._virusFamilia.get(i);
        else
            return null;
    }

    @Override
    public String toString() {
    // Pre: cert; Post: Converteix l'objecte a String
	    return _nomFamilia;
    }
}
