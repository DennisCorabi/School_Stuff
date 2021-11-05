import java.util.UUID;

public class User {
    private String name;
    private final UUID id;

    public User(String name){
        this.name=name;
        this.id=UUID.randomUUID();

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
