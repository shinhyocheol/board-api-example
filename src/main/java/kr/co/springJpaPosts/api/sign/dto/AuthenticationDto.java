package kr.co.springJpaPosts.api.sign.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationDto {


    private Long id;

    private String email;

    private String memberName;

    private String memberNickname;

    private String mobileNumber;

    private String memberProfileImg;

    private String memberEmail;

    private String regDate;

    private String modDate;

}
