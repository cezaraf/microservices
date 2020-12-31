package br.com.alura.microservice.fornecedor.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.microservice.fornecedor.dto.ItemDoPedidoDTO;
import br.com.alura.microservice.fornecedor.model.Pedido;
import br.com.alura.microservice.fornecedor.service.PedidoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("pedido")
public class PedidoController {

	private static final Logger LOG = LoggerFactory.getLogger(PedidoController.class);

	private final PedidoService pedidoService;

	@RequestMapping(method = RequestMethod.POST)
	public Pedido realizaPedido(@RequestBody List<ItemDoPedidoDTO> produtos) {

		LOG.info("pedido recebido");

		return pedidoService.realizaPedido(produtos);
	}

	@RequestMapping("/{id}")
	public Pedido getPedidoPorId(@PathVariable Long id) {

		return pedidoService.getPedidoPorId(id);
	}
}
