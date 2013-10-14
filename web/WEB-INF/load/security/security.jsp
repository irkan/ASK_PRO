<%@ page import="javax.jws.soap.SOAPBinding" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    function returnSec ( firstPassword , secondPassword ){
        var isTrueSecuritye = false;
        if ( firstPassword.toString().trim().length >= minPasswLength ){
            if ( firstPassword == secondPassword ){
                $("[passw='passw']").removeClass("redBorderClass");
                $("#passwordInfoSec").html('');
                isTrueSecuritye = true;
            }else {
                $("[passw='passw']").removeClass("greenBorderClass").addClass("redBorderClass");
                $("#passwordInfoSec").html('Şifrələr üst - üstə düşmür !');
                isTrueSecuritye = false;
            }
        }else if ( firstPassword.toString().trim().length <= minPasswLength && firstPassword.toString().trim().length > 0){
            $("#passwordInfoSec").html("Şifrə minimum " + minPasswLength + " simvol olmalıdır !" );
            isTrueSecuritye = false;
        }

        return isTrueSecuritye;
    }


    $(function(){
        $("[passww='passww']").focusout(function(){
            var firstPassword = $("#userPassword2").val();
            var secondPassword = $("#userPasswordAgain").val();
            returnSec( firstPassword , secondPassword);
        });

        $('#userName2').focusout(function(){
            var ussss = $(this);
            var infoSec = $("#passwordInfoSec");
            var userId = ussss.attr("userId");
            var u =  ussss.val();
            var personId = $("#userName").attr("personId");
            if (u.toString().trim().length > 0 && u != ussss.attr("userNameOld")){
                checkUserName(u,personId ,function(d){
                    if ( d == 1){
                        /*isTrueSecurity = false;*/
                        ussss.removeClass("greenBorderClass").addClass("redBorderClass");
                        infoSec.html("İstifadəçi adı mövcuddur!");
                    }else if (d == 0){
                        ussss.removeClass("redBorderClass").addClass("greenBorderClass");
                        infoSec.html('');
                    }
                });
            }else if (u == ussss.attr("userNameOld")){
                /*isTrueSecurity = true;*/
                ussss.removeClass("redBorderClass").removeClass("greenBorderClass");
                infoSec.html('');
            }
        });

        $( "#password_changeAdmin" ).dialog({
            resizable: false,
            autoOpen: false,
            height:250,
            width: 450,
            modal: true,
            buttons: {
                "Təsdiq et": function() {
                    var ussss = $('#userName2');
                    var infoSec = $("#passwordInfoSec");
                    var userId = ussss.attr("userId");
                    var u =  ussss.val();

                    var firstPassword = $("#userPassword2").val();
                    var secondPassword = $("#userPasswordAgain").val();
                    var personId = $("#userName").attr("personId");
                    checkUserName (u ,personId, function(d){
                        if ( d == 1){
                            ussss.removeClass("greenBorderClass").addClass("redBorderClass");
                            infoSec.html("İstifadəçi adı mövcuddur!");
                        }else if (d == 0){
                            ussss.removeClass("redBorderClass").addClass("greenBorderClass");
                            infoSec.html('');
                            if (returnSec( firstPassword , secondPassword )){
                                /*$("#userTypeCombo").empty();
                                $("#userTypeCombo").append("<option value='" + userGroupId + "'>" + userGroupName +  "</option>");*/
                                $("#userName").val( $("#userName2").val() );
                                $("#userPassword").val( $("#userPassword2").val() );
                                $("#password_changeAdmin").dialog( "close" );
                            }
                        }
                    });
                },
                "İmtina et": function() {
                    $( this ).dialog( "close" );
                }
            },
            open: function() {

            }
        });


        $("#create-dialogplus").button().click(function(){
            var us = $("#userName2");
            us.val( $("#userName").val() );
            us.attr("userNameOld" , $("#userName").val());
            $("#password_changeAdmin").dialog('open');
        })
    });
</script>
<table border="0" width="600" cellspacing="0" cellpadding="0" align="center">
    <tr>
        <td>

            <table class="row9" border="0" width="490" cellspacing="0" cellpadding="0" align="center">
                <tr height="0">

                    <td colspan="5" align="center" style="text-align:center;font: bold 11px/16px tahoma,helvetica,sans-serif;color:#203145"> <label id="tdPerson"> </label> </td>

                </tr>
                <tr>
                    <td colspan="5"></td>
                </tr>
                <tr>
                    <td width="165"></td>
                    <td  width="165">İstifadəçinin tipi</td>
                    <td width="325" colspan="2">
                        <select id="userTypeCombo" style="width:100%" onchange="getUserGroupOperations();"></select>
                    </td>
                    <%--<td width="75">
                        <button role="button" class="ui-button1 ui-widget ui-state-default ui-corner-all ui-button-text-only" style="padding:0;width:110px; float:right; margin-right: 43px; margin-top: 2px;"  id="checkAll">  <label id="labelid">Heç biri</label></button>
                    </td>--%>
                    <td width="180"></td>
                </tr>
                <tr>
                    <td></td>
                    <td>İstifadəçinin adı</td>
                    <td>
                        <input type="text" id="userName" disabled="disabled"style="width:285px;">
                    </td>
                    <td align="right" width="120">
                        <button role="button" style="padding:0;width:100px; float:right; /*margin-right: 43px; margin-top: 2px;*/"  id="create-dialogplus"><label id="labelid2">Redaktə et</label></button>
                    </td>
                    <td width="180"></td>

                </tr>
                <tr>
                    <td></td >
                    <td>İstifadəçinin şifrəsi</td>
                    <td><input type="password" id="userPassword" disabled="disabled"  style="width:285px; float: left;"></td>

                    <%  if(true/*!(prvlg.getUserType().equalsIgnoreCase("student")||prvlg.getUserType().equalsIgnoreCase("employee"))*/) {   %>
                    <td align="right" width="120"><button onclick="print_area_2('regcard');" aria-disabled="false" role="button"  class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" style="padding:0;width:100px; height: 30px; float:right;/* margin-right: 43px; margin-top: 2px;*/">Çıxarış</button></td>
                    <% } %>
                    <td width="180"></td>
                </tr>
            </table>
        </td>
    </tr>
    <tr><td height="15"></td></tr>
    <tr id="security_own_tr_hide"><td>
        <table id ="secGrid">  </table>
    </td>
    </tr>
</table>


<div id="password_changeAdmin" title="Redaktə et">
    <table class="row9" border="0" width="370" cellspacing="0" cellpadding="0" align="center">
        <tr><td colspan="4" height="30"></td></tr>
        <%--<tr>
            <td width="10"></td>
            <td width="350">İstifadəçinin tipi</td>
            <td width="75">
                <select id="userTypeCombo2" style="width:275px;"></select>
            </td>
            <td width="170"></td>
        </tr>--%>
        <tr>
            <td width="10"></td>
            <td>İstifadəçinin adı</td>
            <td> <input userNameOld=""  id="userName2"  type="text"  style="width:270px;"></td>
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td>İstifadəçinin şifrəsi</td>
            <td> <input passww="passww" type="text" id="userPassword2"  style="width:270px; float: left;">
            <td></td>

        </tr>
        <tr>
            <td></td>
            <td>Şifrənin təkrarı</td>
            <td> <input passww="passww" type="text" id="userPasswordAgain"  style="width:270px; float: left;">
            <td></td>

        </tr>


        <tr><td height="28" colspan="4" id="passwordInfoSec"></td></tr>
    </table>
</div>