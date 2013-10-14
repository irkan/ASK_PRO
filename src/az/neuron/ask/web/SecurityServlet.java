package az.neuron.ask.web;

import az.neuron.ask.Constants;
import az.neuron.ask.business.SecurityBusiness;
import az.neuron.ask.business.SecurityBusinessImpl;
import az.neuron.ask.dao.OracleSecurityDao;
import az.neuron.ask.dao.SecurityDao;
import az.neuron.ask.domain.User;
import az.neuron.ask.domain.security.Grid;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

//import oim.util.Helper;

public class SecurityServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = "";
        String address = "";
        boolean forward = false;
        if (request.getParameter(Constants.ACTION) != null) {
            action = request.getParameter(Constants.ACTION);
        }
        System.out.println("SecurityServlet.processRequest action = " + action);
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute(Constants.USER);
        SecurityDao secDao = new OracleSecurityDao();
        SecurityBusiness secBusiness = new SecurityBusinessImpl(secDao);
        try {
            /*if(action.equalsIgnoreCase(Constants.GET_OPERATION_BUTTONS)){
                *//*String link_id = request.getParameter("linkId");*//*
                long modulId = Long.parseLong(request.getParameter("modulId"));
                Module module = (( Map<Long , Module> ) request.getServletContext().getAttribute(Constants.MODULS)).get(modulId);
                //Module module = user.getModuleById( modulId );
                List<Operation> operationList = new ArrayList<Operation>();
                //operationList = user.getOperationList(modulId);
                if ( module.getModuleParent() == 0 ){
                    user.setCurrentModuleId( module.getId() );
                    operationList = user.getOperationListUnion(modulId);
                    address = "WEB-INF/newJSP/parse/button_parse.jsp";
                }else {
                    operationList = user.getOperationList(modulId);
                    address = "WEB-INF/newJSP/parse/button_parse_level_2.jsp";
                }
                *//*request.setAttribute(Constants.OPERATION_BUTTON_LIST, user.getOperationList(link_id));*//*
                request.setAttribute(Constants.OPERATION_BUTTON_LIST, operationList);

                forward = true;
            }else *//*if(action.equalsIgnoreCase(Constants.GET_PERSON_OPERATIONS)){
                if(request.getParameter("personId") != null){
                    long personId = Long.parseLong(request.getParameter("personId")) ;
                    User userInfo = secBusiness.getUserInfo(*//* 45*//* personId);
                    request.setAttribute(Constants.PERSON_USER_INFO, userInfo);
                    address = "WEB-INF/security/user_info_parse.jsp";
                    forward = true;
                }
            }else if(action.equalsIgnoreCase(Constants.ACTION_GET_USER_GROUP_OPERATIONS)){
                if(request.getParameter("userGroupId") != null){
                    long userGroupId = Long.parseLong(request.getParameter("userGroupId")) ;
                    UserGroup userGroup = secBusiness.getUserGroupOperations(userGroupId);
                    request.setAttribute(Constants.USER_GROUP , userGroup);
                    address = "WEB-INF/security/userGroupParse.jsp";
                    forward = true;
                }
            }else if(action.equalsIgnoreCase(Constants.CHECK_USER_NAME)){
                if( request.getParameter("userName")!=null ){
                    String userName = request.getParameter("userName");
                    long personId = Long.parseLong(request.getParameter("person__id"));
                    //User user = (User) request.getSession().getAttribute(Constants.USER);
                    long status = secBusiness.checkUserName( personId,  userName);
                    request.setAttribute(Constants.CHECK_USER_NAME_STATUS, status);
                    address="WEB-INF/security/userNameStatus.jsp";
                    forward = true;
                }
            }else if(action.equalsIgnoreCase(Constants.ACTION_SECURITY_OPERATION)){
                String messageStr;
                String messageType;
                if(request.getParameter("userId") != null &&
                        request.getParameter("personId") != null &&
                        request.getParameter("userName") != null &&
                        request.getParameter("userPassword") != null &&
                        request.getParameter("userGroupId") != null){
                    String userId = request.getParameter("userId");
                    String personId = request.getParameter("personId");
                    String userName = request.getParameter("userName");
                    String userPassword = request.getParameter("userPassword");
                    String userPrivilagies = request.getParameter("userPrivilagies");
                    long persId = Long.parseLong(personId);
                    long minPasswordLength = 6;
                    if ( *//*true*//*userPassword.length() > minPasswordLength ){
                        long status = secBusiness.checkUserName(persId , userName);
                        if ( *//*true*//*status == 0 ){
                            *//*if (!userPassword.equals("*******")){
                                userPassword = Helper.toMD5(userPassword);
                            }*//*
                            User changedUser = new User();
                            changedUser.setId(Long.parseLong(userId));
                            changedUser.setUserName(userName);
                            changedUser.setPassword(userPassword);
                            changedUser.setUserPrivilagies(userPrivilagies);
                            UserGroup userGroup = new UserGroup();
                            userGroup.setId( Long.parseLong(request.getParameter("userGroupId")) );
                            changedUser.setUserGroup(userGroup);
                            Person person = new Person();
                            person.setId( persId );
                            changedUser.setPerson( person );
                            User loggedUser = (User) session.getAttribute(Constants.USER);
                            if (loggedUser != null){
                                long status1 = secBusiness.securityOperation(loggedUser.getId(),changedUser);
                                if(status1 != -1){
                                    messageStr = "İmtiyazlar yeniləndi .";
                                    messageType = "success";
                                }else {
                                    messageStr = "İmtiyazlar yenilənmədi . ";
                                    messageType = "error";
                                }
                            }else {
                                messageStr = "Heç bir əməliyyat yerinə yetirilmədi ";
                                messageType = "error";
                            }
                        }else {
                            messageStr = userName + " istifadəçi adı artıq mövcuddur !";
                            messageType = "success";
                        }
                    }else {
                        messageStr = "Şifrə minimum " + minPasswordLength + " simvol olmalıdır !";
                        messageType = "error";
                    }
                }else {
                    messageStr = "Heç bir əməliyyat yerinə yetirlmədi zəhmət olmasa bir yenidən yoxlayın ..";
                    messageType = "error";
                }
                Message message = new Message();
                message.setMessage(messageStr);
                message.setMessageType(messageType);
                request.setAttribute(Constants.MESSAGE,message);
                address = "WEB-INF/newJSP/parse/message_parse.jsp";
                forward = true;
            }else if (action.equalsIgnoreCase(Constants.GET_ACTIVE_USER_LIST)){
                Map<String , HttpSession> activeUsers = (Map <String , HttpSession>)request.getServletContext().getAttribute(Constants.ACTIVE_USERS);
                Set keys = activeUsers.keySet();
                List<HttpSession> sessionList = new ArrayList<HttpSession>();
                for (Iterator i = keys.iterator(); i.hasNext();) {
                    String key = (String) i.next();
                    HttpSession session_1 = activeUsers.get(key);
                    sessionList.add(session);
                }
                request.setAttribute(Constants.ACTIVE_USERS , sessionList);
                System.out.println("Active Sessions = " + activeUsers.toString());
                address = "WEB-INF/parse/active_users_parse.jsp";
                forward = true ;
            }else if (action.equalsIgnoreCase(Constants.UPDATE_USER_NAME_PASSWORD)){
                //User user = ( User ) request.getSession().getAttribute(Constants.USER);
                String password = request.getParameter("password");
                String userName = request.getParameter("userName");
                //---
              //  String md5Password = Helper.toMD5( password );
             //   System.out.println(" MD5 = " + md5Password);
                boolean isUpdated = secBusiness.changeUserPassword(user.getId(),password *//*md5Password*//*);
                String messageStr;
                String messageType;
                if(isUpdated){
                    messageStr = "Şifrə dəyişdirildi. Yeni şifrə ilə sistemə daxil ola bilersiniz .";
                    messageType = "success";
                }else {
                    messageStr = "Şifrə dəyişdirilmədi. Zəhmət olmasa yenidən yoxlayın. ";
                    messageType = "error";
                }

                Message message = new Message();
                message.setMessage(messageStr);
                message.setMessageType(messageType);
                request.setAttribute(Constants.MESSAGE,message);
                address = "WEB-INF/parse/message_parse.jsp";
                forward = true;
            }else */if (action.equalsIgnoreCase(Constants.ACTION_GET_SECURITY_GRID)){
                Grid grid = secBusiness.getSecirityGrid(user);
                request.setAttribute(Constants.GRID,grid );
                address = "WEB-INF/parse/security/gridParse.jsp";
                forward = true;
            }/*else if ( action.equalsIgnoreCase(Constants.GET_USER_GROUP_LIST)){
                List<UserGroup> userGroupList = secBusiness.getUserGroupList( user.getUserGroup().getId() , user.getCurrentModuleId() );
                request.setAttribute(Constants.USER_GROUP_LIST , userGroupList);
                address =  "WEB-INF/security/user_group_list_parse.jsp";
                forward = true;
            } *//*else if(action.equalsIgnoreCase( Constants.ACTION_GET_MODULE_GRID)){
                Module module = new Module();
                //User user = (User) request.getSession().getAttribute(Constants.USER);
                if (user.getModuleList() != null) {
                    for (Module mod : user.getModuleList()) {
                        if (mod.getLinkId().equalsIgnoreCase(request.getParameter("linkId"))) {
                            module = mod;
                            break;
                        }
                    }
                }
                request.removeAttribute(Constants.MODULE);
                request.setAttribute(Constants.MODULE, module);
                address = "WEB-INF/parse/grid.jsp";
                forward = true;

            }*/
        } catch (Exception ex) {
            ex.printStackTrace();
            forward = true;
            address = Constants.PAGE_ERROR;
        }

        if (forward) {
            RequestDispatcher dispatcher = request.getRequestDispatcher(address);
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect(address);
        }
    }



}
