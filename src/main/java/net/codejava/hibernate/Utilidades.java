package net.codejava.hibernate;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Utilidades {

    /**
     * Método para mostrar un menú y pedirle al usuario que seleccione una opción
     *
     * @param MENU_OPCIONES (Array de Strings que contiene las opcines del menú)
     * @return devuelve un número entero con la posición de la opción elegida del menú
     */
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

    /**
     * Método para solicitar un número entero comprendido entre 2 números pasados como parámetro
     *
     * @param limiteInferior (Número que representa el límite inferior)
     * @param limiteSuperior (Número que representa el límite superior)
     * @param msg            (Mensaje para solicitar el número al usuario)
     * @return devuelve el número entero introducido por el usuario
     */
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

    /**
     * Método para solicitar un número tipo float comprendido entre 2 números pasados como parámetro
     *
     * @param limiteInferior (Número que representa el límite inferior)
     * @param limiteSuperior (Número que representa el límite superior)
     * @param msg            (Mensaje para solicitar el número al usuario)
     * @return devuelve el número tipo float introducido por el usuario
     */
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

    /**
     * Método para solicitar una cadena que no esté vacía
     *
     * @param msg (Mensaje para solicitar la cadena al usuario)
     * @return devuelve la cadena introducida por el usuario
     */
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
}
