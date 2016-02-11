package it.polito.dp2.WF.sol4.server2;


import it.polito.dp2.WF.lab4.gen.server2.ProcessType;
import it.polito.dp2.WF.lab4.gen.server2.WorkflowType;

import java.util.concurrent.Executors;

import javax.xml.ws.Endpoint;
import javax.xml.ws.Holder;

public class WorkflowServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		WorkflowManager wfm;
		try {
			System.out.println("Ciaone");
			wfm = new WorkflowManager();
			
			for(WorkflowType wt:wfm.getWorkflows())
				System.out.println(wt.getName());/*
			
			for(ProcessType pt:wfm.getProcesses())
				System.out.println(pt.getStartAt());
			*/
			Holder<String> hs = new Holder<String>();
			
			WorkflowSearchingImpl wsi = new WorkflowSearchingImpl(wfm);
			WorkflowManagingImpl wmi = new WorkflowManagingImpl(wfm);
			
			wmi.createProcess(wsi.getWorkflows(hs).get(0).getName());
			for(ProcessType pt:wsi.getProcesses(hs))
				System.out.println(pt.getStartAt() + ", " + pt.getActionStatus().get(0).getActionName());
			
			System.out.println("Publishing http://localhost:7081/wfinfo");
	        Endpoint endpoint = Endpoint.create(new WorkflowSearchingImpl(wfm));
	        endpoint.setExecutor(Executors.newFixedThreadPool(5));
	   	    endpoint.publish("http://localhost:7081/wfinfo");
	        
	        System.out.println("Publishing http://localhost:7080/wfcontrol");
	        Endpoint endpoint2 = Endpoint.create(new WorkflowManagingImpl(wfm));
	        endpoint2.setExecutor(Executors.newFixedThreadPool(5));
	   	    endpoint2.publish("http://localhost:7080/wfcontrol");
		} catch (Exception e) {
			System.err.println("Unable to start service: "+e.getMessage());
			e.printStackTrace();
		}

	}

}
