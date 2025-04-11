public class Fila {
    public Celula primeiro, ultimo;
    public Fila(){
        primeiro = new Celula();
        ultimo = primeiro;
    }
    public void inserir(int x){ //enfileirar(int x)
        ultimo.prox = new Celula(x);
        ultimo = ultimo.prox;
    }

    public int remover(){
        if(primeiro != ultimo){
            Celula tmp = primeiro.prox;
            primeiro.prox = null;
            primeiro = tmp;
            tmp = null;
            
        }
        return primeiro.elemento;
    }

    public void imprimir(){
        Celula tmp = primeiro;
        while(tmp != null){
            System.out.print(tmp.elemento + "");
            tmp = tmp.prox;
        }
    }

    public int maiorElemento(int elemento){

        

        return 0;
    }

    public static void main(String[] args) {
        Fila f = new Fila();
        f.inserir(8);
        f.inserir(4);
        f.inserir(4);
        f.inserir(5);
        f.inserir(9);
        f.inserir(3);

        f.imprimir();
        System.out.println();

        f.remover();

        f.imprimir();
        System.out.println();

        f.remover();
        f.remover();
        f.remover();

        f.imprimir();
        System.out.println();
    }
}
