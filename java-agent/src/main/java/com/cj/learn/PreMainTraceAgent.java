package com.cj.learn;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.security.ProtectionDomain;

public class PreMainTraceAgent {

    public static void premain(String agentArgs, Instrumentation inst) throws UnmodifiableClassException {
        System.out.println("agentArgs: " + agentArgs);

        inst.addTransformer(new DefineTransformer(), true);

//        MyClassTransformer transformer = new MyClassTransformer();
//        inst.addTransformer(transformer, true);
    }

    static class DefineTransformer implements ClassFileTransformer {

        @Override
        public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
            System.out.println("premain load Class: " + className);
            return classfileBuffer;
        }
    }

}