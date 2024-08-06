package barriga.service;

import barriga.domain.Conta;
import barriga.domain.exception.ValidationException;
import barriga.service.external.ContaEvent;
import barriga.service.repositories.ContaRepository;

import java.util.List;

public class ContaService {

    public ContaService(ContaEvent contaEvent, ContaRepository contaRepository){
        this.contaEvent = contaEvent;
        this.contaRepository = contaRepository;
    }

    public Conta salvar(Conta conta) {
        List<Conta> contas = contaRepository.obterContasPorUsuario(conta.getUsuario().getId());

        contas.stream().forEach(contaExistente -> {
            if (conta.getNome().equalsIgnoreCase(contaExistente.getNome())) {
                throw new ValidationException("Usuario ja possui uma conta com este nome");
            }
        });

        Conta contaPersistida = contaRepository.salvar(conta);
        try {
            contaEvent.dispatch(contaPersistida, ContaEvent.EventType.CREATED);
        } catch (Exception exception) {
            contaRepository.delete(contaPersistida);
            throw new RuntimeException("Falha na criacao da conta");
        }

        return contaPersistida;
    }

    private ContaEvent contaEvent;

    private ContaRepository contaRepository;
}
