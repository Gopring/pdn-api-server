package com.pdn.pdn_api_server.enums;

public enum QuestionStatus{
    CREATED,
    PROGRESS,
    FINISHED;
    public boolean canTransitionTo(QuestionStatus next) {
        return switch (this) {
            case CREATED -> next == PROGRESS;
            case PROGRESS -> next == FINISHED;
            case FINISHED -> false;
        };
    }
}

