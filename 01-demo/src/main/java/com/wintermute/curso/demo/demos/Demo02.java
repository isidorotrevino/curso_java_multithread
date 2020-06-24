package com.wintermute.curso.demo.demos;

public class Demo02 {

	 public static void main(String[] args) throws InterruptedException {
	        Thread thread = new Thread(new NewThread());
	        thread.start();
	        
	    }
	 private static class NewThread extends Thread{

		@Override
		public void run() {
			 System.out.println("We are in thread: "+this .getName());
		}
		 
	 }
	
}
