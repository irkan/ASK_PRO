package az.neuron.ask.web;

import az.neuron.ask.Constants;
import az.neuron.ask.business.SecurityBusiness;
import az.neuron.ask.business.SecurityBusinessImpl;
import az.neuron.ask.dao.OracleSecurityDao;
import az.neuron.ask.dao.SecurityDao;
import az.neuron.ask.domain.Image;
import az.neuron.ask.domain.User;
import az.neuron.ask.util.DateHelper;
import az.neuron.ask.util.FtpUtility;
import az.neuron.ask.util.Helper;
import az.neuron.ask.util.MailUtility;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Logger;


public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = Constants.ACTION_LOGIN;
        String address = Constants.PAGE_INDEX;
        boolean forward = false;

        if (request.getParameter(Constants.ACTION) != null) {
            action = request.getParameter("action");
        }

        SecurityDao secDao = new OracleSecurityDao();
        SecurityBusiness secBusiness = new SecurityBusinessImpl(secDao);

        try {
            String projectURL = request.getScheme().toString() + "//" + request.getServerName().toString() + ":" + String.valueOf(request.getServerPort()) + request.getServletContext().getContextPath().toString();
            HttpSession session =  request.getSession(true);
            if (action.equals(Constants.ACTION_LOGIN)) {
                boolean isOnline = false;
                String userName = request.getParameter("username");
                String password = request.getParameter("password");
                if(password.length() > 4){
                    String md5Password = Helper.toMD5(password);
                    System.out.println(md5Password);
                    try{
                        User user = secBusiness.getUser(userName, md5Password);
                        if(user.getId()!=-1){
                            String gender = "bəy";
                            if(user.getSex() == 259){
                                gender = "xanım";
                            }
                            Map<String, HttpSession> activeUsers = (Map<String, HttpSession>)request.getServletContext().getAttribute(Constants.ACTIVE_USERS);
                            Iterator iterator = activeUsers.entrySet().iterator();
                            while (iterator.hasNext()) {
                                Map.Entry pairs = (Map.Entry)iterator.next();
                                HttpSession session1 = (HttpSession) pairs.getValue();
                                User user1 = (User) session1.getAttribute(Constants.USER);
                                if(user1.getId()==user.getId()){
                                    isOnline = true;
                                }
                            }
                            if(!isOnline){
                                if(user.getBlocked()!=1){
//                            session.setMaxInactiveInterval(30);
                                    /*String realPath = request.getServletContext().getRealPath(Constants.DOWNLOAD_DIR + File.separator + "EMPLOYEE");
                                    File file = new File(realPath, String.valueOf(user.getPersonId())+".jpg");
                                    String imagePath = "images/profile-picture.jpg";
                                    if(!file.exists()){
                                        Image image = new Image();
                                        image.setPersonType("EMPLOYEE");
                                        image.setFileName(String.valueOf(user.getPersonId()));
                                        image.setRealPath(realPath);
                                        try{
                                            imagePath = FtpUtility.downloadUserImage(image);
                                        } catch(Exception e){
                                            imagePath = "images/profile-picture.jpg";
                                        }

                                    } else {
                                        imagePath =  "download/EMPLOYEE/" + String.valueOf(user.getPersonId())+".jpg";
                                    }
                                    user.setUserImagePath(imagePath);
                                    user.setProjectURL(projectURL); */
                                    session.setAttribute(Constants.USER, user);
                                    if(user.getTempPassword()==1){
                                        session.setAttribute(Constants.IS_LOGIN, true);
                                        address = Constants.PAGE_LOGIN;
                                        forward = false;
                                    } else if(user.getTempPassword()==0){
                                        Date newDate = new Date();
                                        String logDate = DateHelper.DateToStrWithTime(newDate);
                                        user.setLogDate(logDate);
                                        user.setLoginDate(new Date());
                                        user.setRemoteAddress(request.getRemoteAddr());
                                        user.setLogHistoryStatus("login");
                                        user.setUserHistoryId(0);

                                        activeUsers.put(session.getId(), session);
                                        request.getServletContext().setAttribute(Constants.ACTIVE_USERS, activeUsers);
                                        long userHistoryId = secBusiness.userLogHistoryOperation(user);
                                        user.setUserHistoryId(userHistoryId);
                                        address = Constants.PAGE_INDEX ;
                                        forward = false;
                                    }
                                } else {
                                    request.setAttribute(Constants.ERROR_MESSAGE, "Hörmətli " + user.getFirstName() + " " + gender + " sizin sistemə girişiniz əngəllənmişdir!<br/>Zəhmət olmasa adminstratorla əlaqə saxlayın.");
                                    address = Constants.PAGE_LOGIN;
                                    forward = true;
                                }
                            } else {
                                request.setAttribute(Constants.ERROR_MESSAGE, "'" + userName  + "' adlı istifadəçi artıq sistemdə mövcuddur!");
                                address = Constants.PAGE_LOGIN;
                                forward = true;
                            }
                        } else {
                            request.setAttribute(Constants.ERROR_MESSAGE, "İstifadəçi adı və ya şifrə yalnışdır!");
                            address = Constants.PAGE_LOGIN;
                            forward = true;
                        }
                        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
                        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
                        response.setDateHeader("Expires", 0); // Proxies.
                        response.addHeader("session_timeout", "1");
                    } catch( Exception e ){
                        if(String.valueOf(e.getMessage()).trim().equalsIgnoreCase("The Network Adapter could not establish the connection")){
                            request.setAttribute(Constants.ERROR_MESSAGE, "Şəbəkədə problem var. Zəhmət olmasa şəbəkənizi yoxlayın.");
                        } else {
                            request.setAttribute(Constants.ERROR_MESSAGE, "Xəta baş verdi!");
                        }
                        address = Constants.PAGE_LOGIN;
                        forward = true;
                    }
                } else {
                    request.setAttribute(Constants.ERROR_MESSAGE, "Daxil etdiyiniz şifrə ən azı 5 simvol olmalıdır!");
                    address = Constants.PAGE_LOGIN;
                    forward = true;
                }
            } else if (action.equalsIgnoreCase(Constants.ACTION_LOGOUT)){
                User user = (User) session.getAttribute(Constants.USER);
                Date newDate = new Date();
                String logDate = DateHelper.DateToStrWithTime(newDate);
                user.setLogDate(logDate);
                user.setLogHistoryStatus("logout");
                long logHistoryId = secBusiness.userLogHistoryOperation(user);
                if(logHistoryId!=-1){
                    session.invalidate();
                    address = "login.jsp";
                    forward = false;
                    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
                    response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
                    response.setDateHeader("Expires", 0); // Proxies.
                    response.addHeader("session_timeout", "1");
                }
            } else if(action.equalsIgnoreCase(Constants.ACTION_CHANGE_PASSWORD_DIALOG_STATUS)){
                session.setAttribute(Constants.IS_LOGIN, false);
                address = "/WEB-INF/jsp/empty.jsp";
                forward = true;
            } else if(action.equalsIgnoreCase(Constants.ACTION_GET_FORGOT_PASSWORD)){
                address="/WEB-INF/Security/forgotPassword.jsp";
                forward = true;
            } else if(action.equalsIgnoreCase(Constants.ACTION_GET_USER_FOR_EMAIL)){
                if(request.getParameter("email")!=null){
                    String email = request.getParameter("email");
                    User user = secBusiness.getUserForEmail(email);
                    if(user.getId()!=0){
                        String realPath = request.getServletContext().getRealPath(Constants.DOWNLOAD_DIR + File.separator + "EMPLOYEE");
                        File file = new File(realPath, String.valueOf(user.getPersonId())+".jpg");
                        String imagePath = "images/profile-picture.jpg";
                        if(!file.exists()){
                            Image image = new Image();
                            image.setPersonType("EMPLOYEE");
                            image.setFileName(String.valueOf(user.getPersonId()));
                            image.setRealPath(realPath);
                            imagePath = FtpUtility.downloadUserImage(image);
                        } else {
                            imagePath =  "upload/EMPLOYEE/" + String.valueOf(user.getPersonId())+".jpg";
                        }
                        user.setUserImagePath(imagePath);
                    }
                    request.setAttribute(Constants.USER_FOR_EMAIL, user);
                    address = "/WEB-INF/Security/userExistValid.jsp";
                    forward = true;
                }
            } else if(action.equalsIgnoreCase(Constants.ACTION_SEND_EMAIL_RANDOM_TEXT)){
                if(request.getParameter("email")!=null){
                    User user = new User();
                    user.setUserEmail(request.getParameter("email"));
                    user.setSex(258);
                    user.setFirstName("Demo");
                    user.setPassword("Demo");
                    user.setProjectURL(projectURL);
                    user.setMailSendType("forgotPassword");
                    MailUtility mailUtility = new MailUtility();
                    String randomText = mailUtility.randomTextGenerate();
                    user.setSuccessRandomText(randomText);
                    long status = secBusiness.updateUserRandomText(1, randomText);
                    if(status!=-1){
                        mailUtility.sendHtmlMail(user);
                    }
                    address = "/WEB-INF/jsp/empty.jsp";
                    forward = true;
                }
            } else if(action.equalsIgnoreCase(Constants.ACTION_SUCCESS_FORGOT_PASSWORD)){
                if(request.getParameter("randomText")!=null &&
                        request.getParameter("email")!=null){
                    long status = -1;
                    String randomText = request.getParameter("randomText");
                    String email = request.getParameter("email");
                    User user = secBusiness.getUserForEmail(email);
                    if(user.getSuccessRandomText().equals(randomText)){
                        status = 1;
                    }
                    request.setAttribute(Constants.STATUS,status);
                    address="/WEB-INF/parse/status.jsp";
                    forward = true;
                }
            } else if(action.equalsIgnoreCase(Constants.ACTION_CHANGE_PASSWORD_FOR_FORGOT)){
                if(request.getParameter("userId")!=null &&
                        request.getParameter("password")!=null){
                    boolean success = false;
                    long userId = Long.parseLong(request.getParameter("userId"));
                    String password = request.getParameter("password");
                    String md5Password = Helper.toMD5(password);
                    success = secBusiness.changeUserPassword(userId, md5Password);
                    request.setAttribute(Constants.ACCAUNT_UPDATED, success);
                    address="/WEB-INF/parse/accauntUpdated.jsp";
                    forward = true;
                }

            }
        } catch (Exception ex) {
            forward = true;
            address = Constants.PAGE_ERROR;
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        if (forward) {
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect(address);
        }
    }
}
