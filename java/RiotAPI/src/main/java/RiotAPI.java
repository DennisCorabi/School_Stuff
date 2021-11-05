import com.google.gson.*;

import java.util.UUID;

public class RiotAPI {
    public static void main(String[] args) {
        Gson gson = new Gson();
        JsonObject user = new JsonObject();
        JsonArray vet = new JsonArray();
        JsonPrimitive field = new JsonPrimitive("sommo");

        vet.add("sommo");
        vet.add(3);
        System.out.println(vet);

        user.add("name",field);
        user.addProperty("id", UUID.randomUUID().toString());

        User dennis = gson.fromJson(user,User.class);
        System.out.println(dennis.getName());
        System.out.println(gson.toJson(new User("dennis")));
        user =gson.toJsonTree(new User("u")).getAsJsonObject();
        System.out.println(user);
    }
}
