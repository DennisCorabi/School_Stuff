import com.google.gson.*;
import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.summoner.Summoner;

public class RiotAPI {
    public static void main(String[] args) {
        Orianna.setRiotAPIKey("RGAPI-0f70d73c-dda1-44b2-b96c-b75dde785987");
        Orianna.setDefaultRegion(Region.EUROPE_WEST);

        Gson gson = new Gson();

        Summoner player = Summoner.named("denn7ls").withRegion(Region.EUROPE_WEST).get();

    }
}
