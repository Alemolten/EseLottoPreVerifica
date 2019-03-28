/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eselotto;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alessandro
 */
public class EseLotto {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Scanner input = new Scanner(System.in);
        String ruoteVincenti = "(";
        int contVincite = 0;
        
        System.out.println("Inserisci il numero di estrazioni da effettuare");
        int estrazioni = input.nextInt();
        
        System.out.println("Inserisci i 2 numeri su cui si vuole puntare");
        System.out.println("1)");
        int numero1 = input.nextInt();
        
        System.out.println("2)");
        int numero2 = input.nextInt();
        
        CDatiCondivisi dati = new CDatiCondivisi(estrazioni);
        
        dati.setPrimoNumero(numero1);
        dati.setSecondoNumero(numero2);
        
        ThGeneraNumeri th1 = new ThGeneraNumeri(dati);
        ThCercaPrimoNumero th2 = new ThCercaPrimoNumero(dati);
        ThCercaSecondoNumero th3 = new ThCercaSecondoNumero(dati);
        
        th1.start();
        th2.start();
        th3.start();
        
        try {
            dati.waitSemFinito();
            
            for(int i = 0; i < estrazioni; i++) {
                if(dati.restituisciRuotaVincente(i)) {
                    contVincite++;
                    ruoteVincenti = ruoteVincenti + (i + 1) + " ";
                }
            }
            ruoteVincenti = ruoteVincenti.trim();
            ruoteVincenti = ruoteVincenti + ")";
            
            if(contVincite == 0) {
                System.out.println("Non hai vinto su nessuna ruota");
            }
            else {
                System.out.println("Hai vinto su:" + contVincite + " ruota/e" + ruoteVincenti);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(EseLotto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
