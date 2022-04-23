package com.cc;

import java.lang.instrument.Instrumentation;

public class MyAgentMain {

    public static void agentmain(String agentArgs, Instrumentation inst) {
        System.out.println("MyAgentMain ......");
        System.out.println("agentArgs: " + agentArgs);
    }
}
