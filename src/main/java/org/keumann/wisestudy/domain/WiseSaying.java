package org.keumann.wisestudy.domain;

import lombok.*;
import org.keumann.template.saying.dto.WiseSayingDto;

import javax.persistence.*;

@Entity
@Table(name="wise_saying")
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WiseSaying extends BaseEntity{

    @Id
    @Column(name = "wise_saying_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Lob
    private String content;

    @Lob
    private String nativeContent;

    private String person;

    public static WiseSaying createWiseSaying(WiseSayingDto wiseSayingDto){
        return WiseSaying.builder()
                .person(wiseSayingDto.getPerson())
                .content(wiseSayingDto.getContent())
                .nativeContent(wiseSayingDto.getNativeContent())
                .build();
    }

    public void updateWiseSaying(WiseSayingDto wiseSayingDto){
        this.person = wiseSayingDto.getPerson();
        this.content = wiseSayingDto.getContent();
        this.nativeContent = wiseSayingDto.getNativeContent();
    }

}
