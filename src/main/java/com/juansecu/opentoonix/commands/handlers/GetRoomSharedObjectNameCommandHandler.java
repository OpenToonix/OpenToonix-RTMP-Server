package com.juansecu.opentoonix.commands.handlers;

import java.util.HashMap;
import java.util.Map;

import org.red5.logging.Red5LoggerFactory;
import org.red5.server.api.service.IServiceCapableConnection;
import org.slf4j.Logger;

import com.juansecu.opentoonix.commands.models.responses.GetRoomSharedObjectNameCommandCallRoomResponse;
import com.juansecu.opentoonix.commands.models.responses.GetRoomSharedObjectNameCommandResponse;

public class GetRoomSharedObjectNameCommandHandler implements ICommandHandler {
    public static final String COMMAND_NAME = "getRoomSOName";
    public static final String ROOM_SHARED_OBJECT_NAME = "cosmos";

    private static final Logger CONSOLE_LOGGER = Red5LoggerFactory.getLogger(GetRoomSharedObjectNameCommandHandler.class);

    @Override
    public Object handle(
        final Object[] params,
        final Map<IServiceCapableConnection, Object[]> connectedPlayers
    ) {
        if (params.length == 0) {
            GetRoomSharedObjectNameCommandHandler.CONSOLE_LOGGER.error("No command received");
            return null;
        }

        final HashMap <String, Object> callRoom = new HashMap<>(1);
        final GetRoomSharedObjectNameCommandCallRoomResponse callRoomObject = new GetRoomSharedObjectNameCommandCallRoomResponse();
        final HashMap <String, Object> response = new HashMap<>(2);
        final GetRoomSharedObjectNameCommandResponse responseObject = new GetRoomSharedObjectNameCommandResponse();
        final HashMap <String, String> sharedObject = new HashMap<>(1);

        callRoom.put("call", params[1]);

        sharedObject.put(
            "soName",
            GetRoomSharedObjectNameCommandHandler.ROOM_SHARED_OBJECT_NAME
        );

        response.putAll(callRoom);
        response.putAll(sharedObject);

        callRoomObject.setRoom((String) params[1]);

        return response;
    }
}
