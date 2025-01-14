#!/bin/bash

PROJECT_KEY="calculator"
SONAR_URL="http://localhost:9000"
SONAR_TOKEN="squ_100480f66222d99b6b59631b5bbb91ca0f4e3c06"

# Create directory for reports
mkdir -p sonar_reports

# Get main metrics
curl -s -u $SONAR_TOKEN: "$SONAR_URL/api/measures/component?component=$PROJECT_KEY&metricKeys=ncloc,complexity,violations,coverage,duplicated_lines_density" \
  > sonar_reports/main_metrics.json

# Get issues
curl -s -u $SONAR_TOKEN: "$SONAR_URL/api/issues/search?componentKeys=$PROJECT_KEY" \
  > sonar_reports/issues.json

# Get code smells
curl -s -u $SONAR_TOKEN: "$SONAR_URL/api/issues/search?componentKeys=$PROJECT_KEY&types=CODE_SMELL" \
  > sonar_reports/code_smells.json

echo "Reports generated in sonar_reports directory"
