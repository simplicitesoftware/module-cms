package com.simplicite.extobjects.CMS;

import java.util.*;
import com.simplicite.util.*;
import com.simplicite.util.tools.*;

/**
 * External object CmsEditor
 */
public class CmsEditor extends ExternalObject {
	private static final long serialVersionUID = 1L;

	@Override
	public Object display(Parameters params) {
		setDecoration(false);
		try {
			String cmsEltName = params.getParameter("cmsEltName");
			if(cmsEltName==null) throw new Exception("No page name.");
			setResources(true);
			AppLog.info(getClass(), "====================", getResourceURL(Resource.TYPE_OTHER, "FONT_TTF"), getGrant());
			AppLog.info(getClass(), "====================", HTMLTool.getResourceURL(getGrant(), Resource.TYPE_OTHER, "GRAPE_FONT_TTF", ""), getGrant());
			return javascript(getName() + ".render('"+cmsEltName+"');");
		} catch (Exception e) {
			setResources(false);
			AppLog.error(getClass(), "display", null, e, getGrant());
			return e.getMessage();
		}
	}
}
