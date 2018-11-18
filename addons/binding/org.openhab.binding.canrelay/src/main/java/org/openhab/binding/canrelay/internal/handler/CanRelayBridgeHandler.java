/**
 * Copyright (c) 2010-2018 by the respective copyright holders.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.canrelay.internal.handler;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.smarthome.core.library.types.OnOffType;
import org.eclipse.smarthome.core.thing.Bridge;
import org.eclipse.smarthome.core.thing.ChannelUID;
import org.eclipse.smarthome.core.thing.ThingStatus;
import org.eclipse.smarthome.core.thing.binding.BaseBridgeHandler;
import org.eclipse.smarthome.core.types.Command;
import org.eclipse.smarthome.io.transport.serial.SerialPortManager;
import org.openhab.binding.canrelay.internal.CanRelayConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The {@link CanRelayBridgeHandler} is responsible for handling commands, which are
 * sent to one of the channels.
 *
 * @author Lubos Housa - Initial contribution
 */
@NonNullByDefault
public class CanRelayBridgeHandler extends BaseBridgeHandler {

    private final Logger logger = LoggerFactory.getLogger(CanRelayBridgeHandler.class);

    private final SerialPortManager serialPortManager;

    public CanRelayBridgeHandler(Bridge thing, SerialPortManager serialPortManager) {
        super(thing);
        this.serialPortManager = serialPortManager;
    }

    @Override
    public void initialize() {
        logger.debug("Start initializing!");
        CanRelayConfiguration config = getConfigAs(CanRelayConfiguration.class);

        // set the thing status to UNKNOWN temporarily and let the background task decide for the real status.
        updateStatus(ThingStatus.UNKNOWN);

        scheduler.execute(() -> {
            boolean bridgeFound = true; // <background task with long running initialization here>
            serialPortManager.getIdentifier(config.serialPort);
            // when done do:
            if (bridgeFound) {
                updateStatus(ThingStatus.ONLINE);
            } else {
                updateStatus(ThingStatus.OFFLINE);
            }
        });
    }

    @Override
    public void handleCommand(ChannelUID channelUID, Command command) {
        // ignore any potential commands, all should be targeted via individual light switches
    }

    /**
     * Handle a command from a light child thing of this bridge. All child things would delegate their commands in here
     * for this main handler to send the respective CANBUS traffic over
     *
     * @param nodeID  id of the light thing that is to be switched on/off (represents real nodeID to be transmitted over
     *                    CANBUS)
     * @param command actual operation to perform (ON/OFF)
     */
    public void handleLightSwitchCommand(Object nodeID, OnOffType command) {
        logger.debug("Received command {} for light switch with nodeID {}", command, nodeID);
        // TODO implement CANBUS message sent here
    }
}
