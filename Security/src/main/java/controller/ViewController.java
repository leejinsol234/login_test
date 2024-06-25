package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import domain.Member;
import repository.MemberRepository;

@RestController
@RequestMapping("/")
public class ViewController {
	
	@Autowired
	private MemberRepository repository;
	
	@GetMapping("/")
	public String showVuePage() {
		return "/index.html";
	}
	
	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}
	
	@GetMapping("/list")
	public Member getCurrentUser(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userId = null;
		
		if(authentication.getPrincipal() instanceof UserDetails) {
			userId = ((UserDetails) authentication.getPrincipal()).getUsername();
		} else {
			userId = authentication.getPrincipal().toString();
		}
		
		return repository.findByUserid(userId)
				.orElseThrow(()->new RuntimeException("사용자를 찾을 수 없습니다."));
	}
	
//	@GetMapping("/list")
//	public String aedList(@AuthenticationPrincipal User user, Model model) {
//		model.addAttribute("loginId", user.getUsername());
//		model.addAttribute("aedList", user.getAuthorities());
//		return "list";
//	}

}
