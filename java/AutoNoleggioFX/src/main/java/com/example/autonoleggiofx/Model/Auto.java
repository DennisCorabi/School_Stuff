package com.example.autonoleggiofx.Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Auto {

    private String targa;
    private String dataNoleggio = "Non noleggiata";
    private Boolean isNoleggiata = false;
    private String dataUltimaRestituzione = "Nuova";
    private Produttore produttore;
    private String modello;
    private Float costoGiornaliero;
    private Integer volteNoleggiate = 0;
    private long secondiInNoleggio = 0;
    private long ricavoTotale = 0;

    public enum Produttore {
        FIAT, FERRARI, LAMBORGHINI
    }

    public Auto(Produttore produttore, String modello, Float costoGiornaliero) {
        this.targa = generateTarga();
        this.produttore = produttore;
        this.modello = modello;
        this.costoGiornaliero = costoGiornaliero;
    }

    //TODO: implementare anche l'uso di questo costruttore (in base a se la targa è inserita o no);
    public Auto(String targa, Produttore produttore, String modello, Float costoGiornaliero) {
        this.targa = targa;
        this.produttore = produttore;
        this.modello = modello;
        this.costoGiornaliero = costoGiornaliero;
    }


    private String InsertDate() {
        LocalDateTime dateNow = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return formatter.format(dateNow);
    }


    public void setDataUltimaRestituzione() {
        this.dataUltimaRestituzione = InsertDate();
        this.isNoleggiata=false; //se inserisco la data in cui è stata restituita, allora si presuppone che essa sia stata restituita
    }

    public void clearDataNoleggio(){
        this.dataNoleggio="Non noleggiata";
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
        }while(!AutoManager.IsTargaUsable(targa.toString()));    //fino a quando non inserisco una targa accettabile (che altre macchine non hanno)

        return targa.toString();
    }

    public String getTarga() {
        return targa;
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
    public Boolean getNoleggiata() {
        return isNoleggiata;
    }
    public String getDataUltimaRestituzione() {
        return dataUltimaRestituzione;
    }
    public String getDataNoleggio() {
        return dataNoleggio;
    }
    public long getSecondiInNoleggio() {
        return secondiInNoleggio;
    }
    public Integer getVolteNoleggiate() {
        return volteNoleggiate;
    }
    public long getRicavoTotale() {
        return ricavoTotale;
    }

    public void setTarga(String targa) {
        this.targa = targa;
    }
    public void setProduttore(Produttore produttore) {
        this.produttore = produttore;
    }
    public void setModello(String modello) {
        this.modello = modello;
    }
    public void setCostoGiornaliero(Float costoGiornaliero) {
        this.costoGiornaliero = costoGiornaliero;
    }
    public void increaseSecondiInNoleggio(long secondi) {
        this.secondiInNoleggio+=secondi;
        this.ricavoTotale+= secondi*(costoGiornaliero/86400L);       //già che ci sono, ogni qualvolta questa funzione viene chiamata (ovvero quando la macchina viene restituita), incremento il ricavo totale.
    }
    public void increaseVolteNoleggiate() {
        this.volteNoleggiate++;
    }

    public void setNewDataNoleggio(){
        this.dataNoleggio = InsertDate();
        this.isNoleggiata=true;     //se inserisco la data in cui è stata noleggiata, allora si presuppone che essa sia noleggiata
    }

    @Override
    public String toString() {
        return "Auto{" +
                "targa='" + targa + '\'' +
                ", dataNoleggio='" + dataNoleggio + '\'' +
                ", isNoleggiata=" + isNoleggiata +
                ", dataUltimaRestituzione='" + dataUltimaRestituzione + '\'' +
                ", produttore=" + produttore +
                ", modello='" + modello + '\'' +
                ", costoGiornaliero=" + costoGiornaliero +
                '}';
    }

}
