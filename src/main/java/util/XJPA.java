package util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class XJPA {
   static String DBNAME = "RestaurantDB";
    private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory(DBNAME);
    public static EntityManager getEntityManager() {
        return factory.createEntityManager();
    }
}