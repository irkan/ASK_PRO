<%@ page import="org.codehaus.jackson.JsonFactory" %>
<%@ page import="org.codehaus.jackson.JsonGenerator" %>
<%@ page import="java.util.Map" %>
<%@ page import="az.neuron.ask.domain.security.Grid" %>
<%@ page import="az.neuron.ask.domain.User" %>
<%@ page import="az.neuron.ask.Constants" %>
<%@ page import="az.neuron.ask.domain.Modul" %>
<%@ page import="az.neuron.ask.domain.security.Column" %>
<%@ page import="az.neuron.ask.domain.Operation" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    JsonFactory factory = new JsonFactory();
    JsonGenerator generator = factory.createJsonGenerator(out);
    Grid grid = (Grid) request.getAttribute(Constants.GRID);
    User user = ( User ) session.getAttribute(Constants.USER);
    //Map<Long , Modul> moduleMap = ( Map<Long , Modul> ) request.getServletContext().getAttribute(Constants.MODULS);
    generator.writeStartObject();
    generator.writeArrayFieldStart("columnName");
    if(grid.getColumnList() != null){
        for( Column column : grid.getColumnList() ){
            generator.writeString( column.getName() );
        }
    }
    generator.writeEndArray();
    generator.writeArrayFieldStart("columnModel");
    if(grid.getColumnList() != null){
        for(Column column : grid.getColumnList()){
            generator.writeStartObject();
            generator.writeStringField("name", column.getModel());
            generator.writeStringField("index", column.getModel());
            generator.writeNumberField("width", column.getWidth());
            generator.writeBooleanField("hidden", column.isHidden());
            generator.writeStringField("align", column.getAlign());
            generator.writeEndObject();
        }
    }
    generator.writeEndArray();
    generator.writeArrayFieldStart("gridData");

    for (Modul module : user.getModulList()){
        //Module m = moduleMap.get(module.getId());
        //if ( module.getModuleType() == 1 ){
            generator.writeStartObject();
            generator.writeStringField("modul_name", module.getModul_name());
            for (Operation operation : user.getPrivilegieList().get(module.getModul_link_id())){
                //long isExist = user.checkPrivilege( module.getId() , operation.getId() );
                //String field = "s";
                //if ( isExist != 0 ){
                String field = "<input type=\"checkbox\" value=\"" + operation.getModulOperationId() + "\"/>";
                    //System.out.println(field);
                //}
                generator.writeStringField( String.valueOf(operation.getId()), field );
            }
            generator.writeEndObject();
        //}
    }
    generator.writeEndArray();
    generator.writeEndObject();
    generator.flush();
%>