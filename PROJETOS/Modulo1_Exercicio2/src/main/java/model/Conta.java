package main.java;

import java.io.Serializable;

public class Conta implements Serializable {
    private static final long serialVersionUID = 1L;

    private int idConta;            // PK identity
    private int agencia;
    private String contaCorrente;
    private double saldo;

    private Cliente cliente;        // many-to-one

    public Conta() {}

    public int getIdConta() { return idConta; }
    public void setIdConta(int idConta) { this.idConta = idConta; }

    public int getAgencia() { return agencia; }
    public void setAgencia(int agencia) { this.agencia = agencia; }

    public String getContaCorrente() { return contaCorrente; }
    public void setContaCorrente(String contaCorrente) { this.contaCorrente = contaCorrente; }

    public double getSaldo() { return saldo; }
    public void setSaldo(double saldo) { this.saldo = saldo; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    @Override
    public String toString() {
        return "Conta{idConta=" + idConta + ", agencia=" + agencia + ", contaCorrente='" + contaCorrente + "', saldo=" + saldo + "}";
    }
}