package main;

public class Main {

    public static void main(String[] args) {
        // TODO ucitati ime fajla preko konzole
        char[][] mat = Functions.ucitaj("test.in");

        Functions.Position closest = Functions.breadthFirst(mat, new Functions.Position(1, 7, 0));

        System.out.println("Najblizi za (1, 7): " + closest.toString() + "\nUdaljenost: " + closest.getDistance());
    }
}