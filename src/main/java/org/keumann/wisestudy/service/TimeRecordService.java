package org.keumann.wisestudy.service;

import lombok.RequiredArgsConstructor;
import org.keumann.wisestudy.domain.Member;
import org.keumann.wisestudy.domain.StudyType;
import org.keumann.wisestudy.domain.TimeRecord;
import org.keumann.wisestudy.dto.TimeRecordDto;
import org.keumann.wisestudy.repository.MemberRepository;
import org.keumann.wisestudy.repository.TimeRecordRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class TimeRecordService {

    private final TimeRecordRepository timeRecordRepository;
    private final StudyTypeService studyTypeService;
    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;

    public Long register(TimeRecordDto.Register registerDto, Member member) {

        //TODO: 로그인 적용 후 제거
        Member tMember = Member.builder()
                .email("admin@co.kr")
                .password("1111")
                .address("test")
                .name("admin")
                .build();
        memberRepository.save(tMember);

        LocalDate now = LocalDate.now();
        StudyType studyType = studyTypeService.getOrCreate(member, registerDto.getStudyTypeName());
        TimeRecord timeRecord = TimeRecord.builder()
                .member(member)
                .studyType(studyType)
                .seconds(registerDto.getSeconds())
                .studyDate(now)
                .day(now.getDayOfWeek())
                .build();

        TimeRecord savedTimeRecord = timeRecordRepository.save(timeRecord);
        return savedTimeRecord.getId();
    }

    public TimeRecordDto.DetailResponse findById(Long id) {
        TimeRecord timeRecord = timeRecordRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TimeRecord 엔티티를 찾을 수 없습니다. id: " + id));

        return modelMapper.map(timeRecord, TimeRecordDto.DetailResponse.class);
    }
}
