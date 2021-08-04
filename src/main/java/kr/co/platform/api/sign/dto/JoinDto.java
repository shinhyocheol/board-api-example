package kr.co.platform.api.sign.dto;

import kr.co.platform.api.member.domain.entity.Members;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
@NoArgsConstructor
public class JoinDto {

    @NotBlank(message = "'email' is a required input value")
    @Email(message = "do not email type")
    private String email;

    @NotBlank(message = "'password' is a required input value")
    private String password;

    @NotBlank(message = "'name' is a required input value")
    private String name;

    @NotBlank(message = "'nickname' is a required input value")
    private String nickname;

    @NotBlank(message = "'mobile' is a required input value")
    private String mobile;

    public Members toEntity() {

        Members build = Members.builder()
                .email(email)
                .password(password)
                .name(name)
                .nickname(nickname)
                .mobile(mobile)
                .build();

        return build;
    }

}
