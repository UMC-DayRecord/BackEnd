package com.umc5th.dayrecord.web.controller;


import com.umc5th.dayrecord.apiPayload.ApiResponse;
import com.umc5th.dayrecord.service.StreamService.StreamCommandService;
import com.umc5th.dayrecord.web.dto.StreamDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class StreamGeneralController {
    private final StreamCommandService streamCommandService;

    @PutMapping("/stream/change-stream-keyword")
    public ApiResponse<?> changeStreamKeyword(@RequestBody @Valid StreamDTO.ChangeStreamKeywordRequestDTO request) {
        return ApiResponse.onSuccess(streamCommandService.changeStreamKeyword(request));
    }
}
