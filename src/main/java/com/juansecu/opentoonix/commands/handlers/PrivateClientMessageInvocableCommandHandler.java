package com.juansecu.opentoonix.commands.handlers;

import org.red5.logging.Red5LoggerFactory;
import org.red5.server.api.Red5;
import org.red5.server.api.service.IServiceCapableConnection;
import org.slf4j.Logger;

import com.juansecu.opentoonix.commands.models.InvocableCommand;

public class PrivateClientMessageInvocableCommandHandler implements IInvocableCommandHandler {
    public static final String COMMAND_NAME = "privateClientMessage";

    private static final Logger CONSOLE_LOGGER = Red5LoggerFactory.getLogger(PrivateClientMessageInvocableCommandHandler.class);

    @Override
    public void handle(InvocableCommand invocableCommand) {
        PrivateClientMessageInvocableCommandHandler.CONSOLE_LOGGER.info(
            "privateClientMessage param: {}",
            invocableCommand.getParams()[1]
        );

        for(IServiceCapableConnection connection : invocableCommand.getConnectedPlayers().keySet()) {
            if(!connection.getClient().getId().equals(invocableCommand.getParams()[0])){
                continue;
            }

            connection.invoke(
                PrivateClientMessageInvocableCommandHandler.COMMAND_NAME,
                new Object[]{
                    Red5.getConnectionLocal().getClient().getId(),
                    invocableCommand.getParams()[1]
                }
            );
        }
    }
}
