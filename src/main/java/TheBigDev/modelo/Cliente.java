package TheBigDev.modelo;

import javax.persistence.*;
@Entity
@Table(name = "clientes")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipoCliente")
public class Cliente {

    @Id
    @Column(name = "email")
    private String email;

    @Column(name = "nif")
    private String nif;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "domicilio")
    private String domicilio;

    @Column(name = "calcAnual")
    protected float calcAnual;

    @Column(name = "descuentoEnv")
    protected float descuentoEnv;

    public Cliente() {}

    public Cliente(String email, String nif, String nombre, String domicilio) {
        this.email = email;
        this.nif = nif;
        this.nombre = nombre;
        this.domicilio = domicilio;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    // getters y setters
    public String getNombre() {
        return nombre;
    }
    public String getNif() {
        return nif;
    }
    public void setNif(String nif) {
        this.nif = nif;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDomicilio() {
        return domicilio;
    }
    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String tipoCliente() {
        return "";
    }

    public float calcAnual() { return calcAnual; }

    public float descuentoEnv() {
        return descuentoEnv;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "email='" + email + '\'' +
                ", nif='" + nif + '\'' +
                ", nombre='" + nombre + '\'' +
                ", domicilio='" + domicilio + '\'' +
                ", tipoCliente='" + tipoCliente() + '\'' +
                ", calcAnual='" + calcAnual() + '\'' +
                ", descuentoEnv='" + descuentoEnv() + '\'' +
                '}';
    }
}

