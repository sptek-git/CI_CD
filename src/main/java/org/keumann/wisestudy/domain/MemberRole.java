package org.keumann.wisestudy.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "member_role")
@EqualsAndHashCode(of = "rno", callSuper = false)
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberRole extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

    private String roleName;

}
