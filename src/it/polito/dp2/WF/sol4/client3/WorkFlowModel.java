package it.polito.dp2.WF.sol4.client3;

import it.polito.dp2.WF.lab4.gen.client3.*;

import java.net.*;
import java.util.*;

import javax.xml.ws.Holder;

public final class WorkFlowModel {
	
	private WorkFlowModel() {}
	
	public static List<WorkflowType> allWorkflow(Holder<String> time)
	{
		URL url;
		try {
			String /*targetURL = System.getProperty("it.polito.dp2.WF.lab4.URL");
			if(targetURL.isEmpty())*/
				targetURL = "http://localhost:7081/wfinfo?wsdl";
			
			url = new URL(targetURL);
		} catch (MalformedURLException e) {
			url = null;
		}
		
		if(url != null)
		{
			WorkflowSearching_Service service = new WorkflowSearching_Service(url);
			WorkflowSearching proxy = service.getWorkflowSearchingPort();
			
			Holder<String> hs = new Holder<String>();
			
			try {
				List<WorkflowType> ret = proxy.getWorkflows(hs);
				time = hs;
				return ret;
			} catch (SystemErrorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}
	
	public static List<ProcessType> allProcesses(Holder<String> time)
	{	
		URL url;
		try {
			String /*targetURL = System.getProperty("it.polito.dp2.WF.lab4.URL");
			if(targetURL.isEmpty())*/
				targetURL = "http://localhost:7081/wfinfo?wsdl";
			
			url = new URL(targetURL);
		} catch (MalformedURLException e) {
			url = null;
		}
		
		if(url != null)
		{
			WorkflowSearching_Service service = new WorkflowSearching_Service(url);
			WorkflowSearching proxy = service.getWorkflowSearchingPort();
			
			Holder<String> hs = new Holder<String>();
			
			try {
				List<ProcessType> ret = proxy.getProcesses(hs);
				time = hs;
				return ret;
			} catch (SystemErrorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}
	
	/*public static List<Process> whereProcesses(String workflowName)
	{
		return (new ArrayList<Process>());
	}*/
	
	public static WorkflowType findWorkflow(List<WorkflowType> allWF, String name)
	{	
		for(WorkflowType wf:allWF)
			if(wf.getName().trim().equals(name))
				return wf;
		
		return null;
	}

	public static List<ActionType> allActions(WorkflowType wf)
	{
		if(wf != null)
			return wf.getAction();
		return null;
	}

	public static ActionType findAction(WorkflowType wf, String name)
	{	
		if(wf != null)
			for(ActionType act:allActions(wf))
				if(act.getName().trim().equals(name))
					return act;
		
		return null;
	}
}
