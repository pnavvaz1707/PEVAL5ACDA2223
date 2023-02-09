package net.codejava.hibernate;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "pasajero")
public class Pasajero {
    @Id
    @Column(name = "COD")
    private int cod;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "TLF")
    private String telefono;
    @Column(name = "DIRECCION")
    private String direccion;
    @Column(name = "PAIS")
    private String pais;
    @OneToMany(mappedBy = "pasajero", cascade = CascadeType.ALL)
    private Set<Pasaje> pasajes;

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
