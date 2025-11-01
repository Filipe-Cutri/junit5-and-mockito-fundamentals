package cursoJUnit5.Examples.service.external;

import cursoJUnit5.Examples.domain.Conta;

public interface ContaEvent {

	public enum EventType {CREATED, UPDATED, DELETED}
	
	void dispatch(Conta conta, EventType type) throws Exception;
}
