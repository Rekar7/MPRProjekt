package org.example.mprprojekt.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.mprprojekt.mapping.PlayerMapper;
import org.example.mprprojekt.models.developer.Developer;
import org.example.mprprojekt.models.developer.DeveloperRequest;
import org.example.mprprojekt.models.developer.DeveloperResponse;
import org.example.mprprojekt.models.game.Game;
import org.example.mprprojekt.models.game.GameRequest;
import org.example.mprprojekt.models.game.GameResponse;
import org.example.mprprojekt.models.player.Player;
import org.example.mprprojekt.models.player.PlayerRequest;
import org.example.mprprojekt.models.player.PlayerResponse;
import org.example.mprprojekt.repositories.DeveloperRepository;
import org.example.mprprojekt.repositories.GameRepository;
import org.example.mprprojekt.mapping.DeveloperMapper;
import org.example.mprprojekt.mapping.GameMapper;
import org.example.mprprojekt.repositories.PlayerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class GameService {
    private final GameRepository gameRepository;
    private final DeveloperRepository developerRepository;
    private final PlayerRepository playerRepository;
    private final DeveloperMapper developerMapper;
    private final GameMapper gameMapper;
    private final PlayerMapper playerMapper;

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public List<DeveloperResponse> getAllDevelopers() {
        List<Developer> developers = developerRepository.findAll();

        List<DeveloperResponse> developerResponses = new ArrayList<>();

        for (Developer developer : developers) {
            developerResponses.add(developerMapper.mapToDeveloperResponse(developer));
        }

        return developerResponses;
    }

    public List<PlayerResponse> getAllPlayers() {
        List<Player> players = playerRepository.findAll();
        List<PlayerResponse> playerResponses = new ArrayList<>();
        for (Player player : players) {
            playerResponses.add(playerMapper.mapToPlayerResponse(player));
        }
        return playerResponses;
    }

    public GameResponse getDefaultGame() {
        GameResponse gameResponse = new GameResponse();
        gameResponse.setReleaseDate(LocalDate.now());
        List<Player> players = new ArrayList<>();
        gameResponse.setPlayers(players);
        gameResponse.setReviews(3.5f);
        gameResponse.setTitle("Giera");
        Developer developer = new Developer();
        developer.setUuid(UUID.randomUUID());
        developer.setReviews(2.0f);
        developer.setName("ucin");
        developer.setEmail("ucin@gmail.com");
        gameResponse.setDeveloper(developer);
        gameResponse.setInBundle(false);
        gameResponse.setPublisher("EA");
        return gameResponse;
    }

    public GameResponse addGame(GameRequest gameRequest) {
        // Walidacja wejściowych danych
        if (gameRequest == null || gameRequest.getTitle() == null || gameRequest.getDeveloper() == null) {
            throw new IllegalArgumentException("Game, title, or developer cannot be null.");
        }
        Game game = gameMapper.mapToGame(gameRequest);

        Developer developer = game.getDeveloper();
        Developer existingDeveloper;

        if (developer.getUuid() != null) {
            existingDeveloper = developerRepository.findById(developer.getUuid())
                    .orElseThrow(() -> new EntityNotFoundException("Developer not found for UUID: " + developer.getUuid()));
        } else {
            existingDeveloper = developerRepository.findByName(developer.getName());
            if (existingDeveloper == null) {
                existingDeveloper = developerRepository.save(developer);
            }
        }

        game.setDeveloper(existingDeveloper);

        Game savedGame = gameRepository.save(game);

        return gameMapper.mapToGameResponse(savedGame);
    }

    public DeveloperResponse findDeveloperByGameTitle(String gameTitle) {
        Game game = gameRepository.findByTitle(gameTitle)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found with title: " + gameTitle));
        Developer developer = game.getDeveloper();

        return developerMapper.mapToDeveloperResponse(developer);
    }

    public DeveloperResponse updateDeveloper(String name, DeveloperRequest developerRequest) {
        Developer actualDeveloper = developerRepository.findByName(name);
        actualDeveloper.setName(developerRequest.getName());
        actualDeveloper.setEmail(developerRequest.getEmail());
        actualDeveloper.setReviews(developerRequest.getReviews());
        developerRepository.save(actualDeveloper);
        return developerMapper.mapToDeveloperResponse(actualDeveloper);
    }

    public GameResponse updateGame(UUID uuid, GameRequest gameRequest) {
        Game actualGame = gameRepository.findById(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found with ID: " + uuid));

        actualGame.setReviews(gameRequest.getReviews());
        actualGame.setTitle(gameRequest.getTitle());
        actualGame.setReleaseDate(gameRequest.getReleaseDate());
        actualGame.setInBundle(gameRequest.isInBundle());
        actualGame.setPublisher(gameRequest.getPublisher());
        gameRepository.save(actualGame);
        return gameMapper.mapToGameResponse(actualGame);
    }

    public List<GameResponse> getGamesByDeveloper(String name) {
        Developer actualDeveloper = developerRepository.findByName(name);

        List<GameResponse> response = new ArrayList<>();
        for (Game game : actualDeveloper.getGames()) {
            response.add(gameMapper.mapToGameResponse(game));
        }

        return response;
    }

    public PlayerResponse addPlayer(PlayerRequest playerRequest) {
        if (playerRequest == null || playerRequest.getName() == null) {
            throw new IllegalArgumentException("Player name and email cannot be null.");
        }

        Player player = playerMapper.mapToPlayer(playerRequest);

        Player savedPlayer = playerRepository.save(player);

        return playerMapper.mapToPlayerResponse(savedPlayer);
    }

    public String deleteGameById(UUID uuid) {
        List<Player> relatedPlayers = playerRepository.findAll();
        for (Player player : relatedPlayers) {
            List<Game> games = player.getGames();
            games.removeIf(game -> game.getUuid().equals(uuid));
            player.setGames(games);
            playerRepository.save(player);
        }

        gameRepository.deleteById(uuid);
        return "Game " + uuid.toString() + " deleted successfully";
    }
}
