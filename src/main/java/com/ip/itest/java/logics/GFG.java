package com.ip.itest.java.logics;

public class GFG {
	// Driver code
	public static void main(String args[]) {
		int n = 7;
		int maxRows = 6;
		int r, num;
		for (int i = 0; i <= maxRows; i++) {
		    num = 1;
		    r = i + 1;
//		    //pre-spacing
//		    for (int j = maxRows - i; j > 0; j--) {
//		        System.out.print(" ");
//		    }
		    for (int col = 0; col <= i; col++) {
		        if (col > 0) {
		            num = num * (r - col) / col;
		        }
		        System.out.print(num + " ");
		    }
		    System.out.println();
		}
		
		//printPascal(n);
		System.out.println(binomialCoeff(4, 0));
		System.out.println(binomialCoeff(4, 1));
		System.out.println(binomialCoeff(4, 2));
		System.out.println(binomialCoeff(4, 3));
		System.out.println(binomialCoeff(4, 4));

	}

	static void printPascal(int n) {

		// Iterate through every line
		// and print entries in it
		for (int line = 0; line < n; line++) {
			// Every line has number of
			// integers equal to line number
			for (int i = 0; i <= line; i++)
				System.out.print(binomialCoeff(line, i) + " ");

			System.out.println();
		}
	}

	static int binomialCoeff(int n, int k) {
		if (n == 6) {
			System.out.println("=========");
		}
		int res = 1;
		if (k > n - k)
			k = n - k;
		for (int i = 0; i < k; ++i) {
			res *= (n - i);
			res /= (i + 1);
		}
		return res;
	}
}