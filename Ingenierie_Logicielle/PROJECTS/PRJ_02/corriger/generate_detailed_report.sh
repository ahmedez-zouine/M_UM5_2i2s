#!/bin/bash

PROJECT_KEY="calculator"
SONAR_URL="http://localhost:9000"
SONAR_TOKEN="squ_100480f66222d99b6b59631b5bbb91ca0f4e3c06"

# Create report directory
mkdir -p sonar_reports

# Generate markdown report
cat > sonar_reports/detailed_report.md << EOL
# SonarQube Analysis Report
## Project: $PROJECT_KEY
Date: $(date)

## Key Metrics
EOL

# Get and format main metrics
curl -s -u $SONAR_TOKEN: "$SONAR_URL/api/measures/component?component=$PROJECT_KEY&metricKeys=ncloc,complexity,violations,coverage,duplicated_lines_density,reliability_rating,security_rating,sqale_rating" | \
  jq -r '.component.measures[] | "- **" + .metric + ":** " + .value' >> sonar_reports/detailed_report.md

# Add issues section
echo -e "\n## Issues" >> sonar_reports/detailed_report.md

# Get and format issues
curl -s -u $SONAR_TOKEN: "$SONAR_URL/api/issues/search?componentKeys=$PROJECT_KEY" | \
  jq -r '.issues[] | "- **" + .severity + ":** " + .message' >> sonar_reports/detailed_report.md

# Add code smells section
echo -e "\n## Code Smells" >> sonar_reports/detailed_report.md

curl -s -u $SONAR_TOKEN: "$SONAR_URL/api/issues/search?componentKeys=$PROJECT_KEY&types=CODE_SMELL" | \
  jq -r '.issues[] | "- " + .message' >> sonar_reports/detailed_report.md

echo "Detailed report generated in sonar_reports/detailed_report.md"
