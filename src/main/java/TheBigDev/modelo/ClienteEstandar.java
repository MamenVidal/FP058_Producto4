package TheBigDev.modelo;

public class ClienteEstandar extends Cliente {
    public ClienteEstandar(String email, String nif, String nombre, String domicilio) {
        super(email, nif, nombre, domicilio);
        this.tipoCliente = "estandar";
        this.calcAnual = (float)0.00;
        this.descuentoEnv = (float)0.00;
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
