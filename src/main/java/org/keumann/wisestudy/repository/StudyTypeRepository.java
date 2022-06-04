package org.keumann.wisestudy.repository;

import org.keumann.wisestudy.domain.Member;
import org.keumann.wisestudy.domain.StudyType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudyTypeRepository extends JpaRepository<StudyType, Long> {

    Optional<StudyType> findByMemberAndName(Member member, String name);
}
