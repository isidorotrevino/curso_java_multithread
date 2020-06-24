package com.wintermute.curso.demo.demos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Demo05 {

	public static void main(String[] args) throws InterruptedException {
		List<Long> inputNumbers = Arrays.asList(0L, 3435L, 35435L, 2324L, 4646L, 23L, 2435L, 5566L);

		List<FactorialThread> threads = new ArrayList<>();

		for (long inputNumber : inputNumbers) {
			threads.add(new FactorialThread(inputNumber));

		}
		for (FactorialThread t : threads) {
			t.start();
		}
		for (FactorialThread t : threads) {

			t.join(2000);
		}

		for (int i = 0; i < inputNumbers.size(); i++) {
			FactorialThread factorialThread = threads.get(i);
			if (factorialThread.isFinished()) {
				System.out.println(
						"Factorial of " + factorialThread.getInputNumber() + " = " + factorialThread.getResult());
			} else {
				System.out.println("It's still going " + factorialThread.getInputNumber());
			}
		}
	}
}
