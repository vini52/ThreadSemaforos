package view;

import java.util.concurrent.Semaphore;

import controller.CavaleirosController;

public class Corrida {

	public static void main(String[] args) {
		Semaphore s = new Semaphore(1);
		for(int i = 1; i < 5; i++) {
			Thread c = new CavaleirosController(i, s);
			c.start();
		}

	}

}
