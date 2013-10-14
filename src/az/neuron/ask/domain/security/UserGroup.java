package az.neuron.ask.domain.security;

import az.neuron.ask.domain.Base;

import java.util.List;


public class UserGroup extends Base {
    String userGroupName;
    List<Long> operationIdList;

    public String getUserGroupName() {
        return userGroupName;
    }

    public void setUserGroupName(String userGroupName) {
        this.userGroupName = userGroupName;
    }

    public List<Long> getOperationIdList() {
        return operationIdList;
    }

    public void setOperationIdList(List<Long> operationIdList) {
        this.operationIdList = operationIdList;
    }
}
