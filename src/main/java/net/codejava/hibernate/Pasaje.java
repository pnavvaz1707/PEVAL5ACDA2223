package net.codejava.hibernate;

import javax.persistence.*;

/**
 * Entidad llamada Pasaje que representa la tabla "pasaje"
 */
@Entity
@Table(name = "pasaje")
public class Pasaje {
    /**
     * Campo tipo int y clave primaria con nombre "COD" de la tabla "pasaje"
     */
    @Id
    @Column(name = "COD")
    private int cod;

    /**
     * Campo tipo Pasajero que actúa como clave foránea y sirve para unirlo con la entidad Pasajero
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PASAJERO_COD")
    private Pasajero pasajero;

    /**
     * Campo tipo Vuelo que actúa como clave foránea y sirve para unirlo con la entidad Vuelo
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "IDENTIFICADOR")
    private Vuelo vuelo;

    /**
     * Campo tipo int con nombre "NUMASIENTO" de la tabla "pasaje"
     */
    @Column(name = "NUMASIENTO")
    private int numAsiento;

    /**
     * Campo tipo String con nombre "CLASE" de la tabla "pasaje"
     */
    @Column(name = "CLASE")
    private String clase;

    /**
     * Campo tipo float con nombre "PVP" de la tabla "pasaje"
     */
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

    public Pasajero getPasajero() {
        return pasajero;
    }

    public void setPasajero(Pasajero pasajero) {
        this.pasajero = pasajero;
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
