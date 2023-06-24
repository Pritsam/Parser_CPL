package de.hft_stuttgart.cpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Storage {

    private final Map<String, Value> storage = new HashMap<>();

    public void setValue(String name, Value value) {
        storage.put(name, value);
    }

    public Optional<Value> getValue(String name) {
        return Optional.ofNullable(storage.get(name));
    }

    // TODO double for loop like format (C1R3: <value>)
    public void printAllVariables() {
        storage.entrySet().forEach(s -> System.out.println("Key: " + s.getKey() + "\t Value: " + s.getValue().getValue()));
    }
}
