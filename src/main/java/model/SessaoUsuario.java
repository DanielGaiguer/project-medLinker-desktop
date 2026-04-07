package model;

public class SessaoUsuario {
    private static SessaoUsuario instancia;
    private UsuarioBean usuarioLogado;
    private int id;
    
    public static SessaoUsuario getInstance() { 
        if (instancia == null) {
            instancia = new SessaoUsuario(); 
        } 
        return instancia; 
    }
    
    public String getNome() {
        return usuarioLogado.getNome();
    }
    
    public int getId() {
        return this.id;
    }
    
    public void login(UsuarioBean usuario) {
        this.usuarioLogado = usuario;
        this.id = usuario.getId();
    }
    
    public void logout() {
            if (usuarioLogado != null) {
                System.out.println("Não tem nenhuma conta logada no momento.");
            }
            usuarioLogado = null;
            this.id = 0;
        }

        public boolean isLoggedIn() {
            return usuarioLogado != null;
        }

        public UsuarioBean getLoggedUser() {
            return usuarioLogado;
        }
}
