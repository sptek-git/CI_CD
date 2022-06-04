package org.keumann.wisestudy.repository;

import org.keumann.wisestudy.domain.TimeRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeRecordRepository extends JpaRepository<TimeRecord, Long> {
}
