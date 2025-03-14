package pooPolimorfismo;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author 1075702
 */
public class ProdutoNaoPerecivel extends Produto{
    
    public ProdutoNaoPerecivel(String descrisao, double precoCusto, double MargemLucro){
        super(descrisao, precoCusto, MargemLucro);
    }
    
    public ProdutoNaoPerecivel(String descrisao, double precoCusto){
        super(descrisao, precoCusto);
    }
    
    @Override 
    public double valorVenda(){
        return precoCusto * (1+margemLucro);
    }
}
