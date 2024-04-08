package com.juansecu.opentoonix.commands.models.responses;

import lombok.Data;

import com.juansecu.opentoonix.commands.models.RoomObject;

@Data
public class GetRoomSharedObjectNameCommandResponse {
    private RoomObject call;
    private String soName;
}
