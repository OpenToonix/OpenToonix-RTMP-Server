package com.juansecu.opentoonix.commands.handlers;

import java.util.HashMap;
import java.util.Map;

import org.red5.logging.Red5LoggerFactory;
import org.red5.server.api.Red5;
import org.slf4j.Logger;

import com.juansecu.opentoonix.commands.models.InvocableCommand;

public class JoinCommandHandler implements ICommandHandler {
    public static final String COMMAND_NAME = "join";

    private static final Logger CONSOLE_LOGGER = Red5LoggerFactory.getLogger(JoinCommandHandler.class);

    @Override
    public Object handle(final Object[] params) {
        JoinCommandHandler.CONSOLE_LOGGER.info(
            "Command {} - Params {} - Client ID: {}",
            JoinCommandHandler.COMMAND_NAME,
            params[1],
            Red5.getConnectionLocal().getClient().getId()
        );

        final HashMap <String, String> clientId = new HashMap<>(1);
        final HashMap <String, Object> response = new HashMap<>(2);

        clientId.put("clientID", Red5.getConnectionLocal().getClient().getId());

        response.putAll(clientId);
        response.putAll((Map<String, Object>) params[1]);

        new BroadcastServerMessageInvocableCommandHandler().handle(
            new InvocableCommand(
                "clientJoin",
                BroadcastServerMessageInvocableCommandHandler.COMMAND_NAME,
                null,
                new Object[]{response}
            )
        );

        return null;
    }
}
