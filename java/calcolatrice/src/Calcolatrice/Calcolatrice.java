/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Calcolatrice;

/**
 *
 * @author dennis
 */
public class Calcolatrice {
    private float num1;
    private float num2;
    private Character operazione;
    
    public float somma(){
        return (num1+num2);
    }
    public float sottrazione(){
        return (num1-num2);
    }
    public  float moltiplicazione(){
        return (num1*num2);
    }
    
    public  float divisione(){
        return (num1/num2);
    }
    
    public Float percetage(){
        return num1/100;
    }
    
    public void setNum1(Character operator, float value){
        this.num1=value;
        operazione=operator;
    }
    
    public void setNum2(float value){
        this.num2=value;
    }
    
    public float getResult(){
        float risultato=0f;
        switch(operazione){
            case '+' -> risultato=somma();
            case '-' -> risultato=sottrazione();
            case '*' -> risultato=moltiplicazione();
            case '/' -> risultato=divisione();
        }
        return risultato;
    }
    
    
}
