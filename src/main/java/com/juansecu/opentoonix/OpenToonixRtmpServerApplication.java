package com.juansecu.opentoonix;

import java.util.HashMap;
import java.util.Map;

import org.red5.logging.Red5LoggerFactory;
import org.red5.server.adapter.MultiThreadedApplicationAdapter;
import org.red5.server.api.IClient;
import org.red5.server.api.IConnection;
import org.red5.server.api.scope.IScope;
import org.red5.server.api.service.IServiceCapableConnection;
import org.slf4j.Logger;

import com.juansecu.opentoonix.commands.handlers.*;
import com.juansecu.opentoonix.commands.models.InvocableCommand;

public class OpenToonixRtmpServerApplication extends MultiThreadedApplicationAdapter {
    private static final Logger CONSOLE_LOGGER = Red5LoggerFactory.getLogger(OpenToonixRtmpServerApplication.class);
    private static final Map<IServiceCapableConnection, Object[]> CONNECTED_PLAYERS = new HashMap<>();

    private final CommandHandlerController commandHandlerController = new CommandHandlerController();
    private final InvocableCommandHandlerController invocableCommandHandlerController = new InvocableCommandHandlerController();

    public void broadcastMessage(final Object[] params) {
        Map<String, Object> message = (Map<String, Object>) params[0];
        String messageType = (String) message.get("type");

        OpenToonixRtmpServerApplication.CONSOLE_LOGGER.info(
            "Broadcast Message received: Message Type: {} - Message: {}",
            messageType,
            params[0]
        );

        this.invocableCommandHandlerController.handle(
            new InvocableCommand(
                messageType,
                BroadcastClientMessageInvocableCommandHandler.COMMAND_NAME,
                OpenToonixRtmpServerApplication.CONNECTED_PLAYERS,
                params
            )
        );
    }

    public Object command(final Object[] params) {
        OpenToonixRtmpServerApplication.CONSOLE_LOGGER.info(
            "Command received: {}",
            params
        );

        return this.commandHandlerController.handle(
            params,
            OpenToonixRtmpServerApplication.CONNECTED_PLAYERS
        );
    }

    @Override
    public boolean connect(
        final IConnection connection,
        final IScope scope,
        final Object[] params
    ) {
        OpenToonixRtmpServerApplication.CONSOLE_LOGGER.info(
            "Connection received: {}",
            scope
        );

        final HashMap <String, String> clientId = new HashMap<>(1);
        final IServiceCapableConnection conn = (IServiceCapableConnection) connection;

        OpenToonixRtmpServerApplication.CONNECTED_PLAYERS.put(
            conn,
            new Object[]{}
        );

        clientId.put("clientID", connection.getClient().getId());
        conn.invoke("privateServerMessage", new Object[]{"setClientID", clientId});

        return super.connect(connection, scope, params);
    }

    @Override
    public void disconnect(final IConnection connection, final IScope scope) {
        OpenToonixRtmpServerApplication.CONSOLE_LOGGER.info(
            "Disconnection received"
        );

        final HashMap <String, String> clientId = new HashMap<>(1);
        final IServiceCapableConnection conn = (IServiceCapableConnection) connection;

        OpenToonixRtmpServerApplication.CONNECTED_PLAYERS.remove(conn);

        clientId.put("clientID", connection.getClient().getId());

        this.invocableCommandHandlerController.handle(
            new InvocableCommand(
                "clientPart",
                BroadcastServerMessageInvocableCommandHandler.COMMAND_NAME,
                OpenToonixRtmpServerApplication.CONNECTED_PLAYERS,
                new Object[]{clientId}
            )
        );

        super.disconnect(connection, scope);
    }

    public void privateMessage(final Object[] message) {
        OpenToonixRtmpServerApplication.CONSOLE_LOGGER.info(
            "Private Message received: Param 1 {}",
            message
        );

        this.invocableCommandHandlerController.handle(
            new InvocableCommand(
                (String) message[0],
                PrivateClientMessageInvocableCommandHandler.COMMAND_NAME,
                OpenToonixRtmpServerApplication.CONNECTED_PLAYERS,
                message
            )
        );
    }

    @Override
    public boolean roomConnect(
        final IConnection connection,
        final Object[] params
    ) {
        OpenToonixRtmpServerApplication.CONSOLE_LOGGER.info(
            "Room connect: Connection: {} - Params: {}",
            connection,
            params
        );

        return super.roomConnect(connection, params);
    }

    @Override
    public void roomDisconnect(final IConnection connection) {
        OpenToonixRtmpServerApplication.CONSOLE_LOGGER.info(
            "Room disconnect: Connection: {}",
            connection
        );

        super.roomDisconnect(connection);
    }

    @Override
    public boolean roomJoin(final IClient client, final IScope room) {
        OpenToonixRtmpServerApplication.CONSOLE_LOGGER.info(
            "Room join: Client: {} - Room: {}",
            client,
            room
        );

        return super.roomJoin(client, room);
    }

    @Override
    public void roomLeave(final IClient client, final IScope room) {
        OpenToonixRtmpServerApplication.CONSOLE_LOGGER.info(
            "Room leave: Client: {} - Room: {}",
            client,
            room
        );

        super.roomLeave(client, room);
    }

    @Override
    public boolean roomStart(final IScope room) {
        if(!super.roomStart(room)) super.roomStart(room);

        OpenToonixRtmpServerApplication.CONSOLE_LOGGER.info(
            "Room start: Room: {}",
            room
        );

        if (
            !super.createSharedObject(
                room,
                GetRoomSharedObjectNameCommandHandler.ROOM_SHARED_OBJECT_NAME,
                false
            )
        ) {
            OpenToonixRtmpServerApplication.CONSOLE_LOGGER.error(
                "Room start: Error creating shared object: Room: {}",
                room
            );

            return false;
        }

        OpenToonixRtmpServerApplication.CONSOLE_LOGGER.info(
            "Room start: Shared object created successfully: {}",
            room
        );

        return true;
    }

    @Override
    public void roomStop(final IScope room) {
        OpenToonixRtmpServerApplication.CONSOLE_LOGGER.info(
            "Room stop: Room: {}",
            room
        );

        super.roomStop(room);
    }
}
