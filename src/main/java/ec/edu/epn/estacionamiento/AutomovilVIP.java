package ec.edu.epn.estacionamiento;

/** Herencia multinivel: Vehiculo -> Automovil -> AutomovilVIP. */
public class AutomovilVIP extends Automovil {
    private double descuentoPorcentaje;
    public AutomovilVIP(String placa, String propietario, double horas, boolean cubierto,
                        double descuentoPorcentaje) {
        super(placa, propietario, horas, cubierto);
        setDescuentoPorcentaje(descuentoPorcentaje);
    }
    /** Sobrecarga: descuento predeterminado del 20%. */
    public AutomovilVIP(String placa, String propietario, double horas, boolean cubierto) {
        this(placa, propietario, horas, cubierto, 20.0);
    }
    public double getDescuentoPorcentaje() { return descuentoPorcentaje; }
    
    public void setDescuentoPorcentaje(double descuento) {
    if (descuento >= 0 && descuento <= 100) {
        this.descuentoPorcentaje = descuento;
    } else {
        System.out.println("Error: el descuento debe estar entre 0 y 100");
    }

}
    @Override public double calcularTarifa() {
        return super.calcularTarifa() * (1 - descuentoPorcentaje / 100.0);
    }
    @Override public String mostrarInformacion() {
        return super.mostrarInformacion() + " | VIP | Descuento: " + descuentoPorcentaje + "%";
    }
}
