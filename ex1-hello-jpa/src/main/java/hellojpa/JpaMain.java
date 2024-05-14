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
			Team team = new Team();
			team.setName("TeamA");
			em.persist(team);

			Member member = new Member();
			member.setUsername("member1");
			member.setTeam(team);
			em.persist(member);

			em.flush();
			em.clear();

			Member findMember = em.find(Member.class, member.getId());
			List<Member> members = findMember.getTeam().getMembers();

            /*
                Member findMember = em.find(Member.class, member.getId());를 실행하면
                영속성 컨텍스트에서 member 엔티티를 찾을 수 있지만,
                List<Member> members = findMember.getTeam().getMembers();를 실행할 때는
                team 객체와 연결된 member 객체를 데이터베이스에서 찾아야 하는데,
                아직 member 엔티티가 데이터베이스에 반영되지 않았기 때문에
                member 객체를 찾을 수 없어서 출력이 되지 않는 것입니다.

                따라서 em.flush();를 호출하여 영속성 컨텍스트의 상태를 데이터베이스에 동기화하고,
                em.clear();를 호출하여 영속성 컨텍스트를 초기화한 후 다시 데이터베이스에서 member 엔티티를 조회하면,
                team 객체와 연결된 member 객체를 정상적으로 찾아 출력할 수 있습니다.
            */

			for (Member m : members) {
				System.out.println("-> member : " + m.getUsername());
			}

			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}
		emf.close();
	}
}
