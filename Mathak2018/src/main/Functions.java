package main;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Functions {

    public static final int INF = Integer.MAX_VALUE;

    public static char[][] ucitaj(String path) {
        Scanner s;

        try {
            s = new Scanner(new File(path));
        }catch(IOException e) {
            return null;
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

	public static void ispisiResenje(char mat[][], int rez) {
		System.out.println(rez);

		for(int i=0;i<mat.length;i++) {
			for(int j=0;j<mat[i].length;j++) {
				System.out.print(mat[i][j]);
			}
			System.out.println("");
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
}