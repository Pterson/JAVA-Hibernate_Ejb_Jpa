package main.java.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "FORUM") // se você usa schema "forum" no DB, adicione: schema = "forum"
public class Forum implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // MySQL auto_increment
    @Column(name = "IDFORUM")
    private Integer id;

    @Column(name = "ASSUNTO")
    private String assunto;

    @Column(name = "DESCRICAO")
    private String descricao;

    @OneToMany(mappedBy = "forum", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Usuario> usuarios = new ArrayList<>();

    public Forum() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getAssunto() { return assunto; }
    public void setAssunto(String assunto) { this.assunto = assunto; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public List<Usuario> getUsuarios() { return usuarios; }
    public void setUsuarios(List<Usuario> usuarios) { this.usuarios = usuarios; }

    // helper
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
        return "Forum[id=" + id + ", assunto=" + assunto + ", usuarios=" + usuarios.size() + "]";
    }
}