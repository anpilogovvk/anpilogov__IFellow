package steps;

import hooks.WebHooks;
import io.cucumber.java.ru.Допустим;
import io.cucumber.java.ru.И;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;

import util.TestProperties;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestSteps extends WebHooks {

    String mortySmithId;
    Response findMortySmithId;
    Response findCharacterMorty;
    String findRaceMorty;
    String findLocationLMorty;
    String findLastEpisode;
    Response goToEpisode;
    String findLastCharacter;
    Response goToCharacter;
    String findCharacterName;
    String findRaceCharacter;
    String findLocationCharacter;
    JSONObject body;
    Response marketResponse;
    String name;
    String job;

    @Допустим("получить всех персонажей сериала")
    public void setCharacter() {
        findMortySmithId = new RestStepsTest().getApi(TestProperties.getProperty("base.url.rick.and.morty"), TestProperties.getProperty("base.url.rick.and.morty.character"), 200);
    }

    @И("найти id Морти")
    public void findMorty() {
        mortySmithId = findMortySmithId.jsonPath().getString("results.find{ it.name == 'Morty Smith'}.id");
    }

    @И("открыть всю информацию о Морти")
    public void findMortyPage() {
        findCharacterMorty = new RestStepsTest().getApi(TestProperties.getProperty("base.url.rick.and.morty"), TestProperties.getProperty("base.url.rick.and.morty.character") + mortySmithId, 200);
    }

    @И("найти расу, локацию и последний эпизод с участием Морти")
    public void setMortyData() {
        findRaceMorty = findCharacterMorty.jsonPath().getString("species");
        findLocationLMorty = findCharacterMorty.jsonPath().getString("location.name");
        findLastEpisode = findCharacterMorty.jsonPath().getString("episode[-1]");
    }

    @И("перейти в последний эпизод с участием Морти")
    public void goToLastEpisode() {
        goToEpisode = new RestStepsTest().getApi(findLastEpisode, "", 200);
    }

    @И("найти последнего персонажа в эпизоде")
    public void setLastCharacterIntoEpisode() {
        findLastCharacter = goToEpisode.jsonPath().getString("characters[-1]");
    }

    @И("открыть всю информацию о последнем персонаже в эпизоде")
    public void lastCharacterIntoEpisodeInfo() {
        goToCharacter = new RestStepsTest().getApi(findLastCharacter, "", 200);
    }

    @И("найти расу, локацию и последний эпизод с участием персонажа")
    public void setCharacterData() {
        findCharacterName = goToCharacter.jsonPath().getString("name");
        findRaceCharacter = goToCharacter.jsonPath().getString("species");
        findLocationCharacter = goToCharacter.jsonPath().getString("location.name");
    }

    @И("сравнить локацию персонажей")
    public void comparisonCharactersLocation() {
        Assertions.assertNotEquals(findLocationLMorty, findLocationCharacter);
        System.out.println("Локация Морти: " + findLocationLMorty + ". Локация " + findCharacterName + ": " + findLocationCharacter);
    }

    @И("сравнить расу персонажей")
    public void comparisonCharactersRace() {
        Assertions.assertEquals(findRaceMorty, findRaceCharacter);
        System.out.println("Раса Морти: " + findRaceMorty + ". Раса " + findCharacterName + ": " + findRaceCharacter);
    }

    @Допустим("получить body запроса")
    public void setRequestBody() throws IOException {
        body = new JSONObject(new String(Files.readAllBytes(Paths.get("src/test/resources/json/registration.json"))));
    }

    @И("отправить POST запрос на создание нового пользователя")
    public void createUser() {
        marketResponse = new RestStepsTest().postApi(TestProperties.getProperty("base.url.reqres"), "/api/users", body.toString(), 201);
    }

    @И("сверяем имя пользователя из запроса с именем пользователя из ответа")
    public void setNameUserFromResponse() {
        name = marketResponse.jsonPath().getString("name");
        Assertions.assertEquals(body.getString("name"), name);
    }

    @И("сверяем работу пользователя из запроса с работой пользователя из ответа")
    public void setJobUserFromResponse() {
        job = marketResponse.jsonPath().getString("job");
        Assertions.assertEquals(body.getString("job"), job);
    }
}