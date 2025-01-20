package org.example.mprprojekt.controllers;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.example.mprprojekt.models.developer.Developer;
import org.example.mprprojekt.models.developer.DeveloperRequest;
import org.example.mprprojekt.models.developer.DeveloperResponse;
import org.example.mprprojekt.models.game.Game;
import org.example.mprprojekt.models.game.GameRequest;
import org.example.mprprojekt.models.game.GameResponse;
import org.example.mprprojekt.models.player.PlayerRequest;
import org.example.mprprojekt.models.player.PlayerResponse;
import org.example.mprprojekt.services.GameService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class GameController {
    @NonNull
    private final GameService gameService;

    @GetMapping("/getDefaultGame")
    public GameResponse getDefaultGame() {
        return gameService.getDefaultGame();
    }

    @GetMapping("/getAllGames")
    public List<Game> getAllGames() {
        return gameService.getAllGames();
    }

    @GetMapping("/getAllDevelopers")
    public List<DeveloperResponse> getAllDevelopers() {
        return gameService.getAllDevelopers();
    }

    @PostMapping("/addGame")
    public GameResponse addGame(@RequestBody GameRequest game) {
        return gameService.addGame(game);
    }

    @GetMapping("/getDeveloperByGame/{gameTitle}")
    public DeveloperResponse getDeveloperByGameTitle(@PathVariable String gameTitle) {
        return gameService.findDeveloperByGameTitle(gameTitle);
    }

    @GetMapping("/getGamesByDeveloper/{name}")
    public List<GameResponse> getGamesByDeveloper(@PathVariable String name) {
        return gameService.getGamesByDeveloper(name);
    }

    @PutMapping("/editDeveloper/{name}")
    public DeveloperResponse editDeveloper(@PathVariable String name, @RequestBody DeveloperRequest developer) {
        return gameService.updateDeveloper(name, developer);
    }

    @PutMapping("/editGame/{uuid}")
    public GameResponse editGame(@PathVariable UUID uuid, @RequestBody GameRequest game) {
        return gameService.updateGame(uuid, game);
    }

    @PostMapping("/addPlayer")
    public PlayerResponse addPlayer(@RequestBody PlayerRequest player) {
        return gameService.addPlayer(player);
    }

    @GetMapping("/getAllPlayers")
    public List<PlayerResponse> getAllPlayers() {
        return gameService.getAllPlayers();
    }

    @DeleteMapping("/deleteGameById/{uuid}")
    public String deleteGameById(@PathVariable UUID uuid) {
        return gameService.deleteGameById(uuid);
    }
}

