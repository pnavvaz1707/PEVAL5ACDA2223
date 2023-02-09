package net.codejava.hibernate;

import javax.persistence.*;

@Entity
@Table(name = "pasaje")
public class Pasaje {
    @Id
    @Column(name = "COD")
    private int cod;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PASAJERO_COD")
    private Pasajero pasajeroCod;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "IDENTIFICADOR")
    private Vuelo vuelo;
    @Column(name = "NUMASIENTO")
    private int numAsiento;
    @Column(name = "CLASE")
    private String clase;
    @Column(name = "PVP")
    private float pvp;

    public Pasaje() {
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public Pasajero getPasajeroCod() {
        return pasajeroCod;
    }

    public void setPasajeroCod(Pasajero pasajeroCod) {
        this.pasajeroCod = pasajeroCod;
    }

    public Vuelo getVuelo() {
        return vuelo;
    }

    public void setVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
    }

    public int getNumAsiento() {
        return numAsiento;
    }

    public void setNumAsiento(int numAsiento) {
        this.numAsiento = numAsiento;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public float getPvp() {
        return pvp;
    }

    public void setPvp(float pvp) {
        this.pvp = pvp;
    }
}
