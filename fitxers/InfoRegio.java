package fitxers;

import java.util.ArrayList;
import java.util.List;

// Autor: Guillem Bouzas

public class InfoRegio { // Representació de la informació continguda en un fitxer sobre una regió

   public String nomRegio;
   public int habitantsRegio;
   public float mobInterna;
   public List<String> regionsLimitrofes;
   public List<Float> mobExterna;
   


    public InfoRegio(String nom, int nHabitants, float ratioInterna){ // Constructor
        nomRegio = nom; 
        habitantsRegio = nHabitants;
        mobInterna = ratioInterna;
        regionsLimitrofes = new ArrayList<>();
        mobExterna = new ArrayList<>();
    } 

    public void afegirMobilitat(String veina, float mobExt){ // Per poder omplir els vectors
        regionsLimitrofes.add(veina);
        mobExterna.add(mobExt);
    }

}