import java.time.Duration;
import java.time.LocalDateTime;

import excecoes.ExcecaoNaoEhPossivelSairDaVaga;
import excecoes.ExcecaoSaidaJaFinalizada;
import excecoes.ExcecaoServicoJaContratado;
import excecoes.ExcecaoTempoMinimoNaoAtingido;

public class UsoDeVaga {

    private static final double FRACAO_USO = 0.25;
    private static final double VALOR_FRACAO = 4.0;
    private static final double VALOR_MAXIMO = 50.0;

    private Vaga vaga;
    private LocalDateTime entrada;
    private LocalDateTime saida;
    private double valorPago;

	private TipoServico servico;

    /**
     * Construtor da classe UsoDeVaga.
     * 
     * @param vaga Vaga que será utilizada.
     * @param entrada Data e hora da entrada do veículo na vaga.
     */
    public UsoDeVaga(Vaga vaga, LocalDateTime entrada) {
        this.vaga = vaga;
        this.entrada = entrada;
        this.saida = null;
        this.valorPago = 0.0;
    }

    /**
     * Registra a saída do veículo da vaga e calcula o valor a ser pago.
     * 
     * @param saida Data e hora da saída do veículo da vaga.
     */
    public void registrarSaida(LocalDateTime saida) {
        
        if(saida != null){
            throw new ExcecaoSaidaJaFinalizada();
        }

        if(vaga.sair()){
            this.saida = saida;
            calcularValorPago();
        } else {
            throw new ExcecaoNaoEhPossivelSairDaVaga();
        }
    }

    /**
     * Verifica se a entrada ou saída do veículo está dentro do mês especificado.
     * 
     * @param mes Mês a ser verificado.
     * @return true se estiver dentro do mês, false caso contrário.
     */
    public boolean estaDentroDoMes(int mes) {
        if (entrada != null && (entrada.getMonthValue() == mes || (saida != null && saida.getMonthValue() == mes))) {
            return true;
        }
        return false;
    }

    /**
     * Calcula o valor a ser pago com base no tempo que o veículo permaneceu estacionado.
     */
    private void calcularValorPago() {
        if (saida != null) {
            long minutosEstacionados = entrada.until(saida, java.time.temporal.ChronoUnit.MINUTES);
            double valor = minutosEstacionados / 15.0 * VALOR_FRACAO;
            if(servico != null){
                valorPago = Math.min(valor, VALOR_MAXIMO) + servico.getValor();
            }
        }

    }
    public Vaga getVaga() {
        return vaga;
    }

    public LocalDateTime getEntrada() {
        return entrada;
    }

    public LocalDateTime getSaida() {
        return saida;
    }

    public double getValorPago() {
        return valorPago;
    }

	/**
	 * Contra um serviço vinculado ao uso de vaga com tempo mínimo de permanência dependento do tipo de serviço
	 * @param servico o serviço pode ser MANOBRISTA, LAVAGEM, POLIMENTO
	 * @return retorna o valor do serviço.
	 */
	public Double contratarServico(TipoServico servico) {

		if(this.servico != null){
			throw new ExcecaoServicoJaContratado();
		}

		Duration duration = Duration.between(entrada, saida);

		if(duration.toHours() >= servico.getTempo()){
			this.servico = servico;
			return servico.getValor();
		}

		throw new ExcecaoTempoMinimoNaoAtingido();
	}

}
