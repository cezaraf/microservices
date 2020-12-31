package br.com.alura.microservice.fornecedor.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.alura.microservice.fornecedor.model.Produto;
import br.com.alura.microservice.fornecedor.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProdutoService {

	private final ProdutoRepository produtoRepository;

	public List<Produto> getProdutosPorEstado(String estado) {

		return produtoRepository.findByEstado(estado);
	}
}
