package main.java.dao;

import model.Forum;
import model.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ForumJavaPersistenceDAO {

    private EntityManager em;

    public ForumJavaPersistenceDAO(EntityManager em) {
        this.em = em;
    }

    public Forum createForum(String assunto, String descricao){
        Forum forum = new Forum();
        forum.setAssunto(assunto);
        forum.setDescricao(descricao);
        em.getTransaction().begin();
        em.persist(forum);
        em.getTransaction().commit();
        return forum;
    }

    public Usuario createUsuario(String nome, String email){
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setEmail(email);
        em.getTransaction().begin();
        em.persist(usuario);
        em.getTransaction().commit();
        return usuario;
    }

    public Forum findForum(int id){
        return em.find(Forum.class, id);
    }

    public Forum changeDescricaoForum(int id, String descricao){
        em.getTransaction().begin();
        Forum forum = this.findForum(id);
        if (forum != null) {
            forum.setDescricao(descricao);
            em.merge(forum);
        }
        em.getTransaction().commit();
        return forum;
    }

    public void deleteForum(int id){
        em.getTransaction().begin();
        Forum forum = this.findForum(id);
        if (forum != null) {
            // remove usuários primeiro se necessário (dependendo do cascade/orphanRemoval)
            em.remove(forum);
        }
        em.getTransaction().commit();
    }

    public Forum addUsuarioToForum(int id, Usuario usuario){
        em.getTransaction().begin();
        Forum forum = this.findForum(id);
        if (forum != null) {
            // owner is Usuario -> set forum and persist usuario
            usuario.setForum(forum);
            em.persist(usuario);
            // update inverse side (optional, but keeps object graph consistent)
            forum.getUsuarios().add(usuario);
            em.merge(forum);
        }
        em.getTransaction().commit();
        return forum;
    }

    public List<Usuario> listUsuariosFromForum(int id){
        // usa JPQL para trazer usuários do forum
        TypedQuery<Usuario> q = em.createQuery("select u from model.Usuario u where u.forum.id = :id", Usuario.class);
        q.setParameter("id", id);
        return q.getResultList();
    }

    public void closeEntityManager() {
        if (this.em != null && this.em.isOpen()) this.em.close();
    }
}