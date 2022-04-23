package com.cc;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

public class FruitPremainAgent {

    public static void premain(String agentArgs, Instrumentation inst) {
        inst.addTransformer(new FruitTransformer());
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
