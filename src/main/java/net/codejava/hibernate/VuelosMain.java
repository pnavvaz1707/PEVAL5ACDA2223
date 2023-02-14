package net.codejava.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.Scanner;

public class VuelosMain {
    private SessionFactory sessionFactory;

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
        VuelosMain vuelosMain = new VuelosMain();
        vuelosMain.setup();

        int respuesta;
        do {
            respuesta = Utilidades.mostrarMenu(MENU_OPCIONES);

            switch (respuesta) {
                case 1:
                    vuelosMain.insertarPasaje();
                    break;
                case 2:
                    vuelosMain.consultarPasaje();
                    break;
                case 3:
                    vuelosMain.actualizarPasajero();
                    break;
                case 4:
                    vuelosMain.darBajaPasajesDeUnPasajero();
                    break;
                case 5:
                    vuelosMain.obtenerImporteRecaudadoDeUnVuelo();
                    break;
            }
        } while (respuesta != MENU_OPCIONES.length);
        vuelosMain.exit();
    }

    private void setup() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
            System.err.println("//////////////////////////////////////////// ERROR ////////////////////////////////////////////");
            e.printStackTrace();
        }
    }

    private void insertarPasaje() {
//        Session session = sessionFactory.openSession();
//        Pasaje pasaje = new Pasaje();
//
//        Query<Integer> queryMaxCodPasaje = session.createQuery("SELECT MAX(cod) FROM Pasaje");
//        pasaje.setCod(queryMaxCodPasaje.uniqueResult() + 1);
//
//        Query<Integer> queryMaxCodPasajero = session.createQuery("SELECT MAX(cod) FROM Pasajero");
//        pasaje.setPasajero(session.get(Pasajero.class, Utilidades.solicitarEnteroEnUnRango(0, queryMaxCodPasajero.uniqueResult(), "Introduce el código del pasajero asociado")));
//
//        pasaje.setVuelo(session.get(Vuelo.class, Utilidades.solicitarCadenaNoVacia("Introduce el identificador del vuelo asociado")));
//        pasaje.setNumAsiento(Utilidades.solicitarEnteroEnUnRango(0, 70, "Introduce el número de asiento"));
//        pasaje.setClase(Utilidades.solicitarCadenaNoVacia("Introduce la clase del vuelo"));
//        pasaje.setPvp(Utilidades.solicitarFloatEnUnRango(0, 550, "Introduce el precio del pasaje"));
//
//        session.beginTransaction();
//
//        session.save(pasaje);
//
//        session.getTransaction().commit();
//        session.close();
    }

    private void consultarPasaje() {
        Session session = sessionFactory.openSession();

        Query q = session.createQuery("FROM Pasaje WHERE vuelo.identificador = :identificadorSel");
        q.setParameter("identificadorSel", Utilidades.solicitarCadenaNoVacia("Introduce el identificador de vuelo"));

        ArrayList<Pasaje> pasajes = (ArrayList<Pasaje>) q.getResultList();
        for (Pasaje pasaje : pasajes) {
            System.out.println("VUELO: " + pasaje.getVuelo().getIdentificador());
            System.out.println("ORIGEN: " + pasaje.getVuelo().getAeropuertoOrigen() + "\tDESTINO: " + pasaje.getVuelo().getAeropuertoOrigen() + "\tFECHA: " + pasaje.getVuelo().getFecha());
            System.out.println("\tCLASE: " + pasaje.getClase());
            System.out.println("\t\tNombre pasajero: " + pasaje.getPasajero().getNombre() + "\tCódigo pasaje: " + pasaje.getCod() + "\tNúmero de asiento: " + pasaje.getNumAsiento());
        }

        session.close();
    }

    private void actualizarPasajero() {
        Session session = sessionFactory.openSession();

        Query<Integer> queryMaxCodPasajero = session.createQuery("SELECT MAX(cod) FROM Pasajero");

//        Query q = session.createQuery("UPDATE Pasajero SET nombre = :nombreNuevo, telefono = :telNuevo, direccion = :direccionNueva, pais = :paisNuevo  WHERE cod = :codPasajeroSel");
//        q.setParameter("codPasajeroSel", Utilidades.solicitarEnteroEnUnRango(0, queryMaxCodPasajero.uniqueResult(), "Introduce el código del pasajero que desee modificar"));
//        q.setParameter("nombreNuevo", Utilidades.solicitarCadenaNoVacia("Introduce el nombre nuevo del pasajero"));
//        q.setParameter("telNuevo", Utilidades.solicitarCadenaNoVacia("Introduce el teléfono nuevo del pasajero"));
//        q.setParameter("direccionNueva", Utilidades.solicitarCadenaNoVacia("Introduce la dirección nueva del pasajero"));
//        q.setParameter("paisNuevo", Utilidades.solicitarCadenaNoVacia("Introduce el país nuevo del pasajero"));

        System.out.println("Se han modificado " + q.executeUpdate() + " registros");

        session.close();
    }

    private void darBajaPasajesDeUnPasajero() {
        Session session = sessionFactory.openSession();
        Scanner teclado = new Scanner(System.in);

        System.out.println("Introduce el código del pasajero al cual desee darle de alta todos sus pasajes");
        int codPasajeroSel = teclado.nextInt();
        teclado.nextLine();

        Query q = session.createQuery("DELETE  FROM Pasaje WHERE pasajero.cod = :codPasajeroSel");
        q.setParameter("codPasajeroSel", codPasajeroSel);

        System.out.println("Se han borrado " + q.executeUpdate() + " pasajes");
        session.close();
    }

    private void obtenerImporteRecaudadoDeUnVuelo() {
        Session session = sessionFactory.openSession();
        Scanner teclado = new Scanner(System.in);

        System.out.println("Introduce el identificador del vuelo del cual desea obtener el importe total");
        String identificadorSel = teclado.nextLine();

        Query q = session.createQuery("SELECT SUM(pvp) FROM Pasaje WHERE vuelo.identificador = :identificadorSel");
        q.setParameter("identificadorSel", identificadorSel);

        System.out.println("El importe total del vuelo " + identificadorSel + " es de: " + q.uniqueResult() + " €");

        session.close();
    }

    private void exit() {
        // code to close Hibernate Session factory
    }

}