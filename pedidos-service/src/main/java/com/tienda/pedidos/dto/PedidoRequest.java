package com.tienda.pedidos.dto;

import java.util.List;

public record PedidoRequest(String pedidoId, String clienteId, String tipoCliente, List<ItemPedido> items) {}
