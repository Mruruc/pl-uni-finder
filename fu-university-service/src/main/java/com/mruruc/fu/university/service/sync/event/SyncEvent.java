package com.mruruc.fu.university.service.sync.event;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SyncEvent <T>{
    private T event;
    private EventName eventName;
}
