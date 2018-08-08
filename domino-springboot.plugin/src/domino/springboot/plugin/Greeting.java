package domino.springboot.plugin;

import javax.validation.constraints.NotNull;

public class Greeting {

    private final long id;
    private final String content;

    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }
    @NotNull
    public String getContent() {
        return content;
    }
}