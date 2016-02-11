package it.polito.dp2.WF.sol4.server2;

import it.polito.dp2.WF.*;
import it.polito.dp2.WF.lab4.gen.packageRemoteService.*;
import it.polito.dp2.WF.lab4.gen.packageRemoteService.Action;
import it.polito.dp2.WF.lab4.gen.server2.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;

import javax.xml.ws.*;
import javax.xml.datatype.XMLGregorianCalendar;

public class WorkflowManager {
	
	private static Logger logger = Logger.getLogger(WorkflowManager.class.getName());
	private WorkflowMonitor monitor;
	private List<WorkflowType> workflows;
	private long lastMod;

    public WorkflowManager() throws Exception {
    	System.setProperty("it.polito.dp2.WF.WorkflowMonitorFactory", "it.polito.dp2.WF.Random.WorkflowMonitorFactoryImpl");
		WorkflowMonitorFactory factory = WorkflowMonitorFactory.newInstance();
		monitor = factory.newWorkflowMonitor();
		
		logger.fine("Creating Workflows...");
		workflows = createWorkflows();
		logger.fine("Workflows created.");
		setTime();
    }
    
    public synchronized List<WorkflowType> getWorkflows() {
    	setTime();
    	return workflows;
    }
    
    public synchronized String getLastModification() {
    	return String.valueOf(lastMod);
    }
    
    public synchronized List<ProcessType> getProcesses() {
	    	List<ProcessType> ret = new ArrayList<ProcessType>();
	    	for(WorkflowType wt:workflows)
				for(ProcessType pt:wt.getProcess())
					ret.add(pt);
	    	
	    	setTime();
	    	return ret;
    }
    
    public synchronized void createProcessInWorkflow(String wf) throws SystemError_Exception {
    	boolean find = false;
    	for(WorkflowType wft:workflows)
			if(wft.getName().equals(wf))
			{
				wft.getProcess().add(returnNewProcess(wft.getAction()));
				find = true;
				break;
			}
    	
    		setTime();
    	
    		if(!find)
    		{
    			WorkflowType nwft = getWorkflowRemoteInfo(wf);
    			if(nwft != null)
    			{
    				nwft.getProcess().add(returnNewProcess(nwft.getAction()));
    				workflows.add(nwft);
    			}
    			else
    				nwft.getAction();
    		}
    }
    
    private void setTime () {
    	Calendar cal = Calendar.getInstance();
    	lastMod = cal.getTimeInMillis();
    }
    
    private ProcessType returnNewProcess(List<ActionType> lat) {
    	ProcessType prc = new ProcessType();
		
		DateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
		Calendar cal = Calendar.getInstance();
		
		prc.setStartAt(dateFormat.format(cal.getTime()));
		for(ActionType at:lat)
			if(at.isAutomInst())
			{
				ActionStatusType as = new ActionStatusType();
				as.setActionName(at.getName());
				prc.getActionStatus().add(as);
			}
		return prc;
    }
    
    private WorkflowType getWorkflowRemoteInfo(String wfn) {
    	URL url;
		try {
			url = new URL("http://localhost:8181/WorkflowInfoService?wsdl");
		} catch (MalformedURLException e) {
			url = null;
		}
		
		if(url != null)
		{
			WorkflowInfoService service = new WorkflowInfoService(url);
			WorkflowInfo proxy = service.getWorkflowInfoPort();
			
			Holder<XMLGregorianCalendar> hx = new Holder<XMLGregorianCalendar>();
			List<String> ls = new ArrayList<String>();
			Holder<List<Workflow>> hlw = new Holder<List<Workflow>>();
			Holder<List<String>> hls = new Holder<List<String>>();
			
			//System.out.println("Ready to invoke remote operation 1...");
			proxy.getWorkflowNames(hx, hls);
			for(String name:hls.value)
				ls.add(name);
			
			//System.out.println("Ready to invoke remote operation 2...");
			try {
				proxy.getWorkflows(ls, hx, hlw);
				for(Workflow wf:hlw.value)
					if(wf.getName().equals(wfn))
					{
						WorkflowType newWorkflow = new WorkflowType();
						newWorkflow.setName(wfn);
						for(Action act:wf.getAction())
						{
							ActionType newAct = new ActionType();
							newAct.setName(act.getName());
							newAct.setRole(act.getRole());
							newAct.setAutomInst(act.isAutomaticallyInstantiated());
							
							FollowingActionsType fat = new FollowingActionsType();
							for(String str:act.getNextAction())
								fat.getActionName().add(str);
							
							if(fat.getActionName().size() == 0)
								newAct.setType("process");
							else
								newAct.setType("simple");
							
							newAct.setFollowingActions(fat);
							newWorkflow.getAction().add(newAct);
						}
						return newWorkflow;
					}
						
			} catch (UnknownNames_Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
    }
    
    private List<WorkflowType> createWorkflows() {
    	List<WorkflowType> ret = new ArrayList<WorkflowType>();
    	try
		{
			//List<Workflow> wfs = new ArrayList<Workflow>();
			Set<WorkflowReader> set = monitor.getWorkflows();
			
			//ret.setXmlns("http://www.w3schools.com");
			
			for (WorkflowReader wfr: set)
			{
				/*Element workflow = doc.createElement("workflow");
				rootElement.appendChild(workflow);*/
				WorkflowType wf = new WorkflowType();
				
				//List<Action> acts = new ArrayList<Action>();
				
				//workflow.setAttribute("name", wfr.getName());
				wf.setName(wfr.getName());
				for(ProcessType pt:appendProcesses(wfr.getName()))
					wf.getProcess().add(pt);
				
				// Print actions
				//System.out.println("Actions:");
				Set<ActionReader> setAct = wfr.getActions();
				//printHeader("Action Name\tRole\t\tAutom.Inst.\tSimple/Process\tWorkflow\tNext Possible Actions");
				for (ActionReader ar: setAct)
				{
					/*Element action = doc.createElement("action");
					workflow.appendChild(action);*/
					ActionType act = new ActionType();
					
					/*Element role = doc.createElement("role");
					role.appendChild(doc.createTextNode(ar.getRole()));
					action.appendChild(role);*/
					act.setRole(ar.getRole());
					
					//action.setAttribute("name", ar.getName());
					act.setName(ar.getName());
					//action.setAttribute("automInst", String.valueOf(ar.isAutomaticallyInstantiated()));
					act.setAutomInst(ar.isAutomaticallyInstantiated());
					
					if (ar instanceof SimpleActionReader)
					{
						//action.setAttribute("type", "simple");
						act.setType("simple");
						
						Set<ActionReader> setNxt = ((SimpleActionReader)ar).getPossibleNextActions();
						if(!setNxt.isEmpty())
						{
							/*Element followingActions = doc.createElement("followingActions");
							action.appendChild(followingActions);*/
							FollowingActionsType fa = new FollowingActionsType();
							//List<String> fan = new ArrayList<String>();
							
							for (ActionReader nAct: setNxt)
								fa.getActionName().add(nAct.getName());
								/*Element actionName = doc.createElement("actionName");
								actionName.appendChild(doc.createTextNode(nAct.getName()));
								followingActions.appendChild(actionName);*/
							act.setFollowingActions(fa);
						}
					}
					else if (ar instanceof ProcessActionReader)
					{
						//action.setAttribute("type", "process");
						act.setType("process");
						act.setNestedWorkflow(((ProcessActionReader)ar).getActionWorkflow().getName());
						
						/*Element nestedWorkflow = doc.createElement("nestedWorkflow");
						nestedWorkflow.setAttribute("workflowName", ((ProcessActionReader)ar).getActionWorkflow().getName());
						action.appendChild(nestedWorkflow);*/
					}
					wf.getAction().add(act);
				}
				ret.add(wf);
			}
			
			return ret;
		}
		catch(Exception ex)
		{
			return null;
		}
    }
    
    private List<ProcessType> appendProcesses(String workflowName) {
		List<ProcessType> ret = new ArrayList<ProcessType>();
		try
		{
			// Get the list of processes
			Set<ProcessReader> set = monitor.getProcesses();
			
			// For each process print related data
			for (ProcessReader wfr: set)
			{
				if(wfr.getWorkflow().getName() == workflowName)
				{
					/*Element process = doc.createElement("process");
					rootElement.appendChild(process);*/
					ProcessType prc = new ProcessType();
					//List<ActionStatus> ass = new ArrayList<ActionStatus>();
					
					prc.setStartAt(String.valueOf(wfr.getStartTime().getTime()));
					//process.setAttribute("startAt", String.valueOf(wfr.getStartTime().getTime()));
					/*System.out.println("Process started at " + 
										dateFormat.format(wfr.getStartTime().getTime()) +
							            " "+"- Workflow " + wfr.getWorkflow().getName());
					System.out.println("Status:");*/
					List<ActionStatusReader> statusSet = wfr.getStatus();
	
					for (ActionStatusReader asr : statusSet)
					{
						/*Element actionStatus = doc.createElement("actionStatus");
						process.appendChild(actionStatus);*/
						ActionStatusType as = new ActionStatusType();
						
						as.setActionName(asr.getActionName());
						
						//System.out.print(asr.getActionName()+"\t");
						if (asr.isTakenInCharge())
						{
							/*Element actor = doc.createElement("actor");
							actionStatus.appendChild(actor);*/
							ActorType act = new ActorType();
							
							act.setName(asr.getActor().getName());
							act.setRole(asr.getActor().getRole());
							
							as.setActor(act);
							//actor.setAttribute("name", asr.getActor().getName());
							//System.out.print(asr.getActor().getName()+"\t\t");
							/*Element role = doc.createElement("role");
							role.appendChild(doc.createTextNode(asr.getActor().getRole()));
							actor.appendChild(role);*/
						}
						
						/*Element actionName = doc.createElement("actionName");
						actionName.appendChild(doc.createTextNode(asr.getActionName()));
						actionStatus.appendChild(actionName);*/
		
						if (asr.isTerminated())
							as.setTerminatedAt(String.valueOf(asr.getTerminationTime().getTimeInMillis()));
							//as.setTerminatedAt(String.valueOf(dateFormat.format(asr.getTerminationTime().getTime())));
							//actionStatus.setAttribute("terminatedAt", String.valueOf(dateFormat.format(asr.getTerminationTime().getTime())));
							//System.out.println(dateFormat.format(asr.getTerminationTime().getTime()));
						
						//ass.add(as);
						prc.getActionStatus().add(as);
					}
					ret.add(prc);
				}
			}
			return ret;
		}
		catch(Exception ex)
		{
			return null;
		}
	}
}
