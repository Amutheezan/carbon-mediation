package org.wso2.carbon.business.messaging.hl7.common.internal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osgi.service.component.ComponentContext;
import org.wso2.carbon.event.stream.core.EventStreamService;

/**
 * @scr.component name="com.wso2.carbon.business.messaging.hl7.common" immediate="true"
 * @scr.reference name="org.wso2.carbon.event.stream.core.EventStreamService"
 * interface="org.wso2.carbon.event.stream.core.EventStreamService"
 * cardinality="1..1" policy="dynamic" bind="setEventStreamService" unbind="unsetEventStreamService"
 */
public class HL7MessageComponent {

    private static final Log log = LogFactory.getLog(HL7MessageComponent.class);
    private static EventStreamService eventStreamService;

    protected void activate(ComponentContext ctxt) {
        if (log.isDebugEnabled()) {
            log.debug("Activating the tasks");
        }
        try {
        } catch (Exception e) {
            log.error("HL7MessageComponent activation error.", e);
        }
    }

    protected void deactivate(ComponentContext ctxt) {

        if (log.isDebugEnabled()) {
            log.debug("HL7 Services task bundle is deactivated ");
        }
    }

    protected void setEventStreamService(EventStreamService eventStreamService) {
        HL7MessageComponent.eventStreamService = eventStreamService;
    }

    protected void unsetEventStreamService(EventStreamService eventStreamService) {
        HL7MessageComponent.eventStreamService = null;
    }

    public static EventStreamService getEventStreamService() {
        return eventStreamService;
    }
}
