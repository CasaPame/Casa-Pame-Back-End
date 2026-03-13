package br.com.pame.padariaapi.security;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.pame.padariaapi.domain.Cliente;
import br.com.pame.padariaapi.repository.ClienteRepository;

@Service
public class AuthService {

    private final ClienteRepository clienteRepository;

    public AuthService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }
 
    // Para endpoints que EXIGEM login
    public Cliente getClienteLogado() {
        Cliente cliente = getClienteLogadoSeExistir();
        if (cliente == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                    "Não autorizado. Informe usuário e senha (Basic Auth) válidos.");
        }
        return cliente;
    }

    // Para endpoints públicos: devolve null se não estiver logado
    public Cliente getClienteLogadoSeExistir() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null) return null;
        if (!auth.isAuthenticated()) return null;
        if (auth instanceof AnonymousAuthenticationToken) return null;

        String email = auth.getName();
        if (email == null || email.isBlank()) return null;

        return clienteRepository.findByEmail(email).orElse(null);
    }
}