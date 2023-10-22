package org.kaoden.ws.homework.obj;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class Entry {
    private UUID uuid;
    private String name;
    private String description;
    private String link;

    @Override
    public String toString() {
        return "Entry: " +
                "uuid=" + uuid +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", link='" + link;
    }
}
