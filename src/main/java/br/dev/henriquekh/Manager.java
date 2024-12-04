package br.dev.henriquekh;

import java.util.*;

public class Manager {
    private final HashMap<Client, HashMap<UUID, Vehicle>> db;
    private final HashMap<String, Client> table;

    public Manager() {
        this.db = new HashMap<>();
        this.table = new HashMap<>();
    }

    public void addClient(Client client) {
        table.put(client.getName(), client);
        db.putIfAbsent(client, new HashMap<>());
    }

    public void removeClient(Client client) {
        table.remove(client.getName());
        db.remove(client);
    }

    public Optional<Client> getClientFromName(String name) {
        return Optional.ofNullable(table.get(name));
    }

    public Set<Client> getAllClients() {
        return db.keySet();
    }

    public Optional<UUID> addVehicle(Client client, Vehicle vehicle) {
        Optional<HashMap<UUID, Vehicle>> vehicles = Optional.ofNullable(db.get(client));
        return vehicles.map((vec) -> {
            UUID id = UUID.randomUUID();
            vec.put(id, vehicle);
            return id;
        });
    }

    public Optional<Boolean> removeVehicle(Client client, UUID vehicleId) {
        Optional<HashMap<UUID, Vehicle>> vehicles = Optional.ofNullable(db.get(client));
        return vehicles.map((vehicleHashMap) -> vehicleHashMap.remove(vehicleId) != null);
    }

    public Optional<HashMap<UUID, Vehicle>> getVehiclesFromClient(Client client) {
        return Optional.ofNullable(db.get(client));
    }
}
