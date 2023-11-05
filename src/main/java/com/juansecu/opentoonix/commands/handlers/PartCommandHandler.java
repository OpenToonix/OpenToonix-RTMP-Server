package com.juansecu.opentoonix.commands.handlers;

import java.util.HashMap;

import org.red5.logging.Red5LoggerFactory;
import org.red5.server.api.Red5;
import org.slf4j.Logger;

import com.juansecu.opentoonix.commands.models.InvocableCommand;


/**
 * This class is a handler for the command "part", which is sent by the client
 * when a player leaves the room or disconnect from the server.
 */
public class PartCommandHandler implements ICommandHandler {
    public static final String COMMAND_NAME = "part";

    private static final Logger CONSOLE_LOGGER = Red5LoggerFactory.getLogger(PartCommandHandler.class);

    @Override
    public Object handle(final Object[] params) {
        PartCommandHandler.CONSOLE_LOGGER.info(
            "Command {} - Params {}",
            PartCommandHandler.COMMAND_NAME,
            params[1]
        );

        final HashMap<String, String> clientId = new HashMap<>(1);

        clientId.put("clientID", Red5.getConnectionLocal().getClient().getId());

        new BroadcastServerMessageInvocableCommandHandler().handle(
            new InvocableCommand(
                "clientPart",
                BroadcastServerMessageInvocableCommandHandler.COMMAND_NAME,
                null,
                new Object[]{clientId}
            )
        );

        return null;
    }
}
