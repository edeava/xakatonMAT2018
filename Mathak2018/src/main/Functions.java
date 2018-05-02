package main;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Functions {
<<<<<<< HEAD

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

        for(int i = 0; i < r; i++) {
            for(int j = 0; j < c; j++) {
                System.out.print(mat[i][j]);
            }

            System.out.println();
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
}