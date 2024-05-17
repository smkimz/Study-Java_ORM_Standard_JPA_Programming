package hellojpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JpaMain {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			// 1
			Movie movie = new Movie();
			movie.setDirector("AAA");
			movie.setActor("BBB");
			movie.setName("바람과함께사라지다");
			movie.setPrice(10000);
			em.persist(movie);

			// 2
			em.flush();
			em.clear();
			Item findItem = em.find(Item.class, movie.getId());
			System.out.println("-> findItem : " + findItem);

			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}
		emf.close();
	}
}
