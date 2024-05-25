package elisa_daria.be_mood_watch.exceptions;

import lombok.Getter;
import org.springframework.validation.ObjectError;

import java.util.List;

@Getter
public class BadRequestEx extends RuntimeException {
    private List<ObjectError> errorsList;
    public BadRequestEx(String msg){super(msg);}

    public BadRequestEx(List<ObjectError>errorsList)
    {
        super("Validation problems in payload");
        this.errorsList=errorsList;
    }
}
