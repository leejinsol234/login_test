package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import jakarta.servlet.DispatcherType;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

	    @Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    	
	    	/* 인가 설정 - 접근 통제 S */
	    	http
            .authorizeHttpRequests(authorize -> {authorize
            	.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
            	.anyRequest().authenticated()
            	
        		.requestMatchers("/").authenticated() // requestMatchers: 특정 URL 패턴을 허용
        		//.requestMatchers("/admin/**").hasAuthority("ADMIN") //관리자 권한만 접근
                .requestMatchers(
                         "/common/css/**",
                         "/common/js/**",
                         "/common/images/**"
                		).permitAll()
                		.anyRequest().permitAll();
            });
	    	/* 인가 설정 - 접근 통제 E */
    	
	    	/* 인증 설정 - 로그인 S */
	        http.formLogin(login -> {
	            login
	            	.loginPage("/login")
            		.loginProcessingUrl("/login-process")
                    .usernameParameter("userId")
                    .passwordParameter("password")
	            	.defaultSuccessUrl("/list", true)
                    //.successHandler(new LoginSuccessHandler())
                    //.failureHandler(new LoginFailureHandler());
	            	.permitAll();
	        }); 
	        
	        http.logout(c -> {
	            c.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	                    .logoutSuccessUrl("/");
	        });
	        /* 인증 설정 - 로그인 E */
	        
	       

	    	return http.build();
	    }
	    
	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	}


