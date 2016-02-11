
package it.polito.dp2.WF.lab4.gen.client4;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.FaultAction;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "WorkflowManaging", targetNamespace = "http://pad.polito.it/ws/Workflow/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface WorkflowManaging {


    /**
     * 
     * @param workflowName
     * @throws SystemErrorException
     */
    @WebMethod
    @RequestWrapper(localName = "createProcess", targetNamespace = "http://pad.polito.it/ws/Workflow/", className = "it.polito.dp2.WF.lab4.gen.client4.CreateProcess")
    @ResponseWrapper(localName = "createProcessResponse", targetNamespace = "http://pad.polito.it/ws/Workflow/", className = "it.polito.dp2.WF.lab4.gen.client4.CreateProcessResponse")
    @Action(input = "http://pad.polito.it/ws/Workflow/WorkflowManaging/createProcessRequest", output = "http://pad.polito.it/ws/Workflow/WorkflowManaging/createProcessResponse", fault = {
        @FaultAction(className = SystemErrorException.class, value = "http://pad.polito.it/ws/Workflow/WorkflowManaging/createProcess/Fault/SystemError_Exception")
    })
    public void createProcess(
        @WebParam(name = "workflowName", targetNamespace = "")
        String workflowName)
        throws SystemErrorException
    ;

    /**
     * 
     * @param completedAction
     * @throws SystemErrorException
     */
    @WebMethod
    @RequestWrapper(localName = "completeAction", targetNamespace = "http://pad.polito.it/ws/Workflow/", className = "it.polito.dp2.WF.lab4.gen.client4.CompleteAction")
    @ResponseWrapper(localName = "completeActionResponse", targetNamespace = "http://pad.polito.it/ws/Workflow/", className = "it.polito.dp2.WF.lab4.gen.client4.CompleteActionResponse")
    @Action(input = "http://pad.polito.it/ws/Workflow/WorkflowManaging/completeActionRequest", output = "http://pad.polito.it/ws/Workflow/WorkflowManaging/completeActionResponse", fault = {
        @FaultAction(className = SystemErrorException.class, value = "http://pad.polito.it/ws/Workflow/WorkflowManaging/completeAction/Fault/SystemError_Exception")
    })
    public void completeAction(
        @WebParam(name = "completedAction", targetNamespace = "")
        ActionStatusType completedAction)
        throws SystemErrorException
    ;

    /**
     * 
     * @param takenOverAction
     * @return
     *     returns it.polito.dp2.WF.lab4.gen.client4.ActionStatusType
     * @throws SystemErrorException
     * @throws AlreadyTakenOrDifferentRoleErrorException
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "takeAction", targetNamespace = "http://pad.polito.it/ws/Workflow/", className = "it.polito.dp2.WF.lab4.gen.client4.TakeAction")
    @ResponseWrapper(localName = "takeActionResponse", targetNamespace = "http://pad.polito.it/ws/Workflow/", className = "it.polito.dp2.WF.lab4.gen.client4.TakeActionResponse")
    @Action(input = "http://pad.polito.it/ws/Workflow/WorkflowManaging/takeActionRequest", output = "http://pad.polito.it/ws/Workflow/WorkflowManaging/takeActionResponse", fault = {
        @FaultAction(className = AlreadyTakenOrDifferentRoleErrorException.class, value = "http://pad.polito.it/ws/Workflow/WorkflowManaging/takeAction/Fault/AlreadyTakenOrDifferentRoleError_Exception"),
        @FaultAction(className = SystemErrorException.class, value = "http://pad.polito.it/ws/Workflow/WorkflowManaging/takeAction/Fault/SystemError_Exception")
    })
    public ActionStatusType takeAction(
        @WebParam(name = "takenOverAction", targetNamespace = "")
        TakenOverActionType takenOverAction)
        throws AlreadyTakenOrDifferentRoleErrorException, SystemErrorException
    ;

}
