/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package programmamoto;
import motoclasse.Moto;
import java.util.Vector;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author dennis
 */
public class ProgrammaMoto {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException{
        Random random = new Random();
        Vector <Moto>vettoremoto = new Vector<>();      
        int numeromoto=4, distanza=10, secondipassati=0;
        boolean vincitore=false;
        
        
        for (int i=0;i<numeromoto;i++){
            vettoremoto.add(new Moto(i+1));
        }
        
        do{
            for (Moto moto: vettoremoto){
                boolean result;
                do{
                    result=moto.accellera(random.nextInt(-10,10));
                }while(result!=true);
                
                System.out.print("stato moto "+moto.getId()+": "+moto.getVel_now()+"    ");
                moto.aggiornaKmPercorsi();
                System.out.println("distanza percorsa: "+moto.getKm_percorsi());
                
                if (moto.getKm_percorsi()>=distanza){
                    vincitore=true;
                    System.out.println("vince la gara la moto numero "+moto.getId()+" in "+secondipassati+" secondi, con una velocita' media di "+(moto.getKm_percorsi()/secondipassati)+" Km/s!");
                    break;
                }

            }
            System.out.println("\n\n");
            TimeUnit.SECONDS.sleep(1);
            secondipassati++;
            
        }while(vincitore!=true);
        
    }
}

