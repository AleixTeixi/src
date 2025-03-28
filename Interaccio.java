// Creada per Arnau K. Deprez al 21/03/25

import java.util.Scanner;

public class Interaccio {
// Classe que maneja l'interacció amb l'usuari i mostra la GUI (més endavant)

    private Scanner _scanner;

    public Interaccio() {
        _scanner = new Scanner(System.in);
    }

    public void mostrarText(String text){
    // Pre: String no buit
    // Post: mostra string a l'usuari
        System.out.println(text);   // Per ara x terminal
    }

    public void mostrarResultats(int dia, Virus[] virus, Regio[] regions) {
    // Pre: dades d'un dia
    // Post: mostra "stats" a l'usuari
        System.out.println("Dia X:");
        // Mostra els resultats de la simulació per a cada dia
        // ...
    }

    public String rebreInput(String missatge) {
    // Pre: String amb el missatge a mostrar a l'usuari per demanar input (pot ser buit)
    // Post: retorna l'input de l'usuari (String)
        System.out.print(missatge);
        return _scanner.nextLine();
    }
}