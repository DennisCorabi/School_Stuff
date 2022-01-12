/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package motoclasse;

import java.util.Random;

/**
 *
 * @author dennis
 * Classe che definisce una moto; essa è comprensiva di tutti i parametri e metodi di una comune moto
 */


public class Moto {
    private String marca;
    private String modello;
    private String colore;
    private int vel_max;
    private int vel_now;
    private float km_percorsi;
    private int id;

    /**
     * 
     *
     * <p>costruttore completo della classe</p>
     */
    public Moto(String marca, String modello, String colore, int vel_max, int vel_now) {
        this.marca = marca;
        this.modello = modello;
        this.colore = colore;
        this.vel_max = vel_max;
        this.vel_now = vel_now;
        this.km_percorsi = 0;
      
       
    }
    
    /**
     * <p>costruttore della classe solo con lo stretto necessario
     */

    public Moto() {
        this.km_percorsi=0;
        this.vel_now=0;
        this.vel_max=50;
        this.id=0;
    }

    /**
     * 
     * <p>costruttore della classe con un id noto: viene utilizzata all'interno della classe principale del programma
     * 
     */
    public Moto(int id) {
        this.km_percorsi=0;
        this.vel_now=0;
        this.vel_max=50;
        this.id = id;
    }
    
    

    
     /**
     * 
     * <p>funzione che permette di aumentare la velocità a cui una moto sta andando. NOTA: E' MISURATA IN KM/H</p>
     * 
     */
    public boolean accellera(int accelleration){
        if (vel_now+accelleration>vel_max || vel_now+accelleration<0){
            //System.out.println("velocita' oltre i limit!");
            return false;
        }
        else{          
            vel_now+=accelleration;
            //System.out.println("passato "+id+" !"+ vel_now);
            return true;
        }
    }
    
     /**
     * 
     * <p>funzione che permette di calcolare quanti chilometri la moto ha percorso. 
     * NOTA: essendo l'esecuzione del programma troppo lenta mettendo la distanza calcolata in secondi (vel_now/3600), ho ridotto il valore per cui la velocità viene divisa.
     * 
     */
    public void aggiornaKmPercorsi(){
        km_percorsi+=(float) vel_now/30;
        
    }
    
    

    public int getVel_now() {
        return vel_now;
    }

    public float getKm_percorsi() {
        return km_percorsi;
    }

    public int getId() {
        return id;
    }
    
    
    
    
    
    
       
}
