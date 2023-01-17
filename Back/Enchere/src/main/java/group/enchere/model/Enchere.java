package group.enchere.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NamedNativeQuery(
        name="getAllNotDone",
        query = "SELECT * FROM v_enchere_not_done"
        ,resultClass = Enchere.class)
public class Enchere extends EnchereSuperClass{

}
