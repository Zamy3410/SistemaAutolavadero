package org.example.Model;

public class RegistrosLavado {

    private int vehiculoID;
    private int servicioID;
    private String fechalavado;
    private String horainicio;
    private String horafin;
    private double preciototal;

    public RegistrosLavado() {
    }

    public RegistrosLavado(int vehiculoID, int servicioID, String fechalavado, String horainicio, String horafin, double preciototal) {
        this.vehiculoID = vehiculoID;
        this.servicioID = servicioID;
        this.fechalavado = fechalavado;
        this.horainicio = horainicio;
        this.horafin = horafin;
        this.preciototal = preciototal;
    }

    public int getVehiculoID() {
        return vehiculoID;
    }

    public void setVehiculoID(int vehiculoID) {
        this.vehiculoID = vehiculoID;
    }

    public int getServicioID() {
        return servicioID;
    }

    public void setServicioID(int servicioID) {
        this.servicioID = servicioID;
    }

    public String getFechalavado() {
        return fechalavado;
    }

    public void setFechalavado(String fechalavado) {
        this.fechalavado = fechalavado;
    }

    public String getHorainicio() {
        return horainicio;
    }

    public void setHorainicio(String horainicio) {
        this.horainicio = horainicio;
    }

    public String getHorafin() {
        return horafin;
    }

    public void setHorafin(String horafin) {
        this.horafin = horafin;
    }

    public double getPreciototal() {
        return preciototal;
    }

    public void setPreciototal(double preciototal) {
        this.preciototal = preciototal;
    }

    @Override
    public String toString() {
        return "RegistrosLavado{" +
                "vehiculoID=" + vehiculoID +
                ", servicioID=" + servicioID +
                ", fechalavado='" + fechalavado + '\'' +
                ", horainicio='" + horainicio + '\'' +
                ", horafin='" + horafin + '\'' +
                ", preciototal=" + preciototal +
                '}';
    }
}
