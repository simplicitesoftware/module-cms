package com.simplicite.extobjects.CMS;

import java.util.*;
import com.simplicite.util.*;
import com.simplicite.util.tools.*;
import org.json.JSONObject;

/**
 * External object CmsViewer
 */
public class CmsViewer extends ExternalObject {
	private static final long serialVersionUID = 1L;

	/**
	 * Display method
	 * @param params Request parameters
	 */
	@Override
	public Object display(Parameters params) {
		setDecoration(true);
		try {
			String cmsEltName = params.getParameter("cmsEltName");
			if(cmsEltName==null) throw new Exception("No page name.");
			
			String json = getGrant().simpleQuery("select cms_elt_content from cms_element where cms_elt_name='"+params.getParameter("cmsEltName", "")+"'");
			JSONObject rslt = new JSONObject(json);
	    	
			return rslt.getString("gjs-html")+HTMLTool.cssBlock(rslt.getString("gjs-css"));
		} catch (Exception e) {
			AppLog.error(getClass(), "display", null, e, getGrant());
			return e.getMessage();
		}
	}
}
