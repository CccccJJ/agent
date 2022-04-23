package agentmain;

import com.sun.tools.attach.*;

import java.io.IOException;
import java.util.List;

public class AttachTestAgentMain {

    public static void main(String[] args) throws IOException, AttachNotSupportedException, AgentLoadException, AgentInitializationException {
        List<VirtualMachineDescriptor> list = VirtualMachine.list();

        for (VirtualMachineDescriptor virtualMachineDescriptor : list) {
            if(virtualMachineDescriptor.displayName().endsWith("TestAgentMain")){
                VirtualMachine attach = VirtualMachine.attach(virtualMachineDescriptor);
                attach.loadAgent("/Users/cc/projects/agent/lib/agentmain-agent-1.0-SNAPSHOT.jar","param");
            }
        }
    }

}
