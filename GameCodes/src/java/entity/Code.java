/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author ProBebra
 */
@Entity
public class Code implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; //Id
    private String code;//Сам код
    private String purchase;//Куплен или нет
    private String salt;//Для шифровки вопроса
    @ManyToOne
    @JoinColumn(name = "product_id")  // предполагается, что связь идет через поле id
    public Product product ;

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getCode(){
        return code;
    }
    public void setCode(String code){
        this.code = code;
    }
    public Product  getGame(){
        return product ;
        }
    public void setGame(Product  product ){
        this.product  = product ;
    }
    
    public String getPurchase(){
        return purchase;
    }
    public void setPurchase(String purchase){
        this.purchase=purchase;
    }
    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
    
    @Override
    public String toString() {
        return "Code{" + "id=" + id + 
                ", code=" + code + 
                ", product =" + product  + 
                ", purchase=" + purchase +
                ", salt=" + salt +'}';
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.id);
        hash = 13 * hash + Objects.hashCode(this.code);
        hash = 13 * hash + Objects.hashCode(this.product );
        hash = 13 * hash + Objects.hashCode(this.purchase);
        hash = 13 * hash + Objects.hashCode(this.salt);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Code)) {
            return false;
        }
        Code other = (Code) object;
         if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        if (!Objects.equals(this.product , other.product )) {
            return false;
        }
        if (!Objects.equals(this.purchase, other.purchase)) {
            return false;
        }
        if (!Objects.equals(this.salt, other.salt)) {
            return false;
        }
        return true;
    }
    
}
