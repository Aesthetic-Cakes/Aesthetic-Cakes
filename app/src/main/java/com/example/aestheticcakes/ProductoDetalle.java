package com.example.aestheticcakes;

public class ProductoDetalle extends detalleProducto {

    protected static int codigoSeleccionado;
    protected static String nombreSelccionado;
    protected static String descripcionSeleccionada;
    protected static double precioSeleccionado;
    protected static String Image1;
    protected static String Image2;
    protected static String Image3;

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        ProductoDetalle.cantidad = cantidad;
    }

    protected static int cantidad;

    public int getCodigoSeleccionado() {
        return codigoSeleccionado;
    }

    public void setCodigoSeleccionado(int codigoSeleccionado) {
        ProductoDetalle.codigoSeleccionado = codigoSeleccionado;
    }

    public String getNombreSelccionado() {
        return nombreSelccionado;
    }

    public void setNombreSelccionado(String nombreSelccionado) {
        ProductoDetalle.nombreSelccionado = nombreSelccionado;
    }

    public String getDescripcionSeleccionada() {
        return descripcionSeleccionada;
    }

    public void setDescripcionSeleccionada(String descripcionSeleccionada) {
        ProductoDetalle.descripcionSeleccionada = descripcionSeleccionada;
    }

    public double getPrecioSeleccionado() {
        return precioSeleccionado;
    }

    public void setPrecioSeleccionado(double precioSeleccionado) {
        ProductoDetalle.precioSeleccionado = precioSeleccionado;
    }



    public String getImage1() {
        return Image1;
    }

    public void setImage1(String image1) {
        Image1 = image1;
    }

    public String getImage2() {
        return Image2;
    }

    public void setImage2(String image2) {
        Image2 = image2;
    }

    public String getImage3() {
        return Image3;
    }

    public void setImage3(String image3) {
        Image3 = image3;
    }

}
