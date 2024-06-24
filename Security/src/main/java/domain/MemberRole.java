package domain;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

public class MemberRole {
	
	@Id
	@Column(length=20)
	private Member userNo; //사용자 번호
	
	@Id
	@Column(length=20)
	private MemberType roleName; //권한명
}
