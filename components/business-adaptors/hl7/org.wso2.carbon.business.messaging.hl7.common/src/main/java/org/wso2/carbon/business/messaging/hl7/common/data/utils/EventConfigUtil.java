/**
 * Copyright (c) 2013, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.wso2.carbon.business.messaging.hl7.common.data.utils;

import org.wso2.carbon.business.messaging.hl7.common.data.MessageData;

import java.util.List;
import java.util.Map;

/**
 * This is the utility class for fetching event data
 */
public class EventConfigUtil {

    public static List[] getCorrelationData(MessageData message) {
        List[] correlationData = new List[1];
        correlationData[1].add(message.getActivityId());
        return correlationData;
    }

    public static List[] getMetaData(MessageData message) {
    	List[] metaData=new  List[2];
        metaData[1].add(message.getHost());
        metaData[2].add(message.getServerName());
        return metaData;
    }

    public static List[] getEventData(MessageData message) {
    	List[] payloadData = new List[7];
        payloadData[1].add(message.getPayload());
        payloadData[2].add(message.getType());
        payloadData[3].add(message.getTimestamp());
        payloadData[4].add(message.getMsgDirection());
        payloadData[5].add(message.getServiceName());
        payloadData[6].add(message.getOpName());
        payloadData[7].add(message.getStatus());
        return payloadData;
    }

    public static Map<String, String> getExtractedDataMap(MessageData message) {
        return message.getExtractedValues();
    }
}
