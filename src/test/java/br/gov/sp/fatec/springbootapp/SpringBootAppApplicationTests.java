package br.gov.sp.fatec.springbootapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.springbootapp.entity.Usuario;
import br.gov.sp.fatec.springbootapp.repository.UsuarioRepository;

@SpringBootTest
@Transactional // Cria transação para cada teste
@Rollback // não comita as transações
class SpringBootAppApplicationTests {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Test
    void testeInsercao(){
        Usuario usuario = new Usuario();

        usuario.setNome("TESTE");
        usuario.setSenha("senha");
        usuarioRepo.save(usuario);
        assertNotNull(usuario.getId());
    }

    @Test
    void testeAutorizacao(){
        Usuario usuario = usuarioRepo.findById(1L).get();

        assertEquals("ROLE_ADMIN", usuario.getAutorizacoes().iterator().next().getNome());
    }

}
