package Exception_global;

import lombok.Data;
@Data
public class CategorieNotFoundException extends runtimeException {
    public String Message;
}
