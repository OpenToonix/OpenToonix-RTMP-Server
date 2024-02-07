package com.juansecu.opentoonix.commands.handlers;

import org.red5.logging.Red5LoggerFactory;
import org.red5.server.api.Red5;
import org.red5.server.api.service.IServiceCapableConnection;
import org.slf4j.Logger;

import com.juansecu.opentoonix.commands.models.InvocableCommand;

public class PrivateServerMessageInvocableCommandHandler implements IInvocableCommandHandler {
    public static final String COMMAND_NAME = "privateServerMessage";

    private static final Logger CONSOLE_LOGGER = Red5LoggerFactory.getLogger(PrivateServerMessageInvocableCommandHandler.class);

    @Override
    public void handle(final InvocableCommand invocableCommand) {
        PrivateServerMessageInvocableCommandHandler.CONSOLE_LOGGER.info(
            "privateServerMessage param: {}",
            invocableCommand.getParams()[0]
        );

        final IServiceCapableConnection conn = (IServiceCapableConnection) Red5.getConnectionLocal();

        conn.invoke(
            PrivateServerMessageInvocableCommandHandler.COMMAND_NAME,
            new Object[]{
                invocableCommand.getCommand(),
                invocableCommand.getParams()[0]
            }
        );
    }
}
