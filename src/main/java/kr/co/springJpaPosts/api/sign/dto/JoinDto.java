package kr.co.springJpaPosts.api.sign.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class JoinDto {

    @NotBlank(message = "'memberId' is a required input value")
    private String email;

    private String password;

    private String memberName;

    private String memberNickname;

    private String mobileNumber;

}
