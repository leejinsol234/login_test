package domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class AedList {

	@Id
	@Column(length=20)
	private String deviceSno; //장비일련번호
	
	@Column(length=100)
	private String title; //장비명
	
	@Column(length=20)
	private Member userNo; //사용자번호
	
	@Column(length=14)
	private String createDatetime; //등록 일시
}
