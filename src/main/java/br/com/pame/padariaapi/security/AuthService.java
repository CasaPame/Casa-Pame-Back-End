package br.com.pame.padariaapi.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

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

        String email = (auth != null ? auth.getName() : null);

        if (email == null || email.isBlank()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                    "Não autorizado. Informe usuário e senha (Basic Auth) válidos.");
        }

        return clienteRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                        "Não autorizado. Cliente não encontrado para o usuário informado."));
    }
}