package org.xfei.injector;

import java.util.List;

import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;

public class Attach {

	public static void main(String[] args) {
		VirtualMachine vm = null;
        List<VirtualMachineDescriptor> listAfter = null;
        List<VirtualMachineDescriptor> listBefore = null;
        listBefore = VirtualMachine.list();
        while (true) {
            try {
                listAfter = VirtualMachine.list();
                if (listAfter.size() <= 0)
                    continue;
                for (VirtualMachineDescriptor vmd : listAfter) {
                    vm = VirtualMachine.attach(vmd);
                    listBefore.add(vmd);
                    System.out.println("Agent found, start infecting....");
                    Thread.sleep(1000);
                    if (null != vm) {
                        vm.loadAgent(args[0]);//better to pass absolute path
                    	//vm.loadAgentLibrary(args[0]);
                        vm.detach();
                    }
                }
                break;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
	}

}
