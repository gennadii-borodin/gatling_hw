# Gatling Load Testing Project

A comprehensive load testing solution built with Gatling, Scala, and SBT for performance testing web applications.

## Overview

This project contains performance tests for the WebTours application using Gatling, a powerful load testing framework. It includes various test scenarios, configurations, and simulation profiles for different testing needs.

## Project Structure

```
├── src/
│   ├── it/                    # Integration tests
│   │   └── scala/
│   │       └── example/      # Example integration test
│   └── test/                  # Main test scenarios
│       ├── scala/
│       │   ├── webTours/    # WebTours test suite
│       │   │   ├── Actions/     # HTTP request definitions
│       │   │   ├── configs/      # Configuration files
│       │   │   ├── Feeders/      # Data feeders
│       │   │   ├── webTours.scala # Base configuration
│       │   │   ├── CommonScenario.scala # Shared scenario logic
│       │   │   ├── MaxPerformanceTest.scala # Load ramp test
│       │   │   ├── Stability.scala  # Stability test
│       │   │   └── Debug.scala     # Debug configuration
│       │   └── resources/   # Test resources
│       └── test_data/    # Test data files
├── build.sbt                # SBT build configuration
└── README.md                # This file
```

## Features

- **Multiple Test Profiles**: Stability, Max Performance, and Debug configurations
- **Data-Driven Testing**: CSV feeders for user data
- **HTTP Request Builders**: Reusable HTTP request components
- **Configuration Management**: Centralized simulation parameters
- **Integration Testing**: Example integration test setup

## Test Scenarios

### WebTours Application Tests

The project includes comprehensive tests for the WebTours application:

1. **Login Flow**: Authenticates users through the login process
2. **Flight Search**: Searches for flights with random parameters
3. **Flight Reservation**: Completes flight reservation process
4. **Payment Processing**: Handles payment submission

### Simulation Profiles

#### Stability Test
- Gradual load increase to target intensity
- Sustained load for stability testing
- Configurable duration and intensity

#### Max Performance Test
- Incremental load stages
- Ramp-up and ramp-down periods
- Performance benchmarking

## Configuration

### Simulation Parameters

Located in `src/test/scala/webTours/configs/SimulationConfig.scala`:

```scala
object SimulationConfig {
    val intensity = 8              // Target load intensity
    val stagesCount = 8            // Number of load stages
    val stageDuration = 3.minute   // Duration of each stage
    val rampDuration = 20.seconds  // Ramp-up/down duration
    val stabilityDuration = 1.hour // Stability test duration
    val stabilibyIntensity = 5     // Stability test intensity
}
```

### HTTP Configuration

Base configuration in `src/test/scala/webTours/webTours.scala`:

- Base URL: `http://webtours.load-test.ru:1080`
- Custom headers and user agents
- Connection pooling and caching settings

## Running Tests

### Prerequisites

- SBT 1.x
- Java 8 or higher
- Scala 2.13.18

### Basic Usage

```bash
# Run all tests
$ sbt test

# Run specific test class
$ sbt "testOnly webTours.MaxPerformanceTest"

# Run with custom parameters
$ sbt "testOnly webTours.Stability -Dvu=10"
```

### Integration Tests

```bash
# Run integration tests
$ sbt it:test

# Run specific integration test
$ sbt "it:testOnly example.BasicItSimulation"
```

## Test Data

User data is loaded from CSV files in the `test_data` directory:

```csv
username,password
user1,password1
user2,password2
...
```

## Assertions and Checks

- HTTP status code validation
- CSS selector checks for page elements
- Random value selection for test variability
- Session data persistence across requests

## Dependencies

- **Gatling**: 3.14.9
- **Scala**: 2.13.18
- **SBT**: 1.x
- **Gatling Charts**: Highcharts integration

## Reporting

Gatling generates comprehensive HTML reports with:
- Response time distribution
- Throughput metrics
- Error rate analysis
- System resource usage
- Request/response details

## Best Practices

1. **Modular Design**: Reusable HTTP request builders
2. **Data-Driven Testing**: External data sources
3. **Configuration Management**: Centralized parameters
4. **Error Handling**: Proper status code checks
5. **Session Management**: State persistence across requests

## Contributing

1. Follow existing code patterns and conventions
2. Add new test scenarios in appropriate packages
3. Update configuration as needed
4. Ensure tests are data-driven where possible
5. Add documentation for new features

## Troubleshooting

### Common Issues

- **Connection Refused**: Check target application availability
- **Authentication Failures**: Verify user data in feeders
- **Performance Issues**: Adjust simulation parameters
- **Build Errors**: Ensure correct Scala and SBT versions

### Debug Mode

Use the Debug configuration for troubleshooting:

```bash
$ sbt "testOnly webTours.Debug"
```

This provides detailed logging and reduced load for easier debugging.

## License

This project is provided as-is for demonstration purposes. Please refer to Gatling's licensing terms for commercial use.