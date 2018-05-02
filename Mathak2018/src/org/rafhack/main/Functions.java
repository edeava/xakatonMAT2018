package org.rafhack.main;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Functions {

    public static final int INF = Integer.MAX_VALUE;

    public static char[][] read(String path) {
        Scanner s;

        try {
            s = new Scanner(new File(path));
        }catch(IOException e) {
            return new char[0][0];
        }

        int r = s.nextInt();
        int c = s.nextInt();

        char[][] mat = new char[r][c];

        for(int i = 0; i < r; i++) {
            String line = s.nextLine();

            if(line.length() == 0) {
                i--;
                continue;
            }

            for(int j = 0; j < c; j++) {
                mat[i][j] = line.charAt(j);
            }
        }

        return mat;
    }

	public static void write(char[][] mat, int res) {
		System.out.println(res);

		for(int i = 0; i < mat.length; i++) {
			for(int j = 0; j < mat[i].length; j++) {
				System.out.print(mat[i][j]);
			}

			System.out.println();
		}
	}

    public static List<Node> getNodes(char[][] mat) {
        List<Node> nodes = new ArrayList<>();

        for(int i = 0; i < mat.length; i++) {
            for(int j = 0; j < mat[i].length; j++) {
                if(mat[i][j] == 'X') {
                    nodes.add(new Node(i, j, 0, null));
                }
            }
        }

        return nodes;
    }

	/**
     * Format ulaznog/izlaznog grafa
     *
     * {
     * {0, X, X, X},
     * {X, 0, X, X},
     * {X, X, 0, X},
     * {X, X, X, 0}
     * }
     *
     * gde vazi:
     * 0    - udaljenost cvora od samog sebe
     * X    - udaljenost izmedju dva cvora
     * INF  - cvorovi nisu spojeni
	 */
    public static int[][] floydWarshall(int graph[][])  {
        int len = graph.length;
        int dist[][] = new int[len][len];

        for(int i = 0; i < len; i++) {
            for(int j = 0; j < len; j++) {
                dist[i][j] = graph[i][j];
            }
        }

        for(int k = 0; k < len; k++) {
            for(int i = 0; i < len; i++) {
                for(int j = 0; j < len; j++) {
                    if(dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        return dist;
    }

    /**
     * Format ulazne matrice
     *
     * +-+-+-+-+-+-+-+-+-+
     * |     |X| |X|     |
     * + + + + + + + + + +
     * |                X|
     * + + + + + + + + + +
     * |X                |
     * + + + + + + + + + +
     * |     |X| |X|     |
     * +-+-+-+-+-+-+-+-+-+
     *
     * gde vazi:
     * X    - cvor u grafu
     * -    - horizontalna barijera (put ne sme da prodje vertikalno)
     * |    - vertikalna barijera (put ne sme da prodje horizontalno)
     * +    - barijera (put ne sme da prodje ni u kom pravcu)
     */
    public static Node breadthFirst(char[][] original, Node source) throws IllegalArgumentException {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(source);

        char[][] mat = cloneMatrix(original);

        mat[source.row][source.column] = '+';

        while(queue.peek() != null) {
            Node current = queue.poll();

            List<Node> neighbors = getNeighbors(mat, current);

            for(Node neighbor : neighbors) {
                if(mat[neighbor.row][neighbor.column] == 'X') {
                    original[neighbor.row][neighbor.column] = '+';
                    return neighbor;
                }

                queue.offer(neighbor);

                mat[neighbor.row][neighbor.column] = '+';
            }
        }

        throw new IllegalArgumentException("Najblizi cvor ne postoji");
    }

    public static void drawPath(char[][] mat, Node destination, Node source) {
        if(source.parent == null || source.parent.row == destination.row && source.parent.column == destination.column) {
            return;
        }

        mat[source.parent.row][source.parent.column] = '.';

        drawPath(mat, destination, source.parent);
    }

    public static char[][] cloneMatrix(char[][] in) {
        int r = in.length;
        int c = in[0].length;

        char[][] out = new char[in.length][in[0].length];

        for(int i = 0; i < r; i++) {
            for(int j = 0; j < c; j++) {
                out[i][j] = in[i][j];
            }
        }

        return out;
    }

    private static List<Node> getNeighbors(char[][] maze, Node current) {
        List<Node> neighbors = new ArrayList<>();

        // Dole
        if(current.column < maze[0].length - 1
                && (maze[current.row][current.column + 1] != '+' && maze[current.row][current.column + 1] != '|')) {
            neighbors.add(new Node(current.row, current.column + 1, current.distance + 1, current));
        }

        // Desno
        if(current.row < maze.length - 1
                && (maze[current.row + 1][current.column] != '+' && maze[current.row + 1][current.column] != '-')) {
            neighbors.add(new Node(current.row + 1, current.column, current.distance + 1, current));
        }

        // Gore
        if(current.column > 0
                && (maze[current.row][current.column - 1] != '+' && maze[current.row][current.column - 1] != '|')) {
            neighbors.add(new Node(current.row, current.column - 1, current.distance + 1, current));
        }

        // Levo
        if(current.row > 0
                && (maze[current.row - 1][current.column] != '+' && maze[current.row - 1][current.column] != '-')) {
            neighbors.add(new Node(current.row - 1, current.column, current.distance + 1, current));
        }

        return neighbors;
    }

    public static class Node {

        private int row, column, distance;
        private Node parent;

        Node(int row, int column, int distance, Node parent) {
            this.row = row;
            this.column = column;
            this.distance = distance;
            this.parent = parent;
        }

        public int getDistance() {
            return distance;
        }

        @Override
        public String toString() {
            return "(" + row + ", " + column + ")";
        }

        @Override
        public boolean equals(Object obj) {
            if(obj instanceof Node) {
                Node node = (Node) obj;

                return node.row == this.row && node.column == this.column;
            }

            return false;
        }
    }
}