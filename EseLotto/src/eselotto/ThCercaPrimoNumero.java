/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eselotto;

import java.util.logging.*;

/**
 *
 * @author Alessandro
 */
public class ThCercaPrimoNumero extends Thread{
    private CDatiCondivisi PtrDati;
    
    public ThCercaPrimoNumero(CDatiCondivisi dati) {
        PtrDati = dati;
    }
    
    public void run() {
        try {
            while(!PtrDati.isFinito()) {
                PtrDati.waitSem2();
                for(int i = 0; i < 5; i++) {
                    if(PtrDati.restituisciNumero(i) == PtrDati.getPrimoNumero()) {
                        PtrDati.setPrimoTrovato(true);
                    }
                }
                
                PtrDati.signalSem1();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(ThCercaPrimoNumero.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}
