package org.example.mprprojekt.models.player;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.example.mprprojekt.models.game.Game;

import java.util.List;

@Getter
@Setter
public class PlayerRequest {
    @NonNull
    private String name;

    private List<Game> games;
}
