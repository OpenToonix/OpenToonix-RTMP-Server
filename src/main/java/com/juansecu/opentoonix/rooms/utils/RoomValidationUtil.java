package com.juansecu.opentoonix.rooms.utils;

import com.juansecu.opentoonix.rooms.enums.ERoom;

public class RoomValidationUtil {
    private static final String ROOM_REGEX = "^[a-z0-9_]+$";
    private static final String USER_HOME_ROOM_REGEX = "^(uid([0-9]+)_home_default)$";

    private RoomValidationUtil() {}

    public static boolean isValidRoom(final String room) {
        final boolean doesRoomNameMatch = room != null && room.matches(RoomValidationUtil.ROOM_REGEX);
        return doesRoomNameMatch && RoomValidationUtil.isRoomAllowed(room);
    }

    private static boolean isRoomAllowed(final String room) {
        final ERoom[] allowedRooms = ERoom.values();
        final boolean isUserHomeRoom = room.matches(RoomValidationUtil.USER_HOME_ROOM_REGEX);

        if (isUserHomeRoom) return true;

        for (final ERoom allowedRoom : allowedRooms) {
            if (allowedRoom.getRoomName().equals(room)) {
                return true;
            }
        }

        return false;
    }
}
