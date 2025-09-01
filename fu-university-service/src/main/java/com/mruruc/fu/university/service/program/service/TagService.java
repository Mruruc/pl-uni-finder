package com.mruruc.fu.university.service.program.service;

import com.mruruc.fu.university.service.program.entity.Tag;
import com.mruruc.fu.university.service.program.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;


    @Transactional
    public Set<Tag> resolveTags(Set<Long> existingTagIds, Set<String> newTags) {
        var tags = new HashSet<Tag>();

        if (existingTagIds != null && !existingTagIds.isEmpty()) {
            List<Tag> existTags = tagRepository.findAllById(existingTagIds);
            tags.addAll(existTags);
        }

        if (newTags != null && !newTags.isEmpty()) {
            for (String tagName : newTags) {
                Tag tag = tagRepository.findByNameIgnoreCase(tagName)
                        .orElseGet(() -> Tag.builder().name(tagName).build());
                tags.add(tag);
            }
        }

        return tags;
    }
}
