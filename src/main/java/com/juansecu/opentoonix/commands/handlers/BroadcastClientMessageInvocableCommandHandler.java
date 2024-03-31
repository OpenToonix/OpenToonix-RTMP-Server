package com.juansecu.opentoonix.commands.handlers;

import org.red5.logging.Red5LoggerFactory;
import org.red5.server.api.Red5;
import org.red5.server.api.service.IServiceCapableConnection;
import org.slf4j.Logger;

import com.juansecu.opentoonix.commands.models.InvocableCommand;

public class BroadcastClientMessageInvocableCommandHandler implements IInvocableCommandHandler {
    public static final String COMMAND_NAME = "broadcastClientMessage";

    private static final Logger CONSOLE_LOGGER = Red5LoggerFactory.getLogger(BroadcastClientMessageInvocableCommandHandler.class);

    @Override
    public void handle(InvocableCommand invocableCommand) {
        for(IServiceCapableConnection connection : invocableCommand.getConnectedPlayers().keySet()) {
            if (connection == Red5.getConnectionLocal()) {
                continue;
            }

            BroadcastClientMessageInvocableCommandHandler.CONSOLE_LOGGER.info(
                "broadcastClientMessage param: {}",
                invocableCommand.getParams()[0]
            );

            connection.invoke(
                BroadcastClientMessageInvocableCommandHandler.COMMAND_NAME,
                new Object[]{
                    invocableCommand.getClientMethod(),
                    invocableCommand.getParams()[0]
                }
            );
        }
    }
}
