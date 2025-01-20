package org.example.mprprojekt.models.developer;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.example.mprprojekt.models.game.Game;

import java.util.List;

@Getter
@Setter
public class DeveloperRequest {
    @NonNull
    private String name;

    private String email;

    @NonNull
    private float reviews;

    private List<Game> games;
}
