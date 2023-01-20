package code.challenge.thortful.service;

import code.challenge.thortful.exception.StatusException;
import code.challenge.thortful.model.dto.PostDTO;
import code.challenge.thortful.model.entity.DataChildren;
import code.challenge.thortful.model.entity.SearchResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VirtualRealityNewsApiService {
    private static final String BASE_URL = "https://www.reddit.com/r/virtualreality";

    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    @Autowired
    public VirtualRealityNewsApiService(final WebClient webClient, final ObjectMapper objectMapper) {
        this.webClient = webClient;
        this.objectMapper = objectMapper;
    }

    private <T> Mono<T> sendRequest(final String uri,
                                    final ParameterizedTypeReference<T> type,
                                    final String errorMessage) {
        return webClient.get()
                .uri(uri)
                .retrieve()
                .onStatus(HttpStatus::isError, response -> Mono.error(
                        new StatusException(errorMessage))
                )
                .bodyToMono(type);
    }

    public Mono<List<PostDTO>> getNewPosts(final int page,final int size) {
        final String errorMessage = "Error getting new posts from the third party API.";
        return sendRequest(BASE_URL + "/new.json", new ParameterizedTypeReference<SearchResult>() {
        }, errorMessage)
                .map(searchResult ->
                        searchResult.getData().getChildren()
                                .stream()
                                .map(DataChildren::getDataChildrenData)
                                .map(data -> objectMapper.convertValue(data, PostDTO.class))
                                .skip((long) (page) * size)
                                .limit(size)
                                .collect(Collectors.toList())
                );
    }

    public Mono<List<PostDTO>> getTopPosts(final int page, final int size) {
        final String errorMessage = "Error getting the top posts from the third party API.";

        return sendRequest(BASE_URL + "/top.json", new ParameterizedTypeReference<SearchResult>() {
        }, errorMessage)
                .map(searchResult ->
                        searchResult.getData().getChildren()
                                .stream()
                                .map(DataChildren::getDataChildrenData)
                                .map(data -> objectMapper.convertValue(data, PostDTO.class))
                                .skip((long) (page) * size)
                                .limit(size)
                                .collect(Collectors.toList())
                );
    }

    public Mono<List<PostDTO>> getPostsById(final String id) {
        final String errorMessage = "Error getting the post by id from the third party API.";

        return sendRequest(BASE_URL + "/comments/"+ id +".json",
                new ParameterizedTypeReference<List<SearchResult>>() {},
                errorMessage)
                .map(searchResult ->
                        searchResult.stream()
                                .map(result -> result.getData().getChildren())
                                .flatMap(List::stream)
                                .map(DataChildren::getDataChildrenData)
                                .map(data -> objectMapper.convertValue(data, PostDTO.class))
                                .collect(Collectors.toList())
                );
    }

}