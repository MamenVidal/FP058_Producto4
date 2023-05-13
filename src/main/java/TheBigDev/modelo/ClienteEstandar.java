package TheBigDev.modelo;

import javax.persistence.*;
@Entity
@DiscriminatorValue("estandar")
public class ClienteEstandar extends Cliente {

    public ClienteEstandar() {
        super();
    }

    public ClienteEstandar(String email, String nif, String nombre, String domicilio) {
        super(email, nif, nombre, domicilio);
        this.calcAnual = 0.00f;
        this.descuentoEnv = 0.00f;
    }

    @Override
    public String tipoCliente() {
        return "estandar";
    }

    @Override
    public float calcAnual() {
        return calcAnual;
    }

    @Override
    public float descuentoEnv() {
        return descuentoEnv;
    }
}
