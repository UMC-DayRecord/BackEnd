package com.umc5th.dayrecord.service.StreamService;

import com.umc5th.dayrecord.apiPayload.code.status.ErrorStatus;
import com.umc5th.dayrecord.apiPayload.exception.handler.RegisterHandler;
import com.umc5th.dayrecord.apiPayload.exception.handler.UserNotFoundHandler;
import com.umc5th.dayrecord.domain.Post;
import com.umc5th.dayrecord.domain.Stream;
import com.umc5th.dayrecord.domain.User;
import com.umc5th.dayrecord.repository.PostRepository;
import com.umc5th.dayrecord.repository.StreamRepository;
import com.umc5th.dayrecord.repository.UserRepository;
import com.umc5th.dayrecord.service.DiaryService.DiaryCommandService;
import com.umc5th.dayrecord.service.UserQueryService;
import com.umc5th.dayrecord.web.dto.PostDTO;
import com.umc5th.dayrecord.web.dto.StreamDTO;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StreamQueryServiceImpl implements StreamQueryService {

    private final StreamRepository streamRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    private final UserQueryService userQueryService;

    private final DiaryCommandService diaryCommandService;
    

    @Override
    public StreamDTO.streamDefaultDTO insertStream(StreamDTO.streamDefaultDTO request) {
        
        // User user = UserConverter.RegisterRequestToUser(request);
        
        Optional<User> user = userRepository.findById(request.getUserId());
        // System.out.println("test" + user.get());
        Stream stream = Stream.builder()
                        .streamName(request.getStreamName())
                        .isPublic(request.getIsPublic())
                        .user(user.get())
                        .build();
        // System.out.println("stream" + stream);
        
        streamRepository.save(stream);

        // 스트림 생성 시, 다이어리 생성
        diaryCommandService.createDiary(stream, user.get());

        StreamDTO.streamDefaultDTO streamResponse = StreamDTO.streamDefaultDTO.builder()
            .userId(stream.getUser().getId())
            .isPublic(stream.getIsPublic())
            .streamName(stream.getStreamName())
            .streamId(stream.getId())
            .build();
        return streamResponse;
    }

    @Override
    public List<Stream> getStreamList(Long userId, Integer page){
        User user = userRepository.findById(userId).get();
        
        //List<Stream> streamList = streamRepository.findAll();
        List<Stream> streamList = user.getStreamList();
        // userId, PageRequest.of(page, 10)
        //System.out.println("Stream " + streamList);
        return streamList;
    }


    public Post getPostDetailInfo(Long postId) {
        Post post = postRepository.findById(postId).get();
        return post;
    }

    @Override
    public Slice<Post> getStreamPostList(Long userId, Long streamId, Integer page) {
        Slice<Post> postList = streamRepository.findByStream(userId, streamId, PageRequest.of(page, 10));
        return postList;
    }
    
    @Override
    public Slice<Post> getDaliyBoardList(Long userId, Long streamId, Integer page) {
        Slice<Post> postList = streamRepository.findByStream(userId, streamId, PageRequest.of(page, 10));
        return postList;
    }


    @Override
    public Slice<Post> getSearchList(Long userId, String query, Integer page) {
        Slice<Post> postList = streamRepository.findBySearchPost(userId, query, PageRequest.of(page, 10));
        return postList;
    }
    
    @Override
    public void deleteStream(Long streamId){
        streamRepository.deleteById(streamId);
    }
    @Override
    public void deletePost(Long postId){
        postRepository.deleteById(postId);
    }

    @Override
    public Boolean existById(Long streamId) {
        return streamRepository.existsById(streamId);
    }

    // Post updatePost(PostDTO.editPostRequestDTO request, Long postId);

    @Override
    public Stream changeVisibleStream(StreamDTO.visibleStreamRequestDTO request, Long userId, Long streamId){
        Stream stream = streamRepository.findById(streamId).get();
        if(userId.equals( stream.getUser().getId() )) {
            stream.updateVisible(request.getIsPublic());
            streamRepository.save(stream);
        }
        else{
            throw new RegisterHandler(ErrorStatus._USER_NOT_MATCH);
        }
        return stream;
    }
    //Post updatePost(PostDTO.editPostRequestDTO request, Long postId)
    //  }

    @Override
    public Optional<Stream> getStreamFromLoggedOnUserStreamList(Long streamId) {
        String currentUser = userQueryService.getLoggedInUserNickName()
                .orElseThrow(() -> new UserNotFoundHandler(ErrorStatus._UNAUTHORIZED));

        User user = userQueryService.getUser(currentUser)
                .orElseThrow(() -> new UserNotFoundHandler(ErrorStatus._USER_NOT_FOUND));

        List<Stream> userStreamList = user.getStreamList();

        return userStreamList.stream()
                .filter(stream -> stream.getId().equals(streamId))
                .findAny();
    }
}

