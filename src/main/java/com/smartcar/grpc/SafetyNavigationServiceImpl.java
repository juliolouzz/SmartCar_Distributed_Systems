package com.smartcar.grpc;

import io.grpc.stub.StreamObserver;

public class SafetyNavigationServiceImpl extends SafetyNavigationServiceGrpc.SafetyNavigationServiceImplBase {

    @Override
    public void realTimeTrafficAnalysis(RealTimeTrafficAnalysisRequest request, StreamObserver<RealTimeTrafficAnalysisResponse> responseObserver) {
        String location = request.getLocation();
        // Analyze real-time traffic (mocked for simplicity)
        String trafficReport = "Traffic is light in " + location;

        RealTimeTrafficAnalysisResponse response = RealTimeTrafficAnalysisResponse.newBuilder()
                .setTrafficReport(trafficReport)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void hazardDetectionAndAlerts(HazardDetectionAndAlertsRequest request, StreamObserver<HazardDetectionAndAlertsResponse> responseObserver) {
        String location = request.getLocation();
        // Detect hazards (mocked for simplicity)
        String hazardAlerts = "No hazards detected in " + location;

        HazardDetectionAndAlertsResponse response = HazardDetectionAndAlertsResponse.newBuilder()
                .setHazardAlerts(hazardAlerts)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void routeOptimization(RouteOptimizationRequest request, StreamObserver<RouteOptimizationResponse> responseObserver) {
        String destination = request.getDestination();
        // Optimize route (mocked for simplicity)
        String optimizedRoute = "Optimized route to " + destination + " is via Highway 101.";

        RouteOptimizationResponse response = RouteOptimizationResponse.newBuilder()
                .setOptimizedRoute(optimizedRoute)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<RouteGuidanceRequest> bidirectionalRouteGuidance(final StreamObserver<RouteGuidanceResponse> responseObserver) {
        return new StreamObserver<RouteGuidanceRequest>() {
            @Override
            public void onNext(RouteGuidanceRequest request) {
                String currentLocation = request.getCurrentLocation();
                String nextTurn = request.getNextTurn();
                // Mocked route guidance
                String guidanceMessage = "At " + currentLocation + ", turn " + nextTurn;

                RouteGuidanceResponse response = RouteGuidanceResponse.newBuilder()
                        .setGuidanceMessage(guidanceMessage)
                        .build();

                responseObserver.onNext(response);
            }

            @Override
            public void onError(Throwable t) {
                // Handle error
            }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }
        };
    }
}
