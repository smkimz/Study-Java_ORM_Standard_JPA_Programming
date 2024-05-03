package hellojpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) throws IOException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("[Input your order]");
        String order = br.readLine();
        tx.begin();
        if (order.equals("create")) {
            try {
                Member member = new Member();
                member.setId(1L);
                member.setName("HelloA");
                em.persist(member);
                tx.commit();
            } catch (Exception e) {
                tx.rollback();
            } finally {
                em.close();
            }
            emf.close();
            return;
        }

        if (order.equals("read")) {
            try {
                Member findMember = em.find(Member.class, 1L);
                System.out.println("[findMember.id] " + findMember.getId());
                System.out.println("[findMember.name] " + findMember.getName());
                tx.commit();
            } catch (Exception e) {
                tx.rollback();
            } finally {
                em.close();
            }
            emf.close();
            return;
        }

        if (order.equals("delete")) {
            try {
                Member findMember = em.find(Member.class, 1L);
                em.remove(findMember);
                tx.commit();
            } catch (Exception e) {
                tx.rollback();
            } finally {
                em.close();
            }
            emf.close();
            return;
        }

        if (order.equals("update")) {
            try {
                Member findMember = em.find(Member.class, 1L);
                findMember.setName("HelloJPA");
                //em.persist(findMember); // 수정 시에는 persist X
                tx.commit();
            } catch (Exception e) {
                tx.rollback();
            } finally {
                em.close();
            }
            emf.close();
            return;
        }

        if (order.equals("members")) {
            try {
                List<Member> findMembers = em.createQuery("select m from Member m", Member.class)
                        .setFirstResult(0)
                        .setMaxResults(5)
                        .getResultList();
                for (Member member : findMembers) {
                    System.out.println("[member.name] " + member.getName());
                }
                tx.commit();
            } catch (Exception e) {
                tx.rollback();
            } finally {
                em.close();
            }
            emf.close();
            return;
        }
    }
}
