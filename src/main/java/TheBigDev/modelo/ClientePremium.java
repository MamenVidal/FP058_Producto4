package TheBigDev.modelo;


import javax.persistence.*;
@Entity
@DiscriminatorValue("premium")
public class ClientePremium extends Cliente {

    public ClientePremium() {
        super();
    }

    public ClientePremium(String email, String nif, String nombre, String domicilio ) {
        super(email, nif, nombre, domicilio);
        this.calcAnual = 30.00f;
        this.descuentoEnv = 20.00f;
    }

    @Override
    public String tipoCliente() {
        return "premium";
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
