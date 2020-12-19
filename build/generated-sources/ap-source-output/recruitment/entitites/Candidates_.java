package recruitment.entitites;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-12-17T14:53:49")
@StaticMetamodel(Candidates.class)
public class Candidates_ { 

    public static volatile SingularAttribute<Candidates, Serializable> resume;
    public static volatile SingularAttribute<Candidates, String> mobilePhoneNumber;
    public static volatile SingularAttribute<Candidates, Date> recordTime;
    public static volatile SingularAttribute<Candidates, Short> mobileCountryCode;
    public static volatile SingularAttribute<Candidates, String> fullName;
    public static volatile SingularAttribute<Candidates, BigInteger> id;
    public static volatile SingularAttribute<Candidates, String> explanation;
    public static volatile SingularAttribute<Candidates, String> emailAddres;

}