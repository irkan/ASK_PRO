package az.neuron.ask.web; /**
 * Created with IntelliJ IDEA.
 * User: IRKAN
 * Date: 10/9/13
 * Time: 10:39 PM
 * To change this template use File | Settings | File Templates.
 */

import az.neuron.ask.Constants;
import az.neuron.ask.business.CommonBusiness;
import az.neuron.ask.business.CommonBusinessImpl;
import az.neuron.ask.business.SecurityBusiness;
import az.neuron.ask.business.SecurityBusinessImpl;
import az.neuron.ask.dao.CommonDao;
import az.neuron.ask.dao.OracleCommonDao;
import az.neuron.ask.dao.OracleSecurityDao;
import az.neuron.ask.dao.SecurityDao;
import az.neuron.ask.domain.Dictionary;
import az.neuron.ask.domain.Modul;
import az.neuron.ask.domain.Operation;
import az.neuron.ask.domain.User;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApplicationListener implements ServletContextListener,
        HttpSessionListener, HttpSessionAttributeListener {

    // Public constructor is required by servlet spec
    public ApplicationListener() {
    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {
        SecurityDao secDao = new OracleSecurityDao();
        SecurityBusiness secBusiness = new SecurityBusinessImpl(secDao);
        CommonDao commonDao = new OracleCommonDao();
        CommonBusiness commonBusiness = new CommonBusinessImpl(commonDao);
        Map<String , HttpSession> activeUsers = new HashMap<String, HttpSession>();
        sce.getServletContext().setAttribute(Constants.ACTIVE_USERS , activeUsers);
        try {
            Map<String, List<Operation>>  modulOperationList = secBusiness.getModulOperationMap();
            sce.getServletContext().setAttribute(Constants.MODUL_OPERATIONS ,modulOperationList);
            List< Modul > modulList = secBusiness.getModulList();
            sce.getServletContext().setAttribute(Constants.MODUL_LIST, modulList);
            List<Operation> operationList = secBusiness.getOperationList();
            sce.getServletContext().setAttribute(Constants.OPERATION_LIST , operationList);
            Map<Long, List<Dictionary>> dictionaryMap = commonBusiness.getDictionaryMap();
            sce.getServletContext().setAttribute(Constants.DICTIONARY_MAP, dictionaryMap);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void contextDestroyed(ServletContextEvent sce) {
      /* This method is invoked when the Servlet Context 
         (the Web application) is undeployed or 
         Application Server shuts down.
      */
    }

    // -------------------------------------------------------
    // HttpSessionListener implementation
    // -------------------------------------------------------
    public void sessionCreated(HttpSessionEvent se) {
      /* Session is created. */
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        User user = (User)se.getSession().getAttribute(Constants.USER);
        System.out.println("Session is destroyed - " + se.getSession().getId() + " - " + user.getUserName() + " - " + user.getFullName());
        try {
            Map<String, HttpSession> activeUsers = (Map<String, HttpSession>)se.getSession().getServletContext().getAttribute(Constants.ACTIVE_USERS);
            activeUsers.remove(se.getSession().getId());
            se.getSession().removeAttribute(Constants.USER);
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    // -------------------------------------------------------
    // HttpSessionAttributeListener implementation
    // -------------------------------------------------------

    public void attributeAdded(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute 
         is added to a session.
      */
    }

    public void attributeRemoved(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute
         is removed from a session.
      */
    }

    public void attributeReplaced(HttpSessionBindingEvent sbe) {
      /* This method is invoked when an attibute
         is replaced in a session.
      */
    }
}
