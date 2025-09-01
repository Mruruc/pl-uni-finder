package com.mruruc.fu.university.service.sync.mapper;

import com.mruruc.fu.university.service.sync.event.EventName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DocumentMapperFactory {
    private final List<TypesenseDocumentMapper<?>> mappers;


    public Optional<TypesenseDocumentMapper<?>> getMapper(EventName eventName) {
        return mappers.stream()
                .filter(mapper -> mapper.supports(eventName))
                .findFirst();
    }


}
