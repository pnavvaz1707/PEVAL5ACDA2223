package net.codejava.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.Scanner;

public class GestionarVuelos {
    /**
     * Objeto que nos permitirá consultar la base de datos mediante hibernate
     */
    private SessionFactory sessionFactory;

    /**
     * Método para iniciar la sesión con la que consultaremos la base de datos
     */
    public void iniciar() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
            System.err.println("//////////////////////////////////////////// ERROR ////////////////////////////////////////////");
            e.printStackTrace();
        }
    }

    /**
     * Método para insertar un pasaje pidiendo los datos de este por teclado
     */
    public void insertarPasaje() {
        Session session = sessionFactory.openSession();
        Pasaje pasaje = new Pasaje();

        //Obtenemos el código más alto de la tabla pasajes y le sumamos uno para que no se repita, ya que es clave primaria
        Query<Integer> queryMaxCodPasaje = session.createQuery("SELECT MAX(cod) FROM Pasaje");
        pasaje.setCod(queryMaxCodPasaje.uniqueResult() + 1);

        //Obtenemos el código más alto de la tabla pasajeros y le solicitamos un número al usuario que esté entre 1 y el último
        //código de los pasajeros para asegurarnos que introduce un código correcto
        Query<Integer> queryMaxCodPasajero = session.createQuery("SELECT MAX(cod) FROM Pasajero");
        pasaje.setPasajero(session.get(Pasajero.class, Utilidades.solicitarEnteroEnUnRango(1, queryMaxCodPasajero.uniqueResult(), "Introduce el código del pasajero asociado")));

        System.out.println("Pasajero sel --> " + pasaje.getPasajero().getNombre());

        pasaje.setVuelo(session.get(Vuelo.class, Utilidades.solicitarCadenaNoVacia("Introduce el identificador del vuelo asociado")));

        Query<Integer> queryMaxNumAsiento = session.createQuery("SELECT MAX(numAsiento) FROM Pasaje");
        boolean sigue = true;

        int numAsientoSel = 0;

        while (sigue) {
            numAsientoSel = Utilidades.solicitarEnteroEnUnRango(1, queryMaxNumAsiento.uniqueResult(), "Introduce el número de asiento");
            Query<Integer> queryNumAsiento = session.createQuery("SELECT numAsiento FROM Pasaje WHERE numAsiento = " + numAsientoSel);

            System.out.println("Consulta asiento --> " + queryNumAsiento.uniqueResult());
            if (queryNumAsiento.uniqueResult() == null) {
                sigue = false;
            } else {
                System.out.println("El asiento " + numAsientoSel + " está ocupado");
            }
        }

        pasaje.setNumAsiento(numAsientoSel);
        pasaje.setClase(Utilidades.solicitarCadenaNoVacia("Introduce la clase del vuelo"));
        pasaje.setPvp(Utilidades.solicitarFloatEnUnRango(0, 550, "Introduce el precio del pasaje"));

        session.beginTransaction();

        session.save(pasaje);

        session.getTransaction().commit();
        session.close();
    }

    /**
     * Método para consultar los pasajes pidiendo el código del vuelo asociado a este por teclado
     */
    public void consultarPasaje() {
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

    /**
     * Método para actualizar un pasajero pidiendo el código de este por teclado
     */
    public void actualizarPasajero() {
        Session session = sessionFactory.openSession();

        Query<Integer> queryMaxCodPasajero = session.createQuery("SELECT MAX(cod) FROM Pasajero");

        session.beginTransaction();

        Query q = session.createQuery("UPDATE Pasajero SET nombre = :nombreNuevo, telefono = :telNuevo, direccion = :direccionNueva, pais = :paisNuevo  WHERE cod = :codPasajeroSel");
        q.setParameter("codPasajeroSel", Utilidades.solicitarEnteroEnUnRango(0, queryMaxCodPasajero.uniqueResult(), "Introduce el código del pasajero que desee modificar"));
        q.setParameter("nombreNuevo", Utilidades.solicitarCadenaNoVacia("Introduce el nombre nuevo del pasajero"));
        q.setParameter("telNuevo", Utilidades.solicitarCadenaNoVacia("Introduce el teléfono nuevo del pasajero"));
        q.setParameter("direccionNueva", Utilidades.solicitarCadenaNoVacia("Introduce la dirección nueva del pasajero"));
        q.setParameter("paisNuevo", Utilidades.solicitarCadenaNoVacia("Introduce el país nuevo del pasajero"));

        System.out.println("Se han modificado " + q.executeUpdate() + " registros");

        session.getTransaction().commit();

        session.close();
    }

    /**
     * Método para eliminar un pasaje de la base de datos pidiendo el código de pasajero
     */
    public void darBajaPasajesDeUnPasajero() {
        Session session = sessionFactory.openSession();

        Query<Integer> queryMaxCodPasajero = session.createQuery("SELECT MAX(cod) FROM Pasajero");

        session.beginTransaction();

        Query q = session.createQuery("DELETE  FROM Pasaje WHERE pasajero.cod = :codPasajeroSel");
        q.setParameter("codPasajeroSel", Utilidades.solicitarEnteroEnUnRango(0, queryMaxCodPasajero.uniqueResult(), "Introduce el código del pasajero al cual desee darle de alta todos sus pasajes"));


        System.out.println("Se han borrado " + q.executeUpdate() + " pasajes");

        session.getTransaction().commit();
        session.close();
    }

    /**
     * Método para obtener el importe que se ha recaudado con un vuelo indicando el identificador de este por teclado
     */
    public void obtenerImporteRecaudadoDeUnVuelo() {
        Session session = sessionFactory.openSession();
        Scanner teclado = new Scanner(System.in);

        System.out.println("Introduce el identificador del vuelo del cual desea obtener el importe total");
        String identificadorSel = teclado.nextLine();

        Query q = session.createQuery("SELECT SUM(pvp) FROM Pasaje WHERE vuelo.identificador = :identificadorSel");
        q.setParameter("identificadorSel", identificadorSel);

        System.out.println("El importe total del vuelo " + identificadorSel + " es de: " + q.uniqueResult() + " €");

        session.close();
    }

    /**
     * Método para cerrar la sesión con la que consultaremos la base de datos
     */
    public void cerrar() {
        sessionFactory.close();
    }

}