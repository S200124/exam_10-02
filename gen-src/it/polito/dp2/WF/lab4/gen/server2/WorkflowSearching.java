
package it.polito.dp2.WF.lab4.gen.server2;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Holder;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "WorkflowSearching", targetNamespace = "http://pad.polito.it/ws/Workflow/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface WorkflowSearching {


    /**
     * 
     * @param lastModification
     * @return
     *     returns java.util.List<it.polito.dp2.WF.lab4.gen.server2.WorkflowType>
     * @throws SystemError_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getWorkflows", targetNamespace = "http://pad.polito.it/ws/Workflow/", className = "it.polito.dp2.WF.lab4.gen.server2.GetWorkflows")
    @ResponseWrapper(localName = "getWorkflowsResponse", targetNamespace = "http://pad.polito.it/ws/Workflow/", className = "it.polito.dp2.WF.lab4.gen.server2.GetWorkflowsResponse")
    public List<WorkflowType> getWorkflows(
        @WebParam(name = "lastModification", targetNamespace = "", mode = WebParam.Mode.OUT)
        Holder<String> lastModification)
        throws SystemError_Exception
    ;

    /**
     * 
     * @param lastModification
     * @return
     *     returns java.util.List<it.polito.dp2.WF.lab4.gen.server2.ProcessType>
     * @throws SystemError_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getProcesses", targetNamespace = "http://pad.polito.it/ws/Workflow/", className = "it.polito.dp2.WF.lab4.gen.server2.GetProcesses")
    @ResponseWrapper(localName = "getProcessesResponse", targetNamespace = "http://pad.polito.it/ws/Workflow/", className = "it.polito.dp2.WF.lab4.gen.server2.GetProcessesResponse")
    public List<ProcessType> getProcesses(
        @WebParam(name = "lastModification", targetNamespace = "", mode = WebParam.Mode.OUT)
        Holder<String> lastModification)
        throws SystemError_Exception
    ;

}
