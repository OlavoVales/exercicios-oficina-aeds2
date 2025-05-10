public class ProdutoNaoPerecivel extends Produto {
    
    public ProdutoNaoPerecivel(String descricao, double precoCusto, double margemLucro){
        super(descricao, precoCusto, margemLucro);
    }

    public ProdutoNaoPerecivel(String descricao, double precoCusto){
        super(descricao, precoCusto);
    }

    public double valorDeVenda(){
        return precoCusto * (1+margemLucro);
    }

    @Override
    public String gerarDadosTexto() {
        String precoFormatado = String.format("%.2f", precoCusto).replace(',', '.');
        String margemFormatada = String.format("%.2f", margemLucro).replace(',', '.');
    
        return String.format("1;%s;%s;%s", descricao, precoFormatado, margemFormatada);
    }     
}
