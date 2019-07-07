package Pojo;

public class PecaPojo {   
    private int P_ID;
    private String P_NOME;
    private int P_UNIDADE;
    private double P_PRECO;
    private boolean P_ESTADO = false;
    private double P_VALOR_TOTAL;
    
    public double getP_VALOR_TOTAL() {
        return P_VALOR_TOTAL;
    }

    public void setP_VALOR_TOTAL(double P_VALOR_TOTAL) {
        this.P_VALOR_TOTAL = P_VALOR_TOTAL;
    }
        
    public boolean isP_ESTADO() {
        return P_ESTADO;
    }

    public void setP_ESTADO(boolean P_ESTADO) {
        this.P_ESTADO = P_ESTADO;
    }
    
    public int getP_ID() {
        return P_ID;
    }

    public void setP_ID(int P_ID) {
        this.P_ID = P_ID;
    }

    public String getP_NOME() {
        return P_NOME;
    }

    public void setP_NOME(String P_NOME) {
        this.P_NOME = P_NOME;
    }

    public int getP_UNIDADE() {
        return P_UNIDADE;
    }

    public void setP_UNIDADE(int P_UNIDADE) {
        this.P_UNIDADE = P_UNIDADE;
    }

    public double getP_PRECO() {
        return P_PRECO;
    }

    public void setP_PRECO(double P_PRECO) {
        this.P_PRECO = P_PRECO;
    }   
}
