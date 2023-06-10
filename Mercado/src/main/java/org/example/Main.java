package org.example;

import org.example.Exceptions.*;

import java.util.Scanner;

public class Main {

    private static final int FUNCIONARIO = 1, GERENTE = 2, AUTOMATICO = 3, SAIR = 0;

    private static Mercado mercado;
    private static Scanner sc =  new Scanner(System.in);
    private static final String ARQUIVO = "MercadoSave.sav";
    private static final String mensagemInicial = """
    1. Se logar como funcionario
    2. Se logar como gerente
    3. Entrar no caixa automatico
    0. Sair
    >""";

    public static void main(String[] args){

        mercado = Mercado.recuperaMercado(ARQUIVO);
        int escolha;

        do{
            escolha = intInput(mensagemInicial);
            final int ENTRAR = 1;

            switch (escolha) {
                case FUNCIONARIO -> {
                    funcionario(stringInput("Seu nome: "));
                    mercado.salvaMercado(ARQUIVO);
                }
                case GERENTE -> {
                    try {
                        gerente(stringInput("Seu nome: "));
                    } catch (PessoaInvalidaException | GerenteJaExisteException e) {
                        System.out.println(e.getMessage());
                    }
                    mercado.salvaMercado(ARQUIVO);
                }
                case AUTOMATICO -> {
                    automatico();
                    mercado.salvaMercado(ARQUIVO);
                }
                case SAIR -> {
                    mercado.salvaMercado(ARQUIVO);
                }
            }
        }while(escolha != SAIR);


    }

    private static void funcionario(String nome){
        final String TIPO_CAIXA = "manual";
        if(!mercado.temFuncionario(nome)) {
            System.out.println("Não foi possivel encontrar um funcionario com este nome");
            return;
        }

        int caixaNumero = intInput("Informe o número do caixa que você irá ficar: ");
        try {
            mercado.getCaixaManualNumero(caixaNumero);
        } catch (CaixaInvalidoException e) {
            System.out.println(e.getMessage());
            return;
        }

        try{
            mercado.loginFuncionario(nome, caixaNumero);
        } catch (PessoaInvalidaException | CaixaInvalidoException | FuncionarioException e) {
            System.out.println(e.getMessage());
        }

        final int ADICIONAR = 1, REMOVER = 2, FINALIZAR = 3, PESQUISAR = 4;
        String opcoes = """
        1. Adicionar item ao carrinho
        2. Remover item do carrinho
        3. Finalizar compra
        4. Pesquisar codigo item
        0. Sair
        >""";
        int escolha;
        do{
            try {
                System.out.println(mercado.verItensCarrinho(TIPO_CAIXA, caixaNumero));
            } catch (CaixaInvalidoException e) {
                System.out.println(e.getMessage());
                return;
            } catch (VendaInvalidaException e) {
                System.out.println(e.getMessage());
            }

            escolha = intInput(opcoes);
            String codigo;
            int quantidade;
            switch(escolha){
                case ADICIONAR:
                    codigo = stringInput("Informe o codigo do produto que voce deseja adicionar ao carrinho: ");
                    quantidade = intInput("Informe a quantidade que será adicionada ao carrinho: ");
                    try{
                        mercado.adicionarItemCarrinho(TIPO_CAIXA, codigo, caixaNumero, quantidade);
                        System.out.println("Item adicionado com sucesso");
                        break;
                    } catch (ItemInvalidoException | PessoaInvalidaException | CaixaInvalidoException | VendaInvalidaException e) {
                        System.out.println("Não foi possivel adiconar o item ao carrinho:\n"+e.getMessage());
                        break;
                    } catch (QuantidadeInvalidaException e){
                        System.out.println(e.getMessage());
                        break;
                    }

                case REMOVER:
                    codigo = stringInput("Informe o codigo do produto que voce deseja remover: ");
                    try {
                        mercado.removerItemCaixa(TIPO_CAIXA, codigo, caixaNumero);
                        break;
                    } catch (CaixaInvalidoException | ItemInvalidoException e) {
                        System.out.println("Não foi possível remover item do carrinho");
                        System.out.println(e.getMessage());
                        break;
                    }

                case FINALIZAR:
                    try{
                        double total = mercado.finalizarCompraCaixa(TIPO_CAIXA, caixaNumero);
                        System.out.println("Compra finalizada com total de R$"+total);

                    } catch (PessoaInvalidaException | CaixaInvalidoException | VendaInvalidaException | ItemInvalidoException e) {
                        System.out.println(e.getMessage());
                    }

            }

        }while(escolha != SAIR);
        try {
            mercado.logoutFuncionario(nome, caixaNumero);
        } catch (FuncionarioException | CaixaInvalidoException e) {
            System.out.println(e.getMessage());
        }

    }

    private static void criaPrimeiroGerente(String nome) throws PessoaInvalidaException, GerenteJaExisteException {
        if(mercado.getQuantidadeGerentes() ==  0 && intInput("Não há nenhum gerente no mercado, você deseja adicionar um gerente de nome: "+nome+"\n1. Sim\n2. Não\n>") == 1){

            int idade = intInput("Informe sua idade: ");
            double salario = doubleInput("Informe seu salario: ");

            mercado.adicionarGerente(nome, idade, salario);

            System.out.println("Escolha novamente a opção de se logar como gerente e informe o nome: "+nome);
        }
    }

    private static void gerente(String nome) throws PessoaInvalidaException, GerenteJaExisteException {

        if(!mercado.temGerente(nome)) {
            criaPrimeiroGerente(nome);
            return;
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
        >""";
        int escolha;
        do {
            escolha = intInput(opcoes);
            switch (escolha) {
                case FUNCIONARIOS:
                    gerenciarFuncionarios();
                    break;

                case GERENTES:
                    gerenciarGerentes();
                    break;

                case CAIXAS_AUT:
                    gerenciarCaixasAutomaticos();
                    break;

                case CAIXAS_MAN:
                    gerenciarCaixasManuais();
                    break;

                case INVENTARIO:
                    gerenciarInventario();
                    break;

                case VENDAS:
                    gerenciarVendas();
                    break;


            }
        }while(escolha != SAIR);
    }

    private static void gerenciarVendas(){
        final int RELATORIO = 1, RELATORIO_FUNCIONARIO = 2, RELATORIO_CAIXA = 3;
        String opcoes = """
        1. Ver relatorio
        2. Ver relatorio de vendas de funcionario
        3. Ver relatorio de vendas de caixa
        0. Sair
        >""";
        int escolha;
        do{
            escolha = intInput(opcoes);
            switch  (escolha){
                case RELATORIO:
                    System.out.println(mercado.getRelatorioVendas());
                    break;

                case RELATORIO_FUNCIONARIO:
                    String nome = stringInput("Informe o nome do funcionario: ");
                    try {
                        System.out.println(mercado.getRelatorioVendasFuncionario(nome));
                        break;
                    } catch (FuncionarioException e) {
                        System.out.println(e.getMessage());
                        break;
                    }

                case RELATORIO_CAIXA:
                    int numero = intInput("Digite o numero do caixa: ");
                    System.out.println(mercado.getRelatorioVendasCaixa(numero));

                default:
                    break;
            }
        }while(escolha != SAIR);
    }

    private static void gerenciarInventario(){
        final int RELATORIO = 1, ADICIONAR_ITEM = 2, REABASTECER = 3, PROCURAR_ITEM_NOME = 4, PROCURAR_CODIGO = 5, ORDENAR = 6, MUDAR_DESCONTO = 7, REMOVER = 8, RENOMEAR = 9, MUDAR_CODIGO = 10;
        String opcoes = """
        1. Ver relatorio
        2. Adicionar item
        3. Reabastecer item
        4. Procurar item por nome
        5. Procurar item por codigo
        6. Ordenar por preço crescente
        7. Mudar desconto item
        8. Remover produto
        9. Renomear produto
        10. Mudar Código
        0. Sair
        >""";
        int escolha;
        do {
            escolha = intInput(opcoes);
            String nome, codigo;
            int quantidade;
            switch(escolha){
                case RELATORIO:
                    System.out.println(mercado.getRelatorioInventario());
                    break;

                case ADICIONAR_ITEM:
                    nome = stringInput("Informe o nome do item que deseja adicionar: ");
                    codigo = stringInput("Informe o código do item que deseja adicionar: ");
                    double preco = doubleInput("Informe o preço do item: ");
                    quantidade = intInput("Informe a quantidade de "+nome+" que deseja adicionar: ");

                    try {
                        mercado.adicionarItem(nome, preco, quantidade, codigo);
                        System.out.println("Item adicionado com sucesso");
                        break;
                    } catch (ItemInvalidoException | QuantidadeInvalidaException e) {
                        System.out.println(e.getMessage());
                        break;
                    }

                case REABASTECER:
                    codigo = stringInput("Informe o codigo do produto que você deseja reabastecer: ");
                    quantidade = intInput("Informe a quantidade de produtos que você deseja adicionar: ");
                    try {
                        mercado.reabastecerItem(codigo, quantidade);
                        break;
                    } catch (ItemInvalidoException | QuantidadeInvalidaException e) {
                        System.out.println(e.getMessage());
                        break;
                    }

                case PROCURAR_ITEM_NOME:
                    nome = stringInput("Digite uma palavra para procurar itens que contém ela em seu nome: ");
                    System.out.println("Os itens que possuem a palavra informada são:\n"+mercado.pesquisaItemNome(nome));
                    break;

                case PROCURAR_CODIGO:
                    codigo = stringInput("Digite uma palavra para procurar itens que contém ela em seu nome: ");
                    System.out.println("Os itens que possuem a palavra informada são:\n"+mercado.pesquisaItemCodigo(codigo));
                    break;

                case ORDENAR:
                    mercado.ordenarInventario();
                    System.out.println("Itens ordenados com sucesso, para ver as alterações veja o relatorio novamente");
                    break;

                case MUDAR_DESCONTO:
                    codigo = stringInput("Informe o código do item que você deseja mudar o desconto: ");
                    int desconto = intInput("Informe a porcentagem do desconto que será dado (ex.: 15 para 15 por cento): ");
                    try {
                        mercado.descontoItem(codigo, desconto);
                        break;
                    } catch (ItemInvalidoException e) {
                        System.out.println(e.getMessage());
                        break;
                    }

                case REMOVER:
                    codigo = stringInput("Informe o código do item que você deseja remover: ");
                    try{
                        mercado.removeItem(codigo);
                        System.out.println("Item removido com sucesso");
                        break;
                    } catch (ItemInvalidoException e) {
                        System.out.println(e.getMessage());
                        break;
                    }

                case RENOMEAR:
                    codigo = stringInput("Informe o código do item que você deseja renomear: ");
                    String novoNome = stringInput("Informe o novo nome do item");
                    try{
                        mercado.renomeiaItem(codigo, novoNome);
                        break;
                    } catch (ItemInvalidoException e) {
                        System.out.println(e.getMessage());
                        break;
                    }

                case MUDAR_CODIGO:
                    codigo = stringInput("Informe o código atual do item o qual você deseja mudar: ");
                    String novoCodigo = stringInput("Digite o novo código: ");
                    try{
                        mercado.mudarCodigoItem(codigo, novoCodigo);
                        break;
                    } catch (ItemInvalidoException e) {
                        System.out.println(e.getMessage());
                        break;
                    }

                default:
                    break;


            }

        }while(escolha != SAIR);
    }

    private static void gerenciarCaixasManuais(){
        final int RELATORIO = 1, ADICIONAR = 2, REMOVER = 3;
        String opcoes = """
        1. Ver relatorio
        2. Adicionar caixa manual
        3. Remover caixa manual
        0. Sair
        >""";
        int escolha;
        do{
            escolha = intInput(opcoes);
            int numero;
            switch (escolha){
                case RELATORIO:
                    System.out.println(mercado.getRelatorioCaixaManual());
                    break;

                case ADICIONAR:
                    numero = intInput("Informe o número do caixa (digite -1 se quiser que o número seja escolhido automaticamente): ");
                    try {
                        mercado.adicionarCaixaManual(numero);
                        System.out.println("Caixa de numero foi adicionado com sucesso");
                        break;
                    } catch (CaixaInvalidoException e) {
                        System.out.println(e.getMessage());
                        break;
                    }

                case REMOVER:
                    numero = intInput("Informe o número do caixa que você deseja remover: ");
                    try{
                        mercado.removerCaixaManual(numero);
                        break;
                    } catch (CaixaInvalidoException e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                default:
                    break;
            }

        }while(escolha != SAIR);
    }
    private static void gerenciarCaixasAutomaticos(){
        final int RELATORIO = 1, ADICIONAR = 2, REMOVER = 3;
        String opcoes = """
        1. Ver relatorio
        2. Adicionar caixa automatico
        3. Remover caixa automatico
        0. Sair
        >""";
        int escolha;
        do{
            escolha = intInput(opcoes);
            int numero;
            switch (escolha){
                case RELATORIO:
                    System.out.println(mercado.getRelatorioCaixaAutomatico());
                    break;

                case ADICIONAR:
                    numero = intInput("Informe o número do caixa (digite -1 se quiser que o número seja escolhido automaticamente): ");
                    try {
                        mercado.adicionarCaixaAutomatico(numero);
                        System.out.println("Caixa de numero foi adicionado com sucesso");
                        break;
                    } catch (CaixaInvalidoException e) {
                        System.out.println(e.getMessage());
                        break;
                    }

                case REMOVER:
                    numero = intInput("Informe o número do caixa que você deseja remover: ");
                    try{
                        mercado.removerCaixaAutomatico(numero);
                        break;
                    } catch (CaixaInvalidoException e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                default:
                    break;
            }

        }while(escolha != SAIR);
    }

    private static void gerenciarGerentes(){
        final int RELATORIO = 1, ADICIONAR = 2, REMOVER = 3, MUDAR_SALARIO = 4;
        String opcoes = """
        1. Ver relatorio
        2. Adicionar gerente
        3. Remover gerente
        4. Mudar salario
        0. Sair
        >""";
        int escolha;
        do{
            escolha = intInput(opcoes);
            String nome;
            double salario;
            switch(escolha){
                case RELATORIO:
                    System.out.println(mercado.getRelatorioGerente());
                    break;

                case ADICIONAR:
                    nome = stringInput("Digite o nome do gerente que você deseja adicionar: ");
                    int idade = intInput("Idade do gerente: ");
                    salario = doubleInput("Salario do gerente: ");
                    try{
                        mercado.adicionarGerente(nome, idade, salario);
                        System.out.println("Gerente "+nome+" foi adicionado com sucesso");
                        break;
                    } catch (PessoaInvalidaException | GerenteJaExisteException e ){
                        System.out.println(e.getMessage());
                        break;
                    }

                case REMOVER:
                    nome = stringInput("Nome do gerente que voce deseja remover: ");
                    try{
                        mercado.deletaGerente(nome);
                        System.out.println("Gerente "+nome+" foi removido com sucesso");
                        break;
                    } catch (PessoaInvalidaException e) {
                        System.out.println(e.getMessage());
                        break;
                    }

                case MUDAR_SALARIO:
                    nome = stringInput("Informe o nome do gerente que você deseja mudar o salário: ");
                    salario = doubleInput("Informe o novo salario do gerente: ");
                    try{
                        mercado.mudaSalarioGerente(nome, salario);
                        System.out.println("Salário modificado com sucesso");
                        break;
                    } catch (PessoaInvalidaException e) {
                        System.out.println(e.getMessage());
                        break;
                    }

                default:
                    break;


            }
        }while(escolha != SAIR);
    }

    private static void gerenciarFuncionarios(){
        final int RELATORIO = 1, FUNCIONARIOS = 2, ADICIONAR = 3, REMOVER = 4, MUDAR_SALARIO = 5;
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
                        mercado.adicionarFuncionario(nome, idade, salario);
                        System.out.println("Funcionário "+nome+" adicionado com sucesso!");
                        break;
                    } catch (PessoaInvalidaException | FuncionarioException e) {
                        System.out.println(e.getMessage());
                        break;
                    }

                case REMOVER:
                    nome = stringInput("Informe o nome do funcionário que você deseja remover: ");
                    try {
                        mercado.deletaFuncionario(nome);
                        System.out.println("Funcionario removido com sucesso!");
                        break;
                    } catch (FuncionarioException e) {
                        System.out.println(e.getMessage());
                        break;
                    }

                case MUDAR_SALARIO:
                    nome = stringInput("Nome do funcionario que você deseja mudar o salario: ");
                    salario = doubleInput("Quantidade do novo salário: ");
                    try{
                        mercado.mudarSalarioFuncionario(nome, salario);
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
