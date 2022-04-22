import com.sun.tools.attach.*;

import java.io.IOException;
import java.util.List;

public class TestMachineAgentMain {

    public static void main(String[] args) throws IOException, AttachNotSupportedException, AgentLoadException, AgentInitializationException {
        System.out.println("running JVM start ");

        // 获取当前系统所有 运行中的 虚拟机
        List<VirtualMachineDescriptor> list = VirtualMachine.list();

        for (VirtualMachineDescriptor vmd : list) {
            // 如果虚拟机的名称 xxx 则 该虚拟机为目标虚拟机，获取该虚拟机的 pid
            // 然后加载 agent.jar 发送给该虚拟机
            System.out.println(vmd.displayName());

            if(vmd.displayName().endsWith("TestMachineAgentMain")){
                VirtualMachine virtualMachine = VirtualMachine.attach(vmd.id());
                virtualMachine.loadAgent("/Users/cc/projects/agent/lib/java-agent-1.0-SNAPSHOT.jar");
                virtualMachine.detach();
            }
        }
    }

}
