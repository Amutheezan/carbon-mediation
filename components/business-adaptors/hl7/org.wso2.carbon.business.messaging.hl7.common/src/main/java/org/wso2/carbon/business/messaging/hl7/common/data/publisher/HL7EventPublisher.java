/**
 * Copyright (c) 2013, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.wso2.carbon.business.messaging.hl7.common.data.publisher;

import ca.uhn.hl7v2.HL7Exception;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.business.messaging.hl7.common.HL7Constants;
import org.wso2.carbon.business.messaging.hl7.common.data.EventPublishConfigHolder;
import org.wso2.carbon.business.messaging.hl7.common.data.MessageData;
import org.wso2.carbon.business.messaging.hl7.common.data.conf.EventPublisherConfig;
import org.wso2.carbon.business.messaging.hl7.common.data.conf.ServerConfig;
import org.wso2.carbon.business.messaging.hl7.common.data.utils.EventConfigUtil;
import org.wso2.carbon.business.messaging.hl7.common.data.utils.StreamDefUtil;
import org.wso2.carbon.business.messaging.hl7.common.internal.HL7MessageComponent;
import org.wso2.carbon.databridge.commons.Event;
import org.wso2.carbon.databridge.commons.StreamDefinition;
import org.wso2.carbon.databridge.commons.exception.MalformedStreamDefinitionException;
import org.wso2.carbon.databridge.commons.utils.DataBridgeCommonsUtils;
import org.wso2.carbon.event.stream.core.EventStreamService;

import java.util.List;
import java.util.Map;

/**
 * This class represents HL7 data publisher
 */
public class HL7EventPublisher {

    public static final String UNDERSCORE = "_";

    private static Log log = LogFactory.getLog(HL7EventPublisher.class);

    private ServerConfig serverConfig;
    private static  String streamId=DataBridgeCommonsUtils.generateStreamId(HL7Constants.HL7_PUBLISHER_STREAM_NAME,HL7Constants.HL7_PUBLISHER_STREAM_VERSION);

    public HL7EventPublisher(ServerConfig serverConfig) {
        this.serverConfig = serverConfig;
    }

    public void publish(MessageData message) throws HL7Exception {

        List<Object> correlationData = EventConfigUtil.getCorrelationData(message);
        List<Object> metaData = EventConfigUtil.getMetaData(message);
        List<Object> payLoadData = EventConfigUtil.getEventData(message);
        Map<String, String> arbitraryDataMap = EventConfigUtil.getExtractedDataMap(message);
        StreamDefinition streamDef = null;
        Event event=new Event();
        event.setTimeStamp(System.currentTimeMillis());
        event.setStreamId(streamId);
        event.setCorrelationData(correlationData.toArray());
        event.setMetaData(metaData.toArray());
        event.setPayloadData(payLoadData.toArray());
        event.setArbitraryDataMap(arbitraryDataMap);

        try {
            streamDef = StreamDefUtil.getStreamDefinition();
        } catch (MalformedStreamDefinitionException e) {
            log.error("Unable to create HL7 StreamDefinition : " + e.getMessage(), e);
        }
        if (streamDef != null) {
            String key = serverConfig.getUrl() + UNDERSCORE + serverConfig.getUsername() + UNDERSCORE +
                    serverConfig.getPassword();
            EventPublisherConfig eventPublisherConfig = EventPublishConfigHolder.getEventPublisherConfig(key);
            if (serverConfig.isLoadBalancingConfig()) {
               //do Nothing
            } else {
                if (log.isDebugEnabled()) {
                    log.debug("single node receiver mode working.");
                }
                if (eventPublisherConfig == null) {
                    if (log.isDebugEnabled()) {
                        log.debug("Newly creating publisher configuration.");
                    }
                    synchronized (HL7EventPublisher.class) {
                        eventPublisherConfig = new EventPublisherConfig();
                        EventStreamService eventStreamService= HL7MessageComponent.getEventStreamService();
                        eventStreamService.publish(event);
                        if (log.isDebugEnabled()) {
                            log.debug("Created stream definition.");
                        }
                        eventPublisherConfig.setEventStreamService(eventStreamService);
                        if (log.isDebugEnabled()) {
                            log.debug("Adding config info to map.");
                        }
                        EventPublishConfigHolder.getEventPublisherConfigMap().put(key, eventPublisherConfig);
                    }
                }

                if (log.isDebugEnabled()) {
                    log.debug("Successfully published data.");
                }
            }
        }
    }



}




