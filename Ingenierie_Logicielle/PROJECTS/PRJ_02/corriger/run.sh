#!/bin/bash

# Activate virtual environment
source venv/bin/activate

echo "Starting analysis..."

echo "=== Analyzing C Program ==="
cd c_program
cppcheck --enable=all --std=c11 calculator.c
rm -f calculator.c.liz  # Remove existing .liz file
echo "y" | lizard calculator.c  # Automatically answer yes to overwrite

echo "=== Analyzing Java Program ==="
cd ../java_program

# Start SonarQube server if not running
brew services start sonarqube

# Wait for SonarQube to start (it takes a few moments)
echo "Waiting for SonarQube to start..."
sleep 30

# Create sonar-project.properties if it doesn't exist
cat > sonar-project.properties << EOF
sonar.projectKey=calculator
sonar.projectName=Calculator
sonar.projectVersion=1.0
sonar.sources=.
sonar.java.binaries=.
sonar.host.url=http://localhost:9000
# Generate a token from SonarQube web interface and replace it here
sonar.login=squ_d98bda1a6884f2dc425ac6531d33287cfec400d3
EOF

# Compile Java file
javac Calculatrice.java

# Run SonarQube analysis
sonar-scanner

echo "=== Analyzing Python Program ==="
cd ../python_program
radon cc calculator.py
radon hal calculator.py

echo "=== Deactivating virtual environment ==="
#deactivate

echo "Analysis complete!"

# Optional: Stop SonarQube server
# brew services stop sonarqube
