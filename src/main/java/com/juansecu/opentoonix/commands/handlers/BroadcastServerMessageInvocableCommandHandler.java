package com.juansecu.opentoonix.commands.handlers;

import org.red5.logging.Red5LoggerFactory;
import org.red5.server.api.service.IServiceCapableConnection;
import org.slf4j.Logger;

import com.juansecu.opentoonix.commands.models.InvocableCommand;

public class BroadcastServerMessageInvocableCommandHandler implements IInvocableCommandHandler {
    public static final String COMMAND_NAME = "broadcastServerMessage";

    private static final Logger CONSOLE_LOGGER = Red5LoggerFactory.getLogger(BroadcastServerMessageInvocableCommandHandler.class);

    @Override
    public void handle(final InvocableCommand invocableCommand) {
        for(IServiceCapableConnection connection : invocableCommand.getConnectedPlayers().keySet()) {
            BroadcastServerMessageInvocableCommandHandler.CONSOLE_LOGGER.info(
                "broadcastServerMessage param: {}",
                invocableCommand.getParams()[0]
            );

            connection.invoke(
                BroadcastServerMessageInvocableCommandHandler.COMMAND_NAME,
                new Object[]{
                    invocableCommand.getClientMethod(),
                    invocableCommand.getParams()[0]
                }
            );
        }
    }
}
