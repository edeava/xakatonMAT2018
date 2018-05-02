package org.rafhack.main;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // TODO ucitati ime fajla preko konzole
        char[][] mat = Functions.read("test.in");
        char[][] out = Functions.cloneMatrix(mat);

        int sum = 0;

        List<Functions.Node> visited = new ArrayList<>();
        List<Functions.Node> nodes = Functions.getNodes(mat);

        for(Functions.Node node : nodes) {
            Functions.Node closest = Functions.breadthFirst(mat, node);

            if(visited.contains(closest)) {
                continue;
            }

            Functions.drawPath(out, node, closest);

            sum += closest.getDistance();

            visited.add(node);
        }

        Functions.write(out, sum);
    }
}