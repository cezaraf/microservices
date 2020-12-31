package br.com.alura.microservice.fornecedor.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.alura.microservice.fornecedor.dto.ItemDoPedidoDTO;
import br.com.alura.microservice.fornecedor.model.Pedido;
import br.com.alura.microservice.fornecedor.model.PedidoItem;
import br.com.alura.microservice.fornecedor.model.Produto;
import br.com.alura.microservice.fornecedor.repository.PedidoRepository;
import br.com.alura.microservice.fornecedor.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PedidoService {

	private final PedidoRepository pedidoRepository;

	private final ProdutoRepository produtoRepository;

	public Pedido realizaPedido(List<ItemDoPedidoDTO> itens) {

		Objects.nonNull(itens);

		List<PedidoItem> pedidoItens = toPedidoItem(itens);

		Pedido pedido = new Pedido(pedidoItens);

		pedido.setTempoDePreparo(itens.size());

		return pedidoRepository.save(pedido);
	}

	public Pedido getPedidoPorId(Long id) {

		return this.pedidoRepository.findById(id).orElse(new Pedido());
	}

	private List<PedidoItem> toPedidoItem(List<ItemDoPedidoDTO> itens) {

		List<Long> idsProdutos = itens.stream().map(item -> item.getId()).collect(Collectors.toList());

		List<Produto> produtosDoPedido = produtoRepository.findByIdIn(idsProdutos);

		List<PedidoItem> pedidoItens = itens.stream().map(item -> {

			Produto produto = produtosDoPedido.stream().filter(p -> p.getId() == item.getId()).findFirst().get();

			PedidoItem pedidoItem = new PedidoItem();

			pedidoItem.setProduto(produto);

			pedidoItem.setQuantidade(item.getQuantidade());

			return pedidoItem;

		}).collect(Collectors.toList());

		return pedidoItens;
	}
}
