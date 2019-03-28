/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eselotto;

import com.sun.istack.internal.logging.Logger;
import java.util.*;
import java.util.logging.Level;

/**
 *
 * @author Alessandro
 */
public class ThGeneraNumeri extends Thread{
    private int contRuote = 0;
    private CDatiCondivisi PtrDati;
    
    public ThGeneraNumeri(CDatiCondivisi dati) {
        PtrDati = dati;
    }
    
    public void run() {
        try {
            Random rand = new Random();
            while(!PtrDati.isFinito()) {
                for(int i = 0; i < PtrDati.getEstrazioni(); i++) {
                    PtrDati.waitSem1();
                    PtrDati.waitSem1();
                        
                    if((PtrDati.isPrimoTrovato()) && (PtrDati.isSecondoTrovato())) {
                        PtrDati.settaRuotaVincente(i - 1);
                    }
                        
                    PtrDati.setPrimoTrovato(false);
                    PtrDati.setSecondoTrovato(false);
                        
                    for(int ii = 0; ii < 5; ii++) {
                        int numeroRand = rand.nextInt(100);
                            
                        PtrDati.aggiungiNumero(ii, numeroRand);
                    }
                    contRuote++;
                    System.out.println(Arrays.toString(PtrDati.getRuota()) + " Ruota numero " + contRuote);
                    System.out.println("-----------");
                    PtrDati.signalSem2();
                    PtrDati.signalSem3();
                    PtrDati.setFinito(true);
                    PtrDati.signalSemFinito();
                }
            }
        } catch (InterruptedException ex) {
                java.util.logging.Logger.getLogger(ThGeneraNumeri.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}