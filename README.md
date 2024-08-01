# Smart Car System

## Overview

The Smart Car System is a simulation of a smart automated car environment. It features three main services: Engine Diagnostics, Comfort Control, and Safety Navigation. These services are implemented using Java and gRPC for communication. The services are discoverable using jmDNS.

## Features

- **Engine Diagnostics Service**: Run diagnostics, predict maintenance needs, report engine health, and stream live diagnostics updates.
- **Comfort Control Service**: Adjust seat positions, regulate cabin temperature, and optimize in-car entertainment.
- **Safety Navigation Service**: Analyze real-time traffic, detect hazards, optimize routes, and provide interactive route guidance.

## Project Structure

- `src/main/proto`: Protocol Buffers (proto) files defining the gRPC services and messages.
- `src/main/java/com/smartcar/grpc`: Java implementations of the gRPC services.
- `src/main/java/com/smartcar/gui`: Java Swing client GUI for interacting with the services.
- `src/main/java/com/smartcar/grpc/ServiceRegistration.java`: jmDNS service registration.
- `src/main/java/com/smartcar/grpc/ServiceDiscovery.java`: jmDNS service discovery.
- `src/main/java/com/smartcar/grpc/SmartCarServer.java`: Main class to start the gRPC servers.
- `src/main/java/com/smartcar/gui/SmartCarClient.java`: Main class to start the client GUI.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or later
- Apache Maven
- Git

### Setup

1. **Clone the repository**:

   ```bash
   git clone https://github.com/juliolouzz/SmartCar_Distributed_Systems.git
   cd SmartCar_Distributed_Systems
   ```

2. **Build the project**:
   ```bash
   mvn clean install
   ```

### Running the Application

1. **Start the gRPC Servers**:

   - Run the `SmartCarServer` class to start the servers for all services:

   ```bash
   mvn exec:java -Dexec.mainClass="com.smartcar.grpc.SmartCarServer"
   ```

2. **Start the Client GUI**:
   - Run the `SmartCarClient` class to start the client GUI:
   ```bash
   mvn exec:java -Dexec.mainClass="com.smartcar.gui.SmartCarClient"
   ```

### Using the Client GUI

1. **Engine Diagnostics**:

   - Enter the engine ID and click "Run Diagnostics" to see the diagnostic report.
   - Enter the engine ID and click "Predict Maintenance Needs" to see the maintenance schedule.
   - Enter the engine ID and click "Report Engine Health" to see the health report.
   - Enter the engine ID and click "Get Live Diagnostics" to see live updates.

2. **Comfort Control**:

   - Enter the seat ID and position, then click "Adjust Seat Position" to adjust the seat.
   - Enter the desired temperature and click "Set Temperature" to regulate the cabin temperature.
   - Enter entertainment preferences and click "Optimize Entertainment" to adjust the entertainment system.

3. **Safety Navigation**:
   - Enter the location and click "Get Traffic Report" to see the traffic analysis.
   - Enter the location and click "Hazard Detection" to detect any hazards.
   - Enter the destination and click "Optimize Route" to get the best route.
   - Use the interactive guidance to follow real-time route instructions.

### Troubleshooting

- Ensure that all necessary ports (50051, 50052, 50053) are available and not blocked by your firewall.
- Check that Java and Maven are correctly installed and configured on your system.
- If you encounter any issues, check the logs for detailed error messages.

## Contributing

1. Fork the repository.
2. Create a new branch (`git checkout -b feature/YourFeature`).
3. Commit your changes (`git commit -am 'Add YourFeature'`).
4. Push to the branch (`git push origin feature/YourFeature`).
5. Create a new Pull Request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgements

- This project uses [gRPC](https://grpc.io/).
- Service discovery and registration are handled by [jmDNS](https://jmdns.sourceforge.net/).
