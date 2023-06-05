package org.example;

import org.example.Exceptions.*;

import java.util.Scanner;

public class Main {

    public static final int GERENTE = 1, FUNCIONARIO = 2, AUTOMATICO = 3, SAIR = 5;

    public static Mercado mercado = new Mercado();
    public static Scanner sc = new Scanner(System.in);

    public static void main(String args[]){

        String nome;
        boolean continuar = true;
        mercado = mercado.carregaMercado();

        while(continuar) {

            System.out.println("Seja bem vindo ao sistema do mercado!");
            System.out.print("Escolha algumas opções:\n" +
                    "1. entrar como gerente\n" +
                    "2. entrar como funcionario\n" +
                    "3. pagar com caixa automatico\n"+
                    "5. sair\n>");

            int escolha = Integer.parseInt(sc.nextLine());

            switch (escolha) {
                case FUNCIONARIO:
                    System.out.print("Informe seu nome: ");
                    nome = sc.nextLine();
                    try {
                        caixaFuncionario(mercado.getFuncionario(nome));
                    }catch  (FuncionarioException e){
                        System.out.println(e.getMessage());
                        break;
                    }

                case GERENTE:
                    System.out.print("Digite seu nome: ");
                    nome = sc.nextLine();
                    try{
                        gerencia(mercado.getGerente(nome));
                    } catch (FuncionarioException e) {
                        if(mercado.getFuncionarios().size() == 0) {
                            criaGerente();
                            break;
                        }
                    }
            }

            mercado.salvar();
        }

    }

    private static void gerencia(Gerente gerente) {
        if(!gerente.getTipo().equals("gerente"))
            return;

        boolean sair = false;

        do {
            System.out.println("Boas vindas, " + gerente.getNome());
            System.out.print("0. Sair\n" +
                    "1. Ver inventário\n" +
                    "2. Adicionar item\n" +
                    "3. Reabastecer\n" +
                    "4. Adicionar Funcionario\n" +
                    "5. Remover Funcionario\n" +
                    "6. Adicionar Caixa com Funcionario\n" +
                    "7. Adicionar Caixa automatico\n");
            System.out.print(">");
            int escolha = Integer.parseInt(sc.nextLine());

            sair = gerenteFuncoes(escolha, gerente);

        }while(!sair);


    }

    private static boolean gerenteFuncoes(int escolha, Gerente gerente){
        final int VER_INVENTARIO = 1, ADICIONAR = 2, REABASTECER = 3, ADICIONAR_FUNCIONARIO =4,
        REMOVER_FUNCIONARIO = 5, ADICIONAR_CAIXA_FUNCIONARIO = 6, ADICIONAR_CAIXA_AUTOMATICO = 7;
        String nome;
        int quantidade;
        switch(escolha){
            case VER_INVENTARIO:
                System.out.println(gerente.verInventario(mercado));
                break;

            case ADICIONAR:
                System.out.printf("Nome do produto: ");
                nome = sc.nextLine().toLowerCase();

                System.out.printf("Quantidade de "+nome+" para adicionar: ");
                quantidade = Integer.parseInt(sc.nextLine());

                System.out.printf("Preco de "+nome+": ");
                double preco = Double.parseDouble(sc.nextLine());

                try {
                    gerente.adicionar(nome, preco, quantidade, mercado);
                } catch (QuantidadeInvalidaException | ItemInvalidoException e) {
                    System.out.println(e.getMessage());
                }

                break;

            case REABASTECER:
                System.out.printf("Nome do produto: ");
                nome = sc.nextLine().toLowerCase();
                System.out.printf("Quantidade de "+nome+" para adicionar: ");
                quantidade = Integer.parseInt(sc.nextLine());

                try {
                    gerente.reabastecer(nome, quantidade, mercado);
                } catch (ItemInvalidoException | QuantidadeInvalidaException e) {
                    System.out.println(e.getMessage());;
                }
                break;

            case ADICIONAR_FUNCIONARIO:
                nome = perguntaString("Digite o nome do funcionario: ");
                int idade = perguntaInt("Digite a idade do funcionario: ");
                double salario = perguntaDouble("Digite o salario do funcionario: ");
                try {
                    mercado.adicionaFuncionario(nome, idade, salario);
                    System.out.println("Funcionario adicionado com sucesso!");
                } catch (PessoaInvalidaException | FuncionarioException e) {
                    System.out.println(e.getMessage());
                }
                break;

            case REMOVER_FUNCIONARIO:
                nome = perguntaString("Digite o nome do funcionario: ");
                try{
                    mercado.getFuncionario(nome);
                    mercado.removeFuncionario(nome);
                    System.out.println("Funcionario "+nome+" removido com sucesso");
                    break;
                } catch (FuncionarioException e) {
                    System.out.println(e.getMessage());
                    break;
                }

            case ADICIONAR_CAIXA_FUNCIONARIO:
                try {
                    System.out.println("Caixa de numero: "+gerente.adicionaCaixaFuncionario(mercado)+"foi adicionado com sucesso");
                } catch (CaixaInvalidoException e) {
                    System.out.println(e.getMessage());
                }
                break;

            case ADICIONAR_CAIXA_AUTOMATICO:


            default:
                return true;
        }
        return false;
    }

    private static void criaGerente(){
        System.out.print("Informe o nome do gerente: ");
        String nome = sc.nextLine();

        System.out.printf("Informe o salario de %s: ", nome);
        double salario = Double.parseDouble(sc.nextLine());

        System.out.printf("Informe a idade de %s: ", nome);
        int idade = Integer.parseInt(sc.nextLine());

        try {
            mercado.adicionaFuncionario(new Gerente(nome, idade, salario));
        } catch (FuncionarioException | PessoaInvalidaException e) {
            System.out.println(e.getMessage());;
        }

    }

    private static void caixaFuncionario(Funcionario funcionario) {

        if(!funcionario.getTipo().equals("funcionario"))
            return;

        System.out.print("Informe o numero do caixa que você irá trabalhar: ");
        int numeroCaixa = Integer.parseInt(sc.nextLine());
        CaixaFuncionario caixa;
        try {
            if(mercado.getCaixa(numeroCaixa).getFuncionario() == null){
                System.out.println("Já existe um funcionario neste caixa, tente outro caixa");
                return;
            }
            if(mercado.getCaixa(numeroCaixa).getClass() != CaixaFuncionario.class){
                System.out.println("O numero informado pertence a um caixa automatico, não é possível que se tenha um funcionario nele");
                return;
            }
            caixa = (CaixaFuncionario) mercado.getCaixa(numeroCaixa);
        } catch (CaixaInvalidoException e) {
            System.out.println(e.getMessage());
            return;
        }

        boolean logado = funcionario.getTrabalhando();

        final int PASSAR = 1, FINALIZAR = 2, CANCELAR = 3, SAIR = 4;


        while (logado){
            System.out.println("1. Passar produto" +
                    "2. Finalizar compra" +
                    "3. Cancelar Compra"+
                    "3. Sair");
            int escolha = Integer.parseInt(sc.nextLine());

            switch(escolha) {
                case PASSAR:
                    System.out.print("Informe o nome do produto: ");
                    String nomeProduto = sc.nextLine();
                    try {
                        Item item = mercado.getInventario().getItem(nomeProduto);
                        caixa.vende(item);
                    } catch (ItemInvalidoException | PessoaInvalidaException | CaixaInvalidoException |
                             QuantidadeInvalidaException e) {
                        System.out.println(e);
                        ;
                    }
                    break;

                case FINALIZAR:
                    double precoParaPagar = caixa.getVendaAtual().getProdutosVendidos().getPrecoTotal();
                    String escolha2;
                    do {
                        escolha2 = sc.nextLine().toLowerCase();
                        System.out.println("O valor total foi de: R$" + precoParaPagar + "\nDeseja continuar com a compra?\n(s/n)>");
                    } while (!escolha2.equals("s") && !escolha2.equals("n"));

                    if(escolha2.equals("n"))
                        break;

                    try {
                        caixa.finalizaCompra();
                    } catch (VendaInvalidaException | PessoaInvalidaException | CaixaInvalidoException e) {
                        System.out.println(e.getMessage());
                    }

                    break;
            }
        }


    }


    private static int perguntaInt(String mensagem){
        System.out.printf(mensagem);
        return Integer.parseInt(sc.nextLine());
    }

    private static double perguntaDouble(String mensagem){
        System.out.printf(mensagem);
        return Double.parseDouble(sc.nextLine());
    }

    private static String perguntaString(String mensagem){
        return sc.nextLine();
    }
}
