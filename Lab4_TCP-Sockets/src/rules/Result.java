package rules;

import java.io.Serializable;

public interface Result extends Serializable {
    boolean getStatus();
    Object getResult();
}
