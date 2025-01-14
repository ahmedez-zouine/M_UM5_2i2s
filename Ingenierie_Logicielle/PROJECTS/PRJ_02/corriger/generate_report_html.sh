# Install Node.js and sonar-report tool
brew install node
npm install -g sonar-report

# Generate HTML report
sonar-report \
  --sonarUrl=http://localhost:9000 \
  --token=squ_100480f66222d99b6b59631b5bbb91ca0f4e3c06 \
  --projectKey=calculator \
  --output=sonar_report.html
