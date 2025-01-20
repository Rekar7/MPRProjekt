package org.example.mprprojekt.models.developer;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class Developer {
    @NonNull
    @Id
    @UuidGenerator
    private UUID uuid;

    @NonNull
    @Column(unique = true)
    private String name;

    private String email;

    @NonNull
    private float reviews;

    @OneToMany(mappedBy = "developer", cascade = CascadeType.MERGE)
    @JsonManagedReference
    private List<Game> games;
}
