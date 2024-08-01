package com.smartcar.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import com.smartcar.grpc.*;

public class SmartCarClient {

    private static EngineDiagnosticsServiceGrpc.EngineDiagnosticsServiceBlockingStub engineStub;
    private static ComfortControlServiceGrpc.ComfortControlServiceBlockingStub comfortStub;
    private static SafetyNavigationServiceGrpc.SafetyNavigationServiceBlockingStub safetyStub;

    public static void main(String[] args) {
        ManagedChannel channel1 = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();
        engineStub = EngineDiagnosticsServiceGrpc.newBlockingStub(channel1);

        ManagedChannel channel2 = ManagedChannelBuilder.forAddress("localhost", 50052)
                .usePlaintext()
                .build();
        comfortStub = ComfortControlServiceGrpc.newBlockingStub(channel2);

        ManagedChannel channel3 = ManagedChannelBuilder.forAddress("localhost", 50053)
                .usePlaintext()
                .build();
        safetyStub = SafetyNavigationServiceGrpc.newBlockingStub(channel3);

        JFrame frame = new JFrame("Smart Car Client");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);
        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel engineLabel = new JLabel("Engine ID:");
        engineLabel.setBounds(10, 20, 80, 25);
        panel.add(engineLabel);

        JTextField engineText = new JTextField(20);
        engineText.setBounds(100, 20, 165, 25);
        panel.add(engineText);

        JButton diagnosticsButton = new JButton("Run Diagnostics");
        diagnosticsButton.setBounds(10, 50, 150, 25);
        panel.add(diagnosticsButton);
        diagnosticsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RunDiagnosticsRequest request = RunDiagnosticsRequest.newBuilder().setEngineId(engineText.getText()).build();
                try {
                    RunDiagnosticsResponse response = engineStub.runDiagnostics(request);
                    JOptionPane.showMessageDialog(panel, response.getDiagnosticReport());
                } catch (StatusRuntimeException ex) {
                    handleGrpcError(ex);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panel, "Unexpected error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JLabel temperatureLabel = new JLabel("Temperature:");
        temperatureLabel.setBounds(10, 80, 80, 25);
        panel.add(temperatureLabel);

        JTextField temperatureText = new JTextField(20);
        temperatureText.setBounds(100, 80, 165, 25);
        panel.add(temperatureText);

        JButton tempButton = new JButton("Set Temperature");
        tempButton.setBounds(10, 110, 150, 25);
        panel.add(tempButton);
        tempButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegulateCabinTemperatureRequest request = RegulateCabinTemperatureRequest.newBuilder().setTemperature(Float.parseFloat(temperatureText.getText())).build();
                try {
                    RegulateCabinTemperatureResponse response = comfortStub.regulateCabinTemperature(request);
                    JOptionPane.showMessageDialog(panel, response.getStatus());
                } catch (StatusRuntimeException ex) {
                    handleGrpcError(ex);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panel, "Unexpected error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JLabel locationLabel = new JLabel("Location:");
        locationLabel.setBounds(10, 140, 80, 25);
        panel.add(locationLabel);

        JTextField locationText = new JTextField(20);
        locationText.setBounds(100, 140, 165, 25);
        panel.add(locationText);

        JButton trafficButton = new JButton("Get Traffic Report");
        trafficButton.setBounds(10, 170, 150, 25);
        panel.add(trafficButton);
        trafficButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RealTimeTrafficAnalysisRequest request = RealTimeTrafficAnalysisRequest.newBuilder().setLocation(locationText.getText()).build();
                try {
                    RealTimeTrafficAnalysisResponse response = safetyStub.realTimeTrafficAnalysis(request);
                    JOptionPane.showMessageDialog(panel, response.getTrafficReport());
                } catch (StatusRuntimeException ex) {
                    handleGrpcError(ex);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panel, "Unexpected error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private static void handleGrpcError(StatusRuntimeException ex) {
        JOptionPane.showMessageDialog(null, "gRPC Error: " + ex.getStatus().getDescription(), "gRPC Error", JOptionPane.ERROR_MESSAGE);
    }
}
