<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    var isTrue = false;
    $(function(){
        $("#userName_xxx").focusout(function(){
            var u =  $("#userName_xxx").val();
            if (u.toString().trim().length > 0 && u != $("#userName_xxx").attr("oldUserName") ){
                checkUserName(u ,function(d){
                    if ( d == 1){
                        $("#userName_xxx").removeClass("greenBorderClass").addClass("redBorderClass");
                        $('#passwordInfo').html("İstifadəçi adı mövcuddur!");
                    }else if (d == 0){
                        $("#userName_xxx").removeClass("redBorderClass").addClass("greenBorderClass");
                        $('#passwordInfo').html('');
                    }
                });
            }
        });

        $("[passw='passw']").focusout(function(){

            var firstPassword = $("#password_change").val();
            var secondPassword = $("#passwordAgain").val();
            if ( firstPassword.toString().trim().length >= minPasswLength ){
                if ( firstPassword == secondPassword ){
                    $("[passw='passw']").removeClass("redBorderClass");
                    isTrue = true;
                    $("#passwordInfo").html('');
                   // console.log("redBorderClass");

                }else {
                    $("[passw='passw']").removeClass("greenBorderClass").addClass("redBorderClass");
                    $("#passwordInfo").html('Şifrələr üst - üstə düşmür !');
                    isTrue = false;
                }
            }else if ( firstPassword.toString().trim().length <= minPasswLength && firstPassword.toString().trim().length > 0){
                $("#passwordInfo").html("Şifrə minimum " + minPasswLength + " simvol olmalıdır !" );
                isTrue = false;
            }
        });


        $("#changePasswordDialog").dialog({
            resizable:false,
            autoOpen:false,
            height:250,
            width:400,
            modal:true,
            buttons:{
                "Yadda saxla":function () {
                    if(isTrue){
                        var pass = $("#password_change").val();
                        updateUserNameAndPassw("-1", pass);
                        $(this).dialog("close");
                    }
                },
                "İmtina et":function () {
                    $(this).dialog("close");
                }
            } ,
            open: function(){

            }
        });
    });
</script>

<div id="changePasswordDialog">
    <table style="width: 100%; height: 100%; font-size: 1em;">
        <tr style="height: 20%;">
            <td style="width: 10%;" colspan="4"></td>
        </tr>
        <tr style="height: 20%;">
            <td style="width: 10%;" rowspan="3"></td>
            <td style="width: 30%;">İstifadəçi adı:</td>
            <td style="width: 50%; padding: 5px 0 5px;" >
                <input disabled="disabled" id="userName_xxx"  type="text" style="width: 100%; height: 100%;">
            </td>
            <td style="width: 10%;" rowspan="3"></td>
        </tr>
        <tr style="height: 20%;">
            <td style="width: 30%;">Yeni şifrə:</td>
            <td style="width: 50%; padding: 5px 0 5px;">
                <input type="password" id="password_change" passw="passw" style="width: 100%; height: 100%;">
                <%--<div style="width: 100%; height: 100%;">
                    <div style="width: 100%; height: 60%;"></div>
                    <div style="width: 100%; height: 40%;"><label></label></div>
                </div>--%>
            </td>
        </tr>
        <tr style="height: 20%;">
            <td style="width: 30%;">Şifrənin təkrarı:</td>
            <td style="width: 50%; padding: 5px 0 5px;">
                <input type="password" id="passwordAgain" passw="passw" style="width: 100%; height: 100%;">
            </td>
        </tr>
        <tr style="height: 20%;">
            <td align="center" style="width: 10%;" colspan="4"><label style="color: red; font-size: 1em;" id="passwordInfo"></label></td>
        </tr>
    </table>
</div>