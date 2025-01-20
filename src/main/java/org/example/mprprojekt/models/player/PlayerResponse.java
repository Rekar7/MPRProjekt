package org.example.mprprojekt.models.player;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.example.mprprojekt.models.game.Game;

import java.util.List;

@Getter
@Setter
public class PlayerResponse {
    @NonNull
    private String name;

    private List<Game> games;
}
