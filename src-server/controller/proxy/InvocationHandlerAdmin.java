package controller.proxy;

import java.lang.reflect.*;

public class InvocationHandlerAdmin implements InvocationHandler, java.io.Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5369404937254974724L;
	RemoteServerController controller;

    public InvocationHandlerAdmin(RemoteServerController controller) {
        this.controller = controller;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws IllegalAccessException, Throwable {
    	System.out.println("GRANTED RIGHTS : Admin");
        try {
        	try {
                return method.invoke(controller, args);
            } catch (InvocationTargetException e) {
                throw e.getCause();
            }
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

}
