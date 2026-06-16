package com.aripapeleria.ms_pedidos.dto;

public class PedidoDTO {

    private String cliente;
    private String producto;
    private Integer cantidad;
    private Double precioTotal;

    // NUEVO: Agregamos el campo para recibir el código de descuento
    private String codigoPromocion;

    public PedidoDTO() {}

    public PedidoDTO(String cliente, String producto, Integer cantidad, Double precioTotal, String codigoPromocion) {
        this.cliente = cliente;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioTotal = precioTotal;
        this.codigoPromocion = codigoPromocion;
    }

    // Getters y Setters
    public String getCliente() { return cliente; }
    public void setCliente(String cliente) { this.cliente = cliente; }

    public String getProducto() { return producto; }
    public void setProducto(String producto) { this.producto = producto; }

    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }

    public Double getPrecioTotal() { return precioTotal; }
    public void setPrecioTotal(Double precioTotal) { this.precioTotal = precioTotal; }

    // Getter y Setter del nuevo campo de promoción
    public String getCodigoPromocion() { return codigoPromocion; }
    public void setCodigoPromocion(String codigoPromocion) { this.codigoPromocion = codigoPromocion; }
}