/**
 * Copyright (c) 2010-2018 by the respective copyright holders.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.canrelay.internal.handler;

import static org.openhab.binding.canrelay.internal.CanRelayBindingConstants.*;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.smarthome.core.library.types.OnOffType;
import org.eclipse.smarthome.core.thing.ChannelUID;
import org.eclipse.smarthome.core.thing.Thing;
import org.eclipse.smarthome.core.thing.binding.BaseThingHandler;
import org.eclipse.smarthome.core.types.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The {@link CanRelayLightSwitchHandler} is responsible for handling commands, which are
 * sent to one of the channels.
 *
 * @author Lubos Housa - Initial contribution
 */
@NonNullByDefault
public class CanRelayLightSwitchHandler extends BaseThingHandler {

    private final Logger logger = LoggerFactory.getLogger(CanRelayLightSwitchHandler.class);

    public CanRelayLightSwitchHandler(Thing thing) {
        super(thing);
    }

    // getBridge().getHandler(), but shall be safe according to API javadoc for this subordinate thing
    @SuppressWarnings("null")
    @Override
    public void handleCommand(ChannelUID channelUID, Command command) {
        Object nodeID = getThing().getConfiguration().get(CONFIG_NODEID);
        logger.debug("Received command {} on channel {} for light switch {}", command, channelUID, nodeID);
        if (CHANNEL_LIGHT_SWITCH.equals(channelUID.getId())) {
            if (command instanceof OnOffType) {
                if (getBridge() != null) {
                    ((CanRelayBridgeHandler) getBridge().getHandler()).handleLightSwitchCommand(nodeID,
                            (OnOffType) command);
                } else {
                    logger.warn(
                            "Probably misconfigured CanRelay LightSwitch (node ID {}) without a bridge! Ignoring command {}",
                            nodeID, command);
                }
            }
        }
    }
}
