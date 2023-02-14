package net.codejava.hibernate;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

/**
 * Entidad llamada Vuelo que representa la tabla "vuelo"
 */
@Entity
@Table(name = "vuelo")
public class Vuelo {
    /**
     * Campo tipo String y clave primaria con nombre "IDENTIFICADOR" de la tabla "vuelo"
     */
    @Id
    @Column(name = "IDENTIFICADOR")
    private String identificador;

    /**
     * Campo tipo String con nombre "AEROPUERTO_ORIGEN" de la tabla "vuelo"
     */
    @Column(name = "AEROPUERTO_ORIGEN")
    private String aeropuertoOrigen;

    /**
     * Campo tipo String con nombre "AEROPUERTO_DESTINO" de la tabla "vuelo"
     */
    @Column(name = "AEROPUERTO_DESTINO")
    private String aeropuertoDestino;

    /**
     * Campo tipo String con nombre "TIPO_VUELO" de la tabla "vuelo"
     */
    @Column(name = "TIPO_VUELO")
    private String tipo;

    /**
     * Campo tipo String con nombre "FECHA_VUELO" de la tabla "vuelo"
     */
    @Column(name = "FECHA_VUELO")
    private Date fecha;

    /**
     * Campo tipo Set<Pasajes> mapeado por el campo "vuelo" del objeto Pasaje
     */
    @OneToMany(mappedBy = "vuelo", cascade = CascadeType.ALL)
    private Set<Pasaje> pasajes;

    /**
     * Constructor por defecto de la clase Vuelo
     */
    public Vuelo() {
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getAeropuertoOrigen() {
        return aeropuertoOrigen;
    }

    public void setAeropuertoOrigen(String aeropuertoOrigen) {
        this.aeropuertoOrigen = aeropuertoOrigen;
    }

    public String getAeropuertoDestino() {
        return aeropuertoDestino;
    }

    public void setAeropuertoDestino(String aeropuertoDestino) {
        this.aeropuertoDestino = aeropuertoDestino;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Set<Pasaje> getPasajes() {
        return pasajes;
    }

    public void setPasajes(Set<Pasaje> pasajes) {
        this.pasajes = pasajes;
    }
}
