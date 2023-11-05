package com.juansecu.opentoonix.commands.handlers;

import org.red5.logging.Red5LoggerFactory;
import org.slf4j.Logger;

import com.juansecu.opentoonix.commands.models.InvocableCommand;

public class InvocableCommandHandlerController implements IInvocableCommandHandler {
    private static final Logger CONSOLE_LOGGER = Red5LoggerFactory.getLogger(InvocableCommandHandlerController.class);

    @Override
    public void handle(
        final InvocableCommand invocableCommand
    ) {
        switch (invocableCommand.getCommand()) {
            case BroadcastClientMessageInvocableCommandHandler.COMMAND_NAME:
                new BroadcastClientMessageInvocableCommandHandler().handle(
                    invocableCommand
                );
                break;
            case BroadcastServerMessageInvocableCommandHandler.COMMAND_NAME:
                new BroadcastServerMessageInvocableCommandHandler().handle(
                    invocableCommand
                );
                break;
            case PrivateClientMessageInvocableCommandHandler.COMMAND_NAME:
                new PrivateClientMessageInvocableCommandHandler().handle(
                    invocableCommand
                );
                break;
            case PrivateServerMessageInvocableCommandHandler.COMMAND_NAME:
                new PrivateServerMessageInvocableCommandHandler().handle(
                    invocableCommand
                );
                break;
            default:
                InvocableCommandHandlerController.CONSOLE_LOGGER.info(
                    "InvocableCommandHandlerController: command not found: {}",
                    invocableCommand.getCommand()
                );
        }
    }
}
