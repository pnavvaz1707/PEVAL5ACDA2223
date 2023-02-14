package net.codejava.hibernate;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Utilidades {

    public static int mostrarMenu(String[] MENU_OPCIONES) {
        System.out.println("//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////");
        for (int i = 0; i < MENU_OPCIONES.length; i++) {
            System.out.println((i + 1) + ". " + MENU_OPCIONES[i]);
        }
        System.out.println("//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////");

        int opcionSel = solicitarEnteroEnUnRango(1, MENU_OPCIONES.length, "Seleccione una opción");
        System.out.println("Has seleccionado --> " + MENU_OPCIONES[opcionSel - 1]);

        return opcionSel;
    }

    public static int solicitarEnteroEnUnRango(int limiteInferior, int limiteSuperior, String msg) {
        Scanner teclado = new Scanner(System.in);
        boolean sigue = true;
        int num = 0;

        while (sigue) {
            try {
                System.out.println(msg);

                num = teclado.nextInt();
                if (num < limiteInferior || num > limiteSuperior) {
                    throw new Exception("El número debe estar comprendido en el siguiente rango [" + limiteInferior + "," + limiteSuperior + "]");
                }
                sigue = false;

            } catch (InputMismatchException e) {
                System.err.println("Debe introducir un número entero");
                teclado.nextLine();

            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return num;
    }

    public static float solicitarFloatEnUnRango(int limiteInferior, int limiteSuperior, String msg) {
        Scanner teclado = new Scanner(System.in);
        boolean sigue = true;
        float num = 0;

        while (sigue) {
            try {
                System.out.println(msg);

                num = teclado.nextFloat();
                if (num < limiteInferior || num > limiteSuperior) {
                    throw new Exception("El número debe estar comprendido en el siguiente rango [" + limiteInferior + "," + limiteSuperior + "]");
                }
                sigue = false;

            } catch (InputMismatchException e) {
                System.err.println("Debe introducir un número, si es decimal, sepáralo por ','");
                teclado.nextLine();

            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return num;
    }

    public static String solicitarCadenaNoVacia(String msg) {
        Scanner teclado = new Scanner(System.in);
        boolean sigue = true;
        String cadena = "";

        while (sigue) {
            try {
                System.out.println(msg);

                cadena = teclado.nextLine();
                if (cadena.isEmpty()) {
                    throw new Exception("No puedes dejarlo vacío");
                }
                sigue = false;

            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return cadena;
    }

    public static String solicitarCadena(String msg) {
        Scanner teclado = new Scanner(System.in);
        System.out.println(msg);

        return teclado.nextLine();
    }
}
