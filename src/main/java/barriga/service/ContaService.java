package barriga.service;

import barriga.domain.Conta;
import barriga.domain.exception.ValidationException;
import barriga.service.repositories.ContaRepository;

import java.util.List;

public class ContaService {

    public ContaService(ContaRepository contaRepository){
        this.contaRepository = contaRepository;
    }

    public Conta salvar(Conta conta) {
        List<Conta> contas = contaRepository.obterContasPorUsuario(conta.getUsuario().getId());

        contas.stream().forEach(contaExistente -> {
            if (conta.getNome().equalsIgnoreCase(contaExistente.getNome())) {
                throw new ValidationException("Usuario ja possui uma conta com este nome");
            }
        });

        return contaRepository.salvar(conta);
    }

    private ContaRepository contaRepository;
}
