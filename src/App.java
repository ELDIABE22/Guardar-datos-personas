import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

public class App {
    
    private static Object[][] matrizPersonas;
    
    private static final int NUMERO_PERSONAS = 10;
    
    private static final int POSICION_IDENTIFICACION = 0;
    private static final int POSICION_NOMBRE_APELLIDOS = 1;
    private static final int POSICION_ANIO_NACIMIENTO = 2;
    private static final int POSICION_MES_NACIMIENTO = 3;
    private static final int POSICION_DIA_NACIMIENTO = 4;
    private static final int POSICION_EDAD = 5;

    public static void main(String[] args) {

        matrizPersonas = new Object[NUMERO_PERSONAS][6];

        menu();
    }

    private static void menu() {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;
        do {
            System.out.println("Bienvenido al registro de personas");
            System.out.println("1. Agregar una persona");
            System.out.println("2. Buscar una persona");
            System.out.println("3. Actualizar una persona");
            System.out.println("4. Eliminar una persona");
            System.out.println("5. Salir");
            System.out.print("Ingrese una opción: ");
            opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    agregarPersona();
                    break;
                case 2:
                    buscarPersona();
                    break;
                case 3:
                    actualizarPersona();
                    break;
                case 4:
                    eliminarPersona();
                    break;
                case 5:
                    System.out.println("Hasta luego");
                    break;
                default:
                    System.out.println("Opción inválida");
                    break;
            }
        } while (opcion != 5);
        scanner.close();
    }

    private static void agregarPersona() {
        Scanner scanner = new Scanner(System.in);
        // Buscar la primera fila vacía en la matriz
        int filaVacia = -1;
        for (int i = 0; i < matrizPersonas.length; i++) {
            if (matrizPersonas[i][POSICION_IDENTIFICACION] == null) {
                filaVacia = i;
                break;
            }
        }
        if (filaVacia == -1) {
            System.out.println("No hay espacio para más personas");
            return;
        }

        System.out.print("Ingrese la identificación: ");
        int identificacion = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese el nombre y apellidos: ");
        String nombreApellidos = scanner.nextLine();
        System.out.print("Ingrese el año de nacimiento: ");
        int anioNacimiento = scanner.nextInt();
        System.out.print("Ingrese el mes de nacimiento: ");
        int mesNacimiento = scanner.nextInt();
        System.out.print("Ingrese el día de nacimiento: ");
        int diaNacimiento = scanner.nextInt();

        LocalDate fechaNacimiento = LocalDate.of(anioNacimiento, mesNacimiento, diaNacimiento);
        LocalDate fechaActual = LocalDate.now();
        int edad = Period.between(fechaNacimiento, fechaActual).getYears();

        matrizPersonas[filaVacia][POSICION_IDENTIFICACION] = identificacion;
        matrizPersonas[filaVacia][POSICION_NOMBRE_APELLIDOS] = nombreApellidos;
        matrizPersonas[filaVacia][POSICION_ANIO_NACIMIENTO] = anioNacimiento;
        matrizPersonas[filaVacia][POSICION_MES_NACIMIENTO] = mesNacimiento;
        matrizPersonas[filaVacia][POSICION_DIA_NACIMIENTO] = diaNacimiento;
        matrizPersonas[filaVacia][POSICION_EDAD] = edad;

        System.out.println("Persona agregada con éxito");
    }

    private static void buscarPersona() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("¿Cómo desea buscar la persona?");
        System.out.println("1. Por identificación");
        System.out.println("2. Por nombre y apellidos");
        System.out.print("Ingrese una opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();
        if (opcion == 1) {
            System.out.print("Ingrese la identificación: ");
            int identificacion = scanner.nextInt();
            boolean personaEncontrada = false;
            for (int i = 0; i < matrizPersonas.length; i++) {
                if (matrizPersonas[i][POSICION_IDENTIFICACION] != null &&
                    (int)matrizPersonas[i][POSICION_IDENTIFICACION] == identificacion) {
                    System.out.println("Persona encontrada:");
                    mostrarPersona(matrizPersonas[i]);
                    personaEncontrada = true;
                    break;
                }
            }
            if (!personaEncontrada) {
                System.out.println("Persona no encontrada");
            }
        } else if (opcion == 2) {
            System.out.print("Ingrese el nombre y apellidos: ");
            String nombreApellidos = scanner.nextLine();
            boolean personaEncontrada = false;
            for (int i = 0; i < matrizPersonas.length; i++) {
                if (matrizPersonas[i][POSICION_IDENTIFICACION] != null &&
                    matrizPersonas[i][POSICION_NOMBRE_APELLIDOS].equals(nombreApellidos)) {
                    System.out.println("Persona encontrada:");
                    mostrarPersona(matrizPersonas[i]);
                    personaEncontrada = true;
                    break;
                }
            }
            if (!personaEncontrada) {
                System.out.println("Persona no encontrada");
            }
        } else {
            System.out.println("Opción inválida");
        }
    }

    private static void actualizarPersona() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese la identificación de la persona que desea actualizar: ");
        int identificacion = scanner.nextInt();
        boolean personaEncontrada = false;
        for (int i = 0; i < matrizPersonas.length; i++) {
            if (matrizPersonas[i][POSICION_IDENTIFICACION] != null &&
                (int)matrizPersonas[i][POSICION_IDENTIFICACION] == identificacion) {
                    System.out.println("Persona encontrada:");
                    mostrarPersona(matrizPersonas[i]);
                    System.out.println("Ingrese los nuevos datos de la persona:");
                    System.out.print("Nombre y apellidos: ");
                    String nombreApellidos = scanner.next();
                    System.out.print("Año de nacimiento: ");
                    int anioNacimiento = scanner.nextInt();
                    System.out.print("Mes de nacimiento: ");
                    int mesNacimiento = scanner.nextInt();
                    System.out.print("Día de nacimiento: ");
                    int diaNacimiento = scanner.nextInt();
                    LocalDate fechaNacimiento = LocalDate.of(anioNacimiento, mesNacimiento, diaNacimiento);
                    LocalDate fechaActual = LocalDate.now();
                    int edad = Period.between(fechaNacimiento, fechaActual).getYears();
                    matrizPersonas[i][POSICION_NOMBRE_APELLIDOS] = nombreApellidos;
                    matrizPersonas[i][POSICION_ANIO_NACIMIENTO] = anioNacimiento;
                    matrizPersonas[i][POSICION_MES_NACIMIENTO] = mesNacimiento;
                    matrizPersonas[i][POSICION_DIA_NACIMIENTO] = diaNacimiento;
                    matrizPersonas[i][POSICION_EDAD] = edad;
                    System.out.println("Persona actualizada con éxito");
                    personaEncontrada = true;
                    break;
                }
            }
            if (!personaEncontrada) {
            System.out.println("Persona no encontrada");
        }
    }

    private static void calcularEdadPersona() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese la identificación de la persona de la que desea calcular la edad: ");
        int identificacion = scanner.nextInt();
        boolean personaEncontrada = false;
        for (int i = 0; i < matrizPersonas.length; i++) {
            if (matrizPersonas[i][POSICION_IDENTIFICACION] != null &&
                (int)matrizPersonas[i][POSICION_IDENTIFICACION] == identificacion) {
                System.out.println("Persona encontrada:");
                mostrarPersona(matrizPersonas[i]);
                LocalDate fechaNacimiento = LocalDate.of((int)matrizPersonas[i][POSICION_ANIO_NACIMIENTO], 
                                                        (int)matrizPersonas[i][POSICION_MES_NACIMIENTO], 
                                                        (int)matrizPersonas[i][POSICION_DIA_NACIMIENTO]);
                LocalDate fechaActual = LocalDate.now();
                int edad = Period.between(fechaNacimiento, fechaActual).getYears();
                System.out.println("La edad de la persona es: " + edad + " años");
                personaEncontrada = true;
                break;
            }
        }
        if (!personaEncontrada) {
            System.out.println("Persona no encontrada");
        }
    }

    private static void eliminarPersona() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese la identificación de la persona que desea eliminar: ");
        int identificacion = scanner.nextInt();
        boolean personaEncontrada = false;
        for (int i = 0; i < matrizPersonas.length; i++) {
            if (matrizPersonas[i][POSICION_IDENTIFICACION] != null &&
                (int)matrizPersonas[i][POSICION_IDENTIFICACION] == identificacion) {
                System.out.println("Persona encontrada:");
                mostrarPersona(matrizPersonas[i]);
                matrizPersonas[i][POSICION_IDENTIFICACION] = null;
                matrizPersonas[i][POSICION_NOMBRE_APELLIDOS] = null;
                matrizPersonas[i][POSICION_ANIO_NACIMIENTO] = null;
                matrizPersonas[i][POSICION_MES_NACIMIENTO] = null;
                matrizPersonas[i][POSICION_DIA_NACIMIENTO] = null;
                matrizPersonas[i][POSICION_EDAD] = null;
                System.out.println("Persona eliminada con éxito");
                personaEncontrada = true;
                break;
            }
        }
            if (!personaEncontrada) {
            System.out.println("Persona no encontrada");
        }
    }

    private static void mostrarPersona(Object[] persona) {
        System.out.println("Identificación: " + persona[POSICION_IDENTIFICACION]);
        System.out.println("Nombre y apellidos: " + persona[POSICION_NOMBRE_APELLIDOS]);
        System.out.println("Año de nacimiento: " + persona[POSICION_ANIO_NACIMIENTO]);
        System.out.println("Mes de nacimiento: " + persona[POSICION_MES_NACIMIENTO]);
        System.out.println("Día de nacimiento: " + persona[POSICION_DIA_NACIMIENTO]);
        System.out.println("Edad: " + persona[POSICION_EDAD] + " años");
    }
}


