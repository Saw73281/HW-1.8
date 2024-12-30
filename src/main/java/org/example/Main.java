package org.example;

import org.flywaydb.core.Flyway;

public class Main {
    public static void main(String[] args) {
        Flyway flyway = Flyway.configure()
                .dataSource("jdbc:h2:/TestDB/TestDB-2", "so", "")
                .locations("classpath:db/migration")
                .load();
        flyway.migrate();

        ClientService clientService = new ClientService();

        System.out.println("Creating a new client...");
        long clientId = clientService.create("John Doe");
        System.out.println("Client created with ID: " + clientId);

        System.out.println("Fetching client by ID...");
        String clientName = clientService.getById(clientId);
        System.out.println("Client name: " + clientName);

        System.out.println("Updating client name...");
        clientService.setName(clientId, "Jane Doe");
        System.out.println("Updated client name: " + clientService.getById(clientId));

        System.out.println("Listing all clients...");
        clientService.listAll().forEach(System.out::println);

        System.out.println("Deleting client...");
        clientService.deleteById(clientId);
        System.out.println("Client with ID " + clientId + " has been deleted.");

        System.out.println("Listing all clients after deletion...");
        clientService.listAll().forEach(System.out::println);

        System.out.println("Program completed successfully.");
    }
}
