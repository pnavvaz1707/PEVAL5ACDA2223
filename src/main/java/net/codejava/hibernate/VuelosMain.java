package net.codejava.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.Scanner;

public class VuelosMain {

    //Insertar jugada
    //Borrar partida
    //Mostrar jugadas por nombre de partida
    private SessionFactory sessionFactory;

    public static void main(String[] args) {
        VuelosMain vuelosMain = new VuelosMain();
        vuelosMain.setup();

        vuelosMain.insertarPasaje();
        vuelosMain.consultarVuelo();

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
        System.out.println("Creación de pasaje");
        Session session = sessionFactory.openSession();
        Scanner teclado = new Scanner(System.in);
        Pasaje pasaje = new Pasaje();

        System.out.println("Introduce el código del pasajero asociado");
        int codPasajero = teclado.nextInt();
        teclado.nextLine();
        pasaje.setPasajeroCod(session.get(Pasajero.class, codPasajero));

        System.out.println("Introduce el código del vuelo asociado");
        String identificadorVuelo = teclado.nextLine();
        pasaje.setVuelo(session.get(Vuelo.class, identificadorVuelo));

        System.out.println("Introduce el número de asiento");
        int numAsiento = teclado.nextInt();
        teclado.nextLine();
        pasaje.setNumAsiento(numAsiento);

        System.out.println("Introduce el número de asiento");
        String clase = teclado.nextLine();
        pasaje.setClase(clase);

        System.out.println("Introduce el precio del pasaje");
        float pvp = teclado.nextFloat();
        pasaje.setPvp(pvp);

        session.beginTransaction();

        session.save(pasaje);

        session.getTransaction().commit();
        session.close();
    }

    private void consultarVuelo() {
        System.out.println("Creación de pasaje");
        Session session = sessionFactory.openSession();
        Scanner teclado = new Scanner(System.in);

        System.out.println("Introduce el identificador de vuelo");
        String identificadorSel = teclado.nextLine();

        Query q = session.createQuery("SELECT  FROM Vuelo WHERE identificador = :identificadorSel");
        q.setParameter("identificadorSel", identificadorSel);

        session.close();
    }

    private void exit() {
        // code to close Hibernate Session factory
    }

}