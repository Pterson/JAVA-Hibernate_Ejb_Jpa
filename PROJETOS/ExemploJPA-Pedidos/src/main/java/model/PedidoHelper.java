package main.java.model;

import javax.persistence.EntityManager;
import java.util.List;

public class PedidoHelper {
    private EntityManager em;

    public PedidoHelper(EntityManager em) {
        this.em = em;
    }

    // CRUD Cliente
    public String salvarCliente(Cliente cliente) {
        try {
            em.getTransaction().begin();
            em.persist(cliente);
            em.getTransaction().commit();
            return "Cliente salvo: " + cliente.getNome();
        } catch (Exception e) {
            em.getTransaction().rollback();
            return "Erro ao salvar cliente: " + e.getMessage();
        }
    }

    // CRUD Pedido
    public String salvarPedido(Pedido pedido) {
        try {
            em.getTransaction().begin();
            em.persist(pedido);
            em.getTransaction().commit();
            return "Pedido salvo: " + pedido.getDescricao();
        } catch (Exception e) {
            em.getTransaction().rollback();
            return "Erro ao salvar pedido: " + e.getMessage();
        }
    }

    // Adicionar pedido a cliente existente
    public String adicionarPedido(int idCliente, Pedido pedido) {
        try {
            Cliente cliente = em.find(Cliente.class, idCliente);
            if (cliente != null) {
                pedido.setCliente(cliente);
                cliente.getPedidos().add(pedido);

                em.getTransaction().begin();
                em.persist(pedido);
                em.getTransaction().commit();
                return "Pedido adicionado ao cliente: " + cliente.getNome();
            } else {
                return "Cliente não encontrado!";
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            return "Erro ao adicionar pedido: " + e.getMessage();
        }
    }

    // CONSULTAS
    public List<Cliente> listarTodosClientes() {
        return em.createQuery("SELECT c FROM Cliente c", Cliente.class)
                .getResultList();
    }

    public List<Pedido> listarPedidosPorCliente(int idCliente) {
        return em.createQuery("SELECT p FROM Pedido p WHERE p.cliente.id = :idCliente", Pedido.class)
                .setParameter("idCliente", idCliente)
                .getResultList();
    }

    public List<Pedido> listarTodosPedidos() {
        return em.createQuery("SELECT p FROM Pedido p", Pedido.class)
                .getResultList();
    }

    public Cliente buscarClienteComPedidos(int idCliente) {
        return em.createQuery("SELECT c FROM Cliente c LEFT JOIN FETCH c.pedidos WHERE c.id = :id", Cliente.class)
                .setParameter("id", idCliente)
                .getSingleResult();
    }
}
