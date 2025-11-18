package model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Forum");
        EntityManager em = emf.createEntityManager();
        ForumHelper dao = new ForumHelper(em);

        // Criar forum
        main.java.model.Forum forum = new main.java.model.Forum();
        forum.setAssunto("JPA");
        forum.setDescricao("Java Persistence API");
        System.out.println(dao.salvar(forum));

        // Criar usuários
        main.java.model.Usuario usuario1 = new main.java.model.Usuario();
        usuario1.setNome("Joaquim");
        usuario1.setEmail("joaquim@ead.com.br");
        System.out.println(dao.adicionarUsuario(forum.getId(), usuario1));

        main.java.model.Usuario usuario2 = new main.java.model.Usuario();
        usuario2.setNome("Maria");
        usuario2.setEmail("maria@ead.com.br");
        System.out.println(dao.adicionarUsuario(forum.getId(), usuario2));

        main.java.model.Usuario usuario3 = new main.java.model.Usuario();
        usuario3.setNome("Pedro");
        usuario3.setEmail("pedro@ead.com.br");
        System.out.println(dao.adicionarUsuario(forum.getId(), usuario3));

        em.close();
        emf.close();
    }
}