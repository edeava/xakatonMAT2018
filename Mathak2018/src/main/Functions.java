package main;

import com.sun.javaws.exceptions.InvalidArgumentException;
import com.sun.tools.corba.se.idl.InvalidArgument;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Functions {

    public static final int INF = Integer.MAX_VALUE;

    public static char[][] ucitaj(String path) {
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

	public static void ispisi(char[][] mat, int rez) {
		System.out.println(rez);

		for(int i = 0; i < mat.length; i++) {
			for(int j = 0; j < mat[i].length; j++) {
				System.out.print(mat[i][j]);
			}

			System.out.println();
		}
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
        int i, j, k;

        for(i = 0; i < len; i++) {
            for(j = 0; j < len; j++) {
                dist[i][j] = graph[i][j];
            }
        }

        for(k = 0; k < len; k++) {
            for(i = 0; i < len; i++) {
                for(j = 0; j < len; j++) {
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
    public static Position breadthFirst(char[][] maze, Position source) throws IllegalArgumentException {
        Queue<Position> queue = new LinkedList<>();
        queue.offer(source);

        maze[source.row][source.column] = '+';

        while(queue.peek() != null) {
            Position current = queue.poll();

            ArrayList<Position> neighbors = getNeighbors(maze, current);

            for(Position neighbor : neighbors) {
                if(maze[neighbor.row][neighbor.column] == 'X') {
                    return neighbor;
                }

                queue.offer(neighbor);

                maze[neighbor.row][neighbor.column] = '+';
            }
        }

        throw new IllegalArgumentException("Najblizi cvor ne postoji");
    }

    private static ArrayList<Position> getNeighbors(char[][] maze, Position current) {
        ArrayList<Position> neighbors = new ArrayList<>();

        // Levo
        if(current.row > 0
                && (maze[current.row - 1][current.column] != '+' || maze[current.row - 1][current.column] != '|')) {
            neighbors.add(new Position(current.row - 1, current.column, current.distance + 1));
        }

        // Gore
        if(current.column > 0
                && (maze[current.row][current.column - 1] != '+' || maze[current.row][current.column - 1] != '-')) {
            neighbors.add(new Position(current.row, current.column - 1, current.distance + 1));
        }

        // Desno
        if(current.row < maze.length - 1
                && (maze[current.row + 1][current.column] != '+' || maze[current.row + 1][current.column] != '|')) {
            neighbors.add(new Position(current.row + 1, current.column, current.distance + 1));
        }

        // Dole
        if(current.column < maze[0].length - 1
                && (maze[current.row][current.column + 1] != '+' || maze[current.row][current.column + 1] != '-')) {
            neighbors.add(new Position(current.row, current.column + 1, current.distance + 1));
        }

        return neighbors;
    }

    public static class Position {

        private int row, column, distance;

        public Position(int row, int column, int distance) {
            this.row = row;
            this.column = column;
            this.distance = distance;
        }

        public int getDistance() {
            return distance;
        }

        @Override
        public String toString() {
            return "(" + row + ", " + column + ")";
        }
    }
}