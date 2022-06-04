package org.keumann.wisestudy.controller;

import lombok.RequiredArgsConstructor;
import org.keumann.wisestudy.annotation.CurrentMember;
import org.keumann.wisestudy.domain.Member;
import org.keumann.wisestudy.dto.TimeRecordDto;
import org.keumann.wisestudy.service.TimeRecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/time-record")
@RequiredArgsConstructor
public class TimeRecordController {

    private final TimeRecordService timeRecordService;

    @PostMapping
    public ResponseEntity<Long> registerTimeRecord(
            @CurrentMember Member member,
            @Valid @RequestBody TimeRecordDto.Register registerDto) {

        Long timeRecordId = timeRecordService.register(registerDto, member);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(timeRecordId)
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTimeRecord(@PathVariable Long id) {
        TimeRecordDto.DetailResponse dto = timeRecordService.findById(id);
        return ResponseEntity.ok(dto);
    }

}
