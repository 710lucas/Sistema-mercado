package org.example;

import org.example.Exceptions.*;

import java.util.Scanner;

public class Main {

    private static final int FUNCIONARIO = 1, GERENTE = 2, AUTOMATICO = 3, SAIR = 0;

    private static Mercado mercado;
    private static Scanner sc =  new Scanner(System.in);
    private static final String mensagemInicial = """
    1. Se logar como funcionario
    2. Se logar como gerente
    3. Entrar no caixa automatico
    0. Sair
    >
    """;

    public static void main(String[] args){

        mercado = new Mercado();
        int escolha;

        do{
            escolha = intInput(mensagemInicial);
            final int ENTRAR = 1;

            switch (escolha) {
                case FUNCIONARIO -> {
                    funcionario();
                }
                case GERENTE -> {
                    if (intInput("1. Entrar como gerente\n0.Sair\n>") == ENTRAR) {
                        try {
                            gerente(stringInput("Seu nome: "));
                        } catch (PessoaInvalidaException e) {
                            System.out.println(e.getMessage());
                            break;
                        }
                    }
                }
                case AUTOMATICO -> {
                    automatico();
                }
                case SAIR -> {
                    break;
                }
            }
        }while(escolha != SAIR);


    }

    private static void funcionario(){

    }

    private static void gerente(String nome) throws PessoaInvalidaException {

        try {
            if(!mercado.temGerente(nome))
                return;
        } catch (PessoaInvalidaException e) {
            if(mercado.getQuantidadeGerentes() ==  0 && intInput("Não há nenhum gerente no mercado, você deseja adicionar um gerente de nome: "+nome+"\n1. Sim\n2.Não\n>") == 1){
                int idade = intInput("Informe sua idade: ");
                double salario = doubleInput("Informe seu salario: ");
                Gerente gerenteParaAdicionar  = new Gerente(nome, idade, salario);
                mercado.adicionarGerente(gerenteParaAdicionar);
                System.out.println("Escolha novamente a opção de se logar como gerente e informe o nome: "+nome);
                return;
            }
            throw e;
        }

        final int FUNCIONARIOS = 1, GERENTES = 2, CAIXAS_AUT = 3, CAIXAS_MAN = 4, INVENTARIO = 5, VENDAS = 6;
        String opcoes = """
        1. Gerenciar Funcionarios
        2. Gerenciar Gerentes
        3. Gerenciar Caixas Automaticos
        4. Gerenciar Caixas Manuais
        5. Gerenciar Inventario
        6. Gerenciar Vendas
        0. Sair
        >
                """;
        int escolha;
        do {
            escolha = intInput(opcoes);
            switch (escolha) {
                case 1:
                    gerenciarFuncionarios();

            }
        }while(escolha != SAIR);

    }
    private static void gerenciarFuncionarios(){
        final int RELATORIO = 1, FUNCIONARIOS = 2, ADICIONAR = 3, REMOVER = 4, MUDAR = 5;
        String opcoes = """
        1. Ver relatório
        2. Ver funcionarios
        3. Adicionar Funcionario
        4. Remover Funcionario
        5. Mudar salario
        0. Sair
        >
                """;
        int escolha;
        do{
            escolha = intInput(opcoes);
            String nome;
            double salario;
            switch(escolha){
                case RELATORIO:
                    System.out.println(mercado.getRelatorioFuncionarios());
                    break;

                case FUNCIONARIOS:
                    System.out.println(mercado.verFuncionarios());
                    break;

                case ADICIONAR:
                    nome = stringInput("Informe o nome do funcionário que você deseja adicionar: ");
                    int idade = intInput("Digite a idade do funcionário: ");
                    salario = doubleInput("Digite o salário do funcionário: ");
                    try {
                        Funcionario funcionarioParaAdicionar = new Funcionario(nome, idade, salario);
                        mercado.adicionarFuncionario(funcionarioParaAdicionar);
                        System.out.println("Funcionário "+nome+" adicionado com sucesso!");
                        break;
                    } catch (PessoaInvalidaException | FuncionarioException e) {
                        System.out.println(e.getMessage());
                        break;
                    }

                case REMOVER:
                    nome = stringInput("Informe o nome do funcionário que você deseja remover: ");
                    try {
                        Funcionario funcionarioParaRemover = mercado.getfuncionario(nome);
                        mercado.deletaFuncionario(funcionarioParaRemover);
                        System.out.println("Funcionario removido com sucesso!");
                        break;
                    } catch (FuncionarioException e) {
                        System.out.println(e.getMessage());
                        break;
                    }

                case MUDAR:
                    nome = stringInput("Nome do funcionario que você deseja mudar o salario: ");
                    salario = doubleInput("Quantidade do novo salário: ");
                    try{
                        mercado.mudarSalario(nome, salario);
                        System.out.println("Salario mudado com sucesso");
                        break;
                    } catch (FuncionarioException e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                default:
                    break;

            }

        }while(escolha != SAIR);
    }

    private static void automatico(){

    }


    private static String stringInput(String message){
        System.out.printf(message);
        return sc.nextLine();
    }
    private static int intInput(String message){
        return Integer.parseInt(stringInput(message));
    }
    private static double doubleInput(String message){
        return Double.parseDouble(stringInput(message));
    }

}
