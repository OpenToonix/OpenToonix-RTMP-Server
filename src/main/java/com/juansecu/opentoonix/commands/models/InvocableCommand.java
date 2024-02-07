package com.juansecu.opentoonix.commands.models;

import java.util.Map;

import org.red5.server.api.service.IServiceCapableConnection;

public class InvocableCommand {
    private final String clientMethod;
    private final String command;
    private final Map<IServiceCapableConnection, Object[]> connectedPlayers;
    private final Object[] params;

    public InvocableCommand(
        final String clientMethod,
        final String command,
        final Map<IServiceCapableConnection, Object[]> connectedPlayers,
        final Object[] params
    ) {
        this.clientMethod = clientMethod;
        this.command = command;
        this.connectedPlayers = connectedPlayers;
        this.params = params;
    }

    public String getClientMethod() {
        return this.clientMethod;
    }

    public String getCommand() {
        return this.command;
    }

    public Map<IServiceCapableConnection, Object[]> getConnectedPlayers() {
        return this.connectedPlayers;
    }

    public Object[] getParams() {
        return this.params;
    }
}
