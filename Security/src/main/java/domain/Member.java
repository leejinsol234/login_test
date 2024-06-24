package domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Member {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userNo; //사용자 번호
	
	@Column(length=50, unique=true)
	private String userId; //사용자 id
	
	@Column(length=60)
	private String userPassword; //사용자 비밀번호
	
	@Column(length=20)
	private String userName; //사용자 이름
	
	@Enumerated(EnumType.STRING)
	private MemberType roleName;
}
