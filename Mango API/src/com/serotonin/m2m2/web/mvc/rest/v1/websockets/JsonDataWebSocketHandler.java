/**
 * Copyright (C) 2016 Infinite Automation Software. All rights reserved.
 * @author Terry Packer
 */
package com.serotonin.m2m2.web.mvc.rest.v1.websockets;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.infiniteautomation.mango.spring.events.DaoEvent;
import com.serotonin.m2m2.vo.User;
import com.serotonin.m2m2.vo.json.JsonDataVO;
import com.serotonin.m2m2.vo.permission.Permissions;
import com.serotonin.m2m2.web.mvc.rest.v1.WebSocketMapping;
import com.serotonin.m2m2.web.mvc.rest.v1.model.jsondata.JsonDataModel;

/**
 * @author Terry Packer
 *
 */
@Component
@WebSocketMapping("/websocket/json-data")
public class JsonDataWebSocketHandler extends DaoNotificationWebSocketHandler<JsonDataVO> {

    @Override
    protected boolean hasPermission(User user, JsonDataVO vo) {
        return Permissions.hasPermission(user, vo.getReadPermission());
    }

    @Override
    protected Object createModel(JsonDataVO vo) {
        return new JsonDataModel(vo);
    }

    @Override
    @EventListener
    protected void handleDaoEvent(DaoEvent<? extends JsonDataVO> event) {
        this.notify(event);
    }

}
