/**
 * Copyright (C) 2018  Infinite Automation Software. All rights reserved.
 */
package com.infiniteautomation.mango.rest.v2;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.infiniteautomation.mango.rest.v2.exception.AbstractRestV2Exception;
import com.infiniteautomation.mango.rest.v2.exception.ServerErrorException;
import com.infiniteautomation.mango.rest.v2.model.system.actions.SystemActionTemporaryResourceManager;
import com.infiniteautomation.mango.rest.v2.temporaryResource.TemporaryResource;
import com.serotonin.ShouldNeverHappenException;
import com.serotonin.m2m2.log4jreset.Log4JResetActionPermissionDefinition;
import com.serotonin.m2m2.vo.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author Terry Packer
 *
 */
@Api(value="Log4J Reset System Action")
@RestController()
public class Log4JResetSystemActionController {

    private static final String RESOURCE_TYPE = "log4JUtil";
    private static final Log LOG = LogFactory.getLog(Log4JResetSystemActionController.class);
    private final SystemActionTemporaryResourceManager manager;
    
    @Autowired
    public Log4JResetSystemActionController(SystemActionTemporaryResourceManager manager) {
        this.manager = manager;
    }

    @ApiOperation(value = "Log4J Utility", notes = "is admin or has log4J Reset Permission, reset and test logging")
    @RequestMapping(method = RequestMethod.POST, value="/system-actions/log4JUtil")
    public ResponseEntity<TemporaryResource<Log4JUtilResult, AbstractRestV2Exception>> reset(
            @RequestBody Log4JUtilModel requestBody,
            @AuthenticationPrincipal User user,
            UriComponentsBuilder builder) {

        return manager.create(requestBody, user, builder, Log4JResetActionPermissionDefinition.PERMISSION, RESOURCE_TYPE, (resource, taskUser) -> {
            try {
                String output = null;
                switch(requestBody.getAction()){
                    case RESET:
                        LOG.info("Reloading Log4J configuration");
                        ((LoggerContext)LogManager.getContext(false)).reconfigure();
                        output = "Finished reloading Log4J configuration";
                        LOG.info(output);
                        break;
                    case TEST_DEBUG:
                        output = "Log4JReset module test debug message";
                        LOG.debug(output);
                        break;
                    case TEST_INFO:
                        output = "Log4JReset module test info message";
                        LOG.info(output);
                        break;
                    case TEST_WARN:
                        output = "Log4JReset module test warn message";
                        LOG.warn(output);
                        break;  
                    case TEST_ERROR:
                        output = "Log4JReset module test error message";
                        LOG.error(output);
                        break;
                    case TEST_FATAL:
                        output = "Log4JReset module test fatal message";
                        LOG.fatal(output);
                        break;
                    default:
                        throw new ShouldNeverHappenException("Uknonwn command " + requestBody.getAction());        
                }
                Log4JUtilResult result = new Log4JUtilResult();
                result.setLogOutput(output);
                resource.success(result);                
            }catch(Exception e) {
                resource.error(new ServerErrorException(e));
            }
            return null; //No ability to cancel this task
        });
    }  
}
