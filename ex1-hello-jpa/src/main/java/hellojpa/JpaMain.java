package hellojpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.io.IOException;

public class JpaMain {

    public static void main(String[] args) throws IOException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Member member1 = new Member();
            member1.setUsername("A");

            Member member2 = new Member();
            member2.setUsername("B");

            Member member3 = new Member();
            member3.setUsername("C");

            System.out.println("=========================");
            em.persist(member1);
            em.persist(member2);
            em.persist(member3);
            System.out.println("-> member1.id : " + member1.getId());   // DB SEQ = 1, Application = 1
            System.out.println("-> member2.id : " + member2.getId());   // DB SEQ = 51, Application = 2 (메모리)
            System.out.println("-> member3.id : " + member3.getId());   // DB SEQ = 51, Application = 3 (메모리)
            System.out.println("=========================");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
