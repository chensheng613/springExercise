/*package com.mypro.axis2;

import javax.xml.namespace.QName;

import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;

public class TestAxis2 {

	public static void main(String[] args) {
		String namespace = "http://webservice.mypro.com";
		String methodName = "hello";
		try {
			RPCServiceClient client = new RPCServiceClient();
			Options options = client.getOptions();
			EndpointReference tartgetService = new EndpointReference("http://localhost:8080/springExercise/services/SpringAixsService?wsdl");
			options.setTo(tartgetService);
			Object[] objs = new Object[]{};
			Class[] classes = new Class[]{String.class};
			QName qname = new QName(namespace, methodName);
			 System.out.println(client.invokeBlocking(qname, objs, classes)[0]);
		} catch (AxisFault e) {
			e.printStackTrace();
		}
	}

}
*/