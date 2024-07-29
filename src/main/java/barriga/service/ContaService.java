package barriga.service;

import barriga.domain.Conta;
import barriga.service.repositories.ContaRepository;

public class ContaService {

    public ContaService(ContaRepository contaRepository){
        this.contaRepository = contaRepository;
    }

    public Conta salvar(Conta conta) {
        return contaRepository.salvar(conta);
    }

    private ContaRepository contaRepository;
}
