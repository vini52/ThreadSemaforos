package controller;

import java.util.concurrent.Semaphore;

public class Aeroporto extends Thread{
    private int idAviao;
    private Semaphore semaforo;

    public Aeroporto(int idAviao, Semaphore semaforo){
        this.idAviao = idAviao;
        this.semaforo = semaforo;
    }

    @Override
    public void run() {
        try {
            semaforo.acquire();
            manobrar();
            taxiar();
            decolar();
            afastar();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaforo.release();
        }

        System.out.println("O avião " + idAviao + " decolou.");
    }

    private void afastar() {
        int tempo = (int)(Math.random() * 501) + 300;
        System.out.println("O avião " + idAviao + " está afastando e vai levar " + tempo + "ms.");
        try {
            sleep(tempo);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void decolar() {
        int tempo = (int)(Math.random() * 201) + 600;
        System.out.println("O avião " + idAviao + " está decolando e vai levar " + tempo + "ms.");
        try {
            sleep(tempo);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void taxiar() {
        int tempo = (int)(Math.random() * 501) + 500;
        System.out.println("O avião " + idAviao + " está taxiando e vai levar " + tempo + "ms.");
        try {
            sleep(tempo);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void manobrar() {
        int tempo = (int)(Math.random() * 401) + 300;
        System.out.println("O avião " + idAviao + " está manobrando e vai levar " + tempo + "ms.");
        try {
            sleep(tempo);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
