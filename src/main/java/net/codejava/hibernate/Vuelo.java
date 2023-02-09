package net.codejava.hibernate;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "vuelo")
public class Vuelo {
    @Id
    @Column(name = "IDENTIFICADOR")
    private String identificador;
    @Column(name = "AEROPUERTO_ORIGEN")
    private String aeropuertoOrigen;
    @Column(name = "AEROPUERTO_DESTINO")
    private String aeropuertoDestino;
    @Column(name = "TIPO_VUELO")
    private String tipo;
    @Column(name = "FECHA_VUELO")
    private Date fecha;
    @OneToMany(mappedBy = "vuelo", cascade = CascadeType.ALL)
    private Set<Pasaje> pasajes;

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
