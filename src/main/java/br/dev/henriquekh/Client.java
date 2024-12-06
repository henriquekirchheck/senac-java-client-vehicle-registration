package br.dev.henriquekh;

import java.util.Collection;
import java.util.HashSet;
import java.util.UUID;

public class Client {
    private String name;
    private String phone;
    private String email;
    private final HashSet<UUID> vehiclesId;
    private final HashSet<UUID> inspectionsId;

    public Client(String name, String phone, String email) {
        setEmail(email);
        setName(name);
        setPhone(phone);
        this.vehiclesId = new HashSet<>();
        this.inspectionsId = new HashSet<>();
    }

    public void addVehicleId(UUID id) {
        vehiclesId.add(id);
    }

    public void removeVehicleId(UUID id) {
        vehiclesId.remove(id);
    }

    public Collection<UUID> getAllVehiclesId() {
        return (Collection<UUID>) vehiclesId;
    }

    public void addInspectionId(UUID id) {
        inspectionsId.add(id);
    }

    public void removeInspectionId(UUID id) {
        inspectionsId.remove(id);
    }

    public Collection<UUID> getAllInspectionsId() {
        return (Collection<UUID>) inspectionsId;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Cliente" +
                "\n\tNome: " + getName() +
                "\n\tTelefone: " + getPhone() +
                "\n\tEmail: " + getEmail();
    }
}
