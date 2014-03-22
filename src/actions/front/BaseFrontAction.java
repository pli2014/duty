/**
 * 
 */
package actions.front;

import webapps.WebappsConstants;
import actions.BaseTableAction;
import bl.beans.VolunteerBean;
import bl.common.TableBusinessInterface;

/**
 * @author gudong
 * @since $Date:2014-03-22$
 * 
 */
public abstract class BaseFrontAction<B extends TableBusinessInterface> extends
		BaseTableAction<B> {

	public VolunteerBean getLoginedVolunteer() {
		return (VolunteerBean) getSession().getAttribute(
				WebappsConstants.LOGIN_USER_SESSION_ID);
	}
}
