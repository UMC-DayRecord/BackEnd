package com.umc5th.dayrecord.service;

import com.umc5th.dayrecord.web.dto.AutoIndexDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AutoIndexServiceImpl implements AutoIndexService {
    public List<String> autoIndex(AutoIndexDTO.AutoIndexRequestDTO request) {
        // do something

        List<String> result = new ArrayList<>();

        for(String photo: request.getPhotoList()) {
            result.add("이것은 반환값");
        }
        
        return result;
    }
}
