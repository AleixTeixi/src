// Modificacions a càrrec de Guillem Bouzas

public class FamiliaVirus {
// Aquesta classe representa una agrupació de virus té informació conjunta i porta un registre de virus de la mateixa família.

    // Atributs
    String _nomFamilia;
    float _maxVariacio;		// Tant per cent màxim de variació en cas de mutar
    Virus[] _virusFamilia;  // Llista de virus de la família

    public FamiliaVirus (String nom, float maxVar) {
        _nomFamilia = nom;
        _maxVariacio = maxVar;
        _virusFamilia = new Virus[100];
    }

    public float variacioMax() {
    // Pre: cert; Post: retorna la variació màxima de la família
        return _maxVariacio;
    }

    @Override
    public String toString() {
    // Pre: cert; Post: Converteix l'objecte a String
	    return _nomFamilia;
    }
}
