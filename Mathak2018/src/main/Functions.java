package main;

public class Functions {
	public static void ispisiResenje(char mat[][], int n, int m, int rez) {
		System.out.println(rez);
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				System.out.print(mat[i][j]);
			}
			System.out.println("");
		}
	}
}
