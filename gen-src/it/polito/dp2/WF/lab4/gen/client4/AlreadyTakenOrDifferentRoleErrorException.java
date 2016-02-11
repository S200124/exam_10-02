
package it.polito.dp2.WF.lab4.gen.client4;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "AlreadyTakenOrDifferentRoleError", targetNamespace = "http://pad.polito.it/ws/Workflow/")
public class AlreadyTakenOrDifferentRoleErrorException
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private AlreadyTakenOrDifferentRoleError faultInfo;

    /**
     * 
     * @param message
     * @param faultInfo
     */
    public AlreadyTakenOrDifferentRoleErrorException(String message, AlreadyTakenOrDifferentRoleError faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param message
     * @param faultInfo
     * @param cause
     */
    public AlreadyTakenOrDifferentRoleErrorException(String message, AlreadyTakenOrDifferentRoleError faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: it.polito.dp2.WF.lab4.gen.client4.AlreadyTakenOrDifferentRoleError
     */
    public AlreadyTakenOrDifferentRoleError getFaultInfo() {
        return faultInfo;
    }

}