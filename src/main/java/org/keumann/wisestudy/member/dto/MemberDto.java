package org.keumann.wisestudy.member.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.*;
import org.keumann.wisestudy.domain.MemberRole;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "회원")
public class MemberDto {

    private String email;

    private String name;

    private String password;

    private String nickname;

    private String loginYn;

    private String gender;

    private String userType;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate birthday;

    private String ageRange;

    private String profileUrl;

    private String phoneNumber;

    private List<MemberRole> roles;

}
