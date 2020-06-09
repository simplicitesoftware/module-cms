package com.simplicite.extobjects.CMS;

import java.util.*;
import com.simplicite.util.*;
import com.simplicite.util.tools.*;

import org.json.JSONObject;
import com.simplicite.webapp.services.RESTServiceExternalObject;
/**
 * External object CmsStorage
 */
public class CmsStorage extends RESTServiceExternalObject {
    private static final long serialVersionUID = 1L;

    @Override
    public Object get(Parameters params){
    	String json = getGrant().simpleQuery("select cms_elt_content from cms_element where cms_elt_name='"+params.getParameter("cmsEltName", "")+"'");
    	return json!=null ? json : error(400, "No corresponding content found");
    }
    
    @Override
    public Object post(Parameters params){
    	String id = getGrant().simpleQuery("select row_id from cms_element where cms_elt_name='"+params.getParameter("cmsEltName", "")+"'");
    	ObjectDB p = getGrant().getTmpObject("CmsElement");
    	synchronized(p){
    		p.select(id);
			p.setFieldValue("cmsEltContent", params.getJSONObject());
			p.save();
    	}
        return "";
    }
}
