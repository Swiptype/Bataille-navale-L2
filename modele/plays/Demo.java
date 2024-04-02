package modele.plays;

import modele.game.BatailleNavale;
import modele.player.*;
//import java.util.Random;

import java.util.Scanner;

public class Demo {
    public static void main(String[] args) {
        //Random rand = new Random(123);
        Scanner scanner = new Scanner(System.in);
        Player j1 = new Humain("Alexis", scanner);
        Player j2 = new Humain("Theo", scanner);
        BatailleNavale bataille = new BatailleNavale(j1, j2);
        Orchestrator manager = new Orchestrator(bataille);
        manager.play();
        scanner.close();
    }
}
