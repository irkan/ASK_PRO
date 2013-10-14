function loadSecurityGrid(funk){
    var grid = jQuery("#secGrid");
    grid.GridUnload();
    $.ajax({
        url:'fc?action=getSecirityGrid',
        type:'post',
        dataType:'json',
        success:function (data) {
            grid.jqGrid({
                width :730
                ,height : 210
                ,datatype:"json"
                ,scrollOffset:0
                ,colNames:data.columnName
                ,colModel:data.columnModel
                ,rowNum:20
                ,rowList:[10, 20, 30]
                ,sortname:"id"
                ,sortorder:"asc"
                ,viewrecords:true
                ,gridview:true
                /*,onSelectRow:function(id){
                 $("#secGrid tbody tr td[aria-describedby='secGrid_modul_name']").attr('sec_modul_name_sec','sec_modul_name_sec');
                 $("#secGrid tbody tr[id='" + id + "'] td[aria-describedby='secGrid_modul_name']").removeAttr('sec_modul_name_sec'*//*,'none repeat-x scroll 50% 50% #A8BBC4'*//*);
                 }*/
                /*,autowidth:true*/
            });

            $.each(data.gridData, function(i, data){
                grid.addRowData(i,data);
            });
        }
        ,complete:function(){
            jQuery('#gview_secGrid').find('.ui-jqgrid-htable thead').css('height', '100px');
            jQuery('#gview_secGrid').find(".ui-jqgrid-htable thead tr th[id !='secGrid_modul_name'] div").css('height', '100px');
            //$("#secGrid tbody tr td[aria-describedby='secGrid_modul_name']").css('backgroundColor','rgb(202, 208, 222)');
            $("#secGrid tbody tr td[aria-describedby='secGrid_modul_name']").attr('sec_modul_name_sec','sec_modul_name_sec');
            if (funk){
                funk();
            }
        }
    });
}

function imtiyazlarClickFunc() {
    var selrow_sec = curr_grid.jqGrid('getGridParam', 'selrow');
    //console.log(current_Module_Operation_Id);
    createDialogs('91');
    var selrow_data_sec = curr_grid.jqGrid('getRowData', selrow_sec);
    $("#content_sec").load('fcs?action=load', {add: 'security/security.jsp'}, function () {
        //getUserGroups('userTypeCombo', function () {
            loadSecurityGrid(function () {
                //getPersonOperations(selrow_data_sec.personId);
            });
        //});
        $('#security').dialog("option", "title", "İmtiyazlar");
        $('#security').dialog("open");
    });
}