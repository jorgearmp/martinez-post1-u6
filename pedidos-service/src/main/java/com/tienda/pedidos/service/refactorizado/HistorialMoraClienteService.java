package com.tienda.pedidos.service.refactorizado;

public class HistorialMoraClienteService {
    public boolean tieneMoraBloqueante(String clienteId) {
        // Regla: clientes con prefijo MOROSO están bloqueados
        return clienteId != null && clienteId.toUpperCase().contains("MORA");
    }
}
