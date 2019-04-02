package soa.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.util.HashMap;
import java.util.Map;

@FacesConverter("soa.soa.web.converters.PricesMapConverter")
public class PricesMapConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return value.split(",");
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        HashMap<String, Integer> pricesByGroup = (HashMap<String, Integer>) value;
        StringBuilder displayBuilder = new StringBuilder();
        for (Map.Entry<String, Integer> priceByGroup : pricesByGroup.entrySet()) {
            displayBuilder.append(priceByGroup.getKey());
            displayBuilder.append(" - ");
            displayBuilder.append(priceByGroup.getValue());
            displayBuilder.append(",");
        }
        return displayBuilder.toString();
    }
}
