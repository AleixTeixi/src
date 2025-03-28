//Modificacions a càrrec de Guillem Bouzas

import java.util.Random;

public class VirusARN extends Virus{
//Aquesta classe representa un virus mutable, és per això que serà l'encarregada de gestionar les diferents mutacions que pot patir un virus

	// Atributs
	private float _pMutErrorCopia; // Prob. de mutació per error de còpia
	private float _pMutErrorCoincidencia; // Prob. de mutació per coincidència
	private int _nMutacions; // Nombre de vegades en que un virus ha mutat

	public VirusARN(String nom, float pMal, float tInc, float tLat, float pMor, float tCon, float pCon, float tImm, FamiliaVirus fam, float pErrC, float pErrcCoinc){
		super(nom, pMal, tInc, tLat, pMor, tCon, pCon, tImm, fam);
		_pMutErrorCopia = pErrC;
		_pMutErrorCoincidencia = pErrcCoinc;
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
		float xAleatori = generadorAleatoris.nextFloat(margeSup - margeInf) + margeInf;

		// Modifiquem els paràmetres
		float novaPmal = _probMalaltia + _probMalaltia * xAleatori;
		xAleatori = generadorAleatoris.nextFloat(margeSup - margeInf) + margeInf; // Obtenim un altre nombre aleatori

		float novaPmor = _taxaMort + _taxaMort * xAleatori;
		xAleatori = generadorAleatoris.nextFloat(margeSup - margeInf) + margeInf; // Obtenim un altre nombre aleatori

		float nouTcon = _tempsContagi + _tempsContagi * xAleatori;
		xAleatori = generadorAleatoris.nextFloat(margeSup - margeInf) + margeInf; // Obtenim un altre nombre aleatori

		float nouPcon = _probContagi + _probContagi * xAleatori;

		// El virus obté un nom diferent
		_nMutacions ++; // Sumem 1 al nombre de mutacions d'aquest virus
		String nomMutacio = _nom + "_" + _nMutacions;

		// Retornem el nou virus mutat
		VirusARN virusMutat = new VirusARN(nomMutacio, novaPmal, _tempsIncub, _tempsLatencia, novaPmor, nouTcon, nouPcon, _tempsImmune, _familia, _pMutErrorCopia, _pMutErrorCoincidencia);

		return virusMutat;
	}
	
	public VirusARN mutarPerCoincidencia(VirusARN a){
	//Pre: els dos virus són de la mateixa família; Post: retorna un virus mutat a partir de dos virus "pare"
		
		return VirusARN();
	}
}
