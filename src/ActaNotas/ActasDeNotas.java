import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ActasDeNotas {

    public static void main(String[] args) {
        Scanner lector = new Scanner(System.in);

        // Datos Generales del curso
        System.out.println("Ingrese los Datos Generales del curso:");
        System.out.print("Nombre del curso: ");
        String nombreCurso = lector.nextLine();
        System.out.print("Período lectivo: ");
        String periodoLectivo = lector.nextLine();
        System.out.print("Carrera: ");
        String carrera = lector.nextLine();
        System.out.print("Modalidad: ");
        String modalidad = lector.nextLine();
        System.out.print("Código del curso: ");
        String codigoCurso = lector.nextLine();
        System.out.print("Grupo: ");
        String grupo = lector.nextLine();
        System.out.print("Código de asignatura: ");
        String codigoAsignatura = lector.nextLine();
        System.out.print("Código de programa de asignatura: ");
        String codigoProgramaAsignatura = lector.nextLine();
        System.out.print("Cantidad de estudiantes: ");
        int cantidadEstudiantes = lector.nextInt();
        lector.nextLine(); // Consumir el salto de línea

        // Lista de estudiantes
        ArrayList<Estudiante> estudiantes = new ArrayList<>();

        // Ingresar datos de los estudiantes
        for (int i = 0; i < cantidadEstudiantes; i++) {
            System.out.println("Ingrese los datos del estudiante " + (i + 1) + ":");
            System.out.print("Número de carnet: ");
            String numeroCarnet = lector.next();
            lector.nextLine(); // Consumir el salto de línea
            System.out.print("Nombres: ");
            String nombres = lector.nextLine();
            System.out.print("Apellidos: ");
            String apellidos = lector.nextLine();
            System.out.print("Primer parcial (máximo 35.00): ");
            double primerParcial = lector.nextDouble();
            System.out.print("Segundo parcial (máximo 35.00): ");
            double segundoParcial = lector.nextDouble();
            System.out.print("Sistemáticos (máximo 30.00): ");
            double sistematicos = lector.nextDouble();

            // Verificar si la asignatura lleva proyecto de curso
            double proyecto = 0.0;
            if (leerSiLlevaProyecto()) {
                System.out.print("Proyecto (máximo 35.00): ");
                proyecto = lector.nextDouble();
            }

            // Crear un objeto Estudiante y agregarlo a la lista
            estudiantes.add(new Estudiante(numeroCarnet, nombres, apellidos, primerParcial, segundoParcial,
                    sistematicos, proyecto));
        }

        // Mostrar acta de notas ordenada por apellidos
        Collections.sort(estudiantes, (e1, e2) -> e1.apellidos.compareTo(e2.apellidos));
        mostrarActaDeNotas(estudiantes);

        // Mostrar datos estadísticos del curso
        mostrarDatosEstadisticos(estudiantes);

        // Preguntar si desea utilizar de nuevo la aplicación
        System.out.print("¿Desea utilizar de nuevo la aplicación? (S/N): ");
        char respuesta = lector.next().charAt(0);
        if (respuesta == 'N' || respuesta == 'n') {
            System.out.println("Fin del programa.");
            lector.close();
            System.exit(0);
        }
    }

    // Método para mostrar el acta de notas
    private static void mostrarActaDeNotas(ArrayList<Estudiante> estudiantes) {
        System.out.println("\nActa de Notas:");
        System.out.printf("%-15s%-25s%-10s%-10s%-10s%-10s%-10s%-15s%-15s%-15s%-15s%n",
                "Carnet", "Nombres y Apellidos", "1er Parcial", "2do Parcial", "Sistemáticos", "Proyecto", "Nota Final",
                "1ra Convocatoria", "Nota Final 1ra Conv.", "2da Convocatoria", "¿Aprobado?");

        for (Estudiante estudiante : estudiantes) {
            double notaFinal = calcularNotaFinal(estudiante);

            System.out.printf("%-15s%-25s%-10.2f%-10.2f%-10.2f%-10.2f%-10.2f",
                    estudiante.numeroCarnet, estudiante.nombres + " " + estudiante.apellidos, estudiante.primerParcial,
                    estudiante.segundoParcial, estudiante.sistematicos, estudiante.proyecto, notaFinal);

            // Calcular y mostrar la nota de primera convocatoria si es necesario
            if (notaFinal < 60.00) {
                double notaExamen1raConv = lectorNota("Examen de 1ra Convocatoria");
                double notaFinal1raConv = estudiante.sistematicos + notaExamen1raConv;
                System.out.printf("%-15.2f%-15.2f", notaExamen1raConv, notaFinal1raConv);

                // Calcular y mostrar la nota de segunda convocatoria si es necesario
                if (notaFinal1raConv < 60.00) {
                    double notaExamen2daConv = lectorNota("Examen de 2da Convocatoria");
                    System.out.printf("%-15.2f", notaExamen2daConv);
                } else {
                    System.out.print("           ");
                }
            } else {
                System.out.print("                ");
            }

            // Mostrar si el estudiante está aprobado
            System.out.printf("%-15s%n", (notaFinal >= 60.00) ? "Sí" : "No");
        }
    }

    // Método para calcular y mostrar los datos estadísticos del curso
    private static void mostrarDatosEstadisticos(ArrayList<Estudiante> estudiantes) {
        int matriculaInicial = estudiantes.size();
        int matriculaEfectiva = matriculaInicial;
        int deserciones = 0;
        int aprobados = 0;
        int reprobados = 0;
        double sumaNotas = 0.0;
        double notaMinima = 100.0; // Inicializar con un valor alto
        double notaMaxima = 0.0; // Inicializar con un valor bajo

        for (Estudiante estudiante : estudiantes) {
            double notaFinal = calcularNotaFinal(estudiante);

            // Actualizar nota mínima y máxima
            notaMinima = Math.min(notaMinima, notaFinal);
            notaMaxima = Math.max(notaMaxima, notaFinal);

            // Contar aprobados y reprobados
            if (notaFinal >= 60.0) {
                aprobados++;
            } else {
                reprobados++;
            }

            // Sumar las notas para calcular el promedio
            sumaNotas += notaFinal;

            // Contar deserciones
            if (leerDesercion(estudiante)) {
                deserciones++;
                matriculaEfectiva--;
            }
        }

        // Calcular el promedio de notas
        double promedioNotas = sumaNotas / matriculaEfectiva;

        // Mostrar los datos estadísticos
        System.out.println("\nDatos Estadísticos del Curso:");
        System.out.println("Matrícula Inicial: " + matriculaInicial);
        System.out.println("Matrícula Efectiva: " + matriculaEfectiva);
        System.out.println("Número de Deserciones: " + deserciones);
        System.out.println("Cantidad de Aprobados: " + aprobados);
        System.out.println("% de Aprobados: " + ((double) aprobados / matriculaEfectiva) * 100 + "%");
        System.out.println("Cantidad de Reprobados: " + reprobados);
        System.out.println("% de Reprobados: " + ((double) reprobados / matriculaEfectiva) * 100 + "%");
        System.out.println("Nota Mínima: " + notaMinima);
        System.out.println("Nota Máxima: " + notaMaxima);
        System.out.println("Promedio de Notas: " + promedioNotas);
    }

    // Método para calcular la nota final según las reglas dadas
    private static double calcularNotaFinal(Estudiante estudiante) {
        double notaFinal;
        if (estudiante.proyecto > 0) {
            // La asignatura lleva proyecto de curso
            notaFinal = estudiante.primerParcial + estudiante.sistematicos + estudiante.proyecto;
        } else {
            // La asignatura no lleva proyecto de curso
            notaFinal = estudiante.primerParcial + estudiante.segundoParcial + estudiante.sistematicos;
        }
        return notaFinal;
    }

    // Método para leer una nota del usuario con validación
    private static double lectorNota(String nombreNota) {
        Scanner lector = new Scanner(System.in);
        double nota;

        do {
            System.out.print("Ingrese la nota de " + nombreNota + " (máximo 100.00): ");
            nota = lector.nextDouble();
        } while (nota < 0 || nota > 100);

        return nota;
    }

    // Método para leer la deserción del estudiante
    private static boolean leerDesercion(Estudiante estudiante) {
        Scanner lector = new Scanner(System.in);
        char desercion;

        do {
            System.out.print("¿Desertó el curso el estudiante " + estudiante.numeroCarnet + "? (S/N): ");
            desercion = lector.next().charAt(0);
        } while (desercion != 'S' && desercion != 'N');

        return desercion == 'S';
    }

    // Método para leer si la asignatura lleva proyecto de curso
    private static boolean leerSiLlevaProyecto() {
        Scanner lector = new Scanner(System.in);
        char respuesta;

        do {
            System.out.print("¿La asignatura lleva proyecto de curso? (S/N): ");
            respuesta = lector.next().charAt(0);
        } while (respuesta != 'S' && respuesta != 'N');

        return respuesta == 'S';
    }

    // Clase Estudiante con los campos y métodos necesarios
    static class Estudiante {
        String numeroCarnet;
        String nombres;
        String apellidos;
        double primerParcial;
        double segundoParcial;
        double sistematicos;
        double proyecto;

        // Constructor
        public Estudiante(String numeroCarnet, String nombres, String apellidos,
                double primerParcial, double segundoParcial, double sistematicos, double proyecto) {
            this.numeroCarnet = numeroCarnet;
            this.nombres = nombres;
            this.apellidos = apellidos;
            this.primerParcial = primerParcial;
            this.segundoParcial = segundoParcial;
            this.sistematicos = sistematicos;
            this.proyecto = proyecto;
        }
    }
}