package com.juansecu.opentoonix;

import org.red5.logging.Red5LoggerFactory;
import org.red5.server.adapter.MultiThreadedApplicationAdapter;
import org.red5.server.api.IClient;
import org.red5.server.api.IConnection;
import org.red5.server.api.scope.IScope;
import org.slf4j.Logger;

public class OpenToonixRtmpServerApplication extends MultiThreadedApplicationAdapter {
    private static final Logger CONSOLE_LOGGER = Red5LoggerFactory.getLogger(OpenToonixRtmpServerApplication.class);

    @Override
    public boolean connect(
        final IConnection connection,
        final IScope scope,
        final Object[] params
    ) {
        OpenToonixRtmpServerApplication.CONSOLE_LOGGER.info(
            String.format("Connection received: %s", scope)
        );

        return super.connect(connection, scope, params);
    }

    @Override
    public void disconnect(final IConnection connection, final IScope scope) {
        OpenToonixRtmpServerApplication.CONSOLE_LOGGER.info(
            "Disconnection received"
        );

        super.disconnect(connection, scope);
    }

    @Override
    public boolean roomConnect(
        final IConnection connection,
        final Object[] params
    ) {
        OpenToonixRtmpServerApplication.CONSOLE_LOGGER.info(
            String.format(
                "Room connect: Connection: %s - Params: %s",
                connection,
                params
            )
        );

        return super.roomConnect(connection, params);
    }

    @Override
    public void roomDisconnect(final IConnection connection) {
        OpenToonixRtmpServerApplication.CONSOLE_LOGGER.info(
            String.format("Room disconnect: Connection: %s", connection)
        );

        super.roomDisconnect(connection);
    }

    @Override
    public boolean roomJoin(final IClient client, final IScope room) {
        OpenToonixRtmpServerApplication.CONSOLE_LOGGER.info(
            String.format(
                "Room join: Client: %s - Room: %s",
                client,
                room
            )
        );

        return super.roomJoin(client, room);
    }

    @Override
    public void roomLeave(final IClient client, final IScope room) {
        OpenToonixRtmpServerApplication.CONSOLE_LOGGER.info(
            String.format(
                "Room leave: Client: %s - Room: %s",
                client,
                room
            )
        );

        super.roomLeave(client, room);
    }

    @Override
    public boolean roomStart(final IScope room) {
        if(!super.roomStart(room)) super.roomStart(room);

        OpenToonixRtmpServerApplication.CONSOLE_LOGGER.info(
            String.format("Room start: Room: %s", room)
        );

        super.createSharedObject(room, room.getName(), false);

        return true;
    }

    @Override
    public void roomStop(final IScope room) {
        OpenToonixRtmpServerApplication.CONSOLE_LOGGER.info(
            String.format("Room stop: Room: %s", room)
        );

        super.roomStop(room);
    }
}
