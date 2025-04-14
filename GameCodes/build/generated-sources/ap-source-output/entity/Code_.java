package entity;

import entity.Product;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2025-04-14T08:33:02")
@StaticMetamodel(Code.class)
public class Code_ { 

    public static volatile SingularAttribute<Code, Product> game;
    public static volatile SingularAttribute<Code, String> code;
    public static volatile SingularAttribute<Code, String> salt;
    public static volatile SingularAttribute<Code, String> purchase;
    public static volatile SingularAttribute<Code, Long> id;

}