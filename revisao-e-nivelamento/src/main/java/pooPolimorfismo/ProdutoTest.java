package pooPolimorfismo;
/*

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProdutoTest {
    
    static Produto produto;
        
    
    @BeforeAll
    static public void prepare(){
        ProdutoNaoPerecivel produtoNaoPerecivel = new ProdutoNaoPerecivel("Produto teste", 100, 0.1);
    }
    
    @Test
    public void calculaPrecoCorretamente(){
        assertEquals(110.0, produtoNaoPerecivel.valorVenda(), 0.01);
    }

    @Test
    public void stringComDescricaoEValor(){
        String desc = produtoNaoPerecivel.toString();
        assertTrue(desc.contains("Produto teste") && desc.contains("R$ 110,00"));
    }

    @Test
    public void naoCriaProdutoComPrecoNegativo(){
        assertThrows(IllegalArgumentException.class, () -> new produtoNaoPerecivel("teste", -5, 0.5));
    }
    
    @Test
    public void naoCriaProdutoComMargemNegativa(){
        assertThrows(IllegalArgumentException.class, () -> new produtoNaoPerecivel("teste", 5, -1));
    }
    
}
*/