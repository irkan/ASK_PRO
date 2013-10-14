<%@ page import="oim.Constants" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%response.setHeader(Constants.REQUIRES_AUTH,"0");%>
<div id="loginDialog">
    <form id="loginForm" action="ls?action=login" method="post">
        <div id="main">
            <div id="cell">
                <div id="content">
                    <table style="width: 500px; border: 2px solid rgb(37, 54, 95); background:#FFF" align="center">
                        <tbody>
                        <tr height="25">
                            <td colspan="5"></td>
                        </tr>
                        <tr>
                            <td colspan="5" style="font-size: 14px; font-weight: bold; color: rgb(255, 255, 255); text-align: center;" height="62" bgcolor="#14365B"> Sistemə daxili istifadəçilər tərəfindən giriş </td>
                        </tr>
                        <tr height="20">
                            <td colspan="5"></td>
                        </tr>
                        <tr>
                            <td colspan="5" style="font-size: 14px; font-weight: bold; color: rgb(0, 0, 0); text-align: center;" height="25"> Elektron təhsil sisteminə daxil olun </td>
                        </tr>
                        <tr height="27">
                            <td colspan="5"><label id="messageField" class="error"></label></td>
                        </tr>
                        <tr>
                            <td width="112">&nbsp;</td>
                            <td width="125"><label for="username" style="font:tahoma, Helvetica, sans-serif; font-size:14px; font-weight:bold; color:#2E394B "><span class="style1">İstifadəçi adı:</span> </label></td>
                            <td colspan="2"><input value="1" id="username" name="userName" style="width: 160px; border: 1px solid rgb(55, 71, 81); border-radius:6px; padding:3px" type="text" onkeypress="reset1();">                </td>
                            <td width="108">&nbsp;</td>
                        </tr>
                        <tr height="10">
                            <td colspan="5" ></td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                            <td width="125"><label for="username" style="font:tahoma, Helvetica, sans-serif; font-size:14px; font-weight:bold; color:#2E394B " ><span class="style1"> Şifrə: </span></label>                </td>
                            <td colspan="2"><input id = "password" name="passw" style="width: 160px; border: 1px solid rgb(55, 71, 81); border-radius:6px; padding:3px" type="password"  onkeypress="reset1();">                </td>
                            <td><label></label></td>
                        </tr>
                        <tr height="10">
                            <td colspan="5"></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>&nbsp;</td>
                            <td><input name="submit" value="Daxil ol" style="background: url(img/bg_button.png); width: 78px; border: 1px solid rgb(55, 71, 81); border-radius:5px; padding:3px" type="submit">                </td>
                            <td align="right"><input name="clear" value="Təmizlə" style="background: url(img/bg_button.png); width: 78px; border: 1px solid rgb(55, 71, 81); border-radius:5px; padding:3px" type="reset">                </td>
                            <td></td>
                        </tr>
                        <tr height="22">
                            <td colspan="5"></td>
                        </tr>
                        </tbody>
                    </table>
                </div></div></div>
    </form>
</div>
<script type="text/javascript">

    $(function(){
        /*$("#loginForm").ajaxForm({
            data:'&redirect=false'
        });*/
        $("#loginDialog").dialog({
            resizable:false,
            autoOpen:false,
            height:500,
            width:500,
            modal:true,
            buttons:{
                "Daxil ol":function () {
                   $("#loginForm").submit();
                }
            } ,
            open: function(){

            },
            close:function(){
                $("#loginDiv").empty();
            }
        });
    });
</script>