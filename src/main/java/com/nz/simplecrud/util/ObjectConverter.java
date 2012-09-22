package com.nz.simplecrud.util;

import com.nz.simplecrud.entity.Role;
import java.util.HashMap;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("com.nz.util.ObjectConverter")
public class ObjectConverter implements Converter {

    private static HashMap<String, Role> map = new HashMap<String, Role>();
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Role role = map.get(value);
        return role;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Role role = (Role)value;
        map.put(role.getRoleid().toString(), role);
        return role.getRoleid().toString();
    }
}
