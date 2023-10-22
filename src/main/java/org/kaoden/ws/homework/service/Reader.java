package org.kaoden.ws.homework.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.kaoden.ws.homework.obj.Entry;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Reader {
    public Map<UUID, Entry> readEntriesFromJson(String filePath)
            throws IOException {
        List<Entry> entryList = readEntriesAsList(getFile(filePath));
        return convertListToMap(entryList);
    }

    private File getFile(String filePath) {
        File file = new File(filePath);
        if (file.exists())
            return file;
        else
            throw new RuntimeException("File with this location \"" +filePath+ "\" doesn't exist");
    }

    private List<Entry> readEntriesAsList(File file)
            throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(file, new TypeReference<>() {});
    }

    private Map<UUID, Entry> convertListToMap(List<Entry> entryList) {
        Map<UUID, Entry> entries = new HashMap<>();
        entryList.forEach(entry -> entries.put(entry.getUuid(), entry));
        return entries;
    }
}
