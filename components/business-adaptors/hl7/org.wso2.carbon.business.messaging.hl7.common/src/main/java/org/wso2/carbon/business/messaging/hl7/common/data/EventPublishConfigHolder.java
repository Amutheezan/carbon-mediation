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
package org.wso2.carbon.business.messaging.hl7.common.data;

import org.wso2.carbon.business.messaging.hl7.common.data.conf.EventPublisherConfig;
import org.wso2.carbon.event.stream.core.EventStreamService;
import org.wso2.carbon.registry.core.service.RegistryService;
import org.wso2.carbon.utils.ConfigurationContextService;
import java.util.HashMap;
import java.util.Map;

public class EventPublishConfigHolder {


    private EventStreamService publisherService;
    private RegistryService registryService;
    private ConfigurationContextService contextService;
    private boolean isGlobalStatisticsEnabled;

    private static EventPublishConfigHolder serviceHolder = new EventPublishConfigHolder();

    private EventPublishConfigHolder() {

    }

    public static EventPublishConfigHolder getInstance() {
        return serviceHolder;
    }

    public EventStreamService getPublisherService() {
        return publisherService;
    }

    public void setPublisherService(EventStreamService publisherService) {
        this.publisherService = publisherService;
    }

    public RegistryService getRegistryService() {
        return registryService;
    }

    public void setRegistryService(RegistryService registryService) {
        this.registryService = registryService;
    }

    public ConfigurationContextService getContextService() {
        return contextService;
    }

    public void setContextService(ConfigurationContextService contextService) {
        this.contextService = contextService;
    }

    public boolean isGlobalStatisticsEnabled() {
        return isGlobalStatisticsEnabled;
    }

    public void setGlobalStatisticsEnabled(boolean globalStatisticsEnabled) {
        isGlobalStatisticsEnabled = globalStatisticsEnabled;
    }

    private static Map<String, EventPublisherConfig> eventPublisherConfigMap
            = new HashMap<String, EventPublisherConfig>();

    public static EventPublisherConfig getEventPublisherConfig(String key) {
        return eventPublisherConfigMap.get(key);
    }

    public static Map<String, EventPublisherConfig> getEventPublisherConfigMap() {
        return eventPublisherConfigMap;
    }

}
