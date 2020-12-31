package br.com.alura.microservice.loja.dto;

import lombok.Data;

@Data
public class InfoPedidoDTO {

	private Long id;

	public Integer tempoDePreparo;
}
