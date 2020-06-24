package com.wintermute.curso.demo.demos;



public class Demo10 {

	public static void main(String args[]) throws InterruptedException {
		InventoryCounter inventoryCounter = new InventoryCounter();
		IncrementingThread incrementingThread = new IncrementingThread(inventoryCounter);
		DecrementingThread decrementingThread = new DecrementingThread(inventoryCounter);
		
		incrementingThread.start();
		
		decrementingThread.start();
		incrementingThread.join();
		decrementingThread.join();
		System.out.println("Counter "+inventoryCounter.getItems());
	}
	
	public static class IncrementingThread extends Thread {

		private InventoryCounter inventoryCounter;

		public IncrementingThread(InventoryCounter inventoryCounter) {
			this.inventoryCounter = inventoryCounter;
		}

		@Override
		public void run() {
			for (int i = 0; i < 10000; i++) {
				this.inventoryCounter.increment();
			}
		}

	}

	public static class DecrementingThread extends Thread {

		private InventoryCounter inventoryCounter;

		public DecrementingThread(InventoryCounter inventoryCounter) {
			this.inventoryCounter = inventoryCounter;
		}

		@Override
		public void run() {
			for (int i = 0; i < 10000; i++) {
				this.inventoryCounter.decrement();
			}
		}

	}

	private static class InventoryCounter {
		private int items = 0;

		public synchronized void increment() {
			items++;
		}

		public synchronized void decrement() {
			items--;
		}

		public synchronized int getItems() {
			return items;
		}
	}
}
