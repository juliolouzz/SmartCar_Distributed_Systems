package com.smartcar.grpc;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;
import java.io.IOException;
import java.net.InetAddress;

public class ServiceRegistration {

    public static void registerService(String serviceType, String serviceName, int port) {
        try {
            JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
            ServiceInfo serviceInfo = ServiceInfo.create(serviceType, serviceName, port, "path=index.html");
            jmdns.registerService(serviceInfo);

            System.out.println(serviceName + " service registered");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
