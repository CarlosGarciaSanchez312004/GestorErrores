package com.twindustry.gestorerrores.controller;

import com.twindustry.gestorerrores.model.Topic;
import com.twindustry.gestorerrores.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/topics")
@RequiredArgsConstructor
public class TopicController {
    private final TopicService topicService;

    @PostMapping("/{areaId}")
    public Topic createTopic(@PathVariable Long areaId, @RequestBody String name) {
        return topicService.createTopic(areaId, name);
    }
    @GetMapping("/findAll")
    public List<Topic> findAll() {
        return topicService.findAll();
    }
}
