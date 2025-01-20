package org.example.mprprojekt.models.game;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.example.mprprojekt.models.developer.Developer;
import org.example.mprprojekt.models.player.Player;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
public class GameRequest {
    private UUID uuid;

    @NonNull
    private String title;

    @NonNull
    private boolean isInBundle;

    private Developer developer;

    @NonNull
    private LocalDate releaseDate;

    private List<Player> players;
    private String publisher;
    private float reviews;
}
