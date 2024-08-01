package com.smartcar.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class SmartCarServer {

    public static void main(String[] args) throws Exception {
        Server engineDiagnosticsServer = ServerBuilder.forPort(50051)
                .addService(new EngineDiagnosticsServiceImpl())
                .build()
                .start();
        ServiceRegistration.registerService("_engine._tcp.local.", "EngineDiagnosticsService", 50051);

        Server comfortControlServer = ServerBuilder.forPort(50052)
                .addService(new ComfortControlServiceImpl())
                .build()
                .start();
        ServiceRegistration.registerService("_comfort._tcp.local.", "ComfortControlService", 50052);

        Server safetyNavigationServer = ServerBuilder.forPort(50053)
                .addService(new SafetyNavigationServiceImpl())
                .build()
                .start();
        ServiceRegistration.registerService("_safety._tcp.local.", "SafetyNavigationService", 50053);

        System.out.println("Servers started...");
        
        engineDiagnosticsServer.awaitTermination();
        comfortControlServer.awaitTermination();
        safetyNavigationServer.awaitTermination();
    }
}
