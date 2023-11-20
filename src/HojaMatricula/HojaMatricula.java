package HojaMatricula;

import java.util.InputMismatchException;
import java.util.Scanner;

public class HojaMatricula {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            String respuesta;
            int cantidadAsignaturas = obtcanInscripcion();

            String nombreAsignaturas[] = obtNombreAsignaturas(cantidadAsignaturas);

            obtNumRecibo();
            String turno = obtTurno();
            obtNumInscripcion();
            int planEstudio = obtPlanEstudio();
            String nombreCompleto = obtNombyApell();
            int semestre = obtSemestre();
            String numCarnet = obtNumCarnet();
            String fecha = obtFechaMatricula();
            obtCarrera();

            String codigoAsignatura[] = obtCodAsignatura(cantidadAsignaturas, nombreAsignaturas);
            String grupo = obtGrupo();
            int aula = obtAula();
            int creditos[] = obtCreditos(cantidadAsignaturas, nombreAsignaturas);
            int f[] = obtFrecuenciaAsignaturas(cantidadAsignaturas, nombreAsignaturas);
            int r[] = obtRetiros(cantidadAsignaturas, nombreAsignaturas);

            output(nombreAsignaturas, obtNumRecibo(), obtNumInscripcion(), cantidadAsignaturas, codigoAsignatura,
                    creditos,
                    f, r, nombreCompleto, planEstudio, fecha, numCarnet, semestre, grupo,
                    aula, turno);

            do {
                System.out.println("¿Desea hacer un registro?");
                respuesta = sc.next();
                if (respuesta.equalsIgnoreCase("si")) {
                    cantidadAsignaturas = obtcanInscripcion();
                    nombreAsignaturas = obtNombreAsignaturas(cantidadAsignaturas);
                    generarNuevoreporte(cantidadAsignaturas, nombreAsignaturas);
                    output(nombreAsignaturas, obtNumRecibo(), obtNumInscripcion(), cantidadAsignaturas,
                            codigoAsignatura,
                            creditos,
                            f, r, nombreCompleto, planEstudio, fecha, numCarnet, semestre, grupo,
                            aula, turno);

                } else if (respuesta.equalsIgnoreCase("no")) {
                    return;
                }
            } while (!(respuesta.equalsIgnoreCase("si") || respuesta.equalsIgnoreCase("no")));

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            sc.close();
        }
    }

    // Obtencion de los datos de la carrera y estudiante
    public static int obtNumRecibo() {
        int numRecibo = 0;
        while (true) {
            try {
                System.out.println("Número de recibo: ");
                numRecibo = sc.nextInt();
                break; // Si llegamos aquí sin lanzar una excepción, salimos del bucle
            } catch (InputMismatchException e) {
                System.out.println("Error: Ingresa un valor válido para el número de recibo.");
                sc.nextLine(); // Limpia el búfer del scanner
            }
        }
        return numRecibo;
    }

    public static String obtTurno() {
        String modalidad = "";
        while (true) {
            try {
                System.out.println("Turnos disponibles (seleccione según el número que corresponda): ");
                System.out.println("1. Diurno\n2. Nocturno");
                int opcion = sc.nextInt();

                switch (opcion) {
                    case 1:
                        modalidad = "Diurno";
                        break;
                    case 2:
                        modalidad = "Nocturno";
                        break;
                    default:
                        System.out.println("Error: Ingresa un valor válido para el turno.");
                        continue; // Continúa al siguiente ciclo del bucle
                }

                break;
            } catch (InputMismatchException e) {
                System.out.println("Error: Ingresa un valor válido para el turno.");
                sc.nextLine();
            }
        }
        return modalidad;
    }

    public static int obtNumInscripcion() {
        int numInscripcion = 0;
        while (true) {
            try {
                System.out.println("Número de inscripción: ");
                numInscripcion = sc.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Error: Ingresa un valor válido para el número de inscripción.");
                sc.nextLine();
            }
        }
        return numInscripcion;
    }

    public static int obtPlanEstudio() {
        int planEstudio = 0;
        while (true) {
            try {
                System.out.println("Plan de estudio: ");
                planEstudio = sc.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Error: Ingresa un valor válido para el plan de estudio.");
                sc.nextLine();
            }
        }
        return planEstudio;
    }

    public static String obtNombyApell() {
        String nombreCompleto = "";
        while (true) {
            try {
                System.out.println("Primer nombre: ");
                String nombre = sc.next();
                System.out.println("Segundo nombre: ");
                String nombre2 = sc.next();
                System.out.println("Primer apellido: ");
                String apellido = sc.next();
                System.out.println("Segundo apellido: ");
                String apellido2 = sc.next();

                nombreCompleto = (nombre + " " + nombre2 + " " + apellido + " " + apellido2);
                break;
            } catch (InputMismatchException e) {
                System.out.println("Error: Ingresa un valor válido para los nombres y apellidos.");
                sc.nextLine();
            }
        }
        return nombreCompleto;
    }

    public static int obtSemestre() {
        int semestre = 0;
        while (true) {
            try {
                System.out.println("Semestre: ");
                semestre = sc.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Error: Ingresa un valor válido para el semestre.");
                sc.nextLine();
            }
        }
        return semestre;
    }

    public static String obtNumCarnet() {
        String numCarnet = "";
        while (true) {
            try {
                System.out.println("Número de carnet: ");
                numCarnet = sc.next();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Error: Ingresa un valor válido para el número de carnet.");
                sc.nextLine();
            }
        }
        return numCarnet;
    }

    public static String obtFechaMatricula() {
        String fecha = "";
        while (true) {
            try {
                System.out.println("Fecha de matrícula: ");
                System.out.println("Día:");
                int dia = sc.nextInt();
                System.out.println("Mes:");
                int mes = sc.nextInt();
                System.out.println("Año:");
                int año = sc.nextInt();

                fecha = (dia + "/" + mes + "/" + año);
                break;
            } catch (InputMismatchException e) {
                System.out.println("Error: Ingresa un valor válido para la fecha de matrícula.");
                sc.nextLine();
            }
        }
        return fecha;
    }

    public static String obtCarrera() {
        System.out.println("Elija una de las siguientes opciones: ");
        System.out.print(
                "1-Ing.Sistemas\t2-Ing.Computacion\t3-Ing.Electrica\t4-Ing.Electronica\t5-Ing.Mecanica\t6-Ing.Civil");
        System.out.print(
                "\t7-Ing.Quimica\t8-Ing.Agricola\t9-Ing.Economia y Negocios\t10-Ing.Industrial\t11-Arquitectura");
        int opcion = sc.nextInt();
        String eleccion = "";
        switch (opcion) {
            case 1:
                eleccion = "Ingenieria de Sistemas";
                break;
            case 2:
                eleccion = "Ingenieria en computacion";
                break;
            case 3:
                eleccion = "Ingenieria Electrica";
                break;
            case 4:
                eleccion = "Ingenieria Electronica";
                break;
            case 5:
                eleccion = "Ingenieria Mecanica";
                break;
            case 6:
                eleccion = "Ingenieria civil";
                break;
            case 7:
                eleccion = "Ingenieria Quimica";
                break;
            case 8:
                eleccion = "Ingenieria Agricola";
                break;
            case 9:
                eleccion = "Ingenieria en economia y Negocios";
                break;
            case 10:
                eleccion = "Ingenieria Industrial";
                break;
            case 11:
                eleccion = "Arquitectura";
                break;
        }
        return eleccion;
    }

    // Obtencion de datos de las asignaturas
    public static int obtcanInscripcion() {
        int cantAsignaturas = 0;
        while (true) {
            try {
                System.out.println("Cantidad de asignaturas: ");
                cantAsignaturas = sc.nextInt();

                if (cantAsignaturas >= 0 && cantAsignaturas <= 7) {
                    break;
                } else {
                    System.out.println("Error: Ingresa un valor válido para la cantidad de asignaturas (entre 0 y 7).");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Ingresa un valor válido para la cantidad de asignaturas.");
                sc.nextLine();
            }
        }
        return cantAsignaturas;
    }

    public static String[] obtNombreAsignaturas(int cantAsignaturas) {
        String[] nombreAsignaturas = new String[cantAsignaturas];
        // Leer nombres de las asignaturas
        for (int i = 0; i < nombreAsignaturas.length; i++) {
            try {
                System.out.print("No." + (i + 1) + " = ");
                nombreAsignaturas[i] = sc.next();
            } catch (Exception e) {
                System.out.println("Error al ingresar el nombre de la asignatura. Inténtelo de nuevo.");
                sc.nextLine(); // Limpiar el buffer del scanner
                i--; // Decrementar i para volver a intentar con el mismo índice
            }
        }

        return nombreAsignaturas;
    }

    public static String[] obtCodAsignatura(int cantAsignaturas, String nombreAsignaturas[]) {
        String codigoAsignatura[] = new String[cantAsignaturas];

        for (int i = 0; i < codigoAsignatura.length; i++) {
            while (true) {
                try {
                    System.out.println("Codigo de " + nombreAsignaturas[i] + "=");
                    codigoAsignatura[i] = sc.next();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Error: Ingresa un valor válido para el código de asignatura.");
                    sc.nextLine();
                }
            }

        }
        return codigoAsignatura;
    }

    public static String obtGrupo() {
        String grupo = "";

        while (true) {
            try {
                System.out.println("Grupo= ");
                grupo = sc.next();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Error: Ingresa un valor válido");
                sc.nextLine();
            }
        }

        return grupo;
    }

    public static int obtAula() {
        int aula = 0;

        while (true) {
            try {
                System.out.println("Aula=");
                aula = sc.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Error: Ingresa un valor válido para el aula.");
                sc.nextLine();
            }
        }

        return aula;
    }

    public static int[] obtCreditos(int cantAsignaturas, String nombreAsignaturas[]) {
        int creditos[] = new int[cantAsignaturas];
        int sumaCreditos = 0;

        while (sumaCreditos > 28) {
            for (int i = 0; i < cantAsignaturas; i++) {
                while (true) {
                    try {
                        System.out.println("Créditos de " + nombreAsignaturas[i] + ": ");
                        creditos[i] = sc.nextInt();

                        if (creditos[i] >= 1 && creditos[i] <= 4) {
                            sumaCreditos += creditos[i];
                            break;
                        } else {
                            System.out.println("Error: Ingresa un valor válido para los créditos (entre 1 y 4).");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Error: Ingresa un valor válido para los créditos.");
                        sc.nextLine();
                    }
                }
            }
        }

        return creditos;
    }

    public static int[] obtFrecuenciaAsignaturas(int cantAsignaturas, String nombreAsignaturas[]) {
        int f[] = new int[cantAsignaturas];
        for (int i = 0; i < cantAsignaturas; i++) {
            while (true) {
                try {
                    System.out.println("Frecuencia de inscripción de " + nombreAsignaturas[i] + ": ");
                    f[i] = sc.nextInt();

                    if (f[i] >= 1 && f[i] <= 3) {
                        break;
                    } else {
                        System.out.println("Error: Ingresa un valor válido para la frecuencia (entre 1 y 3).");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Error: Ingresa un valor válido para la frecuencia.");
                    sc.nextLine();
                }
            }
        }
        return f;
    }

    public static int[] obtRetiros(int cantAsignaturas, String nombreAsignaturas[]) {
        int r[] = new int[cantAsignaturas];
        for (int i = 0; i < cantAsignaturas; i++) {
            while (true) {
                try {
                    System.out.println("Retiro de asignatura " + nombreAsignaturas[i] + ": ");
                    r[i] = sc.nextInt();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Error: Ingresa un valor válido para el retiro.");
                    sc.nextLine();
                }
            }
        }
        return r;
    }

    public static void ordenamientoAsignaturas(String nombreAsignaturas[], String codigoAsignatura[], int creditos[],
            int f[], int r[]) {
        for (int i = 0; i < nombreAsignaturas.length; i++) {
            for (int j = 0; j < nombreAsignaturas.length && i != j; j++) {
                if (nombreAsignaturas[i].compareToIgnoreCase(nombreAsignaturas[j]) < 0) {
                    String aux = nombreAsignaturas[i];
                    String aux2 = codigoAsignatura[i];
                    int aux3 = creditos[i];
                    int aux4 = f[i];
                    int aux5 = r[i];
                    nombreAsignaturas[i] = nombreAsignaturas[j];
                    codigoAsignatura[i] = codigoAsignatura[j];
                    creditos[i] = creditos[j];
                    f[i] = f[j];
                    r[i] = r[j];
                    nombreAsignaturas[j] = aux;
                    codigoAsignatura[j] = aux2;
                    creditos[j] = aux3;
                    f[j] = aux4;
                    r[j] = aux5;
                }
            }
        }
    }

    public static void output(String nombreAsignaturas[], int numRecibo, int numInscripcion, int cantAsignaturas,
            String codigoAsignatura[], int creditos[], int f[], int r[], String nombreCompleto, int planEstudio,
            String fecha, String numCarnet, int semestre,
            String grupo, int aula, String modalidad) {
        System.out.println(
                "----------------------------------------------------------------------------------------------------------");
        System.out.println("No. Recibo " + numRecibo
                + "                                                          No. Inscripcion " + numInscripcion);
        System.out.println(
                "----------------------------------------------------------------------------------------------------------");
        System.out.println("NOMBRES Y APELLIDOS: " + nombreCompleto + "              CARNET: " + numCarnet
                + "        TURNO: " + modalidad);
        System.out.println("PLAN DE ESTUDIO: " + planEstudio + "                 SEMESTRE: " + semestre
                + "       FECHA: " + fecha);
        System.out.print("\n");
        System.out.println(
                "-----------------------------------------------------------------------------------------------------------");
        System.out.println(
                "|No.|                 ASIGNATURA                  |    GRUPO    |    AULA    |   CREDITO   |   F   |  R  |");
        for (int i = 0; i < cantAsignaturas; i++) {
            System.out.print("| " + (i + 1) + " |" + codigoAsignatura[i] + "\t " + nombreAsignaturas[i] + "\t\t\t\t"
                    + grupo + "\t\t\t" + aula + "\t\t" + creditos[i] + "\t" + f[i] + "\t" + r[i] + "  |\n");
        }
        System.out.println(
                "|---|-------------------------ULTIMA LINEA --------|------------|-----------|-------------|-------|-----|");
        System.out.println("\n");
        System.out.println(
                "|---|----------------------------------------------|------------|-----------|-------------|-------|-----|");
    }

    public static void generarNuevoreporte(int cantAsignaturas, String nombreAsignaturas[]) {

        obtNumRecibo();
        obtTurno();
        obtNumInscripcion();
        obtPlanEstudio();
        obtNombyApell();
        obtSemestre();
        obtNumCarnet();
        obtFechaMatricula();
        obtCarrera();
        obtCodAsignatura(cantAsignaturas, nombreAsignaturas);
        obtGrupo();
        obtAula();
        obtCreditos(cantAsignaturas, nombreAsignaturas);
        obtFrecuenciaAsignaturas(cantAsignaturas, nombreAsignaturas);
        obtRetiros(cantAsignaturas, nombreAsignaturas);
    }
}
