package EquipoBaloncesto;

import java.util.InputMismatchException;
import java.util.Scanner;

public class EquipoBaloncesto {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        int i = 0;
        String nombre[] = new String[5];
        int estatura[] = new int[5];
        int edad[] = new int[5];
        int velocidad[] = new int[5];
        int alcance[] = new int[5];
        int peso[] = new int[5];
        int envergadura[] = new int[5];
        boolean entradaValida = false;

        do {
            try {
                for (i = 0; i < nombre.length; i++) {
                    System.out.print("Nombre jugador n." + (i + 1) + " =");
                    nombre[i] = sc.nextLine();
                    entradaValida = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: entrada invalida");
                sc.nextLine();
            }
        } while (!entradaValida);

        for (i = 0; i < estatura.length; i++) {
            do {
                try {
                    System.out.print("Estatura de " + nombre[i] + " =");
                    estatura[i] = sc.nextInt();
                    entradaValida = true;
                } catch (InputMismatchException e) {
                    System.out.println("Error: entrada invalida");
                    sc.nextLine();
                }
            } while (!entradaValida || estatura[i] == 0);

        }

        do {
            try {
                for (i = 0; i < edad.length; i++) {
                    System.out.print("Edad de " + nombre[i] + " =");
                    edad[i] = sc.nextInt();
                    entradaValida = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: entrada invalida");
                sc.nextLine();
            }
        } while (!entradaValida || edad[i] == 0);

        do {
            try {
                for (i = 0; i < velocidad.length; i++) {
                    System.out.print("velocidad maxima de " + nombre[i] + " = ");
                    velocidad[i] = sc.nextInt();
                    entradaValida = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: entrada invalida");
                sc.nextLine();
            }
        } while (!entradaValida || velocidad[i] == 0);

        do {
            try {
                for (i = 0; i < alcance.length; i++) {
                    System.out.print("Alcance de " + nombre[i] + " = ");
                    alcance[i] = sc.nextInt();
                    entradaValida = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: entrada invalida");
                sc.nextLine();
            }
        } while (!entradaValida || alcance[i] == 0);

        do {
            try {
                for (i = 0; i < peso.length; i++) {
                    System.out.print("Peso de " + nombre[i] + " = ");
                    peso[i] = sc.nextInt();
                }
            } catch (Exception e) {
                System.out.println("Error: entrada invalida");
                sc.nextLine();
            }
        } while (!entradaValida || peso[i] == 0);

        do {
            try {
                for (i = 0; i < envergadura.length; i++) {
                    System.out.print("Envergadura de " + nombre[i] + " = ");
                    envergadura[i] = sc.nextInt();
                    entradaValida = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: entrada invalida");
                sc.nextLine();
            }
        } while (!entradaValida || envergadura[i] == 0);

        // 3.Jugador mas completo
        System.out.println(
                "el jugador mas completo es " + jugCompleto(estatura, velocidad, alcance, peso, envergadura, nombre));

        // 4.Jugadores capaces de clavar
        System.out.println("Jugadores capaces de clavar= ");
        capClavar(estatura, envergadura, alcance, nombre);

        // 5.Listado de jugadores basado en su IMC
        System.out.println("Jugadores basados en su IMC(ordenados de mayor a menor:)");
        calcularIMC(peso, estatura, nombre);

        // 1.Jugador mas rapido al mas lento
        System.out.println("Listado de los nombres del mas rapido al mas lento: ");
        nombre = ordenarPorVelocidad(velocidad, nombre);
        mostrarVelocidad(nombre);

    }

    public static void calcularIMC(int peso[], int estatura[], String nombre[]) {
        double imc[] = new double[5];
        for (int i = 0; i < 5; i++) {
            imc[i] = peso[i] / (Math.pow(estatura[i], 2));
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (imc[j] > imc[j + 1]) {
                    double aux = imc[j];
                    String aux2 = nombre[j];
                    imc[j] = imc[j + 1];
                    nombre[j] = nombre[j + 1];
                    imc[j + 1] = aux;
                    nombre[j + 1] = aux2;
                    System.out.println("." + nombre[j + 1]);
                }
            }
        }

    }

    public static String[] ordenarPorVelocidad(int velocidad[], String nombre[]) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (velocidad[j] < velocidad[j + 1]) {
                    String aux = nombre[j];
                    int aux2 = velocidad[j];
                    nombre[j] = nombre[j + 1];
                    velocidad[j] = velocidad[j + 1];
                    nombre[j + 1] = aux;
                    velocidad[j + 1] = aux2;
                }
            }
        }
        return nombre;
    }

    public static void mostrarVelocidad(String nombre[]) {
        for (int i = 0; i < 5; i++) {
            System.out.println("." + nombre[i]);
        }
    }

    public static String jugCompleto(int estatura[], int velocidad[], int alcance[], int peso[], int envergadura[],
            String nombre[]) {
        double mascompleto[] = new double[5];
        for (int i = 0; i < 5; i++) {
            mascompleto[i] = (estatura[i] * 0.25) + (velocidad[i] * 0.25) + (alcance[i] * 0.2) + (peso[i] * 0.1)
                    + (envergadura[i] * 0.2);
        }
        String jugMascompleto = null;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (mascompleto[j] < mascompleto[j + 1]) {
                    jugMascompleto = nombre[j + 1];

                }
            }
        }
        return jugMascompleto;
    }

    public static void capClavar(int estatura[], int envergadura[], int alcance[], String nombre[]) {
        double calcap[] = new double[5];
        for (int i = 0; i < 5; i++) {
            calcap[i] = estatura[i] + ((envergadura[i] / 2) * 0.7) + alcance[i];
        }
        for (int i = 0; i < 5; i++) {
            if (calcap[i] >= 305) {
                System.out.println("." + nombre[i]);
            }
        }
    }

}
