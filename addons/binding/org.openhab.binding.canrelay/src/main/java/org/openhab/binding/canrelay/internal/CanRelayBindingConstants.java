/**
 * Copyright (c) 2010-2018 by the respective copyright holders.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.canrelay.internal;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.smarthome.core.thing.ThingTypeUID;

/**
 * The {@link CanRelayBindingConstants} class defines common constants, which are
 * used across the whole binding.
 *
 * @author Lubos Housa - Initial contribution
 */
@NonNullByDefault
public class CanRelayBindingConstants {

    private static final String BINDING_ID = "canrelay";

    /**
     * nodeID of a given light on a given CanRelay, used as representation-property to identify the respective
     * thing/light in the handler
     */
    public static final String CONFIG_NODEID = "nodeID";

    public static final String CANRELAY_PORT_NAME = "org.openhab.binding.canrelay";

    // List of all Thing Type UIDs
    public static final ThingTypeUID THING_TYPE_HW_CAN_BRIDGE = new ThingTypeUID(BINDING_ID, "hwCanBridge");
    public static final ThingTypeUID THING_TYPE_LIGHT = new ThingTypeUID(BINDING_ID, "light");
    public static final Set<ThingTypeUID> SUPPORTED_THING_TYPES_UIDS = new HashSet<ThingTypeUID>(
            Arrays.asList(THING_TYPE_HW_CAN_BRIDGE, THING_TYPE_LIGHT));

    // List of all Channel IDs
    public static final String CHANNEL_LIGHT_SWITCH = "lightSwitch";
}
