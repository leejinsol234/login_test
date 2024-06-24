package repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{
	
	Optional<Member> findByUserid(String userId);
		
}
