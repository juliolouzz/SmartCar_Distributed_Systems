package com.smartcar.grpc;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;

public class EngineDiagnosticsServiceImpl extends EngineDiagnosticsServiceGrpc.EngineDiagnosticsServiceImplBase {

    @Override
    public void runDiagnostics(RunDiagnosticsRequest request, StreamObserver<RunDiagnosticsResponse> responseObserver) {
        try {
            String engineId = request.getEngineId();
            System.out.println("Received engine ID: " + engineId); // Debug print

            if (engineId == null || engineId.isEmpty()) {
                System.out.println("Invalid engine ID detected: " + engineId); // Debug print
                responseObserver.onError(Status.INVALID_ARGUMENT.withDescription("Engine ID cannot be null or empty").asRuntimeException());
                return;
            }

            // Perform diagnostics (mocked for simplicity)
            String diagnosticReport = "Engine " + engineId + " is running smoothly.";
            RunDiagnosticsResponse response = RunDiagnosticsResponse.newBuilder()
                    .setDiagnosticReport(diagnosticReport)
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            e.printStackTrace(); // Print the stack trace for debugging
            responseObserver.onError(Status.INTERNAL.withDescription("Internal server error").withCause(e).asRuntimeException());
        }
    }

    @Override
    public void predictMaintenanceNeeds(PredictMaintenanceNeedsRequest request, StreamObserver<PredictMaintenanceNeedsResponse> responseObserver) {
        try {
            String engineId = request.getEngineId();
            // Predict maintenance needs (mocked for simplicity)
            String maintenanceSchedule = "Next maintenance for engine " + engineId + " is in 6 months.";

            PredictMaintenanceNeedsResponse response = PredictMaintenanceNeedsResponse.newBuilder()
                    .setMaintenanceSchedule(maintenanceSchedule)
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            e.printStackTrace(); // Print the stack trace for debugging
            responseObserver.onError(Status.INTERNAL.withDescription("Internal server error").withCause(e).asRuntimeException());
        }
    }

    @Override
    public void reportEngineHealth(ReportEngineHealthRequest request, StreamObserver<ReportEngineHealthResponse> responseObserver) {
        try {
            String engineId = request.getEngineId();
            // Report engine health (mocked for simplicity)
            String healthReport = "Engine " + engineId + " health is good.";

            ReportEngineHealthResponse response = ReportEngineHealthResponse.newBuilder()
                    .setHealthReport(healthReport)
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            e.printStackTrace(); // Print the stack trace for debugging
            responseObserver.onError(Status.INTERNAL.withDescription("Internal server error").withCause(e).asRuntimeException());
        }
    }

    @Override
    public void getLiveDiagnostics(LiveDiagnosticsRequest request, StreamObserver<LiveDiagnosticsResponse> responseObserver) {
        try {
            String engineId = request.getEngineId();
            // Mocked live diagnostics streaming
            for (int i = 0; i < 5; i++) {
                LiveDiagnosticsResponse response = LiveDiagnosticsResponse.newBuilder()
                        .setStatusUpdate("Live update for engine " + engineId + ": status " + i)
                        .build();
                responseObserver.onNext(response);
                Thread.sleep(1000); // simulate delay
            }
            responseObserver.onCompleted();
        } catch (InterruptedException e) {
            e.printStackTrace(); // Print the stack trace for debugging
            responseObserver.onError(Status.INTERNAL.withDescription("Internal server error").withCause(e).asRuntimeException());
        } catch (Exception e) {
            e.printStackTrace(); // Print the stack trace for debugging
            responseObserver.onError(Status.INTERNAL.withDescription("Unexpected error").withCause(e).asRuntimeException());
        }
    }
}
