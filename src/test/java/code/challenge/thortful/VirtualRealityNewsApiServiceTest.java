package code.challenge.thortful;

import code.challenge.thortful.controller.VirtualRealityNewsController;
import code.challenge.thortful.exception.StatusException;
import code.challenge.thortful.model.dto.PostDTO;
import code.challenge.thortful.service.VirtualRealityNewsApiService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VirtualRealityNewsApiServiceTest {

    @Autowired
    private VirtualRealityNewsController virtualRealityNewsController;

    @Mock
    private VirtualRealityNewsApiService virtualRealityNewsApiService;

    @BeforeEach
    void setUp() {
        virtualRealityNewsController = new VirtualRealityNewsController(virtualRealityNewsApiService);
    }

    @Test
    void shouldGetNewPosts() {
        // Arrange
        PostDTO postDTO = new PostDTO();
        postDTO.setTitle("Test Title");
        postDTO.setAuthor("Test Author");
        postDTO.setPermalink("https://www.reddit.com/test");
        List<PostDTO> postDTOList = Collections.singletonList(postDTO);
        when(virtualRealityNewsApiService.getNewPosts(1, 10)).thenReturn(Mono.just(postDTOList));

        // Act
        Mono<ResponseEntity<List<PostDTO>>> result = virtualRealityNewsController.getNewPosts(1, 10);

        // Assert
        StepVerifier
                .create(result)
                .expectNextMatches(response ->
                        response.getStatusCode().equals(HttpStatus.OK) &&
                                Objects.equals(response.getBody(), postDTOList)
                )
                .verifyComplete();
    }

    @Test
    void shouldGetTopPosts() {
        // Arrange
        PostDTO postDTO = new PostDTO();
        postDTO.setTitle("Test Title");
        postDTO.setAuthor("Test Author");
        postDTO.setPermalink("https://www.reddit.com/test");
        List<PostDTO> postDTOList = Collections.singletonList(postDTO);
        when(virtualRealityNewsApiService.getTopPosts(1, 10)).thenReturn(Mono.just(postDTOList));

        // Act
        Mono<List<PostDTO>> result = virtualRealityNewsApiService.getTopPosts(1, 10);

        // Assert
        StepVerifier
                .create(result)
                .expectNext(postDTOList)
                .verifyComplete();
    }

    @Test
    void shouldGetPostById() {
        // Arrange
        String postId = "10gb8kn";
        PostDTO postDTO = new PostDTO();
        postDTO.setTitle("Test Title");
        postDTO.setAuthor("Test Author");
        postDTO.setPermalink("https://www.reddit.com/test");
        List<PostDTO> postDTOList = Collections.singletonList(postDTO);

        when(virtualRealityNewsApiService.getPostsById(postId)).thenReturn(Mono.just(postDTOList));

        // Act
        Mono<List<PostDTO>> result = virtualRealityNewsApiService.getPostsById(postId);

        // Assert
        StepVerifier
                .create(result)
                .expectNext(postDTOList)
                .verifyComplete();
    }

    @Test
    void shoudlCreateErrorWhenGetPostById() {
        // Arrange
        String postId = "10gb8kn";
        String errorMessage = "Error getting the post by id from the third party API.";
        when(virtualRealityNewsApiService.getPostsById(postId)).thenReturn(Mono.error(new StatusException(errorMessage)));

        // Act
        Mono<List<PostDTO>> result = virtualRealityNewsApiService.getPostsById(postId);

        // Assert
        StepVerifier
                .create(result)
                .expectError(StatusException.class)
                .verify();
    }
}