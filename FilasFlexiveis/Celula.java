public class Celula {
    int elemento;
    Celula prox;
    public Celula(){
        this(0);    
    }
    public Celula(int x){
        this.elemento = x;
        this.prox = null;
    }
}
