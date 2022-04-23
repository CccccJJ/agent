package com.cc;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.security.ProtectionDomain;

public class FruitAgentMain {

    public static void agentmain(String agentArgs, Instrumentation inst) throws ClassNotFoundException, UnmodifiableClassException {
        inst.addTransformer(new FruitTransformer(), true);
        inst.retransformClasses(Class.forName("model.Fruit"));
        System.out.println("re-transform success");
    }

    static class FruitTransformer implements ClassFileTransformer {

        @Override
        public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
            if (!className.equals("model/Fruit")) {
                return classfileBuffer;
            }

            String fileName = "/Users/cc/projects/agent/test-agent/src/main/java/model/Fruit_banana.class";

            return getClassBytes(fileName);
        }

        public byte[] getClassBytes(String filename) {
            File file = new File(filename);
            try (FileInputStream fileInputStream = new FileInputStream(file);
                 ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
                long length = file.length();
                byte[] bytes = new byte[(int) length];

                int n;
                while ((n = fileInputStream.read(bytes)) != -1) {
                    byteArrayOutputStream.write(bytes, 0, n);
                }

                return byteArrayOutputStream.toByteArray();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}
