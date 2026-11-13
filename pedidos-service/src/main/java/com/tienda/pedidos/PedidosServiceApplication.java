package com.tienda.pedidos;

import com.tienda.pedidos.dto.ItemPedido;
import com.tienda.pedidos.dto.PedidoRequest;
import com.tienda.pedidos.dto.ResultadoPedido;
import com.tienda.pedidos.service.refactorizado.GestorPedidosRefactorizado;
import com.tienda.pedidos.service.refactorizado.HistorialMoraClienteService;
import com.tienda.pedidos.service.refactorizado.LiquidadorPrecioService;
import com.tienda.pedidos.service.refactorizado.ValidadorStockService;

import java.util.List;

public class PedidosServiceApplication {
    public static void main(String[] args) {
        System.out.println("==================================================================");
        System.out.println(" REFACTORIZACIÓN DE ANTIPATRONES (GESTORPEDIDOS) - UNIDAD 6 (UFPS)");
        System.out.println(" Estudiante: Jorge Martínez");
        System.out.println("==================================================================");

        var validadorStock = new ValidadorStockService();
        var historialMora = new HistorialMoraClienteService();
        var liquidador = new LiquidadorPrecioService();
        var gestor = new GestorPedidosRefactorizado(validadorStock, historialMora, liquidador);

        // Caso 1: Pedido Válido Cliente VIP
        var req1 = new PedidoRequest("PED-001", "CLI-100", "VIP", List.of(
                new ItemPedido("PRD-1", "Disco SSD NVMe 1TB", 80.0, 2),
                new ItemPedido("PRD-2", "Memoria RAM DDR5 32GB", 120.0, 2)
        ));
        ResultadoPedido res1 = gestor.procesarPedido(req1);
        System.out.printf("Resultado 1: %s (Total: $%.2f)%n%n", res1.mensaje(), res1.totalLiquidado());

        // Caso 2: Cliente con Mora (Bloqueado)
        var req2 = new PedidoRequest("PED-002", "CLI-MORA-99", "ESTANDAR", List.of(
                new ItemPedido("PRD-3", "Monitor 24", 150.0, 1)
        ));
        ResultadoPedido res2 = gestor.procesarPedido(req2);
        System.out.printf("Resultado 2: %s%n%n", res2.mensaje());

        System.out.println("Demostración de Refactorización de Antipatrones completada con éxito.");
    }
}
