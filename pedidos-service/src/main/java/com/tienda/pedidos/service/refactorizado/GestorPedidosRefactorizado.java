package com.tienda.pedidos.service.refactorizado;

import com.tienda.pedidos.dto.PedidoRequest;
import com.tienda.pedidos.dto.ResultadoPedido;

public class GestorPedidosRefactorizado {
    private final ValidadorStockService validadorStock;
    private final HistorialMoraClienteService historialMora;
    private final LiquidadorPrecioService liquidadorPrecio;

    public GestorPedidosRefactorizado(ValidadorStockService validadorStock,
                                      HistorialMoraClienteService historialMora,
                                      LiquidadorPrecioService liquidadorPrecio) {
        this.validadorStock = validadorStock;
        this.historialMora = historialMora;
        this.liquidadorPrecio = liquidadorPrecio;
    }

    public ResultadoPedido procesarPedido(PedidoRequest request) {
        System.out.println("Iniciando procesamiento refactorizado para pedido: " + request.pedidoId());

        // 1. Validar mora del cliente (SRP)
        if (historialMora.tieneMoraBloqueante(request.clienteId())) {
            return new ResultadoPedido(false, 0.0, "Pedido rechazado: El cliente presenta mora crediticia activa.");
        }

        // 2. Validar existencias en almacén (SRP)
        if (!validadorStock.validarDisponibilidad(request.items())) {
            return new ResultadoPedido(false, 0.0, "Pedido rechazado: Existencias insuficientes en almacén.");
        }

        // 3. Liquidar precio y registrar (SRP)
        double total = liquidadorPrecio.liquidarTotal(request);
        System.out.printf("  [LIQUIDADO] Subtotal con descuento y tributos calculado: $%.2f%n", total);

        return new ResultadoPedido(true, total, "Pedido aprobado y procesado con éxito.");
    }
}
