
package it.polito.dp2.WF.lab4.gen.server;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
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
     * @throws SystemError_Exception
     */
    @WebMethod
    @RequestWrapper(localName = "createProcess", targetNamespace = "http://pad.polito.it/ws/Workflow/", className = "it.polito.dp2.WF.lab4.gen.server.CreateProcess")
    @ResponseWrapper(localName = "createProcessResponse", targetNamespace = "http://pad.polito.it/ws/Workflow/", className = "it.polito.dp2.WF.lab4.gen.server.CreateProcessResponse")
    public void createProcess(
        @WebParam(name = "workflowName", targetNamespace = "")
        String workflowName)
        throws SystemError_Exception
    ;

    /**
     * 
     * @param takenOverAction
     * @return
     *     returns it.polito.dp2.WF.lab4.gen.server.ActionStatusType
     * @throws AlreadyTakenOrDifferentRoleError_Exception
     * @throws SystemError_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "takeAction", targetNamespace = "http://pad.polito.it/ws/Workflow/", className = "it.polito.dp2.WF.lab4.gen.server.TakeAction")
    @ResponseWrapper(localName = "takeActionResponse", targetNamespace = "http://pad.polito.it/ws/Workflow/", className = "it.polito.dp2.WF.lab4.gen.server.TakeActionResponse")
    public ActionStatusType takeAction(
        @WebParam(name = "takenOverAction", targetNamespace = "")
        TakenOverActionType takenOverAction)
        throws AlreadyTakenOrDifferentRoleError_Exception, SystemError_Exception
    ;

    /**
     * 
     * @param completedAction
     * @throws SystemError_Exception
     */
    @WebMethod
    @RequestWrapper(localName = "completeAction", targetNamespace = "http://pad.polito.it/ws/Workflow/", className = "it.polito.dp2.WF.lab4.gen.server.CompleteAction")
    @ResponseWrapper(localName = "completeActionResponse", targetNamespace = "http://pad.polito.it/ws/Workflow/", className = "it.polito.dp2.WF.lab4.gen.server.CompleteActionResponse")
    public void completeAction(
        @WebParam(name = "completedAction", targetNamespace = "")
        ActionStatusType completedAction)
        throws SystemError_Exception
    ;

}