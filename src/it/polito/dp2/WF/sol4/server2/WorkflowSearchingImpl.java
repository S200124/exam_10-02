package it.polito.dp2.WF.sol4.server2;

import it.polito.dp2.WF.lab4.gen.server2.ProcessType;
import it.polito.dp2.WF.lab4.gen.server2.SystemError_Exception;
import it.polito.dp2.WF.lab4.gen.server2.WorkflowSearching;
import it.polito.dp2.WF.lab4.gen.server2.WorkflowType;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.Holder;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


@WebService(serviceName="WorkflowSearching", portName="WorkflowSearchingPort", targetNamespace="http://pad.polito.it/ws/Workflow/", endpointInterface="it.polito.dp2.WF.lab4.gen.server2.WorkflowSearching")
public class WorkflowSearchingImpl implements WorkflowSearching {

	private WorkflowManager manager;
	
	public WorkflowSearchingImpl(WorkflowManager manager) {
		super();
		this.manager = manager;
	}
	

	@Override
	@WebMethod
	@WebResult(targetNamespace = "")
	@RequestWrapper(localName = "getProcesses", targetNamespace = "http://pad.polito.it/ws/Workflow/", className = "it.polito.dp2.WF.lab4.gen.server2.GetProcesses")
	@ResponseWrapper(localName = "getProcessesResponse", targetNamespace = "http://pad.polito.it/ws/Workflow/", className = "it.polito.dp2.WF.lab4.gen.server2.GetProcessesResponse")
	public List<WorkflowType> getWorkflows(Holder<String> lastModification)
			throws SystemError_Exception {
		lastModification.value=manager.getLastModification();
		return manager.getWorkflows();
	}

	@Override
	@WebMethod
	@WebResult(targetNamespace = "")
	@RequestWrapper(localName = "getWorkflows", targetNamespace = "http://pad.polito.it/ws/Workflow/", className = "it.polito.dp2.WF.lab4.gen.server2.GetWorkflows")
	@ResponseWrapper(localName = "getWorkflowsResponse", targetNamespace = "http://pad.polito.it/ws/Workflow/", className = "it.polito.dp2.WF.lab4.gen.server2.GetWorkflowsResponse")
	public List<ProcessType> getProcesses(Holder<String> lastModification)
			throws SystemError_Exception {
		lastModification.value=manager.getLastModification();
		return manager.getProcesses();
	}

}
