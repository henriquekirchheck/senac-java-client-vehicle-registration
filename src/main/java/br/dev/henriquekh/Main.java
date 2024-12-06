package br.dev.henriquekh;

import java.time.LocalDateTime;
import java.util.*;

import org.apache.commons.lang3.tuple.Pair;

public class Main {
    public static void main(String[] args) {
        Manager manager = new Manager();
        Scanner scanner = new Scanner(System.in);
        mainLoop: while (true) {
            System.out.println("""
                    === Menu ===
                    1. Adicionar Cliente
                    2. Listar Clientes
                    3. Remover Cliente
                    4. Adicionar Veiculo a Cliente
                    5. Listar Veiculos do Cliente
                    6. Remover Veiculo do Cliente
                    7. Agendar Vistoria
                    8. Listar Agendamentos do Cliente
                    9. Adicionar Item ao Checklist de Vistoria
                    10. Listar Itens do Checklist de Vistoria
                    0. Sair
                    """);
            System.out.print("Escolha: ");
            int input = scanner.nextInt();
            scanner.nextLine();

            switch (input) {
                case 1 -> {
                    System.out.print("Digite o nome: ");
                    String name = scanner.nextLine();

                    System.out.print("Digite o telefone: ");
                    String phone = scanner.nextLine();

                    System.out.print("Digite o email: ");
                    String email = scanner.nextLine();

                    manager.addClient(new Client(name, phone, email));
                }
                case 2 -> {
                    for (Client client : manager.getAllClients()) {
                        System.out.println(client);
                    }
                }
                case 3 -> {
                    System.out.print("Digite o nome: ");
                    String name = scanner.nextLine();
                    manager.getClientFromName(name).ifPresentOrElse(manager::removeClient,
                            () -> System.out.println("Cliente com esse nome nao existe"));
                }
                case 4 -> {
                    System.out.print("Digite o nome do cliente: ");
                    String name = scanner.nextLine();

                    Optional<Client> clientOpt = manager.getClientFromName(name);
                    if (clientOpt.isEmpty()) {
                        System.out.println("Cliente com esse nome nao existe");
                        break;
                    }

                    Client client = clientOpt.get();

                    System.out.print("Digite a marca: ");
                    String make = scanner.nextLine();

                    System.out.print("Digite o modelo: ");
                    String model = scanner.nextLine();

                    System.out.print("Digite o placa: ");
                    String plate = scanner.nextLine();

                    UUID id = manager.addVehicle(client, new Vehicle(make, model, plate));

                    System.out.println(id);
                }
                case 5 -> {
                    System.out.print("Digite o nome do cliente: ");
                    String name = scanner.nextLine();

                    Optional<Client> clientOpt = manager.getClientFromName(name);
                    if (clientOpt.isEmpty()) {
                        System.out.println("Cliente com esse nome nao existe");
                        break;
                    }

                    Client client = clientOpt.get();

                    for (Pair<UUID, Vehicle> vehicle : manager.getVehiclesFromClient(client)) {
                        System.out.println("UUID: " + vehicle.getLeft());
                        System.out.println(vehicle.getRight());
                    }
                }
                case 6 -> {
                    System.out.print("Digite o nome do cliente: ");
                    String name = scanner.nextLine();

                    Optional<Client> clientOpt = manager.getClientFromName(name);
                    if (clientOpt.isEmpty()) {
                        System.out.println("Cliente com esse nome nao existe");
                        break;
                    }

                    Client client = clientOpt.get();

                    System.out.print("Digite o UUID do veiculo: ");
                    String uuid = scanner.nextLine();

                    boolean removed = manager.removeVehicle(client, UUID.fromString(uuid));

                    if (removed) {
                        System.out.println("Removido com sucesso");
                    } else {
                        System.out.println("Veiculo nao existe");
                    }
                }
                case 7 -> {
                    System.out.print("Digite o nome do cliente: ");
                    String name = scanner.nextLine();

                    Optional<Client> clientOpt = manager.getClientFromName(name);
                    if (clientOpt.isEmpty()) {
                        System.out.println("Cliente com esse nome nao existe");
                        break;
                    }

                    Client client = clientOpt.get();

                    System.out.print("Digite a data da inspecao (YYYY-MM-DD): ");
                    String date = scanner.nextLine();

                    System.out.print("Digite a hora da inspecao (HH:MM): ");
                    String time = scanner.nextLine();

                    System.out.print("Digite o status atual: ");
                    String status = scanner.nextLine();

                    System.out.print("Digite o UUID do veiculo: ");
                    String uuid = scanner.nextLine();

                    Inspection inspection = new Inspection(LocalDateTime.parse(date + "T" + time), status,
                            UUID.fromString(uuid));

                    System.out.println(manager.addInspection(client, inspection));
                }
                case 8 -> {
                    System.out.print("Digite o nome do cliente: ");
                    String name = scanner.nextLine();

                    Optional<Client> clientOpt = manager.getClientFromName(name);
                    if (clientOpt.isEmpty()) {
                        System.out.println("Cliente com esse nome nao existe");
                        break;
                    }

                    Client client = clientOpt.get();

                    for (Pair<UUID, Inspection> inspection : manager.getInspectionsFromClient(client)) {
                        System.out.println("UUID: " + inspection.getLeft());
                        System.out.println(inspection.getRight());
                    }
                }
                case 9 -> {
                    System.out.print("Digite o UUID da vistoria: ");
                    String uuid = scanner.nextLine();

                    Optional<Inspection> inspectionOpt = manager.getInspectionById(UUID.fromString(uuid));
                    if (inspectionOpt.isEmpty()) {
                        System.out.println("Vistoria com esse id nao existe");
                        break;
                    }

                    Inspection inspection = inspectionOpt.get();
                    
                    System.out.print("Digite a descricao do item: ");
                    String description = scanner.nextLine();

                    manager.addToInspectionChecklist(inspection, new ChecklistItem(false, description));
                }
                case 10 -> {
                    System.out.print("Digite o UUID da vistoria: ");
                    String uuid = scanner.nextLine();

                    Optional<Inspection> inspectionOpt = manager.getInspectionById(UUID.fromString(uuid));
                    if (inspectionOpt.isEmpty()) {
                        System.out.println("Vistoria com esse id nao existe");
                        break;
                    }

                    Inspection inspection = inspectionOpt.get();

                    for (ChecklistItem item : manager.getChecklistFromInspection(inspection)){
                        System.out.println(item);
                    }
                }
                case 0 -> {
                    break mainLoop;
                }
                default -> System.out.println("Selecao Invalida: " + input);
            }
        }
        scanner.close();
    }
}