package com.example.autonoleggiofx.Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Auto {
    private final String targa;
    private LocalDate dataNoleggio;
    private final Marca marca;
    private final String modello;
    private Float costoGiornaliero;

    public enum Marca{
        FIAT, FERRARI, LAMBORGHINI
    }


    public Auto(Marca marca, String modello, String dataNoleggio, Float costoGiornaliero) {
        this.targa = generateTarga();
        this.dataNoleggio = InsertDate(dataNoleggio);
        this.marca = marca;
        this.modello = modello;
        this.costoGiornaliero = costoGiornaliero;
    }

    private LocalDate InsertDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(date,formatter);
    }

    public String generateTarga(){
        Random random = new Random();
        StringBuilder targa = new StringBuilder();
        for (int i=0;i<8; i++){
            if (i<2 || i>4) targa.append((char) random.nextInt(65, 90));
            else targa.append(random.nextInt(0, 9));
        }
        return targa.toString();
    }

    public String getTarga() {
        return targa;
    }

    public LocalDate getDataNoleggio() {
        return dataNoleggio;
    }

    public String getMarca() {
        return marca.toString();
    }

    public String getModello() {
        return modello;
    }

    public Float getCostoGiornaliero() {
        return costoGiornaliero;
    }

    @Override
    public String toString() {
        return "Auto{" +
                "targa='" + targa + '\'' +
                ", dataNoleggio=" + dataNoleggio +
                ", marca=" + marca +
                ", modello='" + modello + '\'' +
                ", costoGiornaliero=" + costoGiornaliero +
                '}';
    }
}
