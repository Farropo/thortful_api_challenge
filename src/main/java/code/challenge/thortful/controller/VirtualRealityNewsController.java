package code.challenge.thortful.controller;

import code.challenge.thortful.model.dto.PostDTO;
import code.challenge.thortful.service.VirtualRealityNewsApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/virtual-reality")
@Api(value = "virtual reality news")
public class VirtualRealityNewsController {

    private final VirtualRealityNewsApiService virtualRealityNewsApiService;

    @Autowired
    public VirtualRealityNewsController(VirtualRealityNewsApiService virtualRealityNewsApiService) {
        this.virtualRealityNewsApiService = virtualRealityNewsApiService;
    }

    @GetMapping("/new")
    @ApiOperation(value = "Get new post", response = PostDTO.class)
    public Mono<ResponseEntity<List<PostDTO>>> getNewPosts(
            @RequestParam(value = "page", defaultValue = "1") final int page,
            @RequestParam(value = "size", defaultValue = "10") final int size) {
        return virtualRealityNewsApiService.getNewPosts(page, size)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/top")
    @ApiOperation(value = "Get top post", response = PostDTO.class)
    public Mono<ResponseEntity<List<PostDTO>>> getTopPosts(
            @RequestParam(value = "page", defaultValue = "1") final int page,
            @RequestParam(value = "size", defaultValue = "10") final int size) {
        return virtualRealityNewsApiService.getTopPosts(page, size)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<List<PostDTO>>> getPostById(@PathVariable final String id) {
        return virtualRealityNewsApiService.getPostsById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

}
