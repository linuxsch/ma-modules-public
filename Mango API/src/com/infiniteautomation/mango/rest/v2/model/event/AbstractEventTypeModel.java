/**
 * Copyright (C) 2018  Infinite Automation Software. All rights reserved.
 */
package com.infiniteautomation.mango.rest.v2.model.event;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.serotonin.m2m2.Common;
import com.serotonin.m2m2.rt.event.type.DuplicateHandling;
import com.serotonin.m2m2.rt.event.type.EventType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 *
 * Read only models to send information out, not designed to create new
 * event types
 *
 * @author Terry Packer
 *
 */
@ApiModel(discriminator="eventType")
@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include=JsonTypeInfo.As.EXISTING_PROPERTY, property="eventType")
public abstract class AbstractEventTypeModel<T extends EventType> {

    @ApiModelProperty("Type of event")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected String eventType;
    
    @ApiModelProperty("Sub-type of event")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected String subType;
    protected DuplicateHandling duplicateHandling;
    
    @ApiModelProperty("ID used in event type/subtype combination")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected Integer referenceId1;
    
    @ApiModelProperty("ID used in event type/subtype combination")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected Integer referenceId2;
    
    protected Boolean rateLimited;

    public AbstractEventTypeModel(T type) {
        fromVO(type);
    }

    /**
     * @return the eventType
     */
    public String getEventType() {
        return eventType;
    }


    /**
     * @return the subType
     */
    public String getSubType() {
        return subType;
    }

    /**
     * @return the duplicateHandling
     */
    public DuplicateHandling getDuplicateHandling() {
        return duplicateHandling;
    }

    /**
     * @return the referenceId1
     */
    public Integer getReferenceId1() {
        return referenceId1;
    }

    /**
     * @return the referenceId2
     */
    public Integer getReferenceId2() {
        return referenceId2;
    }

    /**
     * @return the rateLimited
     */
    public Boolean getRateLimited() {
        return rateLimited;
    }

    /**
     * EventType(s) are lacking setters 
     *  so they must be created/filled in this method
     *  implemented in the concrete model class
     * @return
     */
    public abstract T toVO();
    
    public void fromVO(T type) {
        this.eventType = type.getEventType();
        this.subType = type.getEventSubtype();
        this.duplicateHandling = type.getDuplicateHandling();
        this.referenceId1 = type.getReferenceId1() == Common.NEW_ID ? null : type.getReferenceId1();
        this.referenceId2 = type.getReferenceId2() == Common.NEW_ID ? null : type.getReferenceId2();
        this.rateLimited = type.isRateLimited();
    }

}

