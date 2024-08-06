package barriga.service;

import barriga.domain.Conta;
import barriga.domain.builders.ContaBuilder;
import barriga.domain.exception.ValidationException;
import barriga.service.repositories.ContaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
public class ContaServiceTest {

    @Test
    public void deveSalvarComSucesso() {
        Conta contaASerSalva = ContaBuilder.buildConta().setId(null).build();

        Mockito.when(contaRepository.salvar(contaASerSalva))
                .thenReturn(ContaBuilder.buildConta().build());

        Conta contaSalva = contaService.salvar(contaASerSalva);

        Assertions.assertNotNull(contaSalva.getId());

    }

    @Test
    public void deveRejeitarContaRepetida() {
        Conta contaASerSalva = ContaBuilder.buildConta().setId(null).build();

        Mockito.when(contaRepository.obterContasPorUsuario(contaASerSalva.getUsuario().getId()))
                .thenReturn(Arrays.asList(ContaBuilder.buildConta().build()));

        String mensagem = Assertions.assertThrows(ValidationException.class, () -> {
            contaService.salvar(contaASerSalva);
        }).getMessage();

        Assertions.assertEquals("Usuario ja possui uma conta com este nome", mensagem);
    }

    @Test
    public void deveSalvarContaMesmoJaExistindoOutras() {
        Conta contaASerSalva = ContaBuilder.buildConta().setId(null).build();

        Mockito.when(contaRepository.obterContasPorUsuario(contaASerSalva.getUsuario().getId()))
                .thenReturn(Arrays.asList(ContaBuilder.buildConta().setNome("OutraConta").build()));
        Mockito.when(contaRepository.salvar(contaASerSalva))
                .thenReturn(ContaBuilder.buildConta().build());

        Conta contaSalva = contaService.salvar(contaASerSalva);

        Assertions.assertNotNull(contaSalva);
    }

    @InjectMocks
    private ContaService contaService;

    @Mock
    private ContaRepository contaRepository;

}
