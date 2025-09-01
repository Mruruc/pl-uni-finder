package com.mruruc.fu.university.service.sync.mapper;

import com.mruruc.fu.university.service.sync.event.EventName;

import java.util.Map;

public interface TypesenseDocumentMapper<T> {

    Map<String, Object> mapToDocument(T source);

    boolean supports(EventName eventName);

    String getCollectionName();

}
