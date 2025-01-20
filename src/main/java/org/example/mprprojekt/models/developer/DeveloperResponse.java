package org.example.mprprojekt.models.developer;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.example.mprprojekt.models.game.Game;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class DeveloperResponse {

    @NonNull
    private String name;

    private String email;

    @NonNull
    private float  reviews;

    private List<Game> games;
}
