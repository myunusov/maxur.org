package org.maxur.commons.component.application.osgi.classresolver;

public class PrimitivesTypeMap extends ClassResolver {

    public Class<?> resolve(final String className) throws ClassNotFoundException {
        switch (className) {
            case "byte":
                return byte.class;
            case "short":
                return short.class;
            case "int":
                return int.class;
            case "long":
                return long.class;
            case "float":
                return float.class;
            case "double":
                return double.class;
            case "boolean":
                return boolean.class;
            case "char":
                return char.class;
            default:
                return super.resolve(className);
        }
    }
}