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
import org.wso2.carbon.business.messaging.hl7.common.data.MessageData;
import org.wso2.carbon.business.messaging.hl7.common.data.conf.EventPublisherConfig;
import org.wso2.carbon.business.messaging.hl7.common.data.conf.ServerConfig;
import org.wso2.carbon.business.messaging.hl7.common.data.utils.EventConfigUtil;
import org.wso2.carbon.databridge.commons.StreamDefinition;
import org.wso2.carbon.databridge.commons.utils.DataBridgeCommonsUtils;

import java.util.List;
import java.util.Map;

//import static javafx.scene.input.KeyCode.UNDERSCORE;

/**
 * This class represents HL7 data publisher
 */

public class HL7EventPublisher {

	private static final String UNDERSCORE= "_";
	private static Log log = LogFactory.getLog(HL7EventPublisher.class);
	private static String streamId = DataBridgeCommonsUtils.generateStreamId(HL7Constants.HL7_PUBLISHER_STREAM_NAME, HL7Constants.HL7_PUBLISHER_STREAM_VERSION);

	private ServerConfig serverConfig;

	public HL7EventPublisher(ServerConfig serverConfig) {
		this.serverConfig = serverConfig;
	}

	public void publish(MessageData message)throws HL7Exception{
		List[] metaData= EventConfigUtil.getMetaData(message);
		List[] correlationData=EventConfigUtil.getCorrelationData(message);
		List[] payLoadData=EventConfigUtil.getPayloadData(message);
		Map<String, String> arbitraryDataMap = EventConfigUtil.getExtractedDataMap(message);
		StreamDefinition streamDef = null;


	}
	private void loadBalancerPublisher(EventPublisherConfig eventPublisherConfig, StreamDefinition streamDef,
									   String key, List[] correlationData, List[] metaData,
									   List[] payLoadData, Map<String, String> arbitraryDataMap)
			throws HL7Exception {

	}

	private Object[] getObjectArray(List[] list) {
		if (list.length > 0) {
			return list;
		}
		return null;
	}
}
