package controller.proxy;

import java.lang.reflect.*;

public class InvocationHandlerAnonymous implements InvocationHandler, java.io.Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -2269956763025679444L;
	RemoteServerController controller;

    public InvocationHandlerAnonymous(RemoteServerController controller) {
        this.controller = controller;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws IllegalAccessException, Throwable {
        try {
            if (method.getName().equals("login") || method.getName().equals("register")) {
                return method.invoke(controller, args);
            } else {
                throw new IllegalAccessException();
            }
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

}
