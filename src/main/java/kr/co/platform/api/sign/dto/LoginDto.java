package kr.co.platform.api.sign.dto;

import javax.validation.constraints.NotBlank;

import kr.co.platform.api.member.domain.entity.Members;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class LoginDto {

	@NotBlank(message = "'email' is a required input value")
	private String email;

	@NotBlank(message = "'password' is a required input value")
	private String password;

	
	public Members toEntity() {
		Members build = Members.builder()
				.email(email)
				.password(password)
				.build();
		
		return build;
	}
	
}
