package main;

public class Functions {
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
