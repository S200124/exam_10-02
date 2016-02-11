package it.polito.dp2.WF.lab4.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import it.polito.dp2.WF.*;
import it.polito.dp2.WF.lab3.Refreshable;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Set;

import org.apache.tools.ant.*;


public class WFTest22 {
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
    
    class ActionReaderComparator implements Comparator<ActionReader> {
        public int compare(ActionReader f0, ActionReader f1) {
        	return f0.getName().compareTo(f1.getName());
        }
    }
    
    class WorkflowReaderComparator implements Comparator<WorkflowReader> {
        public int compare(WorkflowReader f0, WorkflowReader f1) {
        	return f0.getName().compareTo(f1.getName());
        }
    }
    
    class ProcessReaderComparator implements Comparator<ProcessReader> {
        public int compare(ProcessReader f0, ProcessReader f1) {
        	return f0.getStartTime().compareTo(f1.getStartTime());
        }
    }

    
	private static WorkflowMonitor referenceWorkflowMonitor;	// reference data generator
	private static WorkflowMonitor testWorkflowMonitor;			// implementation under test
	private static long client4exitcode;						// the exit code of client2
	
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    	try {
    	// Create reference data generator
        System.setProperty("it.polito.dp2.WF.WorkflowMonitorFactory", "it.polito.dp2.WF.Random.WorkflowMonitorFactoryImpl");

        referenceWorkflowMonitor = WorkflowMonitorFactory.newInstance().newWorkflowMonitor();

        // Create implementation under test
        System.setProperty("it.polito.dp2.WF.WorkflowMonitorFactory", "it.polito.dp2.WF.sol4.client3.WorkflowMonitorFactory");

        testWorkflowMonitor = WorkflowMonitorFactory.newInstance().newWorkflowMonitor();
        
        // Run client4 target from buildTest2.xml
        Project project = new Project();
        project.init();
        File buildFile = new File("buildTest2.xml");
        ProjectHelper.configureProject(project, buildFile);
        try {
        	project.executeTarget("run-client4");
        	client4exitcode = 0;
        } catch (BuildException be) {
        	System.out.println("Client4 exited with errors");
        	client4exitcode=1;
        }
        
        // refresh client3
    	Refreshable refreshable = (Refreshable) testWorkflowMonitor; 
        refreshable.refresh();
        
    	} catch (Exception e) {
    		System.out.println("Warning: unable to complete pre-processing for junit tests of part 2"); 
    	}
                
    }
    
    @Before
    public void setUp() throws Exception {
        assertNotNull("Internal tester error during test setup: null reference", referenceWorkflowMonitor);
        assertNotNull("Could not run tests: the implementation under test generated a null WorkflowMonitor", testWorkflowMonitor);
    }
	
	@Test
    public final void testExitCodeOfClient2() {
		assertEquals("client4 unexpectedly failed",0,client4exitcode);
	}
	
    @Test
    public final void testGetWorkflows() {
    		// call getWorkflows on the two implementations
			Set<WorkflowReader> rs = referenceWorkflowMonitor.getWorkflows();
			Set<WorkflowReader> ts = testWorkflowMonitor.getWorkflows();
			
			// if one of the two calls returned null while the other didn't return null, the test fails
	        if ((rs == null) && (ts != null) || (rs != null) && (ts == null)) {
	            fail("getWorkflows returns null when it should return non-null or vice versa");
	            return;
	        }

	        // if both calls returned null, there are no workflows, and the test passes
	        if ((rs == null) && (ts == null)) {
	            assertTrue("There are no Workflows!", true);
	            return;
	        }
	        
	        // check that the number of workflows matches
	        assertEquals("Wrong Number of Workflows", rs.size()+1, ts.size());
	        
    }
	
    @Test
    public final void testGetProcesses() {
    	// call getProcesses on the two implementations
    	Set<ProcessReader> rs = referenceWorkflowMonitor.getProcesses();
		Set<ProcessReader> ts = testWorkflowMonitor.getProcesses();
		
        // check that the number of processes matches
        assertEquals("Wrong Number of Processes", rs.size()+1, ts.size());
		
    }
    
    @Test
    public final void testGetWorkflow() {
    	// call getWorkflow on the implementation under test and check NormalSale workflow exists with one process
		WorkflowReader wr = testWorkflowMonitor.getWorkflow("NormalSale");
		assertNotNull("Expected NormalSale workflow does not exist",wr);
		
		Set<ProcessReader> ps = wr.getProcesses();
		assertNotNull("getProcesses returned null",ps);	
		
        // check that the number of processes matches
        assertEquals("Wrong Number of Processes in NormalSale workflow", 1, ps.size());
		
    }
    
}
