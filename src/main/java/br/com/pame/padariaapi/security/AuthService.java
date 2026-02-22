package br.com.pame.padariaapi.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.pame.padariaapi.domain.Cliente;
import br.com.pame.padariaapi.repository.ClienteRepository;

@Service
public class AuthService {

    private final ClienteRepository clienteRepository;

    public AuthService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente getClienteLogado() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        // no Basic Auth, o "name" geralmente é o username = email
        String email = auth.getName();

        return clienteRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Cliente logado não encontrado no banco: " + email));
    }
}