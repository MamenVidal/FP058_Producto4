package TheBigDev.modelo;

public class ClientePremium extends Cliente {

    public ClientePremium(String email, String nif, String nombre, String domicilio) {
        super(email, nif, nombre, domicilio);
        this.tipoCliente = "premium";
        this.calcAnual = (float)30.00;
        this.descuentoEnv = (float)20.00;
    }

    @Override
    public String tipoCliente() {
        return tipoCliente;
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
