/*
 * Copyright (C) 2018 Infinite Automation Software. All rights reserved.
 */
package com.infiniteautomation.mango.rest.v2.bulk;

import com.infiniteautomation.mango.rest.v2.exception.AbstractRestV2Exception;
import com.infiniteautomation.mango.rest.v2.util.RestExceptionMapper;

/**
 * @author Jared Wiltshire
 */
public class RestExceptionIndividualResponse<A, B> extends IndividualResponse<A, B, AbstractRestV2Exception> implements RestExceptionMapper {
    /**
     * Sets the http status and error fields from the exception
     * @param exception
     */
    public void exceptionCaught(Exception exception) {
        AbstractRestV2Exception e = this.mapException(exception);
        this.setHttpStatus(e.getStatus().value());
        this.setError(e);
    }
}
