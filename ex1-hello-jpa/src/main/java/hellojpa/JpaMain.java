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
        System.out.print("-> Input your order : ");
        String order = br.readLine();
        if (order.equals("1")) {
            try {
                // 비영속
                Member member = new Member();
                member.setId(100L);
                member.setName("HelloJPA");

                // 영속
                System.out.println("-> BEFORE");
                em.persist(member);
                System.out.println("-> AFTER");

                Member findMember = em.find(Member.class, 100L);
                System.out.println("-> findMember.id : " + findMember.getId());
                System.out.println("-> findMember.name : " + findMember.getName());

                tx.commit();
            } catch (Exception e) {
                tx.rollback();
            } finally {
                em.close();
            }
        }

        if (order.equals("2")) {
            try {
                Member member1 = new Member(101L, "A");
                Member member2 = new Member(102L, "B");
                em.persist(member1);
                em.persist(member2);
                System.out.println("====================");
                tx.commit();
            } catch (Exception e) {
                tx.rollback();
            } finally {
                em.close();
            }
        }

        if (order.equals("3")) {
            try {
                Member member = em.find(Member.class, 100L);
                member.setName("ZZZZZ");
                // em.persist(member)가 없어도 자동으로 member가 업데이트 된다.
                tx.commit();
            } catch (Exception e) {
                tx.rollback();
            } finally {
                em.close();
            }
        }

        if (order.equals("4")) {
            try {
                Member member = new Member(100L, "Member");
                em.persist(member);
                em.flush();
                System.out.println("=========================");
                tx.commit();
            } catch (Exception e) {
                tx.rollback();
            } finally {
                em.close();
            }
        }

        if (order.equals("5")) {
            try {
                Member member = em.find(Member.class, 100L);
                member.setName("AAAAA");
                em.detach(member);
                System.out.println("=========================");
                tx.commit();
            } catch (Exception e) {
                tx.rollback();
            } finally {
                em.close();
            }
        }

        if (order.equals("6")) {
            try {
                Member member1 = em.find(Member.class, 100L);
                member1.setName("BBBBB");
                em.clear();
                Member member2 = em.find(Member.class, 100L);
                System.out.println("=========================");
                tx.commit();
            } catch (Exception e) {
                tx.rollback();
            } finally {
                em.close();
            }
        }


        emf.close();
    }
}
