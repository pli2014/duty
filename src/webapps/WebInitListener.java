package webapps;

import java.io.FileInputStream;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import actions.SystemSettingAction;
import bl.mongobus.VolunteerBusiness;
import common.Constants;
import util.ServerContext;

import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

import dao.MongoDBConnectionFactory;

public class WebInitListener implements ServletContextListener {
  protected static Logger LOG = LoggerFactory.getLogger(WebInitListener.class);

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    LOG.info("init dynamic form war");

    LOG.info("init server.properties file");
    ServerContext.init(WebInitListener.class.getResourceAsStream("/server.properties"));

    LOG.info("init /etc/db.properties file");
    try {
      ServerContext.init(new FileInputStream("/etc/db.properties"));
    } catch (Exception e) {
      LOG.error("Reading file has some exception #0", e.getMessage());
    }
    LOG.info("init MongoDB");
    MongoDBConnectionFactory.initDb();

    // init Global Setting
    Object global = SystemSettingAction.init();
    sce.getServletContext().setAttribute(Constants.GLOBALSETTING, global);
    VolunteerBusiness volunteerBusiness = new VolunteerBusiness();
    sce.getServletContext().setAttribute(WebappsConstants.UNVERIFIED_VOLUNTEER_KEY, volunteerBusiness.getUnVerifiedVolunteers());
    sce.getServletContext().setAttribute(WebappsConstants.UNINTERVIEWED_VOLUNTEER_KEY, volunteerBusiness.getUnInterviewedVolunteers());
    sce.getServletContext().setAttribute("rootPath", sce.getServletContext().getContextPath());
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    LOG.info("destroy dynamic form war");
    LOG.info("disconnect conection of MongoDB");
    MongoDBConnectionFactory.destroy();
  }

}
