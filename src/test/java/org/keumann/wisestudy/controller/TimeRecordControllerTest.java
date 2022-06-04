package org.keumann.wisestudy.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.keumann.wisestudy.domain.TimeRecord;
import org.keumann.wisestudy.dto.TimeRecordDto;
import org.keumann.wisestudy.repository.TimeRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TimeRecordControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    TimeRecordRepository timeRecordRepository;

    Long id;

    @Test
    void register() throws Exception {

        TimeRecordDto.Register register = new TimeRecordDto.Register();
        register.setStudyTypeName("study");
        register.setSeconds(1000L);
        String json = new ObjectMapper().writeValueAsString(register);

        mockMvc.perform(post("/api/time-record")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
        )
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void detail() throws Exception {
        mockMvc.perform(get("/api/time-record/" + id))
                .andDo(print())
                .andExpect(status().isOk());
    }

}