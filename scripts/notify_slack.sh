#!/bin/bash

STATUS="$1"
JOB_NAME="$2"
BUILD_NUMBER="$3"
BUILD_URL="$4"
WEBHOOK_URL="$5"

if [ "$STATUS" == "SUCCESS" ]; then
    COLOR="#36a64f"
    EMOJI=":white_check_mark:"
    TITLE="BUILD SUCCESS"
else
    COLOR="#FF0000"
    EMOJI=":x:"
    TITLE="BUILD FAILURE"
fi

MESSAGE="*${TITLE}* ${EMOJI}\\n*Job:* ${JOB_NAME}\\n*Build:* #${BUILD_NUMBER}\\n<${BUILD_URL}|View Build>"

# Build JSON payload properly
JSON=$(cat <<EOF
{
  "attachments": [
    {
      "color": "${COLOR}",
      "text": "${MESSAGE}"
    }
  ]
}
EOF
)

curl -X POST -H "Content-type: application/json" \
     --data "${JSON}" \
     "${WEBHOOK_URL}"
