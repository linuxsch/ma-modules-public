/*
 * Copyright (C) 2018 Infinite Automation Software. All rights reserved.
 */
package com.infiniteautomation.mango.rest.v2.temporaryResource;

import com.infiniteautomation.mango.rest.v2.temporaryResource.TemporaryResource.TemporaryResourceStatus;

/**
 * @author Jared Wiltshire
 */
public class TemporaryResourceStatusUpdate {
    private TemporaryResourceStatus status;

    public TemporaryResourceStatus getStatus() {
        return status;
    }

    public void setStatus(TemporaryResourceStatus status) {
        this.status = status;
    }
}
