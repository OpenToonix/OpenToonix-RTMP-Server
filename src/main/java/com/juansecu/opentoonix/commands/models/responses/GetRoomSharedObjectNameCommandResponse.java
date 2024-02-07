package com.juansecu.opentoonix.commands.models.responses;

import lombok.Data;

@Data
public class GetRoomSharedObjectNameCommandResponse {
    private GetRoomSharedObjectNameCommandCallRoomResponse call;
    private String soName;
}
