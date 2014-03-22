/**
 * 
 */
package actions.backend;

import webapps.WebappsConstants;
import actions.BaseTableAction;
import bl.beans.BackendUserBean;
import bl.common.TableBusinessInterface;

/**
 * @author gudong
 * @since $Date:2014-03-22$
 * 
 */
public abstract class BaseBackendAction<B extends TableBusinessInterface>
		extends BaseTableAction<B> {

	public BackendUserBean getLoginedVolunteer() {
		return (BackendUserBean) getSession().getAttribute(
				WebappsConstants.LOGIN_BACKEND_USER_SESSION_ID);
	}
}
