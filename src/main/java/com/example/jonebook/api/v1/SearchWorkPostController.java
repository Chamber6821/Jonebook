package com.example.jonebook.api.v1;

import com.example.jonebook.entities.WorkPost;
import com.example.jonebook.repositories.WorkPostRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@PreAuthorize("hasRole('USER')")
@RequestMapping("/api/v1/work-post")
public class SearchWorkPostController {

    private final WorkPostRepository workPosts;

    public SearchWorkPostController(WorkPostRepository workPosts) {
        this.workPosts = workPosts;
    }

    @GetMapping("/search")
    public List<String> search(@RequestParam String name) {
        return workPosts.findByNameStartingWithOrderByName(name).stream()
                .map(WorkPost::getName)
                .toList();
    }
}
