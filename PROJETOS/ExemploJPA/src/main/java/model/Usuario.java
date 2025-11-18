package main.java.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "USUARIO", schema = "forum01")
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDUSUARIO")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDFORUM")
    private main.java.model.Forum forum;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "EMAIL")
    private String email;

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public main.java.model.Forum getForum() { return forum; }
    public void setForum(main.java.model.Forum forum) { this.forum = forum; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}