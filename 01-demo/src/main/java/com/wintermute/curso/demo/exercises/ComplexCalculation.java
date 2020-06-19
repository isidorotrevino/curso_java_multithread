package com.wintermute.curso.demo.exercises;

import java.math.BigInteger;

public class ComplexCalculation {

	public static void main(String[] args) throws InterruptedException {
		System.out.println(new ComplexCalculation().calculateResult(new BigInteger("2"), new BigInteger("10"), new BigInteger("2"), new BigInteger("10")));
	}
	
	public BigInteger calculateResult(BigInteger base1, BigInteger power1, BigInteger base2, BigInteger power2) {
        BigInteger result;
        /*
            Calculate result = ( base1 ^ power1 ) + (base2 ^ power2).
            Where each calculation in (..) is calculated on a different thread
        */
        PowerCalculatingThread thread1 = new PowerCalculatingThread(base1, power1);
        PowerCalculatingThread thread2 = new PowerCalculatingThread(base2, power2);
        try {
        	thread1.start();
        	thread2.start();
			thread1.join();
			thread2.join();
			result = thread1.getResult().add(thread2.getResult());
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
        return result;
    }

    private static class PowerCalculatingThread extends Thread {
        private BigInteger result = BigInteger.ONE;
        private BigInteger base;
        private BigInteger power;
    
        public PowerCalculatingThread(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }
    
        @Override
        public void run() {
        	for(BigInteger i = BigInteger.ZERO; i.compareTo(power)!=0; i = i.add(BigInteger.ONE)) {
        		System.out.println("RESULT "+result+ " BASE "+base);
        		result = result.multiply(base);
        	}
        }
    
        public BigInteger getResult() { return result; }
    }
	
}
