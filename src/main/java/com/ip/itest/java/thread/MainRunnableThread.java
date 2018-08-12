package com.ip.itest.java.thread;

import java.util.Arrays;

class HiR implements Runnable {
	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println("HiR");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class HelloR implements Runnable {
	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println("HelloR");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

public class MainRunnableThread {
	public static void main(String[] args) {
		HiR hi = new HiR();
		HelloR hello = new HelloR();
//		hi.start();
//		try {
//			Thread.sleep(10);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		hello.start();
		Thread t1 = new Thread(hi);
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Thread t2 = new Thread(hello);
		t1.start();
		t2.start();
		
	}

}
