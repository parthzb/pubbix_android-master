package com.pubbix.data.event;

import com.pubbix.feature.common.Enums.SnackBarMessageType;

public class SnackbarEvent {
    public String message;
    public SnackBarMessageType type;

    public SnackbarEvent(String message, SnackBarMessageType type) {
        this.message = message;
        this.type = type;
    }
}
