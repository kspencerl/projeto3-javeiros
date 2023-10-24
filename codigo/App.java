import java.time.LocalDate;
import java.util.Scanner;

import estacionamentos.*;
import excecoes.ExcecaoClienteJaCadastrado;
import excecoes.ExcecaoVeiculoJaCadastrado;




public class App {

    private static Scanner teclado = new Scanner(System.in);
    private static Estacionamento estacionamentos[] = new Estacionamento[10];
    private static Estacionamento estacionamentoHelper;

    public static void main(String args[]) throws ExcecaoClienteJaCadastrado, ExcecaoVeiculoJaCadastrado {
        try{

        }catch(Exception e){

        }
        menu();
    }

    public static void menu() throws ExcecaoClienteJaCadastrado, ExcecaoVeiculoJaCadastrado {

        int i = 0;
        while (i != 3) {
            System.out.println("|-------------------------------|");
            System.out.println("|        Menu Principal         |");
            System.out.println("|-------------------------------|");
            System.out.println("| 1. Criar um Estacionamento    |");
            System.out.println("| 2. Acessar um Estacionamento  |");
            System.out.println("| 3. Sair                       |");
            System.out.println("|-------------------------------|");
            System.out.print("Digite uma das opções acima: ");

            i = Integer.parseInt(teclado.nextLine());

            switch (i) {
                
                case 1: {
                    if (addEstacionamento() == 1) {
                        System.out.println("Estacionamento cadastrado com sucesso");
                    } else {
                        System.out.println("Não foi possível cadastrar o estacionamento");
                    }
                    break;
                }

                case 2:
                    selecionarEstacionamentos();
                    int optionMenu = menuEstacionamento();
                    switchMenuEstacionameto(optionMenu);
                    break;
            }
        }
    }



    public static int addEstacionamento() {

        String nome;
        int fileiras;
        int veiculosFileiras;
        Estacionamento estacionamento;

        System.out.println("Digite o nome do Estacionamento: ");

        nome = teclado.nextLine();
        fileiras = Integer.parseInt(teclado.nextLine());
        veiculosFileiras = Integer.parseInt(teclado.nextLine());
        estacionamento = new Estacionamento(nome, fileiras, veiculosFileiras);

        for (int j = 0; j < estacionamentos.length; j++) {
            if (estacionamentos[j] == null) {
                estacionamentos[j] = estacionamento;
                return 1;
            }
        }
        return 0;
    }

    public static int menuEstacionamento() {
        int option = 0;
        System.out.println("|-------------------------------|");
        System.out.println("|    Selecione uma das Opcões   |");
        System.out.println("|-------------------------------|");
        System.out.println("| 1. Adicionar Veiculo          |");
        System.out.println("| 2. Adicionar Cliente          |");
        System.out.println("| 3. Estacionar                 |");
        System.out.println("| 4. Sair                       |");
        System.out.println("| 5. Total Arrecadado           |");
        System.out.println("| 6. Arracadação no Mes         |");
        System.out.println("| 7. Valor Médio por Uso        |");
        System.out.println("| 8. Top 5 clientes             |");
        System.out.println("| 9. Sair                      |");
        System.out.println("|-------------------------------|");
        option = Integer.parseInt(teclado.nextLine());
        return option;
    }

    public static void selecionarEstacionamentos() {

        for (int i = 0; i < estacionamentos.length; i++) {
            System.out.println(i + "- " + estacionamentos[i].getNome());
        }
        int index = Integer.parseInt(teclado.nextLine());
        estacionamentoHelper = estacionamentos[index];
    }


    public static void switchMenuEstacionameto(int option) throws ExcecaoClienteJaCadastrado, ExcecaoVeiculoJaCadastrado {

        switch (option) {
            case 1:
                addCliente();
            break;

            case 2:
                cadastrarVeiculo();
            break;

            case 3:
                estacionar();
            break;

            case 4:
                sair();
            break;

            case 5:
                totalArrecadado();
            break;

            case 6:
                arrecadadoNoMes();
            break;

            case 7:
               valorMedioUso();
            break;

            case 8:
                topClientes();
                break;

            case 9:

                break;

        }
    }

    
    public static void addCliente() throws ExcecaoClienteJaCadastrado {

        String nome;
        String id;

        System.out.println("Digite o nome do Cliente: ");
        nome = teclado.nextLine();
        System.out.println("Digite o id do cliente: ");
        id = teclado.nextLine();

        estacionamentoHelper.addCliente(new Cliente(nome, id));

    }

    public static void cadastrarVeiculo() throws ExcecaoVeiculoJaCadastrado{

        System.out.println("Digite o id do cliente no qual deseja cadastrar o veiculo: ");
        String id = (teclado.nextLine());
        Veiculo veiculo = criarVeiculo();
        estacionamentoHelper.addVeiculo(veiculo, id);
    }



    public static Veiculo criarVeiculo(){
        String placa = teclado.nextLine();
        System.out.println("Digite a placa");
        Veiculo veiculo = new Veiculo(placa);
        return veiculo;
    }


    public static void estacionar(){
        System.out.println("Digite a placa do veiculos: ");
        estacionamentoHelper.estacionar(teclado.nextLine());
    }

    public static void sair(){
        System.out.println("Digite a placa do veiculo:");
        estacionamentoHelper.sair(teclado.nextLine());
    }


    public static void totalArrecadado(){
        System.out.println("Valor total: " + estacionamentoHelper.totalArrecadado());
    }

    public static void arrecadadoNoMes(){
        LocalDate dataAtual = LocalDate.now();
        
        for (int i = 1; i <= 11; i++) {
            LocalDate mesAnterior = dataAtual.minusMonths(i);
            System.out.println("|"+ i + " -  " + mesAnterior.getMonth() + " " + mesAnterior.getYear() + " |");
        }
    }

    public static void valorMedioUso(){
        System.out.println("Valor medio por uso: " + estacionamentoHelper.valorMedioPorUso());
    }

    public static void topClientes(){
        System.out.println("Digite o valor do mes:");

        System.out.println(estacionamentoHelper.top5Clientes(Integer.parseInt(teclado.nextLine())));
    }


}