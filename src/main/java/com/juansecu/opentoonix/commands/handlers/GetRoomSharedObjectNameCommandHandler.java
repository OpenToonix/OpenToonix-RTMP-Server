package com.juansecu.opentoonix.commands.handlers;

import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.red5.logging.Red5LoggerFactory;
import org.red5.server.api.Red5;
import org.red5.server.api.service.IServiceCapableConnection;
import org.slf4j.Logger;

import com.juansecu.opentoonix.commands.models.RoomObject;
import com.juansecu.opentoonix.commands.models.responses.GetRoomSharedObjectNameCommandResponse;

@RequiredArgsConstructor
public class GetRoomSharedObjectNameCommandHandler implements ICommandHandler {
    public static final String COMMAND_NAME = "getRoomSOName";
    public static final String ROOM_SHARED_OBJECT_NAME = "cosmos";

    private static final Logger CONSOLE_LOGGER = Red5LoggerFactory.getLogger(GetRoomSharedObjectNameCommandHandler.class);

    private final ObjectMapper objectMapper;

    @Override
    public Object handle(
        final Object[] params,
        final Map<IServiceCapableConnection, Object[]> connectedPlayers
    ) {
        if (!(params[1] instanceof Map)) {
            GetRoomSharedObjectNameCommandHandler.CONSOLE_LOGGER.error("Invalid command received");
            Red5.getConnectionLocal().close();
            return null;
        }

        Map<String, Object> response = null;
        RoomObject roomObject = null;
        String roomName = null;
        String sharedObjectName = Red5.getConnectionLocal().getScope().getName() + "_";

        final GetRoomSharedObjectNameCommandResponse responseObject = new GetRoomSharedObjectNameCommandResponse();

        try {
            roomObject = this.objectMapper.convertValue(params[1], new TypeReference<RoomObject>() {});
        } catch (Exception exception) {
            GetRoomSharedObjectNameCommandHandler.CONSOLE_LOGGER.error(
                "Error parsing request: {}",
                exception.getMessage()
            );

            Red5.getConnectionLocal().close();

            return null;
        }

        roomName = roomObject.getRoom();

        if (roomName == null) {
            GetRoomSharedObjectNameCommandHandler.CONSOLE_LOGGER.error("No room name provided");
            Red5.getConnectionLocal().close();
            return null;
        }

        sharedObjectName += roomName;

        responseObject.setCall(roomObject);
        responseObject.setSoName(sharedObjectName);

        try {
            response = this.objectMapper.convertValue(
                responseObject,
                new TypeReference<Map<String, Object>>() {}
            );
        } catch (Exception exception) {
            GetRoomSharedObjectNameCommandHandler.CONSOLE_LOGGER.error(
                "Error parsing response: {}",
                exception.getMessage()
            );

            Red5.getConnectionLocal().close();

            return null;
        }

        return response;
    }
}
