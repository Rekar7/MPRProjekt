package org.example.mprprojekt.models.player;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.example.mprprojekt.models.game.Game;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Player {
    @NonNull
    @Id
    @UuidGenerator
    private UUID uuid;

    @NonNull
    private String name;

    @ManyToMany
    @JoinTable(
            name = "player_game",
            joinColumns = @JoinColumn(name="player_id", referencedColumnName = "uuid"),
            inverseJoinColumns = @JoinColumn(name="game_id", referencedColumnName = "uuid")
    )
    @JsonIgnore
    private List<Game> games;

}
