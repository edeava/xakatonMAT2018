package org.rafhack.main;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        // TODO ucitati ime fajla preko konzole
        char[][] mat = Functions.read("test.in");

        List<Functions.Node> nodes = Functions.getNodes(mat);

        for(Functions.Node node : nodes) {
            Functions.Node closest = Functions.breadthFirst(mat, node);
            System.out.println("Najblizi za " + node.toString() + ": " + closest.toString() + " udaljenost: " + closest.getDistance());
        }
    }
}