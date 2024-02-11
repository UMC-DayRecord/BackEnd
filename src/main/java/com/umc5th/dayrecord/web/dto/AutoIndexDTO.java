package com.umc5th.dayrecord.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

public class AutoIndexDTO {
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AutoIndexRequestDTO {
        @NotNull(message = "photoList는 필수 항목입니다.")
        private List<String> photoList;
    }
}
