package main.java.principal;

import dao.ForumJavaPersistenceDAO;
import model.Forum;
import model.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class TesteJPA {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ExemploJPA");
        EntityManager em = emf.createEntityManager();
        ForumJavaPersistenceDAO forumDAO = new ForumJavaPersistenceDAO(em);

        // cria um forum
        Forum forum = forumDAO.createForum("Java Persistence API", "JPA é utilizado para tratamento ORM");

        // cria usuários e adiciona ao fórum
        for (int i = 0; i < 10; i++) {
            Usuario usuario = new Usuario();
            usuario.setNome("Usuario" + i);
            usuario.setEmail("usuario" + i + "@ead.com.br");
            forumDAO.addUsuarioToForum(forum.getId(), usuario);
        }

        // lista usuários do fórum
        List<Usuario> usuarios = forumDAO.listUsuariosFromForum(forum.getId());
        System.out.println("Forum: " + forumDAO.findForum(forum.getId()).getAssunto());
        for (Usuario usuario : usuarios) {
            System.out.println(usuario.getNome() + " - " + usuario.getEmail());
        }

        forumDAO.closeEntityManager();
        emf.close();
    }
}