package entity;

import entity.Role;
import entity.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2025-04-07T13:37:52")
@StaticMetamodel(UserRoles.class)
public class UserRoles_ { 

    public static volatile SingularAttribute<UserRoles, Role> role;
    public static volatile SingularAttribute<UserRoles, Long> id;
    public static volatile SingularAttribute<UserRoles, User> user;

}