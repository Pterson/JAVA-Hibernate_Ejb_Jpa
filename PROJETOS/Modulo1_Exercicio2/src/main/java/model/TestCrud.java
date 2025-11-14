package model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TestCrud {
    public static void main(String[] args) throws Exception {
        Configuration cfg = new Configuration().configure(); // carrega hibernate.cfg.xml
        SessionFactory sf = cfg.buildSessionFactory();
        Session s = sf.openSession();

        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = fmt.parse("1990-05-20");

        String cpf = "12345678901";

        // declarar variáveis no topo do try para clareza/escopo
        List<Conta> contas = null;

        try {
            // ===================== INCLUIR =====================
            s.beginTransaction();

            Cliente cliente = new Cliente();
            cliente.setCpf(cpf);
            cliente.setNome("José da Silva");
            cliente.setDataNasc(dt);

            // cria contas e associa
            Conta c1 = new Conta();
            c1.setAgencia(1001);
            c1.setContaCorrente("00012345-6");
            c1.setSaldo(1500.50);
            // associa bidirecional
            cliente.addConta(c1);

            Conta c2 = new Conta();
            c2.setAgencia(1001);
            c2.setContaCorrente("00098765-4");
            c2.setSaldo(250.75);
            cliente.addConta(c2);

            // Salva cliente (PK assigned) e depois salva as contas (owner = Conta)
            s.save(cliente);
            s.save(c1);
            s.save(c2);

            s.getTransaction().commit();
            System.out.println("=== INCLUSÃO efetuada ===");

            // ===================== LISTAR =====================
            s.beginTransaction();
            List<Cliente> clientes = s.createQuery("from model.Cliente", Cliente.class).list();
            System.out.println("Clientes cadastrados: " + clientes.size());
            for (Cliente cl : clientes) {
                System.out.println(cl);
                // listar contas associadas (a sessão está aberta)
                cl.getContas().forEach(ac -> System.out.println("  -> " + ac));
            }
            s.getTransaction().commit();

            // ===================== ALTERAR =====================
            s.beginTransaction();
            Cliente found = s.get(Cliente.class, cpf);
            if (found != null) {
                System.out.println("Cliente antes: " + found.getNome());
                found.setNome("José da Silva - Atualizado");
                s.update(found);
            }

            // atualizar saldo de uma conta (busca as contas do cliente)
            contas = s.createQuery("from model.Conta where cliente.cpf = :cpf", Conta.class)
                    .setParameter("cpf", cpf).list();
            if (contas != null && !contas.isEmpty()) {
                Conta edit = contas.get(0);
                System.out.println("Saldo antes: " + edit.getSaldo());
                edit.setSaldo(edit.getSaldo() + 100.0); // acrescenta 100
                s.update(edit);
                System.out.println("Saldo atualizado para: " + edit.getSaldo());
            }
            s.getTransaction().commit();
            System.out.println("=== ALTERAÇÕES efetuadas ===");

            // ===================== REMOVER =====================
            s.beginTransaction();
            // remove uma conta (por id) — usa a lista recuperada acima
            if (contas != null && !contas.isEmpty()) {
                Conta toRemove = contas.get(0);
                System.out.println("Removendo conta id=" + toRemove.getIdConta());
                // retirar da coleção do cliente para manter consistência
                Cliente owner = toRemove.getCliente();
                if (owner != null) owner.getContas().remove(toRemove);
                s.delete(toRemove);
            }

            // remover cliente (dependendo do mapeamento/constraint, isto também removerá contas)
            Cliente toRemoveClient = s.get(Cliente.class, cpf);
            if (toRemoveClient != null) {
                System.out.println("Removendo cliente cpf=" + toRemoveClient.getCpf());
                s.delete(toRemoveClient);
            }
            s.getTransaction().commit();
            System.out.println("=== REMOÇÕES efetuadas ===");

            // final list
            s.beginTransaction();
            List<Cliente> finalList = s.createQuery("from model.Cliente", Cliente.class).list();
            System.out.println("Clientes restantes: " + finalList.size());
            s.getTransaction().commit();

        } catch (Exception ex) {
            ex.printStackTrace();
            if (s.getTransaction() != null && s.getTransaction().isActive()) s.getTransaction().rollback();
        } finally {
            if (s != null && s.isOpen()) s.close();
            if (sf != null && !sf.isClosed()) sf.close();
        }
    }
}