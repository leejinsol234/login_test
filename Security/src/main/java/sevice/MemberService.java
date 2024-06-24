package sevice;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.Member;
import repository.MemberRepository;

@Service
public class MemberService {
	
	private final MemberRepository repository;
	
	@Autowired
	public MemberService(MemberRepository repository) {
		this.repository = repository;
	}
	
	public Optional<Member> findOne(String userId){
		return repository.findByUserid(userId);
	}

}
