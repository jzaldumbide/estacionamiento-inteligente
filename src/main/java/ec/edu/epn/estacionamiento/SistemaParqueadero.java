package ec.edu.epn.estacionamiento;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/** Showcase completo. Todas las entradas se leen con nextLine y luego se convierten. */
public class SistemaParqueadero {
    private final Scanner scanner = new Scanner(System.in);
    private final Map<String, Vehiculo> estacionados = new HashMap<>();
    private final List<Vehiculo> historial = new ArrayList<>();
    private int siguienteTicket = 150;
    private final Map<String, String> tickets = new HashMap<>();

    public static void main(String[] args) { new SistemaParqueadero().iniciar(); }

    public void iniciar() {
        int opcion;
        do {
            System.out.println("\n=== ESTACIONAMIENTO INTELIGENTE ===");
            System.out.println("1. Registrar vehiculo");
            System.out.println("2. Consultar vehiculo");
            System.out.println("3. Registrar salida");
            System.out.println("4. Mostrar historial");
            System.out.println("5. Mostrar ingresos");
            System.out.println("6. Salir");
            opcion = leerEntero("Opcion: ");
            switch (opcion) {
                case 1: registrar(); break;
                case 2: consultar(); break;
                case 3: salida(); break;
                case 4: mostrarHistorial(); break;
                case 5: mostrarIngresos(); break;
                case 6: System.out.println("Hasta pronto."); break;
                default: System.out.println("Opcion invalida.");
            }
        } while (opcion != 6);
    }
    private String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }
    private int leerEntero(String mensaje) {
        while (true) {
            try { return Integer.parseInt(leerTexto(mensaje)); }
            catch (NumberFormatException ex) { System.out.println("Ingrese un entero valido."); }
        }
    }
    private double leerDouble(String mensaje) {
        while (true) {
            try {
                double valor = Double.parseDouble(leerTexto(mensaje));
                if (Double.isFinite(valor)) return valor;
            } catch (NumberFormatException ex) { /* Se informa abajo. */ }
            System.out.println("Ingrese un numero decimal valido (use punto).");
        }
    }
    private boolean leerSiNo(String mensaje) {
        while (true) {
            String respuesta = leerTexto(mensaje).toUpperCase();
            if (respuesta.equals("S")) return true;
            if (respuesta.equals("N")) return false;
            System.out.println("Responda S o N.");
        }
    }
    private void registrar() {
        System.out.println("1. Automovil | 2. Motocicleta | 3. Automovil VIP");
        int tipo = leerEntero("Tipo: ");
        if (tipo < 1 || tipo > 3) { System.out.println("Tipo invalido."); return; }
        String placa = leerTexto("Placa: ").toUpperCase();
        if (placa.isEmpty() || estacionados.containsKey(placa)) {
            System.out.println("Placa vacia o ya registrada."); return;
        }
        String propietario = leerTexto("Propietario: ");
        if (propietario.isEmpty()) { System.out.println("Propietario obligatorio."); return; }
        double horas = leerDouble("Horas estacionado (ej. 2.5): ");
        if (horas <= 0 || horas > Integer.MAX_VALUE - 1.0) {
            System.out.println("Las horas deben ser positivas y razonables."); return;
        }
        Vehiculo vehiculo;
        if (tipo == 2) {
            int cc = leerEntero("Cilindraje (cc): ");
            if (cc <= 0) { System.out.println("Cilindraje invalido."); return; }
            vehiculo = new Motocicleta(placa, propietario, horas, cc);
        } else {
            boolean cubierto = leerSiNo("Estacionamiento cubierto? (S/N): ");
            if (tipo == 1) vehiculo = new Automovil(placa, propietario, horas, cubierto);
            else {
                boolean personalizado = leerSiNo("Descuento personalizado? (S/N): ");
                if (personalizado) {
                    double descuento = leerDouble("Descuento entre 0 y 100 (%): ");
                    if (descuento < 0 || descuento > 100) {
                        System.out.println("Descuento invalido."); return;
                    }
                    vehiculo = new AutomovilVIP(placa, propietario, horas, cubierto, descuento);
                } else vehiculo = new AutomovilVIP(placa, propietario, horas, cubierto);
            }
        }
        String ticket = "PK-" + String.valueOf(siguienteTicket);
        estacionados.put(placa, vehiculo);
        tickets.put(placa, ticket);
        siguienteTicket++;
        System.out.printf("Registrado. Ticket %s | Tarifa estimada $%.2f%n", ticket, vehiculo.calcularTarifa());
    }
    private void consultar() {
        String placa = leerTexto("Placa: ").toUpperCase();
        if (!estacionados.containsKey(placa)) { System.out.println("No esta estacionado."); return; }
        Vehiculo v = estacionados.get(placa);
        System.out.println("Ticket: " + tickets.get(placa));
        System.out.println(v.mostrarInformacion());
        System.out.printf("Tarifa: $%.2f%n", v.calcularTarifa());
    }
    private void salida() {
        String placa = leerTexto("Placa: ").toUpperCase();
        if (!estacionados.containsKey(placa)) { System.out.println("No esta estacionado."); return; }
        Vehiculo v = estacionados.remove(placa);
        tickets.remove(placa);
        historial.add(v);
        System.out.printf("Salida registrada: %s | Cobro: $%.2f%n", placa, v.calcularTarifa());
    }
    private void mostrarHistorial() {
        if (historial.isEmpty()) { System.out.println("No hay salidas registradas."); return; }
        for (Vehiculo v : historial) {
            System.out.printf("%s | Cobro: $%.2f%n", v.mostrarInformacion(), v.calcularTarifa());
        }
    }
    private void mostrarIngresos() {
        double total = 0;
        for (Vehiculo v : historial) total += v.calcularTarifa();
        System.out.printf("Vehiculos estacionados: %d | Salidas: %d | Ingresos: $%.2f%n",
                estacionados.size(), historial.size(), total);
    }
}
