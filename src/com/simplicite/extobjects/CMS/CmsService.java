package com.simplicite.extobjects.CMS;

import java.util.*;
import com.simplicite.util.*;
import com.simplicite.util.tools.*;
import org.json.JSONObject;
import com.simplicite.webapp.services.RESTServiceExternalObject;

/**
 * External object CmsService
 */
public class CmsService extends RESTServiceExternalObject {
	private static final long serialVersionUID = 1L;

    @Override
    public Object get(Parameters params){
    	String service = params.getParameter("service", "html");
    	String cmsEltContent = getGrant().simpleQuery("select cms_elt_content from cms_element where cms_elt_name='"+params.getParameter("element", "")+"'");
    	
    	if(Tool.isEmpty(cmsEltContent))
    		return error(400, "No corresponding content found");
    	else if("grape".equals(service))
    		return cmsEltContent;
    	else if("html".equals(service) || "css".equals(service)){
    		JSONObject json = new JSONObject(cmsEltContent);
    		return json.getString(service);
    	}
    	else 
    		return error(400, "Service not known");
    }
    
    @Override
    public Object post(Parameters params){
    	ObjectDB p = getGrant().getTmpObject("CmsElement");
    	synchronized(p){
    		p.select(getIdFromElementName(params.getParameter("element", "")));
			p.setFieldValue("cmsEltContent", params.getJSONObject());
			p.save();
    	}
        return "";
    }
    
    private String getIdFromElementName(String name){
    	return getGrant().simpleQuery("select row_id from cms_element where cms_elt_name='"+name+"'");
    }
}
