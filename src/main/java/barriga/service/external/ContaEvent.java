package barriga.service.external;

import barriga.domain.Conta;

public interface ContaEvent {

    public enum EventType{CREATED, UPDATED, DELETED};

    void dispatch(Conta conta, EventType eventType) throws Exception;
}
