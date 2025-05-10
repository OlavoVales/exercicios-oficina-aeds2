import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Produto {
    protected static final double MARGEM_PADRAO = 0.2;
    protected String descricao;
    protected double precoCusto;
    protected double margemLucro;
     
    private void init(String desc, double precoCusto, double margemLucro){
               
        if(desc.length()<3 ||precoCusto<=0||margemLucro<=0)
            throw new IllegalArgumentException("Valores inválidos para o produto");
        descricao = desc;
        this.precoCusto = precoCusto;
        this.margemLucro = margemLucro;
    }

    protected Produto(String desc, double precoCusto, double margemLucro){
        init(desc, precoCusto, margemLucro);
    }

    protected Produto(String desc, double precoCusto){
        init(desc, precoCusto, MARGEM_PADRAO);
    }

    public abstract double valorDeVenda();
    
    @Override
    public String toString(){
        NumberFormat moeda = NumberFormat.getCurrencyInstance();
        
        return String.format("%s: %s", descricao, moeda.format(valorDeVenda()));
    }

    @Override
    public boolean equals(Object obj){
        Produto outro = (Produto)obj;
        return this.descricao.toLowerCase().equals(outro.descricao.toLowerCase());
    }


    public abstract String gerarDadosTexto();
    
    static Produto criarDoTexto(String linha) {
        Produto novoProduto = null;
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String[] atributos = linha.split(";");
        int tipo = Integer.parseInt(atributos[0]);
        String descricao = atributos[1];
        double precoCusto = Double.parseDouble(atributos[2]);
        double margemLucro = Double.parseDouble(atributos[3]);
        LocalDate dataValidade = null;
    
        if (tipo == 1) 
            novoProduto = new ProdutoNaoPerecivel(descricao, precoCusto, margemLucro);
        else if (tipo == 2) {
            dataValidade = LocalDate.parse(atributos[4], formatoData);
            novoProduto = new ProdutoPerecivel(descricao, precoCusto, margemLucro, dataValidade);
        }
    
        return novoProduto;
    }
}
