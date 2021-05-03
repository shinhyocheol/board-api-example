package kr.co.platform.api.sign.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class JoinDto {

    @NotBlank(message = "'email' is a required input value")
    private String email;

    @NotBlank(message = "'password' is a required input value")
    private String password;

    @NotBlank(message = "'name' is a required input value")
    private String name;

    @NotBlank(message = "'nickname' is a required input value")
    private String nickname;

    @NotBlank(message = "'mobile' is a required input value")
    private String mobile;

}
