package org.keumann.wisestudy.service;

import lombok.RequiredArgsConstructor;
import org.keumann.wisestudy.domain.Member;
import org.keumann.wisestudy.domain.StudyType;
import org.keumann.wisestudy.repository.StudyTypeRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudyTypeService {

    private final StudyTypeRepository studyTypeRepository;

    public StudyType getOrCreate(Member member, String name) {

        return studyTypeRepository.findByMemberAndName(member, name)
                .orElseGet(() -> {
                    StudyType studyType = StudyType.builder()
                            .member(member)
                            .name(name)
                            .build();
                    return studyTypeRepository.save(studyType);
                });

    }

}
