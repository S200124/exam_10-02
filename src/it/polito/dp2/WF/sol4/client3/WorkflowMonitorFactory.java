package it.polito.dp2.WF.sol4.client3;

import it.polito.dp2.WF.WorkflowMonitorException;

public class WorkflowMonitorFactory extends it.polito.dp2.WF.WorkflowMonitorFactory {
	
	@Override
	public WorkflowMonitor newWorkflowMonitor() throws WorkflowMonitorException {
		return (new WorkflowMonitor());
	}

}
