package main.java.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

public class MainPedidos {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Pedidos");
        EntityManager em = emf.createEntityManager();
        main.java.model.PedidoHelper dao = new main.java.model.PedidoHelper(em);

        System.out.println("=== APLICAÇÃO CLIENTES/PEDIDOS ===\n");

        // Criar clientes
        main.java.model.Cliente cliente1 = new main.java.model.Cliente("João Silva", "joao.silva@email.com");
        main.java.model.Cliente cliente2 = new main.java.model.Cliente("Maria Santos", "maria.santos@email.com");
        main.java.model.Cliente cliente3 = new main.java.model.Cliente("Pedro Oliveira", "pedro.oliveira@email.com");

        System.out.println(dao.salvarCliente(cliente1));
        System.out.println(dao.salvarCliente(cliente2));
        System.out.println(dao.salvarCliente(cliente3));

        // Criar pedidos para os clientes
        main.java.model.Pedido pedido1 = new main.java.model.Pedido(cliente1, LocalDateTime.now(), "Notebook Dell", 2500.00);
        main.java.model.Pedido pedido2 = new main.java.model.Pedido(cliente1, LocalDateTime.now().minusDays(1), "Mouse Wireless", 89.90);
        main.java.model.Pedido pedido3 = new main.java.model.Pedido(cliente2, LocalDateTime.now(), "Smartphone Samsung", 1500.00);
        main.java.model.Pedido pedido4 = new main.java.model.Pedido(cliente3, LocalDateTime.now().minusHours(5), "Tablet iPad", 3200.00);

        System.out.println(dao.salvarPedido(pedido1));
        System.out.println(dao.salvarPedido(pedido2));
        System.out.println(dao.salvarPedido(pedido3));
        System.out.println(dao.salvarPedido(pedido4));

        // CONSULTAS
        System.out.println("\n=== CONSULTANDO DADOS ===");

        // Listar todos os clientes
        System.out.println("\n--- TODOS OS CLIENTES ---");
        dao.listarTodosClientes().forEach(System.out::println);

        // Listar todos os pedidos
        System.out.println("\n--- TODOS OS PEDIDOS ---");
        dao.listarTodosPedidos().forEach(System.out::println);

        // Listar pedidos por cliente específico
        System.out.println("\n--- PEDIDOS DO CLIENTE 1 ---");
        dao.listarPedidosPorCliente(1).forEach(pedido -> {
            System.out.println(" - " + pedido.getDescricao() + " - R$ " + pedido.getValor());
        });

        em.close();
        emf.close();

        System.out.println("\n=== APLICAÇÃO FINALIZADA ===");
    }
}