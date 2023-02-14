package net.codejava.hibernate;

public class Main {
    /**
     * Array que contiene las opciones del menú
     */
    private static final String[] MENU_OPCIONES = {
            "Dar de alta un pasaje nuevo",
            "Visualizar todos los datos de un pasaje dando su identificador de vuelo",
            "Actualizar los datos de un pasajero",
            "Dar de baja todos los pasajes de un pasajero dando su código de pasajero",
            "Visualizar el importe total recaudado en los pasajes de un vuelo",
            "Salir"
    };

    public static void main(String[] args) {
        GestionarVuelos gestionarVuelos = new GestionarVuelos();
        gestionarVuelos.iniciar();

        int respuesta;
        do {
            respuesta = Utilidades.mostrarMenu(MENU_OPCIONES);

            switch (respuesta) {
                case 1:
                    gestionarVuelos.insertarPasaje();
                    break;
                case 2:
                    gestionarVuelos.consultarPasaje();
                    break;
                case 3:
                    gestionarVuelos.actualizarPasajero();
                    break;
                case 4:
                    gestionarVuelos.darBajaPasajesDeUnPasajero();
                    break;
                case 5:
                    gestionarVuelos.obtenerImporteRecaudadoDeUnVuelo();
                    break;
            }
        } while (respuesta != MENU_OPCIONES.length);
        gestionarVuelos.cerrar();
    }
}
