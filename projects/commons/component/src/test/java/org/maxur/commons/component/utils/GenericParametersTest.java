package org.maxur.commons.component.utils;

import org.junit.Test;

import java.util.Collection;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * @author Maxim Yunusov
 * @version 1.0 19.05.12
 */
public class GenericParametersTest {

    static class A<K, L> {
        // String, Integer
    }
    static class B<P, Q, R extends Collection> extends A<Q, P> {
        // Integer, String, Set
    }
    static class C<X extends Comparable<String>, Y, Z> extends B<Z, X, Set<Long>> {
        // String, Double, Integer
    }
    static class D<M, N extends Comparable<Double>> extends C<String, N, M> {
        // Integer, Double
    }
    static class E extends D<Integer, Double> {
    }


    @Test
    public void testMethodInnerClass() throws Exception {
        final Class clazz = GenericParameters.getParameter(new D<String, Double>(){}.getClass(), D.class, 0);
        assertEquals(String.class, clazz);
    }

    @Test (expected = IllegalStateException.class)
    public void testMethodInnerClassWithStaticMethod() throws Exception {
        final D d1 = new D<String, Double>();
        final Class clazz = GenericParameters.getParameter(d1.getClass(), C.class, 2);
        assertEquals(String.class, clazz);
    }

    @Test(expected = java.lang.ArrayIndexOutOfBoundsException.class)
    public void testCaseClassDParameter2() throws Exception {
        final Class clazz = GenericParameters.getParameter(E.class, D.class, 2);
        assertEquals(Double.class, clazz);
    }

    @Test(expected = java.lang.IllegalArgumentException.class)
    public void testCaseNonSuperclass() throws Exception {
        final Class clazz = GenericParameters.getParameter(C.class, E.class, 2);
        assertEquals(Double.class, clazz);
    }

    @Test
    public void testCaseClassDParameter0() throws Exception {
        final Class clazz = GenericParameters.getParameter(E.class, D.class, 0);
        assertEquals(Integer.class, clazz);
    }

    @Test
    public void testCaseClassDParameter1() throws Exception {
        final Class clazz = GenericParameters.getParameter(E.class, D.class, 1);
        assertEquals(Double.class, clazz);
    }

    @Test
    public void testCaseClassCParameter0() throws Exception {
        final Class clazz = GenericParameters.getParameter(E.class, C.class, 0);
        assertEquals(String.class, clazz);
    }

    @Test
    public void testCaseClassCParameter1() throws Exception {
        final Class clazz = GenericParameters.getParameter(E.class, C.class, 1);
        assertEquals(Double.class, clazz);
    }

    @Test
    public void testCaseClassCParameter2() throws Exception {
        final Class clazz = GenericParameters.getParameter(E.class, C.class, 2);
        assertEquals(Integer.class, clazz);
    }

    @Test
    public void testCaseClassBParameter0() throws Exception {
        final Class clazz = GenericParameters.getParameter(E.class, B.class, 0);
        assertEquals(Integer.class, clazz);
    }

    @Test
    public void testCaseClassBParameter1() throws Exception {
        final Class clazz = GenericParameters.getParameter(E.class, B.class, 1);
        assertEquals(String.class, clazz);
    }

    @Test
    public void testCaseClassBParameter2() throws Exception {
        final Class clazz = GenericParameters.getParameter(E.class, B.class, 2);
        assertEquals(Set.class, clazz);
    }


    @Test
    public void testCaseClassAParameter0() throws Exception {
        final Class clazz = GenericParameters.getParameter(E.class, A.class, 0);
        assertEquals(String.class, clazz);
    }

    @Test
    public void testCaseClassAParameter1() throws Exception {
        final Class clazz = GenericParameters.getParameter(E.class, A.class, 1);
        assertEquals(Integer.class, clazz);
    }



}
