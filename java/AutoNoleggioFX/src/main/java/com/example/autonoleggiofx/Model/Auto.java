package com.example.autonoleggiofx.Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Auto {

    private final String targa;
    private String dataNoleggio;
    private final Produttore produttore;
    private final String modello;
    private final Float costoGiornaliero;

    public enum Produttore {
        FIAT, FERRARI, LAMBORGHINI
    }


    public Auto(Produttore produttore, String modello, String dataNoleggio, Float costoGiornaliero) {
        this.targa = generateTarga();
        this.dataNoleggio = InsertDate(dataNoleggio);
        this.produttore = produttore;
        this.modello = modello;
        this.costoGiornaliero = costoGiornaliero;
    }

    public Auto(String targa, String dataNoleggio, Produttore produttore, String modello, Float costoGiornaliero) {
        this.targa = targa;
        this.dataNoleggio = dataNoleggio;
        this.produttore = produttore;
        this.modello = modello;
        this.costoGiornaliero = costoGiornaliero;
    }

    private String InsertDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(date,formatter).toString();
    }


    public static String generateTarga(){
        Random random = new Random();
        StringBuilder targa = new StringBuilder();
        final int GRANDEZZA_TARGA=8;

        do {
            for (int i = 0; i < GRANDEZZA_TARGA; i++) {
                if (i < 2 || i > 4) targa.append((char) random.nextInt(65, 90));
                else targa.append(random.nextInt(0, 9));
            }
        }while(!AutoManager.IsTargaUsable(targa.toString()));        //fino a quando non inserisco una targa accettabile (che altre macchine non hanno)

        return targa.toString();
    }

    public String getTarga() {
        return targa;
    }

    public String getDataNoleggio() {
        return dataNoleggio;
    }

    public Produttore getProduttore() {
        return produttore;
    }

    public String getModello() {
        return modello;
    }

    public Float getCostoGiornaliero() {
        return costoGiornaliero;
    }

    public void setDataNoleggio(String dataNoleggio) {
        this.dataNoleggio = InsertDate(dataNoleggio);
    }

    @Override
    public String toString() {
        return "Auto{" +
                "targa='" + targa + '\'' +
                ", dataNoleggio=" + dataNoleggio +
                ", produttore=" + produttore +
                ", modello='" + modello + '\'' +
                ", costoGiornaliero=" + costoGiornaliero +
                '}';
    }
}
