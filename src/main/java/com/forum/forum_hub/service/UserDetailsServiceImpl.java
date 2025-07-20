package com.forum.forum_hub.service;



import com.forum.forum_hub.UsuarioRepository;
import com.forum.forum_hub.models.Usuario; // Importa o modelo de usuário
import org.springframework.beans.factory.annotation.Autowired; // Importa a anotação @Autowired
import org.springframework.security.core.userdetails.UserDetails; // Interface do Spring Security para os detalhes do usuário
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException; // Exceção quando o usuário não é encontrado
import org.springframework.stereotype.Service; // Marca a classe como um serviço



@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByNome(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        return org.springframework.security.core.userdetails.User.builder()
                .username(usuario.getNome())
                .password(usuario.getSenha())
                .roles("USER")
                .build();
    }
}