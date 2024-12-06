package br.dev.henriquekh;

import java.time.LocalDateTime;
import java.util.UUID;

public class Inspection {
  private LocalDateTime date;
  private String status;
  private UUID vehicleId;
  private final UUID checklistId;

  public Inspection(LocalDateTime date, String status, UUID vehicleId) {
    setDate(date);
    setStatus(status);
    setVehicleId(vehicleId);
    this.checklistId = UUID.randomUUID();
  }

  public UUID getChecklistId() {
    return checklistId;
  }

  public LocalDateTime getDate() {
    return date;
  }
  public String getStatus() {
    return status;
  }

  public UUID getVehicleId() {
    return vehicleId;
  }

  public void setDate(LocalDateTime date) {
    this.date = date;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public void setVehicleId(UUID vehicleId) {
    this.vehicleId = vehicleId;
  }

  @Override
    public String toString() {
        return "Agendamento Inspecao" +
                "\n\tData: " + getDate() +
                "\n\tStatus: " + getStatus() +
                "\n\tVeiculo: " + getVehicleId();
    }
}
