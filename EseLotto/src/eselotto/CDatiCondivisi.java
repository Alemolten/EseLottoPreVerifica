/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eselotto;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

/**
 *
 * @author Alessandro
 */
public class CDatiCondivisi {
    
    private int estrazioni;
    private boolean primoTrovato;
    private boolean secondoTrovato;
    private int [] ruota;
    private boolean[] ruoteVincenti;
    private int primoNumero;
    private int secondoNumero;
    private boolean finito;
    
    public boolean isFinito() {
        return finito;
    }
    
    public void setFinito(boolean finito) {
        this.finito = finito;
    }
    
    private Semaphore sem1;
    private Semaphore sem2;
    private Semaphore sem3;
    private Semaphore semFinito;
    
    public CDatiCondivisi(int estr) {
        estrazioni = estr;
        sem1 = new Semaphore(2);
        sem2 = new Semaphore(0);
        sem3 = new Semaphore(0);
        semFinito = new Semaphore(0);
        primoNumero = 0;
        secondoNumero = 0;
        ruoteVincenti = new boolean[estrazioni];
        for(int ii = 0; ii < estrazioni; ii++) {
            ruoteVincenti[ii] = false;
        }
        ruota = new int[5];
        for(int i = 0; i < 5; i++) {
            ruota[i] = 0;
        }
        primoTrovato = false;
        secondoTrovato = false;
    }
    
    public synchronized int getEstrazioni() {
        return estrazioni;
    }
    
    public synchronized void setEstrazioni(int estrazioni) {
        this.estrazioni = estrazioni;
    }
    
    public synchronized boolean isPrimoTrovato() {
        return primoTrovato;
    }
    
    public synchronized void setPrimoTrovato(boolean primoTrovato) {
        this.primoTrovato = primoTrovato;
    }
    
    public synchronized boolean isSecondoTrovato() {
        return secondoTrovato;
    }
    
    public synchronized void setSecondoTrovato(boolean secondoTrovato) {
        this.secondoTrovato = secondoTrovato;
    }
    
    public synchronized void aggiungiNumero(int posizione, int numero) {
        ruota[posizione] = numero;
    }
    
    public synchronized int restituisciNumero(int posizione) {
        return ruota[posizione];
    }
    
    public synchronized int[] getRuota() {
        return ruota;
    }
    
    public synchronized void setRuota(int[] ruota) {
        this.ruota = ruota;
    }
    
    public synchronized int getPrimoNumero() {
        return primoNumero;
    }
    
    public synchronized void setPrimoNumero(int primoNumero) {
        this.primoNumero = primoNumero;
    }
    
    public synchronized int getSecondoNumero() {
        return secondoNumero;
    }
    
    public synchronized void setSecondoNumero(int secondoNumero) {
        this.secondoNumero = secondoNumero;
    }
    
    public void waitSem1() throws InterruptedException{
        sem1.acquire();
    }
    
    public void signalSem1() {
        sem1.release();
    }
    
    public void waitSem2() throws InterruptedException {
        sem2.acquire();
    }
    
    public void signalSem2() {
        sem2.release();
    }
    
    public void waitSem3() throws InterruptedException {
        sem3.acquire();
    }
    
    public void signalSem3() {
        sem3.release();
    }
    
    public void settaRuotaVincente(int pos) {
        ruoteVincenti[pos] = true;
    }
    
    public boolean restituisciRuotaVincente(int pos) {
        return ruoteVincenti[pos];
    }
    
    public void waitSemFinito() throws InterruptedException {
        semFinito.acquire();
    }
    
    public void signalSemFinito() {
        semFinito.release();
    }
}
