package com.umc5th.dayrecord.web.controller;


import com.umc5th.dayrecord.apiPayload.ApiResponse;
import com.umc5th.dayrecord.service.AutoIndexService;
import com.umc5th.dayrecord.service.StreamService.StreamCommandService;
import com.umc5th.dayrecord.web.dto.AutoIndexDTO;
import com.umc5th.dayrecord.web.dto.StreamDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class StreamGeneralController {
    private final StreamCommandService streamCommandService;
    private final AutoIndexService autoIndexService;

    @PutMapping("/stream/change-stream-keyword")
    public ApiResponse<?> changeStreamKeyword(@RequestBody @Valid StreamDTO.ChangeStreamKeywordRequestDTO request) {
        return ApiResponse.onSuccess(streamCommandService.changeStreamKeyword(request));
    }

    @GetMapping("/stream/auto-indexing")
    public ApiResponse<?> autoIndex(@RequestBody @Valid AutoIndexDTO.AutoIndexRequestDTO request) {
        return ApiResponse.onSuccess(autoIndexService.autoIndex(request));
    }
}
