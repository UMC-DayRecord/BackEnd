package com.umc5th.dayrecord.service.DiaryService;

import com.umc5th.dayrecord.converter.DiaryConverter;
import com.umc5th.dayrecord.domain.Diary;
import com.umc5th.dayrecord.domain.Post;
import com.umc5th.dayrecord.domain.Stream;
import com.umc5th.dayrecord.domain.User;
import com.umc5th.dayrecord.repository.DiaryRepository;
import com.umc5th.dayrecord.repository.UserRepository;
import com.umc5th.dayrecord.service.PostService.PostCommandService;
import com.umc5th.dayrecord.web.dto.DiaryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiaryCommandServiceImpl implements DiaryCommandService {

    private final DiaryQueryService diaryQueryService;
    private final DiaryRepository diaryRepository;
    private final PostCommandService postCommandService;
    private final UserRepository userRepository;


    // 일기 기록
    @Override
    public Diary recordDetail(DiaryDTO.requestDiaryDetailDTO request) {
        Diary diary = diaryQueryService.findDiary(request.getStreamId());

        diary.update(request.getDetail());
        return diaryRepository.save(diary);
    }

    @Override
    public void saveDiary(Long userId) {
        User user = userRepository.findById(userId).get();
        for(Stream stream : user.getStreamList()) {
            Diary diary = diaryQueryService.findDiary(stream.getId());
            Post post = postCommandService.createPost(diary);
            deleteDiary(diary);
            createDiary(stream, user);
        }
    }

    // 저장한 일기 삭제
    @Override
    public void deleteDiary(Diary diary) {
        diaryRepository.delete(diary);
    }

    // 새로운 일기보드 생성
    @Override
    public void createDiary(Stream stream, User user) {
        Diary diary = DiaryConverter.createDiary(stream, user);
        diaryRepository.save(diary);
    }
}
