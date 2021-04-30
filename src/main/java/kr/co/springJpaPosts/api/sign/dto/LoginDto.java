package kr.co.springJpaPosts.api.sign.dto;

import javax.validation.constraints.NotBlank;

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

	
}
