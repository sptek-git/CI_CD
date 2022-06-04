package org.keumann.wisestudy.repository;

import org.keumann.wisestudy.domain.WiseSaying;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WiseSayingRepository extends JpaRepository<WiseSaying, Long> {
}
