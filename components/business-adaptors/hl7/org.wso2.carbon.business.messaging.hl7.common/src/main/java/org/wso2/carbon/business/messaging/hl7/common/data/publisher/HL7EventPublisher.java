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
import org.wso2.carbon.business.messaging.hl7.common.HL7Constants;
import org.wso2.carbon.business.messaging.hl7.common.data.MessageData;
import org.wso2.carbon.business.messaging.hl7.common.data.utils.EventConfigUtil;
import org.wso2.carbon.business.messaging.hl7.common.internal.HL7MessageComponent;
import org.wso2.carbon.databridge.commons.Event;
import org.wso2.carbon.databridge.commons.utils.DataBridgeCommonsUtils;
import org.wso2.carbon.event.stream.core.EventStreamService;

import java.util.List;
import java.util.Map;

/**
 * This class represents HL7 data publisher
 */
public class HL7EventPublisher {

    private static  String streamId=DataBridgeCommonsUtils.generateStreamId(HL7Constants.HL7_PUBLISHER_STREAM_NAME,HL7Constants.HL7_PUBLISHER_STREAM_VERSION);

    public void publish(MessageData message) throws HL7Exception {

        List<Object> correlationData = EventConfigUtil.getCorrelationData(message);
        List<Object> metaData = EventConfigUtil.getMetaData(message);
        List<Object> payLoadData = EventConfigUtil.getEventData(message);
        Map<String, String> arbitraryDataMap = EventConfigUtil.getExtractedDataMap(message);

        Event event=new Event();
        event.setTimeStamp(System.currentTimeMillis());
        event.setStreamId(streamId);
        event.setCorrelationData(correlationData.toArray());
        event.setMetaData(metaData.toArray());
        event.setPayloadData(payLoadData.toArray());
        event.setArbitraryDataMap(arbitraryDataMap);

        EventStreamService eventStreamService= HL7MessageComponent.getEventStreamService();
        eventStreamService.publish(event);

    }
}




