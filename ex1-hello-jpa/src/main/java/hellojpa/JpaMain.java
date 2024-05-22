package hellojpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class JpaMain {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			Team teamA = new Team();
			teamA.setName("TeamA");
			em.persist(teamA);

			Team teamB = new Team();
			teamB.setName("TeamB");
			em.persist(teamB);

			Member memberA = new Member();
			memberA.setUsername("MemberA");
			memberA.setTeam(teamA);
			em.persist(memberA);

			Member memberB = new Member();
			memberB.setUsername("MemberB");
			memberB.setTeam(teamB);
			em.persist(memberB);

			em.flush();
			em.clear();

			List<Member> members = em.createQuery("SELECT m FROM Member m JOIN FETCH m.team", Member.class).getResultList();

			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
		emf.close();
	}
}
