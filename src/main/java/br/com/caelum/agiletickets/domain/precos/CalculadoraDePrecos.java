package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;
import br.com.caelum.agiletickets.models.TipoDeEspetaculo;

public class CalculadoraDePrecos {

	public static BigDecimal calcula(Sessao sessao, Integer quantidade) {
		BigDecimal preco;
		
		Integer totalIngressos = sessao.getTotalIngressos();
		
		Integer ingressosReservados = sessao.getIngressosReservados();
		
		TipoDeEspetaculo tipoEspetaculo = sessao.getEspetaculo().getTipo();
		
		BigDecimal precoSessao = sessao.getPreco();
		
		preco = precoSessao;
		
		int diffIngressos = totalIngressos - ingressosReservados;
		
		switch (tipoEspetaculo) {
		
		case CINEMA:
			if(diffIngressos / totalIngressos.doubleValue() <= 0.05) { 
				preco = precoSessao.add(precoSessao.multiply(BigDecimal.valueOf(0.10)));
			}
			break;
		
		case SHOW:
			//quando estiver acabando os ingressos... 
			if(diffIngressos / totalIngressos.doubleValue() <= 0.05) { 
				preco = precoSessao.add(precoSessao.multiply(BigDecimal.valueOf(0.10)));
			} 
			break;
		
		case BALLET:
			if(diffIngressos / totalIngressos.doubleValue() <= 0.50) { 
				preco = precoSessao.add(precoSessao.multiply(BigDecimal.valueOf(0.20)));
			} 
			
			if(sessao.getDuracaoEmMinutos() > 60){
				preco = preco.add(precoSessao.multiply(BigDecimal.valueOf(0.10)));
			}
			break;
		case ORQUESTRA:
			if(diffIngressos / totalIngressos.doubleValue() <= 0.50) { 
				preco = precoSessao.add(precoSessao.multiply(BigDecimal.valueOf(0.20)));
			} 
			if(sessao.getDuracaoEmMinutos() > 60){
				preco = preco.add(precoSessao.multiply(BigDecimal.valueOf(0.10)));
			}
			break;
		default:
			//nao aplica aumento para teatro (quem vai é pobretão)
			preco = precoSessao;
			break;
		} 

		return preco.multiply(BigDecimal.valueOf(quantidade));
	}

}