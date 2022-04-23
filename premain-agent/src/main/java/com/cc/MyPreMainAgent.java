package com.cc;

import java.lang.instrument.Instrumentation;

public class MyPreMainAgent {

    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("MyPreMainAgent ......");
        System.out.println("agentArgs: " + agentArgs);
    }

}
