package com.twindustry.gestorerrores.service;


import com.twindustry.gestorerrores.model.Area;
import com.twindustry.gestorerrores.model.Topic;
import com.twindustry.gestorerrores.repository.AreaRepository;
import com.twindustry.gestorerrores.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TopicService {
    private final TopicRepository topicRepository;
    private final AreaRepository areaRepository;

    @Transactional
    public Topic createTopic(Long areaId, String name){
        Area area = areaRepository.findById(areaId).orElseThrow(() -> new IllegalArgumentException("No existe el area"));
        Topic topic = new Topic();
        topic.setName(name);
        topic.setArea(area);
        return topicRepository.save(topic);

    }

    @Transactional (readOnly = true)
    public List<Topic> findAll(){
        return topicRepository.findAll();
    }
}
