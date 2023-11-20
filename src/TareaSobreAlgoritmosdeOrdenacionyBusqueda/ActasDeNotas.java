package TareaSobreAlgoritmosdeOrdenacionyBusqueda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class ActasDeNotas {

    private static Scanner lector = new Scanner(System.in);

    public static void main(String[] args) {
        ArrayList<Estudiante> estudiantes = new ArrayList<>();

        ingresarDatosGenerales(estudiantes);
        int opcion;
        do {
            System.out.println("\nMenú de Acciones:");
            System.out.println("1. Ordenar notas");
            System.out.println("2. Buscar notas");
            System.out.println("3. Mostrar y graficar rangos de notas");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción (1-4): ");
            opcion = lector.nextInt();

            switch (opcion) {
                case 1:
                    menuOrdenarNotas(estudiantes);
                    break;
                case 2:
                    menuBuscarNotas(estudiantes);
                    break;
                case 3:
                    mostrarRangosDeNotas(estudiantes);
                    break;
                case 4:
                    System.out.println("Fin del programa.");
                    lector.close();
                    System.exit(0);
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 4);
    }

    private static void ingresarDatosGenerales(ArrayList<Estudiante> estudiantes) {
        System.out.print("Nombre del curso: ");
        String nombreCurso = lector.next();

        // Resto del código para ingresar los datos generales del curso...

        System.out.print("Cantidad de estudiantes: ");
        int cantidadEstudiantes = lector.nextInt();

        for (int i = 0; i < cantidadEstudiantes; i++) {
            System.out.println("\nIngresando datos para el estudiante " + (i + 1) + ":");
            System.out.print("Número de carnet: ");
            String numeroCarnet = lector.next();
            System.out.print("Nombres: ");
            String nombres = lector.next();
            System.out.print("Apellidos: ");
            String apellidos = lector.next();
            System.out.print("Primer parcial: ");
            double primerParcial = lector.nextDouble();
            System.out.print("Segundo parcial: ");
            double segundoParcial = lector.nextDouble();
            System.out.print("Sistemáticos: ");
            double sistematicos = lector.nextDouble();
            System.out.print("Proyecto: ");
            double proyecto = lector.nextDouble();

            estudiantes.add(new Estudiante(numeroCarnet, nombres, apellidos, primerParcial, segundoParcial,
                    sistematicos, proyecto));
        }
    }

    private static void mostrarActaDeNotas(ArrayList<Estudiante> estudiantes) {
        System.out.println("\nActa de Notas:");
        System.out.printf("%-15s%-30s%-15s%-15s%-15s%-15s%-15s%-15s%-15s%-15s%-15s\n", "Carnet", "Nombres y Apellidos",
                "1er Parcial", "2do Parcial", "Sistemáticos", "Proyecto", "Nota Final", "1ra Conv.", "2da Conv.",
                "Aprobado", "Deserción");
        for (Estudiante estudiante : estudiantes) {
            double notaFinal = calcularNotaFinal(estudiante);
            boolean aprobado = notaFinal >= 60.00;

            System.out.printf("%-15s%-30s%-15.2f%-15.2f%-15.2f%-15.2f%-15.2f", estudiante.numeroCarnet,
                    estudiante.nombres + " " + estudiante.apellidos, estudiante.primerParcial,
                    estudiante.segundoParcial,
                    estudiante.sistematicos, estudiante.proyecto, notaFinal);

            if (!aprobado) {
                double notaExamen1raConv = lectorNota("Examen de 1ra Convocatoria");
                double notaFinal1raConv = estudiante.sistematicos + notaExamen1raConv;
                System.out.printf("%-15.2f", notaFinal1raConv);

                if (notaFinal1raConv < 60.00) {
                    double notaExamen2daConv = lectorNota("Examen de 2da Convocatoria");
                    System.out.printf("%-15.2f", notaExamen2daConv);
                } else {
                    System.out.printf("%-15s", "-");
                }
            } else {
                System.out.printf("%-15s%-15s", "-", "-");
            }

            System.out.printf("%-15s%-15s\n", aprobado ? "Sí" : "No", leerDesercion(estudiante) ? "Sí" : "No");
        }
    }

    static class Estudiante {
        String numeroCarnet;
        String nombres;
        String apellidos;
        double primerParcial;
        double segundoParcial;
        double sistematicos;
        double proyecto;

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

    private static void menuOrdenarNotas(ArrayList<Estudiante> estudiantes) {
        System.out.println("\nSeleccione el criterio de ordenación:");
        System.out.println("1. Por nota final");
        System.out.println("2. Por nota final después de primera convocatoria");
        System.out.println("3. Por nota final después de segunda convocatoria");
        int opcionOrdenacion = lector.nextInt();

        Comparator<Estudiante> comparador = null;

        switch (opcionOrdenacion) {
            case 1:
                comparador = Comparator.comparingDouble(e -> calcularNotaFinal(e));
                break;
            case 2:
                comparador = Comparator.comparingDouble(e -> {
                    double notaExamen1raConv = lectorNota("Examen de 1ra Convocatoria");
                    return e.sistematicos + notaExamen1raConv;
                });
                break;
            case 3:
                comparador = Comparator.comparingDouble(e -> {
                    double notaExamen2daConv = lectorNota("Examen de 2da Convocatoria");
                    return notaExamen2daConv;
                });
                break;
            default:
                System.out.println("Opción no válida.");
                return;
        }

        System.out.println("\nSeleccione el orden:");
        System.out.println("1. Ascendente");
        System.out.println("2. Descendente");
        int opcionOrden = lector.nextInt();

        if (opcionOrden == 2) {
            comparador = comparador.reversed();
        }

        Collections.sort(estudiantes, comparador);

        mostrarActaDeNotas(estudiantes);
    }

    private static void menuBuscarNotas(ArrayList<Estudiante> estudiantes) {
        System.out.println("\nSeleccione el criterio de búsqueda:");
        System.out.println("1. Por número de carnet");
        System.out.println("2. Por nombres y apellidos");
        int opcionBusqueda = lector.nextInt();

        switch (opcionBusqueda) {
            case 1:
                buscarPorNumeroCarnet(estudiantes);
                break;
            case 2:
                buscarPorNombresYApellidos(estudiantes);
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }

    private static void buscarPorNumeroCarnet(ArrayList<Estudiante> estudiantes) {
        System.out.print("Ingrese el número de carnet a buscar: ");
        String numeroCarnet = lector.next();

        for (Estudiante estudiante : estudiantes) {
            if (estudiante.numeroCarnet.equals(numeroCarnet)) {
                mostrarDatosEstudiante(estudiante);
                return;
            }
        }

        System.out.println("Estudiante con número de carnet " + numeroCarnet + " no encontrado.");
    }

    private static void buscarPorNombresYApellidos(ArrayList<Estudiante> estudiantes) {
        System.out.print("Ingrese los nombres y apellidos a buscar: ");
        String nombresYApellidos = lector.next() + lector.nextLine();

        for (Estudiante estudiante : estudiantes) {
            if ((estudiante.nombres + " " + estudiante.apellidos).equalsIgnoreCase(nombresYApellidos)) {
                mostrarDatosEstudiante(estudiante);
                return;
            }
        }

        System.out.println("Estudiante con nombres y apellidos " + nombresYApellidos + " no encontrado.");
    }

    private static void mostrarDatosEstudiante(Estudiante estudiante) {
        System.out.println("\nDatos del estudiante encontrado:");
        System.out.println("Número de carnet: " + estudiante.numeroCarnet);
        System.out.println("Nombres y apellidos: " + estudiante.nombres + " " + estudiante.apellidos);
        System.out.println("Primer parcial: " + estudiante.primerParcial);
        System.out.println("Segundo parcial: " + estudiante.segundoParcial);
        System.out.println("Sistemáticos: " + estudiante.sistematicos);
        System.out.println("Proyecto: " + (estudiante.proyecto >= 0 ? estudiante.proyecto : "No aplica"));
        System.out.println("Nota Final: " + calcularNotaFinal(estudiante));
        boolean aprobado = calcularNotaFinal(estudiante) >= 60.0;
        System.out.println("¿Aprobado? " + (aprobado ? "Sí" : "No"));
        System.out.println("¿Desertó el curso? " + (leerDesercion(estudiante) ? "Sí" : "No"));
    }

    private static void mostrarRangosDeNotas(ArrayList<Estudiante> estudiantes) {
        int reprobado = 0, regular = 0, bueno = 0, muyBueno = 0, excelente = 0;

        for (Estudiante estudiante : estudiantes) {
            double notaFinal = calcularNotaFinal(estudiante);

            if (notaFinal >= 0 && notaFinal < 60) {
                reprobado++;
            } else if (notaFinal >= 60 && notaFinal < 70) {
                regular++;
            } else if (notaFinal >= 70 && notaFinal < 80) {
                bueno++;
            } else if (notaFinal >= 80 && notaFinal < 90) {
                muyBueno++;
            } else if (notaFinal >= 90 && notaFinal <= 100) {
                excelente++;
            }
        }

        System.out.println("\nRangos de Notas:");
        System.out.println("Reprobado (0 - 59): " + reprobado);
        System.out.println("Regular (60 - 69): " + regular);
        System.out.println("Bueno (70 - 79): " + bueno);
        System.out.println("Muy bueno (80 - 89): " + muyBueno);
        System.out.println("Excelente (90 - 100): " + excelente);
    }

    private static double lectorNota(String mensaje) {
        System.out.print(mensaje + ": ");
        return lector.nextDouble();
    }

    private static boolean leerDesercion(Estudiante estudiante) {
        char desercion;
        do {
            System.out.print("¿Desertó el curso? (S/N): ");
            desercion = lector.next().charAt(0);
        } while (desercion != 'S' && desercion != 'N');

        return desercion == 'S';
    }

    // Método para calcular la nota final de un estudiante
    private static double calcularNotaFinal(Estudiante estudiante) {
        double notaProyecto = estudiante.proyecto;
        if (notaProyecto >= 0) {
            return estudiante.primerParcial + estudiante.segundoParcial + estudiante.sistematicos + notaProyecto;
        } else {
            return estudiante.primerParcial + estudiante.segundoParcial + estudiante.sistematicos;
        }
    }

}
