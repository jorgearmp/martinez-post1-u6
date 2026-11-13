package com.tienda.pedidos.service.refactorizado;

import com.tienda.pedidos.dto.ItemPedido;
import com.tienda.pedidos.dto.PedidoRequest;

public class LiquidadorPrecioService {
    public double liquidarTotal(PedidoRequest request) {
        double subtotal = request.items().stream()
                .mapToDouble(i -> i.precioUnitario() * i.cantidad())
                .sum();

        double descuento = 0.0;
        if ("VIP".equalsIgnoreCase(request.tipoCliente())) {
            descuento = subtotal * 0.15;
        } else if ("CORPORATIVO".equalsIgnoreCase(request.tipoCliente())) {
            descuento = subtotal * 0.10;
        }

        double totalBase = subtotal - descuento;
        double iva = totalBase * 0.19; // IVA 19%
        return totalBase + iva;
    }
}
