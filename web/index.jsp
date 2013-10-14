<%@ page import="az.neuron.ask.Constants" %>
<%@ page import="az.neuron.ask.domain.User" %>
<%@ page import="az.neuron.ask.domain.Modul" %>
<%@ page import="az.neuron.ask.domain.Operation" %>
<%--
  Created by IntelliJ IDEA.
  User: IRKAN
  Date: 10/9/13
  Time: 7:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge, chrome=1"/>
    <meta name="description" content="JavaScript desktop environment built with jQuery."/>
    <title>Welcome</title>
    <!--[if lt IE 7]>
    <script>
        window.top.location = 'http://desktop.sonspring.com/ie.html';
    </script>

    <script src="lib/jquery-ui-1.9.2.custom/js/jquery-1.8.3.js"></script>
    <script src="lib/jquery-ui-1.9.2.custom/js/jquery-ui-1.9.2.custom.js"></script>
    <![endif]-->
    <link rel="stylesheet" href="assets/css/reset.css"/>
    <link rel="stylesheet" href="assets/css/desktop.css"/>
    <link rel="stylesheet" href="lib/jquery-ui-1.9.2.custom/css/smoothness/jquery-ui-1.9.2.custom.css"/>
    <!--[if lt IE 9]>
    <link rel="stylesheet" href="assets/css/ie.css"/>
    <![endif]-->
    <% User user = (User) request.getSession().getAttribute(Constants.USER); %>
</head>
<body>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"/>
<div class="abs" id="wrapper">
<div class="abs" id="desktop">
<% int left = 20;
    int top = 10;
    int counter = 0;
    for (Modul module : user.getModulList()) {
        counter ++;
%>
<a class="abs icon" style="left:<%=left%>px;top:<%=top%>px;" href="#<%=module.getModul_link_id()%>__taskbar">
    <img src="assets/images/icons/<%=module.getModul_link_id()%>.png"/>
    <%=module.getModul_name()%><%--Müraciətçilər--%>
</a>

<div id="window__<%=module.getModul_link_id()%>" class="abs window">
    <div class="abs window_inner">
        <div class="window_top">
          <span class="float_left">
            <img src="assets/images/icons/icon_16_computer.png"/>
            <%=module.getModul_name()%>
          </span>
          <span class="float_right">
            <a href="#" class="window_min"></a>
            <a href="#" class="window_resize" style="display: none"></a>
            <a href="#<%=module.getModul_link_id() %>__taskbar" class="window_close"></a>
          </span>
        </div>
        <div class="abs window_content" >
             <div style="width: 100%; height: 100%;">
                 <div  class="float_left" style="width: 100px;">
                     <div class="operation_button_container">
                         <% for ( Operation operation : user.getPrivilegieList().get(module.getModul_link_id()) ){
                             if(operation.getId()!=8 && operation.getId()!=10){
                         %>
                         <button style="width: 90px;" id="<%=operation.getModulOperationId()%>"><img width="30px" height="30px" src="icons/buttons/<%=operation.getOperationButtonId()%>.png"> </button>
                         <%}
                         } %>
                     </div>

                    <%-- <ul>


                     <% for ( Operation operation : user.getPrivilegieList().get(module.getModul_link_id()) ){
                         if(operation.getId()!=8 && operation.getId()!=10){
                     %>
                     <li><button style="width: 90px;" id="<%=operation.getModulOperationId()%>"><img width="30px" height="30px" src="icons/buttons/<%=operation.getOperationButtonId()%>.png"> </button></li>
                     <%}
                     } %>
                     </ul>--%>


                 </div>
                 <div class="float_right">
                       hkjhgkjhgkjhgkjhgkgjh
                 </div>
                 <%--<table style="width: 100%; height: 100%;">
                     <tr style="height: 80px; width: 100%;">
                         <td>Burda filtrasiya olacaq</td>
                     </tr>
                     <tr style="height: 100%; width: 100%;">
                         <td  align="left" style="height: 100%; width: 100%;">
                             <table style="height: 100%; width: 100%;">
                                 <tr style="height: 100%; width: 100%;">
                                     <td  align="left" style="background-color: #0066cc; width: 100px;">
                                         <table>
                                             <% for ( Operation operation : user.getPrivilegieList().get(module.getModul_link_id()) ){
                                                 if(operation.getId()!=8 && operation.getId()!=10){
                                             %>
                                             <tr> <td align="left"><button style="width: 90px;" id="<%=operation.getModulOperationId()%>"><img width="30px" height="30px" src="icons/buttons/<%=operation.getOperationButtonId()%>.png"> </button> </td> </tr>
                                             <%}
                                             } %>
                                         </table>
                                     </td>
                                     <td style="width: 100%;"></td>
                                 </tr>
                             </table>
                         </td>
                     </tr>

                 </table>--%>
             </div>
        </div>
    </div>
</div>

<% top += 80;
    if (counter == 7) {
        left = 110;
        top = 10;
    }


}%>


</div>
<div id="bar_top_header"
     style="background-color: #000000; padding-left: 100px !important; padding-right: 100px !important;"></div>
<div id="bar_top_inner" style="cursor: pointer !important; ">
    <div style="cursor: pointer !important; border-radius: 3px; width: 100%; height: 3px; background-color: #0072d8"></div>
</div>

<div class="abs" id="bar_top">
    <span class="float_right" id="clock"></span>
    <ul>
        <li>
            <a class="menu_trigger" href="#">Modullar</a>
            <ul class="menu">
                <% for (Modul module : user.getModulList()) {
                    counter++; %>
                <li>
                    <a class="iconShortcut" href="#<%=module.getModul_link_id()%>__taskbar">
                        <img src="assets/images/icons/<%=module.getModul_link_id()%>.png"
                             style="margin-right: 5px; width: 16px;">
                        <%=module.getModul_name()%>
                    </a>
                </li>
                <% }%>
            </ul>
        </li>
        <li>
            <a class="menu_trigger" href="#">Tənzimləmələr</a>
            <ul class="menu">
                <li>
                    <a href="#">Şifrənizi dəyişin</a>
                </li>
                <li>
                    <a href="#">Email notofokasiya <input type="checkbox" class="common" id="switch1"
                                                          checked="checked"/></a>
                </li>
            </ul>
        </li>
    </ul>

    <div class="float_left" style="width: 50%; height: 100%; text-align: center; color: black;">
        Bildirişləri aç ↓
    </div>

    <ul class="float_right">
        <li>
            <a class="menu_trigger" href="#"><%= user.getFullName()%></a>
            <ul class="menu" style="min-width: 250px">
                <li>
                    <table>
                        <tr valign="middle">
                            <td>
                                <div style="width: 90px; height: 120px; border: 1px solid #b0b0b0; margin-right: 5px;">
                                    <img src="assets/images/users/90_120.jpg">
                                </div>
                            </td>
                            <td align="center">
                                <table cellpadding="0" cellspacing="0" style="line-height: 19px;">
                                    <tr>
                                        <td>
                                            Cinsi:
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            Kişi
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            Doğum tarixi:
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            25.09.1989
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            Vəzifə:
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            Aparıcı mütəxəsis
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>

                </li>
                <li>
                    <a href="#" onclick="$('#logoutForm').submit();">Çıxış</a>
                </li>
            </ul>
        </li>
    </ul>
</div>
<div class="abs" id="bar_bottom">
    <a class="float_left" href="#" id="show_desktop" title="Show Desktop">
        <img src="assets/images/icons/icon_22_desktop.png"/>
    </a>
    <ul id="dock">
        <% for (Modul module : user.getModulList()) {
            counter++; %>
        <li id="<%=module.getModul_link_id()%>__taskbar">
            <a href="#window__<%=module.getModul_link_id()%>">
                <img style="height: 22px;" src="assets/images/icons/<%=module.getModul_link_id()%>.png"/>
                <%=module.getModul_name()%>
            </a>
        </li>
        <% }%>
    </ul>
    <a class="float_right" href="#" title="Secure Hosting">
        <img src="assets/images/misc/firehost.png"/>
    </a>
</div>
</div>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.16/jquery-ui.min.js"></script>
<script>
    !window.jQuery && document.write(unescape('%3Cscript src="assets/js/jquery.js"%3E%3C/script%3E'));
    !window.jQuery.ui && document.write(unescape('%3Cscript src="assets/js/jquery.ui.js"%3E%3C/script%3E'));
</script>
<script src="assets/js/jquery.desktop.js"></script>
<script>
    $(function(){
        <%
        for (Modul module : user.getModulList()){
            for ( Operation operation : user.getPrivilegieList().get(module.getModul_link_id()) ){%>
        $("#<%=operation.getModulOperationId()%>").click(function(){
            <%=operation.getOperationButtonClickFunc()%>;
        });
        <%}
    }
    %>
    });

    var _gaq = [
        ['_setAccount', 'UA-166674-8'],
        ['_trackPageview']
    ];

    (function (d, t) {
        var g = d.createElement(t),
                s = d.getElementsByTagName(t)[0];
        g.async = true;
        g.src = '//www.google-analytics.com/ga.js';
        s.parentNode.insertBefore(g, s);
    })(this.document, 'script');
</script>
<form id="logoutForm" method="post" action="fs?action=logout" style="display: none"></form>
</body>
</html>