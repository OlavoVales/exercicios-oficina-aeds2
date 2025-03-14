package pooPolimorfismo;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.text.NumberFormat;

/**
 *
 * @author 1075702
 */
public abstract class Produto {
    private static final double MARGEM_PADRAO = 0.2;
    private String descricao;
    protected double precoCusto;
    protected double margemLucro;
    
    private void init(String descricao, double precoCusto, double margemLucro){
        if(descricao.length()<3 ||precoCusto<=0||margemLucro<=0)
            throw new IllegalArgumentException("Valores inválidos para o produto");
        this.descricao = descricao;
        this.precoCusto = precoCusto;
        this.margemLucro = margemLucro;
    }
    
    protected Produto(String descricao, double precoCusto, double MargemLucro){
        init(descricao, precoCusto, margemLucro);
    }
    
    protected Produto(String descricao, double precoCusto){
        init(descricao, precoCusto, MARGEM_PADRAO);
    }
    
    public abstract double valorVenda();
    
    public String toString(){
        NumberFormat moeda = NumberFormat.getCurrencyInstance();
        
        return String.format("NOME: %s: %s", descricao, moeda.format(valorVenda()));
    }
}
