package entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Forum implements Serializable {
    private static final long serialVersionUID = 1L;
    private int idforum;
    private String assunto;
    private String descricao;
    private Set<Usuario> usuarios = new HashSet<Usuario>(0);

    public Forum() {
    }

    public int getIdforum() {
        return idforum;
    }
    public void setIdforum(int idforum) {
        this.idforum = idforum;
    }
    public String getAssunto() {
        return assunto;
    }
    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public Set<Usuario> getUsuarios() {
        return usuarios;
    }
    public void setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    // helpers para associação bidirecional
    public void addUsuario(Usuario u) {
        if (u == null) return;
        usuarios.add(u);
        u.setForum(this);
    }

    public void removeUsuario(Usuario u) {
        if (u == null) return;
        usuarios.remove(u);
        u.setForum(null);
    }

    @Override
    public String toString() {
        return "Forum{idforum=" + idforum + ", assunto='" + assunto + "', descricao='" + descricao + "', usuarios=" + usuarios.size() + "}";
    }
}