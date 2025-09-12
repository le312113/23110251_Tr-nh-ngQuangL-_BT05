package Configs;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaConfig {
    private static final EntityManagerFactory factory =
            Persistence.createEntityManagerFactory("QLNV1");
    public static EntityManager em(){
        return factory.createEntityManager();
    }
    public static void close(){
        if (factory != null && factory.isOpen()) factory.close();
    }
}
