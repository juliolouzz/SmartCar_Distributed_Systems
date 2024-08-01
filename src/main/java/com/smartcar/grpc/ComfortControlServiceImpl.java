package com.smartcar.grpc;

import io.grpc.stub.StreamObserver;

public class ComfortControlServiceImpl extends ComfortControlServiceGrpc.ComfortControlServiceImplBase {

    @Override
    public void adjustSeatPosition(AdjustSeatPositionRequest request, StreamObserver<AdjustSeatPositionResponse> responseObserver) {
        String seatId = request.getSeatId();
        String position = request.getPosition();
        // Adjust seat position (mocked for simplicity)
        String status = "Seat " + seatId + " adjusted to " + position + ".";

        AdjustSeatPositionResponse response = AdjustSeatPositionResponse.newBuilder()
                .setStatus(status)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void regulateCabinTemperature(RegulateCabinTemperatureRequest request, StreamObserver<RegulateCabinTemperatureResponse> responseObserver) {
        float temperature = request.getTemperature();
        // Regulate cabin temperature (mocked for simplicity)
        String status = "Cabin temperature set to " + temperature + " degrees.";

        RegulateCabinTemperatureResponse response = RegulateCabinTemperatureResponse.newBuilder()
                .setStatus(status)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void optimizeInCarEntertainment(OptimizeInCarEntertainmentRequest request, StreamObserver<OptimizeInCarEntertainmentResponse> responseObserver) {
        String preferences = request.getPreferences();
        // Optimize in-car entertainment (mocked for simplicity)
        String entertainmentSetup = "Entertainment setup based on preferences: " + preferences;

        OptimizeInCarEntertainmentResponse response = OptimizeInCarEntertainmentResponse.newBuilder()
                .setEntertainmentSetup(entertainmentSetup)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<SeatAdjustmentRequest> streamSeatAdjustments(final StreamObserver<SeatAdjustmentResponse> responseObserver) {
        return new StreamObserver<SeatAdjustmentRequest>() {
            @Override
            public void onNext(SeatAdjustmentRequest request) {
                String seatId = request.getSeatId();
                String position = request.getPosition();
                // Adjust seat position (mocked for simplicity)
                String status = "Seat " + seatId + " adjusted to " + position + ".";

                SeatAdjustmentResponse response = SeatAdjustmentResponse.newBuilder()
                        .setStatus(status)
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
