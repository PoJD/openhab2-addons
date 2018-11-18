/**
 * Copyright (c) 2010-2018 by the respective copyright holders.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.canrelay.internal;

import static org.openhab.binding.canrelay.internal.CanRelayBindingConstants.*;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.smarthome.core.thing.Bridge;
import org.eclipse.smarthome.core.thing.Thing;
import org.eclipse.smarthome.core.thing.ThingTypeUID;
import org.eclipse.smarthome.core.thing.binding.BaseThingHandlerFactory;
import org.eclipse.smarthome.core.thing.binding.ThingHandler;
import org.eclipse.smarthome.core.thing.binding.ThingHandlerFactory;
import org.eclipse.smarthome.io.transport.serial.SerialPortManager;
import org.openhab.binding.canrelay.internal.handler.CanRelayBridgeHandler;
import org.openhab.binding.canrelay.internal.handler.CanRelayLightSwitchHandler;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * The {@link CanRelayHandlerFactory} is responsible for creating things and thing
 * handlers.
 *
 * @author Lubos Housa - Initial contribution
 */
@NonNullByDefault
@Component(configurationPid = "binding.canrelay", service = ThingHandlerFactory.class)
public class CanRelayHandlerFactory extends BaseThingHandlerFactory {

    @NonNullByDefault({})
    private SerialPortManager serialPortManager;

    @Reference
    protected void setSerialPortManager(final SerialPortManager serialPortManager) {
        this.serialPortManager = serialPortManager;
    }

    protected void unsetSerialPortManager(final SerialPortManager serialPortManager) {
        this.serialPortManager = null;
    }

    @Override
    public boolean supportsThingType(ThingTypeUID thingTypeUID) {
        return SUPPORTED_THING_TYPES_UIDS.contains(thingTypeUID);
    }

    @Override
    protected @Nullable ThingHandler createHandler(Thing thing) {
        ThingTypeUID thingTypeUID = thing.getThingTypeUID();

        if (THING_TYPE_HW_CAN_BRIDGE.equals(thingTypeUID)) {
            return new CanRelayBridgeHandler((Bridge) thing, serialPortManager);
        }

        if (THING_TYPE_LIGHT.equals(thingTypeUID)) {
            return new CanRelayLightSwitchHandler(thing);
        }
        return null;
    }
}
