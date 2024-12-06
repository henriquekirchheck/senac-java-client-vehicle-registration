package br.dev.henriquekh;

import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;
import java.util.Vector;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.Pair;

public class Manager {
    private final HashMap<UUID, Vehicle> vehicleMap;
    private final HashMap<UUID, Inspection> inspectionMap;
    private final HashMap<UUID, Vector<ChecklistItem>> checklistMap;
    private final HashMap<String, Client> clientNameTable;

    public Manager() {
        this.vehicleMap = new HashMap<>();
        this.inspectionMap = new HashMap<>();
        this.checklistMap = new HashMap<>();
        this.clientNameTable = new HashMap<>();
    }

    public void addClient(Client client) {
        clientNameTable.putIfAbsent(client.getName(), client);
    }

    public void removeClient(Client client) {
        clientNameTable.remove(client.getName());
    }

    public Optional<Client> getClientFromName(String name) {
        return Optional.ofNullable(clientNameTable.get(name));
    }

    public Collection<Client> getAllClients() {
        return clientNameTable.values();
    }

    public UUID addVehicle(Client client, Vehicle vehicle) {
        UUID id = UUID.randomUUID();
        vehicleMap.putIfAbsent(id, vehicle);
        client.addVehicleId(id);
        return id;
    }

    public boolean removeVehicle(Client client, UUID vehicleId) {
        client.removeVehicleId(vehicleId);
        return Optional.ofNullable(vehicleMap.remove(vehicleId)).isPresent();
    }

    public Collection<Pair<UUID, Vehicle>> getVehiclesFromClient(Client client) {
        return client.getAllVehiclesId().stream().map((id) -> Pair.of(id, vehicleMap.get(id)))
                .filter((val) -> val.getRight() != null)
                .collect(Collectors.toList());
    }

    public Optional<Vehicle> getVehiclesById(UUID id) {
        return Optional.ofNullable(vehicleMap.get(id));
    }

    public UUID addInspection(Client client, Inspection inspection) {
        UUID id = UUID.randomUUID();
        inspectionMap.putIfAbsent(id, inspection);
        checklistMap.putIfAbsent(inspection.getChecklistId(), new Vector<>());
        client.addInspectionId(id);
        return id;
    }

    public Optional<Boolean> removeInspection(Client client, UUID inspectionId) {
        client.removeInspectionId(inspectionId);
        return Optional.ofNullable(inspectionMap.remove(inspectionId)).map((val) -> {
            checklistMap.remove(val.getChecklistId());
            return val != null;
        });
    }

    public Collection<Pair<UUID, Inspection>> getInspectionsFromClient(Client client) {
        return client.getAllInspectionsId().stream().map((id) -> Pair.of(id, inspectionMap.get(id)))
                .filter((val) -> val.getRight() != null)
                .collect(Collectors.toList());
    }

    public Optional<Inspection> getInspectionById(UUID id) {
        return Optional.ofNullable(inspectionMap.get(id));
    }

    public void addToInspectionChecklist(Inspection inspection, ChecklistItem check) {
        checklistMap.get(inspection.getChecklistId()).add(check);
    }

    public void removeFromInspectionChecklist(Inspection inspection, ChecklistItem check) {
        checklistMap.get(inspection.getChecklistId()).remove(check);
    }

    public Collection<ChecklistItem> getChecklistFromInspection(Inspection inspection) {
        return checklistMap.get(inspection.getChecklistId());
    }
}
