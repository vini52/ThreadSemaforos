package controller;

import java.util.concurrent.Semaphore;

public class CavaleirosController extends Thread {
	private int idCavaleiro;
	private int distAtual;
	private int distTotal;
	private static boolean tocha = true;
	private static boolean pedra = true;
	private static int[] portas = {0, 0, 0, 0};
	private Semaphore semaforo;
	
	public CavaleirosController(int idCavaleiro, Semaphore semaforo) {
		this.idCavaleiro = idCavaleiro;
		this.semaforo = semaforo;
	}
	
	@Override
	public void run() {
		caminhar();
		abrirPortas();
		sortearVencedor();
	}
	
	private void caminhar() {
		int velocidade = (int)(Math.random() * 3) + 2;
		distTotal = 2000;
		distAtual = 0;
		int tempo = 50;
		
		while(distAtual < distTotal) {
			distAtual += velocidade;
			try {
				sleep(tempo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			try {
				semaforo.acquire();
				if(distAtual >= 500 && tocha) {
					System.out.println("CAVALEIRO "
							+ idCavaleiro + " PEGOU A TOCHA!");
					velocidade += 2;
					tocha = false;
				}
				if(distAtual >= 1500 && pedra) {
					System.out.println("CAVALEIRO "
							+ idCavaleiro + " PEGOU A PEDRA!");
					velocidade += 2;
					pedra = false;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				semaforo.release();
			}
			
			System.out.println("Cavaleiro " + idCavaleiro
					+ " velocidade " + velocidade + "m/50ms"
					+ " já percorreu: " + distAtual + "m.");
		}
		System.out.println("Cavaleiro " + idCavaleiro + " chegou na porta.");		
	}
	
	private void abrirPortas() {
		boolean isPorta = true;
		
		while(isPorta) {
			int porta = (int)(Math.random() * 3);
			for(int i = 0; i < 4; i++) {
				if(portas[porta] == 0) {
					portas[porta] = idCavaleiro;
					isPorta = false;
				}
			}
		}
	}
	private void sortearVencedor() {
		int vencedor = (int) Math.random() * 3;
		System.out.println("Vencedor foi o cavaleiro " + portas[vencedor]);
	}
	
}
