package az.neuron.ask.util;

import az.neuron.ask.Constants;
import az.neuron.ask.domain.PagingObject;
import az.neuron.ask.domain.User;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by IntelliJ IDEA.
 * User: Irkan Ehmedov
 * Date: 4/5/12
 * Time: 10:48 AM
 * To change this template use File | Settings | File Templates.
 */
public class PagingUtility {
    public static PagingObject getPagingObject(HttpServletRequest request) {
        PagingObject object = new PagingObject();
        User user = (User)request.getSession().getAttribute(Constants.USER);

        if (request.getParameter("page") != null) {
            object.setPageNumber(Integer.parseInt(request.getParameter("page")));
        } else {
            object.setPageNumber(1);
        }

        if (request.getParameter("rows") != null) {
            object.setPageSize(Integer.parseInt(request.getParameter("rows")));
        } else {
            object.setPageSize(20);
        }

        if (request.getParameter("sidx") != null) {
            object.setSortIndex(request.getParameter("sidx"));
        } else {
            object.setSortIndex("1");
        }

        if (request.getParameter("sord") != null) {
            object.setSortOrder(request.getParameter("sord"));
        } else {
            object.setSortOrder("asc");
        }
        if (request.getParameter("searchParam") != null) {
            object.setSearchParam(request.getParameter("searchParam"));
        } else {
            object.setSearchParam("");
        }

        return object;
    }
}
