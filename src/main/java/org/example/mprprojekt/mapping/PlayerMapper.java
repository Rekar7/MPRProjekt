package org.example.mprprojekt.mapping;

import org.example.mprprojekt.models.player.Player;
import org.example.mprprojekt.models.player.PlayerRequest;
import org.example.mprprojekt.models.player.PlayerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PlayerMapper {
    PlayerResponse mapToPlayerResponse(Player player);
    @Mapping(target = "uuid", ignore = true)
    Player mapToPlayer(PlayerRequest playerRequest);
}
