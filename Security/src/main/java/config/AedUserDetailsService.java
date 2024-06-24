package config;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import domain.Member;
import sevice.MemberService;

@Component
public class AedUserDetailsService implements UserDetailsService{
	
	private final MemberService memberService;
	
	@Autowired
	public AedUserDetailsService(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException{
		Optional<Member> findOne = memberService.findOne(userId);
		Member member = findOne.orElseThrow(()-> new UsernameNotFoundException("존재하지 않는 회원입니다."));
		
		return User.builder()
				.username(member.getUserId())
				.password(member.getUserPassword())
				.build();
	}

}
