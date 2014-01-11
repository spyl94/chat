package controller.proxy;

import java.lang.reflect.*;

public class InvocationHandlerUser implements InvocationHandler {

    RemoteServerController controller;

    public InvocationHandlerUser(RemoteServerController controller) {
        this.controller = controller;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws IllegalAccessException, Throwable {

        try {
            if (method.getName().startsWith("get")) {
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
