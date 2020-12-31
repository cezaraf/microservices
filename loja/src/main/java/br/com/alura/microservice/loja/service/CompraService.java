package br.com.alura.microservice.loja.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.alura.microservice.loja.client.FornecedorClient;
import br.com.alura.microservice.loja.dto.CompraDTO;
import br.com.alura.microservice.loja.dto.InfoFornecedorDTO;
import br.com.alura.microservice.loja.dto.InfoPedidoDTO;
import br.com.alura.microservice.loja.model.Compra;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CompraService {

	private static final Logger LOG = LoggerFactory.getLogger(CompraService.class);

	private final FornecedorClient fornecedorClient;

	public Compra realizaCompra(CompraDTO compra) {

		final String estado = compra.getEndereco().getEstado();

		LOG.info("buscando informações do fornecedor de {}", estado);
		InfoFornecedorDTO info = fornecedorClient.getInfoPorEstado(estado);

		LOG.info("realizando um pedido");
		InfoPedidoDTO infoPedido = fornecedorClient.realizaPedido(compra.getItens());

		Compra compraSalva = new Compra();

		compraSalva.setPedidoId(infoPedido.getId());

		compraSalva.setTempoDePreparo(infoPedido.getTempoDePreparo());

		compraSalva.setEnderecoDestino(info.getEndereco());

		return compraSalva;
	}
}
