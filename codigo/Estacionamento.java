

public class Estacionamento {
    private int contClientes = 1;
    private String nome;
    private Cliente[] id;
    private Vaga[] vagas;
    private int quantFileiras;
    private int vagasPorFileira;

    public Estacionamento(String nome, int fileiras, int vagasPorFila) {
        
    }

    public void addVeiculo(Veiculo veiculo, String idCli) {
        
    }

    public void addCliente(Cliente cliente) {
        
    }

    private void gerarVagas() {
        
    }

    public void estacionar(String placa) {
        Veiculo veiculo = null;

        for (Cliente cliente : id) {
            veiculo = cliente.possuiVeiculo(placa);
        }

        if(veiculo == null) 
        return;

        for(Vaga vaga: vagas){
            if(vaga.disponivel()){
                veiculo.estacionar(vaga);
                break;
            }
        }


        
    }
    

    public double sair(String placa) {
        
    }

    public double totalArrecadado() {
        
    }

    public double arrecadacaoNoMes(int mes) {
        
    }

    public double valorMedioPorUso() {
        
    }

    public String top5Clientes(int mes) {
        
    }

}
