package controller.proxy;

import java.lang.reflect.*;

public class InvocationHandlerUser implements InvocationHandler, java.io.Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3641171793502056678L;
	RemoteServerController controller;

    public InvocationHandlerUser(RemoteServerController controller) {
        this.controller = controller;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws IllegalAccessException, Throwable {
    	System.out.println("GRANTED RIGHTS : User");
        try {
                return method.invoke(controller, args);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

}
