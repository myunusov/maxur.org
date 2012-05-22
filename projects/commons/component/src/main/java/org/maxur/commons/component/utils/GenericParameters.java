package org.maxur.commons.component.utils;

import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Stack;

/**
 *
 * Thanks to Alex Tracer. see http://habrahabr.ru/post/66593/
 *
 * @author Maxim Yunusov
 * @version 1.0 19.05.12
 *
 */
public class GenericParameters {

    private final Stack<ParameterizedType> genericClasses = new Stack<>();

    private final Class actualClass;

    private final Class genericClass;

    public GenericParameters(final Class actualClass, final Class genericClass) {
        this.actualClass = actualClass;
        this.genericClass = genericClass;
    }

    public static Class getParameter(
            final Class actualClass,
            final Class genericClass,
            int index
    ) {
        return new GenericParameters(actualClass, genericClass).get(index);
    }

    private Stack<ParameterizedType> initGenericClasses(final Class clazz) {
        final Type genericSuperclass = clazz.getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            genericClasses.push((ParameterizedType) genericSuperclass);
        } else {
            genericClasses.clear();
        }
        if (!getRawType(genericSuperclass).equals(genericClass)) {
            return initGenericClasses(clazz.getSuperclass());
        } else {
            return genericClasses;
        }
    }

    public Class get(int index) {
        assertIsGenericClassSuperclassForActualClass();
        initGenericClasses(actualClass);
        final Type type = extractResult(this.genericClasses, index);
        validateResult(actualClass, type);
        return (Class) type;
    }

    private void assertIsGenericClassSuperclassForActualClass() {
        if (!genericClass.isAssignableFrom(actualClass.getSuperclass())) {
            throw new IllegalArgumentException(String.format(
                    "Class %s is not a superclass of %s.",
                    genericClass.getName(),
                    actualClass.getName()
            ));
        }
    }

    private static void validateResult(Class actualClass, Type result) {
        if (result == null) {
            throw new IllegalStateException("Unable to determine actual parameter type for "
                    + actualClass.getName() + ".");
        }
        if (!(result instanceof Class)) {
            throw new IllegalStateException("Actual parameter type for " + actualClass.getName() + " is not a Class.");
        }
    }


    private static Type extractResult(final Stack<ParameterizedType> classes, int index) {
        final Type result = classes.pop().getActualTypeArguments()[index];
        if (result instanceof TypeVariable) {
            if (classes.empty()) {
                throw new IllegalStateException("Unable to resolve type variable " + result + "."
                        + " Try to replace instances of parametrized class with its non-parameterized subtype.");
            }
            return extractResult(classes, getParameterTypeDeclarationIndex((TypeVariable) result));
        } else {
            return getRawType(result);
        }
    }

    private static int getParameterTypeDeclarationIndex(final TypeVariable typeVariable) {
        final GenericDeclaration genericDeclaration = typeVariable.getGenericDeclaration();
        final TypeVariable[] typeVariables = genericDeclaration.getTypeParameters();
        for (int i = 0; i < typeVariables.length; i++) {
            if (typeVariables[i].equals(typeVariable)) {
                return i;
            }
        }
        throw new IllegalStateException(String.format("Argument %s is not found in %s.",
                typeVariable.toString(),
                genericDeclaration.toString()
        ));
    }

    private static Type getRawType(final Type type) {
        return type instanceof ParameterizedType ?
                ((ParameterizedType) type).getRawType() :
                type;
    }


}
