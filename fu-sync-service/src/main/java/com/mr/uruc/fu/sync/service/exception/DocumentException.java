package com.mr.uruc.fu.sync.service.exception;

public class DocumentException extends RuntimeException {
    public DocumentException(String failedToSyncPrograms, Exception exception) {
        super(failedToSyncPrograms, exception);
    }
}
