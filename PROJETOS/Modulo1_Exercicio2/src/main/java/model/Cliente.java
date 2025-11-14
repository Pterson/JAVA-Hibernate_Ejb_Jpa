package model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Date;
import java.util.Set;

public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;

    private String cpf;       // PK (assigned)
    private String nome;
    private Date dataNasc;

    private Set<Conta> contas = new HashSet<>();

    public Cliente() {}

    public Cliente(String cpf, String nome, Date dataNasc) {
        this.cpf = cpf;
        this.nome = nome;
        this.dataNasc = dataNasc;
    }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Date getDataNasc() { return dataNasc; }
    public void setDataNasc(Date dataNasc) { this.dataNasc = dataNasc; }

    public Set<Conta> getContas() { return contas; }
    public void setContas(Set<Conta> contas) { this.contas = contas; }

    // helper para associação bidirecional
    public void addConta(Conta c) {
        if (c == null) return;
        contas.add(c);
        c.setCliente(this);
    }

    public void removeConta(Conta c) {
        if (c == null) return;
        contas.remove(c);
        c.setCliente(null);
    }

    @Override
    public String toString() {
        return "Cliente{cpf='" + cpf + "', nome='" + nome + "', dataNasc=" + dataNasc + ", contas=" + contas.size() + "}";
    }
}

