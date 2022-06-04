package org.keumann.wisestudy.repository;

import org.keumann.wisestudy.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

    Member findMemberByEmail(String email);

}