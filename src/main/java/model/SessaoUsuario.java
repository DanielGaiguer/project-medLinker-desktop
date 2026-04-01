package model;

public class SessaoUsuario {
    private static SessaoUsuario instancia;
    private UsuarioBean usuarioLogado;
    
    public static SessaoUsuario getInstance() { 
        if (instancia == null) {
            instancia = new SessaoUsuario(); 
        } 
        return instancia; 
    }
    
    public void login(UsuarioBean usuario) {
        this.usuarioLogado = usuario;
        System.out.println(usuario.getUsuario()+ " logado com sucesso!");
    }
    
    public void logout() {
            if (usuarioLogado != null) {
                System.out.println(usuarioLogado.getUsuario()+ " deslogado!");
            }
            usuarioLogado = null;
        }

        public boolean isLoggedIn() {
            return usuarioLogado != null;
        }

        public UsuarioBean getLoggedUser() {
            return usuarioLogado;
        }
}
