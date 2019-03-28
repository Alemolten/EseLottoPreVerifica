/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eselotto;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alessandro
 */
public class ThCercaSecondoNumero extends Thread{
    private CDatiCondivisi PtrDati;
    
    public ThCercaSecondoNumero(CDatiCondivisi dati) {
        PtrDati = dati;
    }
    
    public void run() {
        try {
            while(!PtrDati.isFinito()) {
                PtrDati.waitSem3();
                for(int i = 0; i < 5; i++) {
                    if(PtrDati.restituisciNumero(i) == PtrDati.getSecondoNumero()) {
                    PtrDati.setSecondoTrovato(true);
                    }
                }
            }
                    
            PtrDati.signalSem1();
        } catch (InterruptedException ex) {
            Logger.getLogger(ThCercaSecondoNumero.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
