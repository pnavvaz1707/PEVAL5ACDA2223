package net.codejava.hibernate;

import javax.persistence.*;
import java.util.Set;

/**
 * Entidad llamada Pasajero que representa la tabla "pasajero"
 */
@Entity
@Table(name = "pasajero")
public class Pasajero {
    /**
     * Campo tipo int y clave primaria con nombre "COD" de la tabla "pasajero"
     */
    @Id
    @Column(name = "COD")
    private int cod;

    /**
     * Campo tipo String con nombre "NOMBRE" de la tabla "pasajero"
     */
    @Column(name = "NOMBRE")
    private String nombre;

    /**
     * Campo tipo String con nombre "TLF" de la tabla "pasajero"
     */
    @Column(name = "TLF")
    private String telefono;

    /**
     * Campo tipo String con nombre "DIRECCION" de la tabla "pasajero"
     */
    @Column(name = "DIRECCION")
    private String direccion;

    /**
     * Campo tipo String con nombre "PAIS" de la tabla "pasajero"
     */
    @Column(name = "PAIS")
    private String pais;

    /**
     * Campo tipo Set<Pasajes> mapeado por el campo "pasajero" del objeto Pasaje
     */
    @OneToMany(mappedBy = "pasajero", cascade = CascadeType.ALL)
    private Set<Pasaje> pasajes;

    /**
     * Constructor por defecto de la clase
     */
    public Pasajero() {
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Set<Pasaje> getPasajes() {
        return pasajes;
    }

    public void setPasajes(Set<Pasaje> pasajes) {
        this.pasajes = pasajes;
    }
}
