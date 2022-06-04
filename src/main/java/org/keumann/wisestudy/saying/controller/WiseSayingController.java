package org.keumann.template.saying.controller;

import lombok.RequiredArgsConstructor;
import org.keumann.template.saying.dto.WiseSayingDto;
import org.keumann.template.saying.service.WiseSayingService;
import org.keumann.wisestudy.domain.WiseSaying;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class WiseSayingController {

    private final WiseSayingService wiseSayingService;

    @GetMapping(value = {"/wise/saying", "/wise/saying/{id}"})
    public String goSaying(Model model, @PathVariable("id") Optional<Long> id){
        Long wiseSayingId = id.isPresent() ? id.get() : null;
        WiseSayingDto wiseSayingDto = wiseSayingService.getWiseSayingDto(wiseSayingId);
        model.addAttribute("wiseSayingDto", wiseSayingDto);
        return "saying/wiseSaying";
    }

    @PostMapping("/wise/saying")
    public @ResponseBody ResponseEntity<Long> saveWiseSaying(Principal principal, @Valid WiseSayingDto wiseSayingDto){
        Long id = wiseSayingService.saveWiseSaying(wiseSayingDto);
        return new ResponseEntity<Long>(id, HttpStatus.OK);
    }

    @GetMapping("/wise/sayings")
    public String wiseSayingList(Model model, @RequestParam(defaultValue = "0", required = false) int page){
        Pageable pageable = PageRequest.of(page, 10, Sort.by("id").descending());
        Page<WiseSaying> pages = wiseSayingService.getWiseSayingList(pageable);
        model.addAttribute("pages", pages);
        model.addAttribute("page", pageable.getPageNumber());
        model.addAttribute("maxPage", 5);
        return "saying/wiseSayingList";
    }

}
