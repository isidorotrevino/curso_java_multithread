package com.wintermute.curso.demo.demos;

import java.util.Random;

public class Demo12 {

	public static void main(String args[]) throws InterruptedException {
		Metrics metrics = new Metrics();
		BusinessLogic blThread1 = new BusinessLogic(metrics);
		BusinessLogic blThread2 = new BusinessLogic(metrics);
		MetricsPrinter printer = new MetricsPrinter(metrics);
		blThread1.start();
		blThread2.start();
		printer.start();
		
				
	}
	
	public static class MetricsPrinter extends Thread{
		private Metrics metrics;
		public MetricsPrinter(Metrics metrics) {
			this.metrics = metrics;
		}

		@Override
		public void run() {
			while(true) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				double currentAverage = metrics.getAverage();
				System.out.println("Average: "+currentAverage);
			}
		}
	}

	public static class Metrics {
		private long count = 0;
		private volatile double average = 0.0;

		public synchronized void addSample(long sample) {
			double currentSum = average * count;
			count++;
			average = (currentSum + sample) / count;
		}

		public double getAverage() {
			return average;
		}
	}

	public static class BusinessLogic extends Thread {
		private Metrics metrics;
		private Random random = new Random();

		public BusinessLogic(Metrics metrics) {
			this.metrics = metrics;
		}

		@Override
		public void run() {
			while (true) {
				long start = System.currentTimeMillis();
				try {
					Thread.sleep(random.nextInt(10));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				long end = System.currentTimeMillis();
				metrics.addSample(end - start);
			}
		}

	}
}
