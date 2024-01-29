package com.juansecu.opentoonix.commands.handlers;

import java.util.Map;

import org.red5.server.api.service.IServiceCapableConnection;

public interface ICommandHandler {
    Object handle(
        Object[] params,
        Map<IServiceCapableConnection, Object[]> connectedPlayers
    );
}
