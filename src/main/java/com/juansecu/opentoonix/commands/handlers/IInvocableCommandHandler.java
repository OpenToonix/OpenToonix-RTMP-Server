package com.juansecu.opentoonix.commands.handlers;

import com.juansecu.opentoonix.commands.models.InvocableCommand;

public interface IInvocableCommandHandler {
    void handle(InvocableCommand invocableCommand);
}
