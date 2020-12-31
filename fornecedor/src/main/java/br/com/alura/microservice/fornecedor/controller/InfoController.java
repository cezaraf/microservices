package br.com.alura.microservice.fornecedor.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.microservice.fornecedor.model.InfoFornecedor;
import br.com.alura.microservice.fornecedor.service.InfoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/info")
@RequiredArgsConstructor
public class InfoController {

	private static final Logger LOG = LoggerFactory.getLogger(InfoController.class);

	private final InfoService infoService;

	@RequestMapping("/{estado}")
	public InfoFornecedor getInfoPorEstado(@PathVariable String estado) {
		
		LOG.info("recebido pedido de informações do fornecedor de {}", estado);
		
		return infoService.getInfoPorEstado(estado);
	}
}
