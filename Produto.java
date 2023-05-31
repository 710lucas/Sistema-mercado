public class Produto {

    private double preco;

    private String nome;

    public Produto(double preco, String nome) throws ProdutoInvalidoException {
        validaProduto(preco, nome);
        this.preco = preco;
        this.nome = nome;
    }

    public void validaProduto(double preco, String nome) throws ProdutoInvalidoException {
        if (preco <= 0){
            throw new ProdutoInvalidoException("O valor do produto não pode ser menor ou igual a zero");
        }
        if (nome == null){
            throw new ProdutoInvalidoException("O nome do produto não pode estar vazio");
        }
    }

    public void setPreco(double preco){
        this.preco = preco;
    }

    public void setNome(String nome){
        this.nome = nome;
    }


}
