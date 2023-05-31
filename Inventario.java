import java.util.ArrayList;

public class Inventario {

    private ArrayList<Produto> produtos;

    public void criarInventario(){
        this.produtos = new ArrayList<>();
    }

    public Produto getProduto(int codigo) throws Exception {
        for(int i = 0; i< produtos.size(); i++){
            Produto produtoProcurado = produtos.get(i);
            if(produtoProcurado.getCodigo() == codigo){
                return produtoProcurado;
            }
        }
        throw  new ProdutoInvalidoException("O código informado não corresponde a nenhum item no inventário");
    }

    public Tipo getTipo(int codigo) throws ProdutoInvalidoException {
        for(int i = 0; i< produtos.size(); i++){
            Produto produtoProcurado = produtos.get(i);
            if(produtoProcurado.getCodigo() == codigo){
                return;
            }
        }
        throw  new ProdutoInvalidoException("O código informado não corresponde a nenhum item no inventário");
    }
    }

}
