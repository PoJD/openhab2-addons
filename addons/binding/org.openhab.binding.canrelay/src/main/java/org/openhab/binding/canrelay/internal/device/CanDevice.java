package org.openhab.binding.canrelay.internal.device;

/**
 * CanDevice API. Used to communicate to the device connected to this machine to acquire access to the CANBUS
 *
 * @author Lubos Housa
 */
public interface CanDevice {

    /**
     * Connect to the device using the in-passed device path (port, e.g. /dev/ttyACM0)
     * 
     * @param device device to connect to
     * @return true if connect was successful, false otherwise
     */
    boolean connect(String device);
}
