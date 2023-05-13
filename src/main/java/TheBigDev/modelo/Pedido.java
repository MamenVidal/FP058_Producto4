package TheBigDev.modelo;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "pedidos")
public class Pedido {
    @Id
    @Column(name = "numero")
    private int numero;

    @ManyToOne
    @JoinColumn(name = "cliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "articulo")
    private Articulo articulo;

    @Column(name = "cantidad")
    private int cantidad;

    @Column(name = "fechaHora")
    private LocalDateTime fechaHora;

    @Column(name = "enviado")
    private Boolean enviado;

    public Pedido() {}

    public Pedido(int numero, Cliente cliente, Articulo articulo, int cantidad, LocalDateTime fechaHora,
            Boolean enviado) {
        this.numero = numero;
        this.cliente = cliente;
        this.articulo = articulo;
        this.cantidad = cantidad;
        this.fechaHora = fechaHora;
        this.enviado = enviado;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Boolean getEnviado() {
        return enviado;
    }

    public void setEnviado(Boolean enviado) {
        this.enviado = enviado;
    }

    public boolean pedidoEnviado() {
        return enviado;
    }

    public float precioEnvio() {
        /*
         * los gastos de envio son la cantidad de producto por el coste de envio
         * unitario por el porcentaje a cobrar reducido el descuento según cliente
         */
        return (articulo.getGastoEnvio() * (float) cantidad) * ((100 - cliente.descuentoEnv()) / 100);
    }

    public float precioTotal() {
        return (float) cantidad * articulo.getPrecioVenta() + precioEnvio();
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "numero=" + numero +
                ", cliente=" + cliente +
                ", articulo=" + articulo +
                ", cantidad=" + cantidad +
                ", fechaHora=" + fechaHora +
                ", enviado=" + enviado +
                ", precioEnvio=" + precioEnvio() +
                ", precioTotal=" + precioTotal() +
                '}';
    }
}
