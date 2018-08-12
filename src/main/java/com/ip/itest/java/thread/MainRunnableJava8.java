package com.ip.itest.java.thread;

import java.util.Arrays;

public class MainRunnableJava8 {
	public static void main(String[] args) throws InterruptedException {

		Runnable obj1 = () -> {
			for (int i = 0; i < 5; i++) {
				System.out.println("HiR");
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		};
		Thread t1 = new Thread(() -> {
			for (int i = 0; i < 5; i++) {
				System.out.println("HiR");
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		t1.start();
		Thread t2 = new Thread(() -> {
			for (int i = 0; i < 5; i++) {
				System.out.println("HelloR "+Thread.currentThread().getName() + " :: "+Thread.currentThread().getPriority());
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		/**
		 *  Thread name Thread-0,Thread-1,..
		 *  new Thread(runnable,"name")
		 */
		System.out.println(t2.getName());
		t2.setName("SR");
		System.out.println(t2.getName());

		/**
		 *  Thread Priority  0(low) - 10(high)  ;   5(Normal) - default
		 *  t2.setPriority(10);  or  t2.setPriority(thrad.min_prority); (max /normal)
		 *  new Thread(runnable,"name")
		 */
		System.out.println(t2.getPriority());
		t2.setPriority(10);
		System.out.println(t2.getPriority());
		
		
		t2.start();
		
		/** Join & Is Alive **/
		System.out.println(t1.isAlive());
		t1.join();
		System.out.println(t1.isAlive());
		t2.join();

		System.out.println("End");
	}

}
