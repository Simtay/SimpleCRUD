/**
This file is part of javaee-patterns.

javaee-patterns is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 2 of the License, or
(at your option) any later version.

javaee-patterns is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.opensource.org/licenses/gpl-2.0.php>.

* Copyright (c) 22. June 2009 Adam Bien, blog.adam-bien.com
* http://press.adam-bien.com
*/
package com.nz.simplecrud.service;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author adam-bien.com
 */
public class QueryParameter {
    
    private Map<String,Object> parameters = null;
    
    private QueryParameter(String name,Object value){
        this.parameters = new HashMap<String,Object>();
        this.parameters.put(name, value);
    }
    public static QueryParameter with(String name,Object value){
        return new QueryParameter(name, value);
    }
    public QueryParameter and(String name,Object value){
        this.parameters.put(name, value);
        return this;
    }
    public Map<String,Object> parameters(){
        return this.parameters;
    }
}
