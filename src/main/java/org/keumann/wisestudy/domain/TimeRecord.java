package org.keumann.wisestudy.domain;

import lombok.*;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalDate;

@Entity
@Table(name="time_record")
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TimeRecord extends BaseEntity{

    @Id
    @Column(name="time_record_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate studyDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "day")
    private DayOfWeek day;

    private Long seconds;

    @OneToOne
    @JoinColumn(name = "study_type_id")
    private StudyType studyType;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public TimeRecord(LocalDate studyDate, DayOfWeek day, Long seconds, StudyType studyType, Member member) {
        this.studyDate = studyDate;
        this.day = day;
        this.seconds = seconds;
        this.studyType = studyType;
        this.member = member;
    }
}
