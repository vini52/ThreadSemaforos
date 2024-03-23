package view;

import controller.Aeroporto;

import java.util.concurrent.Semaphore;

public class AeroportoMain {
    public static void main(String[] args) {
        Semaphore  norte = new Semaphore(1);
        Semaphore  sul = new Semaphore(1);
        for(int i = 0; i < 12; i++){
            int pista = (int) Math.random() * 2;
            if(pista == 1){
                Thread aviao = new Aeroporto(i+1, sul);
                aviao.start();
            } else{
                Thread aviao = new Aeroporto(i+1, norte);
                aviao.start();
            }
        }
    }
}
