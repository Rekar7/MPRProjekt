package org.example.mprprojekt.mapping;
import org.example.mprprojekt.models.game.Game;
import org.example.mprprojekt.models.game.GameRequest;
import org.example.mprprojekt.models.game.GameResponse;
import org.example.mprprojekt.repositories.GameRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GameMapper {
    GameResponse mapToGameResponse(Game game);

    @Mapping(target = "uuid", ignore = true)
    Game mapToGame(GameRequest gameRequest);
}
