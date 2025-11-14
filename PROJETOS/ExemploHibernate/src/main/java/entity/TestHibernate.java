package entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class TestHibernate {
    public static void main(String[] args) {
        // Carrega hibernate.cfg.xml do classpath (src/main/resources)
        Configuration cfg = new Configuration();
        cfg.configure(); // busca hibernate.cfg.xml

        SessionFactory sessionFactory = null;
        Session session = null;

        try {
            sessionFactory = cfg.buildSessionFactory();
            session = sessionFactory.openSession();

            session.beginTransaction();

            // Criar Forum (IDs "assigned" — ajustar se mudar o mapping para auto)
            Forum forum = new Forum();
            forum.setIdforum(300); // escolha um id que não exista ainda no DB
            forum.setAssunto("Tópico teste");
            forum.setDescricao("Descrição do tópico de teste");

            // Criar Usuario e associar ao Forum
            Usuario user = new Usuario();
            user.setIdusuario(100); // id "assigned"
            user.setNome("Teste Usuario");
            user.setEmail("teste@exemplo.com");
            user.setForum(forum);

            // Salva (salve forum antes do usuário para evitar FK problems)
            session.save(forum);
            session.save(user);

            session.getTransaction().commit();

            // Consulta para verificar
            session.beginTransaction();
            List<Forum> lista = session.createQuery("from entity.Forum", Forum.class).list();
            System.out.println("Foruns encontrados: " + lista.size());
            for (Forum f : lista) {
                System.out.println("Forum[id=" + f.getIdforum() + ", assunto=" + f.getAssunto()
                        + ", usuarios=" + (f.getUsuarios() != null ? f.getUsuarios().size() : 0) + "]");
            }
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            if (session != null && session.getTransaction().isActive()) session.getTransaction().rollback();
        } finally {
            if (session != null) session.close();
            if (sessionFactory != null) sessionFactory.close();
        }
    }
}