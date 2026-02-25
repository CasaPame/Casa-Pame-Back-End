package br.com.pame.padariaapi.security;

import br.com.pame.padariaapi.domain.Cliente;
import br.com.pame.padariaapi.repository.ClienteRepository;

import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class ClienteDetailsService implements UserDetailsService {

    private final ClienteRepository clienteRepository;

    public ClienteDetailsService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Cliente cliente = clienteRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Cliente não encontrado"));

        // Banco: ADMIN / CLIENTE
        // Spring: hasRole("ADMIN") espera ROLE_ADMIN (o .roles("ADMIN") já adiciona ROLE_)
        String role = (cliente.getRole() != null) ? cliente.getRole().name() : "CLIENTE";

        return User.withUsername(cliente.getEmail())
                .password(cliente.getSenha())
                .roles(role) // ADMIN ou CLIENTE
                .build();
    }
}