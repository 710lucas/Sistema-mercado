package org.example;

import org.example.Exceptions.*;

import java.util.Date;

public class CaixaFuncionario extends Caixa{

    private Funcionario pessoa;

    public CaixaFuncionario(int numero) throws CaixaInvalidoException {
        super(numero);
        setDinheiro(0);
        setVendaAtual(null);
    }

    @Override
    public void adicionaCarrinho(Produto produto, int quantidade) throws ItemInvalidoException, QuantidadeInvalidaException, PessoaInvalidaException, CaixaInvalidoException, VendaInvalidaException {
        if(produto == null)
            throw new ItemInvalidoException();

        try{
            getVendaAtual();
        } catch (VendaInvalidaException e) {
            setVendaAtual(new Venda(this.pessoa, this));
        }

        Item item = new Item(produto, quantidade);
        item.setQuantidade(quantidade);

        getVendaAtual().adicionaItem(item);

    }


    public void login(Funcionario funcionario) throws FuncionarioException, PessoaInvalidaException, CaixaInvalidoException {
        if(funcionario.getTrabalhando())
            throw new FuncionarioException("Funcionario já está trabalhando");

        setHorarioDeEntrada(new Date());

        funcionario.setTrabalhando(true);
        this.pessoa = funcionario;

    }

    public void logout(Funcionario funcionario) throws FuncionarioException {
        if(!funcionario.getTrabalhando())
            throw new FuncionarioException("Funcionario não estava trabalhando para poder exercer logout");

        funcionario.adicionaHorasTrabalhadas(getHorarioDeEntrada(), new Date());
        funcionario.setTrabalhando(false);
        this.pessoa = funcionario;
    }


    @Override
    public double finalizaCompra() throws VendaInvalidaException, PessoaInvalidaException, CaixaInvalidoException {
        double dinheiroDaCompra = getVendaAtual().getProdutosVendidos().getPrecoTotal();
        getVendas().adicionaVenda(getVendaAtual());
        setDinheiro(getDinheiro()+getVendaAtual().getProdutosVendidos().getPrecoTotal());

        this.pessoa.adicionaVendas(getVendaAtual());

        setVendaAtual(null);

        return dinheiroDaCompra;
    }

}
