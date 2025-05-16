package virus;

//Modificacions a càrrec de Guillem Bouzas

import java.util.Random;

public class VirusARN extends Virus implements Comparable<VirusARN> {
//Aquesta classe representa un virus mutable, és per això que serà l'encarregada de gestionar les diferents mutacions que pot patir un virus

	// Atributs
	private float _pMutErrorCopia; // Prob. de mutació per error de còpia
	private int _nMutacions; // Nombre de vegades en que un virus ha mutat

	public VirusARN(String nom, float pMal, float tInc, float tLat, float pMor, float tCon, float pCon, float tImm, FamiliaVirus fam, float pErrC){
		super(nom, pMal, tInc, tLat, pMor, tCon, pCon, tImm, fam);
		_pMutErrorCopia = pErrC;
		_nMutacions = 0;
	
	}
	
	public VirusARN mutarPerError(){
	//Pre: cert; Post: retorna un virus mutat a partir d'un virus "pare"
		// Aconseguim la taxa de variació de la família
		float taxaVariacio = _familia.variacioMax();

		// Generador de nombres Aleatoris
		Random generadorAleatoris = new Random();
		float margeInf = (-1)*taxaVariacio;
		float margeSup = taxaVariacio;
		float rang = margeSup-margeInf;
		float xAleatori = generadorAleatoris.nextFloat() * rang + margeInf;

		// Modifiquem els paràmetres
		float novaPmal = _probMalaltia + _probMalaltia * xAleatori;
		xAleatori = generadorAleatoris.nextFloat() * rang + margeInf; // Obtenim un altre nombre aleatori

		float novaPmor = _taxaMort + _taxaMort * xAleatori;
		xAleatori = generadorAleatoris.nextFloat() * rang + margeInf; // Obtenim un altre nombre aleatori

		float nouTcon = _tempsContagi + _tempsContagi * xAleatori;
		xAleatori = generadorAleatoris.nextFloat() * rang + margeInf; // Obtenim un altre nombre aleatori

		float nouPcon = _probContagi + _probContagi * xAleatori;

		// El virus obté un nom diferent
		_nMutacions ++; // Sumem 1 al nombre de mutacions d'aquest virus
		String nomMutacio = _nom + "_" + _nMutacions;

		// Retornem el nou virus mutat
		VirusARN virusMutat = new VirusARN(nomMutacio, novaPmal, _tempsIncub, _tempsLatencia, novaPmor, nouTcon, nouPcon, _tempsImmune, _familia, _pMutErrorCopia);

		_familia.afegirVirus(virusMutat); // Incloem el nostre virus a la seva família

		return virusMutat;
	}
	
	public Virus mutarPerCoincidencia(VirusARN b){ 
	//Pre: els dos virus són de la mateixa família; Post: retorna un virus mutat a partir de dos virus "pare", si el virus ja existia retorna la mutació ja existent
		
		// Per concatenar els noms sempre de la mateixa manera
		String nomMutacio = null;

		// compararem alfabèticament pq tan si ens passen grip i grip_1 o grip_1 i grip sempre acabem amb grip_grip_1_
		if (this._nom.compareTo(b._nom) <= 0) {
			nomMutacio = this._nom + "_" + b._nom + "_"; // cas this == grip i b == grip_1
		} else {
			nomMutacio = b._nom + "_" + this._nom + "_"; // cas this == grip_1 i b == grip
		}

		Virus virusMutat = this._familia.buscarVirus(nomMutacio); // Cerquem si el virus existeix

		if(virusMutat == null){ // si el virus no existeix el creem

			// Generador de nombres Aleatoris
			Random generadorAleatoris = new Random();
			float p = generadorAleatoris.nextFloat();

			// Modifiquem els paràmetres
			float novaPmal = p*this._probMalaltia + (1-p)*b._probMalaltia;
			p = generadorAleatoris.nextFloat(); // Obtenim un altre nombre aleatori

			float novaPmor = p*this._taxaMort + (1-p)*b._taxaMort;
			p = generadorAleatoris.nextFloat();

			float nouTcon = p*this._tempsContagi + (1-p)*b._tempsContagi;
			p = generadorAleatoris.nextFloat();

			float nouPcon = p*this._tempsContagi  + (1-p)*b._tempsContagi;
			
			// Creem el nou virus, la resta de paràmetres seran la mitjana entre els dos virus
			virusMutat = new VirusARN(nomMutacio, novaPmal, (this._tempsIncub + b._tempsIncub)/2, (this._tempsLatencia + b._tempsLatencia)/2,
										novaPmor, nouTcon, nouPcon, (this._tempsImmune + b._tempsImmune)/2, this._familia, (this._pMutErrorCopia + b._pMutErrorCopia)/2);

			_familia.afegirVirus(virusMutat); // Afegim el virus a la seva família
		}

		return virusMutat;
	}

	@Override
    // retorna -1 si this és més fort que v, 1 si és al contrari, i 0 si tenen igual força 
    public int compareTo(VirusARN vir) {
		int resultat = 0; // si són iguals tornarà 0
		if(this._probContagi == vir._probContagi){
            if(this._probMalaltia == vir._probMalaltia){
				// comparació per ordre alfabètic (si tenen la mateixa taxa de contagi i prob Malaltia)
                if(this._nom.compareTo(vir._nom) < 0) resultat = -1; // this va primer en ordre alfabètic
				else{
					if(this._nom.compareTo(vir._nom) > 0) resultat = 1; 
				}
			}
            else {
			// comparació per prob Malaltia (si tenen la mateix taxa de contagi)
				if(this._probMalaltia > vir._probMalaltia) resultat = -1;
                else {
					if(this._probMalaltia < vir._probMalaltia) resultat = 1;
				}
			}
        }
        else {
		// comparació per taxa de contagi
			if(this._probContagi > vir._probContagi) resultat = -1;
			else {
				if(this._probContagi < vir._probContagi) resultat = 1;
			}
		}
		return resultat;
	}
}
