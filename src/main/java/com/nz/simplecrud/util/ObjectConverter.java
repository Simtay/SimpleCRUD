package com.nz.simplecrud.util;

import com.nz.simplecrud.entity.Role;
import java.util.HashMap;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * Object converting utility is to convert between String and the desired 
 * Object type.
 * JSF cannot convert the given String value to the Object type, to be able to 
 * bind the selected Object to the list we will have to override getAsObject 
 * and getAsString functions.
 * Usage:
 * <f:converter converterId="com.nz.util.ObjectConverter" />
 * TODO: Make it generic
 * @author Emre Simtay <emre@simtay.com>
 */
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
        map.put(role.getId().toString(), role);
        return role.getId().toString();
    }
}
