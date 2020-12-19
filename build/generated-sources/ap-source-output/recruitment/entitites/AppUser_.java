package recruitment.entitites;

import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-12-17T14:53:49")
@StaticMetamodel(AppUser.class)
public class AppUser_ { 

    public static volatile SingularAttribute<AppUser, String> password;
    public static volatile SingularAttribute<AppUser, String> companyName;
    public static volatile SingularAttribute<AppUser, String> emailOrPhone;
    public static volatile SingularAttribute<AppUser, String> fullName;
    public static volatile SingularAttribute<AppUser, Date> lastLoginDate;
    public static volatile SingularAttribute<AppUser, String> userName;
    public static volatile SingularAttribute<AppUser, BigInteger> userId;

}