<%@ page import="az.neuron.ask.domain.User" %>
<%@ page import="az.neuron.ask.Constants" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% User user = (User) request.getSession().getAttribute(Constants.USER); %>
<table style="width: 100%; height: 20%;" class="row">
    <tr>
        <td align="center"><label style="font-weight: bold;"><%=user.getPerson().getFullName()%></label></td>
    </tr>
    <tr style="border: 1px solid #000000;">
        <table>
            <tr>
                <td>
                    <div style="float: left;">
                        <img src="images/facebook-profile-picture.jpg" style="width: 160px; height: 120px">
                    </div>
                    <div style="float: right">
                        <table style="height: 120px;" class="row">
                            <tr>
                                <td>İstifadəçi adı</td>
                                <td><input type="text" value="<%=user.getUserName()%>" disabled="disabled"></td>
                            </tr>
                            <tr>
                                <td>Şifrə</td>
                                <td><input id="changedPassword" type="password" ></td>
                            </tr>
                            <tr>
                                <td>Şifrənin təkrarı</td>
                                <td><input id="changedPasswordConfirmed" type="password"></td>
                            </tr>
                        </table>
                    </div>
                </td>
            </tr>
        </table>
    </tr>
</table>
<input type="hidden" id="changedUserId" value="<%= user.getId()%>">