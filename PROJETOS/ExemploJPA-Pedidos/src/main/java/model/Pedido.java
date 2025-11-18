package main.java.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.*;

@Entity
@Table(name = "pedidos")
public class Pedido implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDPEDIDO")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDCLIENTE", nullable = false)
    private Cliente cliente;

    @Column(name = "DATA", nullable = false)
    private LocalDateTime data;

    @Column(name = "DESCRICAO", length = 45, nullable = false)
    private String descricao;

    @Column(name = "VALOR", nullable = false)
    private double valor;

    // Construtores
    public Pedido() {}

    public Pedido(Cliente cliente, LocalDateTime data, String descricao, double valor) {
        this.cliente = cliente;
        this.data = data;
        this.descricao = descricao;
        this.valor = valor;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public LocalDateTime getData() { return data; }
    public void setData(LocalDateTime data) { this.data = data; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }

    @Override
    public String toString() {
        return "Pedido [id=" + id + ", data=" + data + ", descricao=" + descricao + ", valor=R$ " + valor + "]";
    }
}