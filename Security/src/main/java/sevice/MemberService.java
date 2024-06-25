package sevice;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import domain.Member;
import repository.MemberRepository;

@Service
public class MemberService implements UserDetailsService{
	
	private final MemberRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		Member member = repository.findByUserid(username)
				.orElseThrow(()-> new UsernameNotFoundException("존재하지 않는 사용자입니다."));
		
		if(member.getUserId().equals(username)) {
			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		}
		
		return new User(member.getUserId(), member.getUserPassword(), authorities);
	}
	
	@Autowired
	public MemberService(MemberRepository repository) {
		this.repository = repository;
	}
	
	public Optional<Member> findOne(String userId){
		return repository.findByUserid(userId);
	}

}
