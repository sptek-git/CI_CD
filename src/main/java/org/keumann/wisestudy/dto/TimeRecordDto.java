package org.keumann.wisestudy.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

public class TimeRecordDto {

    @Getter @Setter
    public static class Register {
        @NotEmpty
        private String studyTypeName;
        @Min(0)
        private Long seconds;
    }

    @Getter @Setter
    public static class DetailResponse {
        private Long id;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
        private LocalDate studyDate;
        private Long seconds;
        private String StudyTypeName;
    }

}
