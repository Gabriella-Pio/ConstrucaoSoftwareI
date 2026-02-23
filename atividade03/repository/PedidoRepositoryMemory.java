package atividade03.repository;

import atividade03.entity.Pedido;
import atividade03.entity.StatusPedido;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PedidoRepositoryMemory implements PedidoRepository {
    private final Map<Integer, Pedido> pedidos = new HashMap<>();

    @Override
    public Pedido salvar(Pedido pedido) {
        pedidos.put(pedido.getId(), pedido);
        return pedido;
    }

    @Override
    public List<Pedido> listarTodos() {
        return new ArrayList<>(pedidos.values());
    }

    @Override
    public Optional<Pedido> buscarPorId(int id) {
        return Optional.ofNullable(pedidos.get(id));
    }

    @Override
    public boolean atualizar(Pedido pedido) {
        if (pedidos.containsKey(pedido.getId())
                && pedido.getStatus() != StatusPedido.CANCELADO) {
            pedidos.put(pedido.getId(), pedido);
            return true;
        }
        return false;
    }

    @Override
    public boolean deletar(int id) {
        return pedidos.remove(id) != null;
    }

    @Override
    public int contar() {
        return pedidos.size();
    }
}