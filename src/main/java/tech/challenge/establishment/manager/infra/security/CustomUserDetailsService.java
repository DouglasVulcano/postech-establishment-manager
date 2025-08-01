package tech.challenge.establishment.manager.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import tech.challenge.establishment.manager.entities.User;
import tech.challenge.establishment.manager.repositories.UserRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Este método carrega os detalhes do usuário com base no nome de usuário (neste
     * caso, email).
     * Ele é usado pelo Spring Security para autenticação.
     *
     * @param username - O nome de usuário fornecido para autenticação
     * @return UserDetails - Objeto contendo as informações do usuário para o
     *         processo de autenticação
     * @throws UsernameNotFoundException - Exceção lançada se o usuário não for
     *                                   encontrado
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Procura o usuário no banco de dados pelo email (nome de usuário) fornecido
        User user = this.userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));

        // Retorna uma instância de UserDetails com o email, senha e as autoridades definidas no objeto User
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.getAuthorities());
    }
}