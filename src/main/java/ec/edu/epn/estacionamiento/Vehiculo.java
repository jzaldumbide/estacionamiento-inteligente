package ec.edu.epn.estacionamiento;

/** Clase base: encapsulamiento, casting explicito y polimorfismo. */
public class Vehiculo {
    private String placa;
    private String propietario;
    private double horasEstacionado;

    public Vehiculo(String placa, String propietario, double horasEstacionado) {
        this.placa = placa;
        this.propietario = propietario;
        setHorasEstacionado(horasEstacionado);
    }
    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }
    public String getPropietario() { return propietario; }
    public void setPropietario(String propietario) { this.propietario = propietario; }
    public double getHorasEstacionado() { return horasEstacionado; }
    public void setHorasEstacionado(double horas) {
        if (horas > 0 && Double.isFinite(horas)) this.horasEstacionado = horas;
    }
    /** Trunca con casting y suma una hora si existe fraccion. */
    
    public int horasFacturables() {
        int completas = (int) horasEstacionado;
        if (horasEstacionado > completas) {
        completas = completas + 1;
        }
        return completas;
    }

    public double calcularTarifa() {
        return horasFacturables() * 1.50;
    }
    public String mostrarInformacion() {
        return String.format("Placa: %s | Propietario: %s | Horas: %.2f | Facturables: %d",
                placa, propietario, horasEstacionado, horasFacturables());
    }
}
