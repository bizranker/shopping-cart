#!/bin/bash

STATUS="$1"
JOB_NAME="$2"
BUILD_NUMBER="$3"
BUILD_URL="$4"
WEBHOOK_URL="$5"

if [ "$STATUS" == "SUCCESS" ]; then
    COLOR="#36a64f"
    EMOJI=":white_check_mark:"
    MESSAGE="*BUILD SUCCESS* $EMOJI\n*Job:* $JOB_NAME\n*Build:* #$BUILD_NUMBER\n<$BUILD_URL|View Build>"
else
    COLOR="#FF0000"
    EMOJI=":x:"
    MESSAGE="*BUILD FAILED* $EMOJI\n*Job:* $JOB_NAME\n*Build:* #$BUILD_NUMBER\n<$BUILD_URL|View Build>"
fi

payload="payload={\"attachments\":[{\"color\":\"$COLOR\",\"text\":\"$MESSAGE\"}]}"

curl -X POST --data-urlencode "$payload" "$WEBHOOK_URL"
