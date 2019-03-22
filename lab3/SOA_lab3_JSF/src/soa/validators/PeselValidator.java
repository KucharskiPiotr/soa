package soa.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("PeselValidator")
public class PeselValidator implements Validator {

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        String pesel = o.toString();
        if (pesel.startsWith("-")) {
            FacesMessage msg = new FacesMessage("Pesel is negative");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext.getCurrentInstance().addMessage("FacesContextError", msg);
            throw new ValidatorException(msg);
        }
    }
}
