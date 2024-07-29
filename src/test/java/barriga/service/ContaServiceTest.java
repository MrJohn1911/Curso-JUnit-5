package barriga.service;

import barriga.domain.Conta;
import barriga.domain.builders.ContaBuilder;
import barriga.service.repositories.ContaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

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

    @InjectMocks
    private ContaService contaService;

    @Mock
    private ContaRepository contaRepository;

}
