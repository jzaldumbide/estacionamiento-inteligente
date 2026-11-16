package ec.edu.epn.estacionamiento;

/** Ejemplos independientes para explicar casting, parsing y conversion a String. */
public class DemoConversiones {
    public static void main(String[] args) {
        int entero = 5;
        double implicito = entero;
        double decimal = 3.75;
        int truncado = (int) decimal;
        long redondeado = Math.round(decimal);
        double techo = Math.ceil(decimal);
        int opcion = Integer.parseInt("2");
        double horas = Double.parseDouble("3.75");
        String ticket = "PK-" + String.valueOf(150);
        System.out.println("Casting implicito int -> double: " + implicito);
        System.out.println("Casting explicito double -> int: " + truncado);
        System.out.println("Math.round: " + redondeado);
        System.out.println("Math.ceil: " + techo);
        System.out.println("Parsing int: " + opcion);
        System.out.println("Parsing double: " + horas);
        System.out.println("Numero a texto: " + ticket);

    }
}
