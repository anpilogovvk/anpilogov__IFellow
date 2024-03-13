package testClasses;

import hooks.WebHooks;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.RestSteps;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

class RickAndMortyTest extends WebHooks {

    @Test
    public void FindMorty() {

        Response findMortySmithId = new RestSteps().getApi("https://rickandmortyapi.com/api/character", 200);
        String mortySmithId = findMortySmithId.jsonPath().getString("results.find{ it.name == 'Morty Smith'}.id");

        Response findCharacterMorty = new RestSteps().getApi("https://rickandmortyapi.com/api/character/" + mortySmithId, 200);
        String findRaceMorty = findCharacterMorty.jsonPath().getString("species");
        String findLocationLMorty = findCharacterMorty.jsonPath().getString("location.name");
        String findLastEpisode = findCharacterMorty.jsonPath().getString("episode[-1]");

        Response goToEpisode = new RestSteps().getApi(findLastEpisode, 200);
        String findLastCharacter = goToEpisode.jsonPath().getString("characters[-1]");

        Response goToCharacter = new RestSteps().getApi(findLastCharacter, 200);
        String findCharacterName = goToCharacter.jsonPath().getString("name");
        String findRaceCharacter = goToCharacter.jsonPath().getString("species");
        String findLocationCharacter = goToCharacter.jsonPath().getString("location.name");

        Assertions.assertEquals(findRaceMorty, findRaceCharacter);
        System.out.println("Раса Морти: " + findRaceMorty + ". Раса " + findCharacterName + ": " + findRaceCharacter);
        Assertions.assertNotEquals(findLocationLMorty, findLocationCharacter);
        System.out.println("Локация Морти: " + findLocationLMorty + ". Локация " + findCharacterName + ": " + findLocationCharacter);
    }

    @Test
    public void deepeningIntoApi() throws IOException {

        JSONObject body = new JSONObject(new String(Files.readAllBytes(Paths.get("src/test/resources/json/parent.json"))));
        Response marketResponse = new RestSteps().postApi("https://reqres.in", "/api/users", body.toString(), 201);
        String name = marketResponse.jsonPath().getString("name");
        Assertions.assertEquals(body.getString("name"), name);
        String job = marketResponse.jsonPath().getString("job");
        Assertions.assertEquals(body.getString("job"), job);
    }
}