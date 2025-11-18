package main.java.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "USUARIO") // adicione schema = "forum" se necessário
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDUSUARIO")
    private Integer id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "EMAIL")
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDFORUM") // coluna FK na tabela USUARIO
    private Forum forum;

    public Usuario() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Forum getForum() { return forum; }
    public void setForum(Forum forum) { this.forum = forum; }

    @Override
    public String toString() {
        return "Usuario[id=" + id + ", nome=" + nome + ", email=" + email + "]";
    }
}