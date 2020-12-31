package br.com.alura.microservice.fornecedor.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.alura.microservice.fornecedor.model.Produto;
import br.com.alura.microservice.fornecedor.service.ProdutoService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/produto")
public class ProdutoController {

	private final ProdutoService produtoService;

	@RequestMapping("/{estado}")
	public List<Produto> getProdutosPorEstado(@PathVariable("estado") String estado) {

		return produtoService.getProdutosPorEstado(estado);
	}
}
