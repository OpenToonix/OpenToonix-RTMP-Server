package com.juansecu.opentoonix.commands.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.red5.logging.Red5LoggerFactory;
import org.red5.server.api.service.IServiceCapableConnection;
import org.slf4j.Logger;

import java.util.Map;

public class CommandHandlerController implements ICommandHandler {
    private static final Logger CONSOLE_LOGGER = Red5LoggerFactory.getLogger(CommandHandlerController.class);

    @Override
    public Object handle(
        final Object[] params,
        final Map<IServiceCapableConnection, Object[]> connectedPlayers
    ) {
        if (params.length == 0) {
            CommandHandlerController.CONSOLE_LOGGER.error("No command received");
            return null;
        }

        if (params.length != 2 || !(params[0] instanceof String)) {
            CommandHandlerController.CONSOLE_LOGGER.error("Invalid command received");
            return null;
        }

        final ObjectMapper objectMapper = new ObjectMapper();

        CommandHandlerController.CONSOLE_LOGGER.info(
            "Command received: {}",
            GetRoomSharedObjectNameCommandHandler.COMMAND_NAME
        );

        switch ((String) params[0]) {
            case GetRoomSharedObjectNameCommandHandler.COMMAND_NAME:
                return new GetRoomSharedObjectNameCommandHandler(objectMapper).handle(
                    params,
                    connectedPlayers
                );
            case JoinCommandHandler.COMMAND_NAME:
                return new JoinCommandHandler().handle(
                    params,
                    connectedPlayers
                );
            case PartCommandHandler.COMMAND_NAME:
                return new PartCommandHandler().handle(
                    params,
                    connectedPlayers
                );
            default:
                CommandHandlerController.CONSOLE_LOGGER.error(
                    "Command {} not found",
                    params[0]
                );
        }

        return null;
    }
}
