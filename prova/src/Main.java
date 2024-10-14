import java.util.ArrayList;
import java.util.List;

// Classe Livro
class Livro {
    private String titulo;
    private String autor;
    private int anoPublicacao;

    public Livro(String titulo, String autor, int anoPublicacao) {
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void exibirInformacoes() {
        System.out.println("Título: " + titulo + ", Autor: " + autor + ", Ano de Publicação: " + anoPublicacao);
    }
}

// Classe Usuario
class Usuario {
    private String nome;
    private List<Livro> livrosEmprestados;

    public Usuario(String nome) {
        this.nome = nome;
        this.livrosEmprestados = new ArrayList<>();
    }

    public void emprestarLivro(Biblioteca biblioteca, Livro livro) {
        if (biblioteca.removerLivro(livro)) {
            livrosEmprestados.add(livro);
            System.out.println(nome + " emprestou o livro: " + livro.getTitulo());
        } else {
            System.out.println("O livro " + livro.getTitulo() + " não está disponível.");
        }
    }

    public void devolverLivro(Biblioteca biblioteca, Livro livro) {
        if (livrosEmprestados.remove(livro)) {
            biblioteca.adicionarLivro(livro);
            System.out.println(nome + " devolveu o livro: " + livro.getTitulo());
        } else {
            System.out.println(nome + " não possui o livro " + livro.getTitulo() + ".");
        }
    }

    public void exibirLivrosEmprestados() {
        if (livrosEmprestados.isEmpty()) {
            System.out.println(nome + " não possui livros emprestados no momento.");
        } else {
            System.out.println("Livros emprestados por " + nome + ":");
            for (Livro livro : livrosEmprestados) {
                livro.exibirInformacoes();
            }
        }
    }
}

// Classe Biblioteca
class Biblioteca {
    private String nome;
    private List<Livro> livrosDisponiveis;

    public Biblioteca(String nome) {
        this.nome = nome;
        this.livrosDisponiveis = new ArrayList<>();
    }

    public void adicionarLivro(Livro livro) {
        livrosDisponiveis.add(livro);
        System.out.println("Livro " + livro.getTitulo() + " adicionado à biblioteca " + nome + ".");
    }

    public boolean removerLivro(Livro livro) {
        if (livrosDisponiveis.remove(livro)) {
            System.out.println("Livro " + livro.getTitulo() + " foi removido da biblioteca " + nome + ".");
            return true;
        }
        return false;
    }

    public void exibirLivrosDisponiveis() {
        if (livrosDisponiveis.isEmpty()) {
            System.out.println("Não há livros disponíveis na biblioteca " + nome + " no momento.");
        } else {
            System.out.println("Livros disponíveis na biblioteca " + nome + ":");
            for (Livro livro : livrosDisponiveis) {
                livro.exibirInformacoes();
            }
        }
    }
}

// Classe Principal (Main)
public class Main {
    public static void main(String[] args) {
        // Criando os livros
        Livro livro1 = new Livro("1984", "George Orwell", 1949);
        Livro livro2 = new Livro("Dom Quixote", "Miguel de Cervantes", 1605);

        // Criando o usuário
        Usuario usuario1 = new Usuario("Ana");

        // Criando a biblioteca
        Biblioteca biblioteca = new Biblioteca("Biblioteca Central");

        // Adicionando livros à biblioteca
        biblioteca.adicionarLivro(livro1);
        biblioteca.adicionarLivro(livro2);

        // Exibindo os livros disponíveis na biblioteca
        biblioteca.exibirLivrosDisponiveis();

        // Usuário emprestando um livro
        usuario1.emprestarLivro(biblioteca, livro1);

        // Exibindo os livros emprestados pelo usuário
        usuario1.exibirLivrosEmprestados();

        // Exibindo os livros disponíveis na biblioteca após o empréstimo
        biblioteca.exibirLivrosDisponiveis();

        // Usuário devolvendo o livro
        usuario1.devolverLivro(biblioteca, livro1);

        // Exibindo os livros disponíveis na biblioteca após a devolução
        biblioteca.exibirLivrosDisponiveis();
    }
}
