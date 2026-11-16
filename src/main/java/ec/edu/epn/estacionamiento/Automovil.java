package ec.edu.epn.estacionamiento;

public class Automovil extends Vehiculo {
    private boolean cubierto;
    public Automovil(String placa, String propietario, double horas, boolean cubierto) {
        super(placa, propietario, horas);
        this.cubierto = cubierto;
    }
    public boolean isCubierto() { return cubierto; }
    public void setCubierto(boolean cubierto) { this.cubierto = cubierto; }
    @Override public double calcularTarifa() {
        double tarifa = super.calcularTarifa();
        if (cubierto) {tarifa = tarifa + 2.00;}
        return tarifa;
    }
    @Override public String mostrarInformacion() {
        String informacion = super.mostrarInformacion();
        String tipoCubierto;
        if (cubierto) {tipoCubierto = "Si";}
        else {tipoCubierto = "No";}
        return informacion + " | Automovil | Cubierto: " + tipoCubierto;
    }
}
