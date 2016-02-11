package it.polito.dp2.WF.sol4.client3;

import it.polito.dp2.WF.lab4.gen.client3.ActionType;
import it.polito.dp2.WF.lab4.gen.client3.WorkflowType;

public class ActionReader implements it.polito.dp2.WF.ActionReader {
	
	private ActionType action;
	private WorkflowType workflow;
	
	public ActionReader(ActionType act, WorkflowType wf)
	{
		action = act;
		workflow = wf;
	}

	@Override
	public WorkflowReader getEnclosingWorkflow() {
		return (new WorkflowReader(workflow));
	}

	@Override
	public String getName() {
		return action.getName();
	}

	@Override
	public String getRole() {
		String role = action.getRole();
		if(role != null)
			return role;
		else
			return "";
	}

	@Override
	public boolean isAutomaticallyInstantiated() {
		return action.isAutomInst();
	}

}
