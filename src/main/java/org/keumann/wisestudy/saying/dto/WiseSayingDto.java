package org.keumann.template.saying.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter @Setter
@Builder @NoArgsConstructor @AllArgsConstructor
public class WiseSayingDto {

    private Long id;

    @NotBlank
    private String content;

    private String nativeContent;

    @NotBlank
    private String person;

}
