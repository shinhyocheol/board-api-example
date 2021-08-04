package kr.co.platform.api.sign.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuthenticationDto {


    private Long id;

    private String email;

    private String name;

    private String nickname;

    private String mobile;

    private String regDate;

    private String modDate;

}
