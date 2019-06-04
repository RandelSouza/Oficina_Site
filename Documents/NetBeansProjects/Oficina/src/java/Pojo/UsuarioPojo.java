/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pojo;

/**
 *
 * @author Davi
 */
public class UsuarioPojo {
    private String U_LOGIN;
    private String U_SENHA;
    private Integer U_UID;
    private Integer F_FID;
    
    /**
     * @return the U_UID
     */
    public Integer getU_UID() {
        return U_UID;
    }

    /**
     * @param U_UID the U_UID to set
     */
    public void setU_UID(Integer U_UID) {
        this.U_UID = U_UID;
    }

    /**
     * @return the F_FID
     */
    public Integer getF_FID() {
        return F_FID;
    }

    /**
     * @param F_FID the F_FID to set
     */
    public void setF_FID(Integer F_FID) {
        this.F_FID = F_FID;
    }
  

    /**
     * @return the U_LOGIN
     */
    public String getU_LOGIN() {
        return U_LOGIN;
    }

    /**
     * @param U_LOGIN the U_LOGIN to set
     */
    public void setU_LOGIN(String U_LOGIN) {
        this.U_LOGIN = U_LOGIN;
    }

    /**
     * @return the U_SENHA
     */
    public String getU_SENHA() {
        return U_SENHA;
    }

    /**
     * @param U_SENHA the U_SENHA to set
     */
    public void setU_SENHA(String U_SENHA) {
        this.U_SENHA = U_SENHA;
    }
}