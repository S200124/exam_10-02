package it.polito.dp2.WF.sol4.server2;

import it.polito.dp2.WF.lab4.gen.server2.*;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


@WebService(serviceName="WorkflowManaging", portName="WorkflowManagingPort", targetNamespace="http://pad.polito.it/ws/Workflow/", endpointInterface="it.polito.dp2.WF.lab4.gen.server2.WorkflowManaging")
public class WorkflowManagingImpl implements WorkflowManaging {

	private WorkflowManager manager;
	
	public WorkflowManagingImpl(WorkflowManager manager) {
		super();
		this.manager = manager;
	}
	
	@Override
	@WebMethod
	@RequestWrapper(localName = "createProcess", targetNamespace = "http://pad.polito.it/ws/Workflow/", className = "it.polito.dp2.WF.lab4.gen.server2.CreateProcess")
	@ResponseWrapper(localName = "createProcessResponse", targetNamespace = "http://pad.polito.it/ws/Workflow/", className = "it.polito.dp2.WF.lab4.gen.server2.CreateProcessResponse")
	public void createProcess(
			@WebParam(name = "workflow", targetNamespace = "") String workflow)
			throws SystemError_Exception, SystemError_Exception {
		manager.createProcessInWorkflow(workflow);
	}

	@Override
	@WebMethod
	@WebResult(targetNamespace = "")
	@RequestWrapper(localName = "takeAction", targetNamespace = "http://pad.polito.it/ws/Workflow/", className = "it.polito.dp2.WF.lab4.gen.server2.TakeAction")
	@ResponseWrapper(localName = "takeActionResponse", targetNamespace = "http://pad.polito.it/ws/Workflow/", className = "it.polito.dp2.WF.lab4.gen.server2.TakeActionResponse")
	public ActionStatusType takeAction(
			@WebParam(name = "takenOverAction", targetNamespace = "") TakenOverActionType takenOverAction)
			throws AlreadyTakenOrDifferentRoleError_Exception,
			SystemError_Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@WebMethod
	@RequestWrapper(localName = "completeAction", targetNamespace = "http://pad.polito.it/ws/Workflow/", className = "it.polito.dp2.WF.lab4.gen.CompleteAction")
	@ResponseWrapper(localName = "completeActionResponse", targetNamespace = "http://pad.polito.it/ws/Workflow/", className = "it.polito.dp2.WF.lab4.gen.server2.CompleteActionResponse")
	public void completeAction(
			@WebParam(name = "completedAction", targetNamespace = "") ActionStatusType completedAction)
			throws SystemError_Exception {
		// TODO Auto-generated method stub
		
	}

}
