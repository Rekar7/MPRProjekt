package org.example.mprprojekt.models.game;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.example.mprprojekt.models.developer.Developer;
import org.example.mprprojekt.models.player.Player;
import org.hibernate.annotations.DialectOverride;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Game {
    @NonNull
    @Id
    @UuidGenerator
    private UUID uuid;

    @NonNull
    @Column(unique = true)
    private String title;

    @NonNull
    private boolean isInBundle;

    @NonNull
    @ManyToOne()
    @JoinColumn(name="developer_id", referencedColumnName = "uuid")
    @JsonBackReference
    private Developer developer;

    @NonNull
    private LocalDate releaseDate;

    @ManyToMany(mappedBy = "games")
    @JsonIgnore
    private List<Player> players;

    private String publisher;
    private float reviews;

    @Version
    private int version;

}
