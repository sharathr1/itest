package com.ip.itest.java;

class SingleThread extends Thread{  
	  public void run(){  
	    System.out.println("My thread is in running state.");  
	  }   
	  public static void main(String args[]){  
	     SingleThread obj=new SingleThread();   
	     obj.start();  
	  }  
	  
}