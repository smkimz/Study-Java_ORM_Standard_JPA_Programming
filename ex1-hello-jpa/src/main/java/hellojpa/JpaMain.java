package hellojpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JpaMain {

    public static void main(String[] args) throws IOException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            Member member = new Member();
            member.setId(100L);
            member.setName("HelloJPA");
            em.persist(member);

            Member findMember = em.find(Member.class, 100L);
            System.out.println("-> findMember.id : " + findMember.getId());
            System.out.println("-> findMember.name : " + findMember.getName());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
