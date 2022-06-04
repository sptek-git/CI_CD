package org.keumann.wisestudy.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.keumann.wisestudy.admin.member.dto.MemberFormDto;
import org.keumann.wisestudy.constant.Role;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="member")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Member extends BaseEntity{

    @Id
    @Column(name="member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private String address;

    private String nickname;

    private String gender;

    private String userType;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate birthday;

    private String ageRange;

    private String profileUrl;

    private String phoneNumber;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private List<MemberRole> roles;

    @Builder
    public Member(String name, String email, String password, String address, List<MemberRole> roles) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.roles = roles;
    }

    public static Member createAdmin(MemberFormDto memberFormDto) {
        List<MemberRole> memberRoles = new ArrayList<>();
        MemberRole memberRole = MemberRole.builder()
                .roleName(Role.ADMIN.toString())
                .build();
        memberRoles.add(memberRole);

        return Member.builder()
                .name(memberFormDto.getName())
                .email(memberFormDto.getEmail())
                .address(memberFormDto.getAddress())
                .password(memberFormDto.getPassword())
                .roles(memberRoles)
                .build();
    }
}
