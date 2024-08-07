package barriga.service;

import barriga.domain.Conta;
import barriga.domain.builders.ContaBuilder;
import barriga.domain.exception.ValidationException;
import barriga.service.external.ContaEvent;
import barriga.service.repositories.ContaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.management.modelmbean.ModelMBeanOperationInfo;
import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
public class ContaServiceTest {

    @Test
    public void deveSalvarContaComSucesso() throws Exception {
        Conta contaASerSalva = ContaBuilder.buildConta().setId(null).build();

        Mockito.when(contaRepository.salvar(Mockito.any(Conta.class)))
                .thenReturn(ContaBuilder.buildConta().build());

        Mockito.doNothing()
                .when(contaEvent)
                .dispatch(ContaBuilder.buildConta().build(), ContaEvent.EventType.CREATED);

        Conta contaSalva = contaService.salvar(contaASerSalva);
        Assertions.assertNotNull(contaSalva.getId());

        Mockito.verify(contaRepository).salvar(contaCaptor.capture());
        Assertions.assertTrue(contaCaptor.getValue().getNome().startsWith("Test"));
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

    @Test
    public void naoDeveManterContaSemEvento() throws Exception {
        Conta contaASerSalva = ContaBuilder.buildConta().setId(null).build();
        Conta contaSalvaMock = ContaBuilder.buildConta().build();

        Mockito.when(contaRepository.salvar(contaASerSalva))
                .thenReturn(contaSalvaMock);

        Mockito.doThrow(new Exception("Falha catastrofica"))
                .when(contaEvent)
                .dispatch(contaSalvaMock, ContaEvent.EventType.CREATED);

        String mensagem = Assertions.assertThrows(Exception.class, () -> {
            contaService.salvar(contaASerSalva);
        }).getMessage();

        Assertions.assertEquals("Falha na criacao da conta", mensagem);

        Conta contaSalva = contaService.salvar(contaASerSalva);

        Assertions.assertNotNull(contaSalva.getId());

        Mockito.verify(contaRepository).delete(contaSalvaMock);
    }

    @Captor
    private ArgumentCaptor<Conta> contaCaptor;

    @InjectMocks
    private ContaService contaService;

    @Mock
    private ContaEvent contaEvent;

    @Mock
    private ContaRepository contaRepository;

}
