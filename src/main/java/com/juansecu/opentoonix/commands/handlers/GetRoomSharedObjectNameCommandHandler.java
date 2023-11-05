package com.juansecu.opentoonix.commands.handlers;

import java.util.HashMap;

import org.red5.logging.Red5LoggerFactory;
import org.slf4j.Logger;

public class GetRoomSharedObjectNameCommandHandler implements ICommandHandler {
    public static final String COMMAND_NAME = "getRoomSOName";
    public static final String ROOM_SHARED_OBJECT_NAME = "cosmos";

    private static final Logger CONSOLE_LOGGER = Red5LoggerFactory.getLogger(GetRoomSharedObjectNameCommandHandler.class);

    @Override
    public Object handle(final Object[] params) {
        if (params.length == 0) {
            GetRoomSharedObjectNameCommandHandler.CONSOLE_LOGGER.error("No command received");
            return null;
        }

        final HashMap <String, Object> callRoom = new HashMap<>(1);
        final HashMap <String, Object> response = new HashMap<>(2);
        final HashMap <String, String> sharedObject = new HashMap<>(1);

        callRoom.put("call", params[1]);

        sharedObject.put(
            "soName",
            GetRoomSharedObjectNameCommandHandler.ROOM_SHARED_OBJECT_NAME
        );

        response.putAll(callRoom);
        response.putAll(sharedObject);

        return response;
    }
}
