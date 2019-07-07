package Pojo;

import java.util.Date;

public class ControlaPojo {
    private int U_ID;
    private int P_ID;
    private Date C_DATA;
    private Date C_HORA;
    private String C_ACAO;
    
    public int getU_ID() {
        return U_ID;
    }

    public void setU_ID(int U_ID) {
        this.U_ID = U_ID;
    }

    public int getP_ID() {
        return P_ID;
    }

    public void setP_ID(int P_ID) {
        this.P_ID = P_ID;
    }

    public Date getC_DATA() {
        return C_DATA;
    }

    public void setC_DATA(Date C_DATA) {
        this.C_DATA = C_DATA;
    }

    public Date getC_HORA() {
        return C_HORA;
    }

    public void setC_HORA(Date C_HORA) {
        this.C_HORA = C_HORA;
    }

    public String getC_ACAO() {
        return C_ACAO;
    }

    public void setC_ACAO(String C_ACAO) {
        this.C_ACAO = C_ACAO;
    }      
}
