package br.dev.henriquekh;

import jdk.jshell.spi.ExecutionControl;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Manager manager = new Manager();
        Scanner scanner = new Scanner(System.in);
        mainLoop:
        while (true) {
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
                    Set<Client> clients = manager.getAllClients();

                    for (Client client :
                            clients) {
                        System.out.println(client);
                    }
                }
                case 3 -> {
                    System.out.print("Digite o nome: ");
                    String name = scanner.nextLine();
                    manager.getClientFromName(name).ifPresentOrElse(manager::removeClient, () -> System.out.println("Cliente com esse nome nao existe"));
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

                    Optional<UUID> id = manager.addVehicle(client, new Vehicle(make, model, plate));

                    id.ifPresentOrElse(System.out::println, () -> System.out.println("Cliente nao existe"));
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

                    Optional<HashMap<UUID, Vehicle>> vehiclesOpt = manager.getVehiclesFromClient(client);

                    vehiclesOpt.ifPresentOrElse((vehicles) -> {
                        vehicles.forEach((uuid, vehicle) -> {
                            System.out.println("UUID: " + uuid);
                            System.out.println(vehicle);
                        });
                    }, () -> System.out.println("Cliente nao existe"));
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

                    Optional<Boolean> removedOpt = manager.removeVehicle(client, UUID.fromString(uuid));

                    removedOpt.ifPresentOrElse((removed) -> {
                        if (removed) {
                            System.out.println("Removido com sucesso");
                        } else {
                            System.out.println("Veiculo nao existe");
                        }
                    }, () -> System.out.println("Cliente nao existe"));
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