package com.ip.itest.java.logics;

public class BinomialCoeff {
	public static void main(String[] args) {
		int maxr = 4;
		int num, r;
	//	System.out.println(4 * (5 - 2) / 2);
		for (int i = 0; i < maxr; i++) {
			num = 1;
			r = i + 1;
			for (int j = 0; j < maxr-r; j++) {
				System.out.print(" ");
			}
			for (int c = 0; c < r; c++) {
				if (c > 0) {
					num = num * (r - c) / c;
				}
				System.out.print(num + " ");
			}
			System.out.println();
		}

	}
}
/**
 * 
 * 1 1 1 1 2 1 1 3 3 1 1 4 6 4 1 1 5 10 10 5 1 1 6 15 20 15 6 1
 * 
 */