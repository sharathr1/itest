package com.ip.itest.java.thread;

class Q {
	int num;
	boolean valueSet = false;

	public synchronized void put(int num) throws InterruptedException {
		while (valueSet) {
			wait();
		}
		System.out.println("Put :" + num);
		this.num = num;
		valueSet = true;
		notify();
	}

	// for wait() synchronized is mandatary
	public synchronized void get() throws InterruptedException {
		while (!valueSet) {
			// wait belongs to Object
			wait();
		}
		System.out.println("Get ::" + num);
		valueSet = false;
		// notify belongs to Object
		notify();
	}
}

class Producer implements Runnable {
	Q q;

	public Producer(Q q) {
		this.q = q;
		Thread t = new Thread(this, "Producer");
		t.start();
	}

	public void run() {
		int i = 0;
		while (true) {
			try {
				q.put(++i);
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
	}

}

class Consumer implements Runnable {
	Q q;

	public Consumer(Q q) {
		this.q = q;
		Thread t = new Thread(this, "Consumer");
		t.start();
	}

	public void run() {
		int i = 0;
		while (true) {
			try {
				q.get();
				Thread.sleep(1000);

			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
	}

}

public class InterThreadComm {
	public static void main(String[] args) {
		// Thread t1 = new Thread(() -> {
		// for (int i = 0; i <= 5000; i++) {
		// c.increment();
		// }
		//
		// });

		Q q = new Q();
		new Producer(q);
		new Consumer(q);

	}
}
