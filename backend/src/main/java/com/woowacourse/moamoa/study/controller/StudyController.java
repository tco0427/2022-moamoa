package com.woowacourse.moamoa.study.controller;

import com.woowacourse.moamoa.study.service.StudyService;
import com.woowacourse.moamoa.study.service.response.StudiesResponse;
import com.woowacourse.moamoa.study.controller.request.FilterRequest;
import com.woowacourse.moamoa.study.service.StudyFilterService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/studies")
@RequiredArgsConstructor
public class StudyController {

    private final StudyService studyService;
    private final StudyFilterService studyFilterService;

    @GetMapping
    public ResponseEntity<StudiesResponse> getStudies(
            @PageableDefault(size = 5) final Pageable pageable
    ) {
        final StudiesResponse studiesResponse = studyService.getStudies(pageable);
        return ResponseEntity.ok().body(studiesResponse);
    }

    @GetMapping("/search")
    public ResponseEntity<StudiesResponse> searchStudies(
            @RequestParam(required = false, defaultValue = "") final String title,
            @ModelAttribute FilterRequest filterRequest,
            @PageableDefault(size = 5) final Pageable pageable
    ) {
        final StudiesResponse studiesResponse = studyFilterService.searchBy(title, filterRequest, pageable);
        return ResponseEntity.ok().body(studiesResponse);
    }
}
