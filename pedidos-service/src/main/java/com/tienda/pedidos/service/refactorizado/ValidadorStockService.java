package com.tienda.pedidos.service.refactorizado;

import com.tienda.pedidos.dto.ItemPedido;
import java.util.List;

public class ValidadorStockService {
    public boolean validarDisponibilidad(List<ItemPedido> items) {
        // Validación cohesiva de stock
        return items != null && !items.isEmpty() && items.stream().allMatch(i -> i.cantidad() > 0 && i.cantidad() <= 100);
    }
}
