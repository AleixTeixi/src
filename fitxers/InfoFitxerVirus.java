package fitxers;

import java.util.List;

import virus.FamiliaVirus;
import virus.VirusADN;
import virus.VirusARN;

// Autor: Guillem Bouzas

public class InfoFitxerVirus { // Aquesta classe serveix per retornar la informació continguda dins el fixter amb la informació dels virus
    
    public List<FamiliaVirus> families;
    public List<VirusADN> virusAdn;
    public List<VirusARN> virusArn;

   public InfoFitxerVirus(List<FamiliaVirus> fam, List<VirusADN> virAd, List<VirusARN> virAr){families = fam; virusAdn = virAd; virusArn = virAr;} // Constructor
}
