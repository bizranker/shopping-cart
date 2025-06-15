#!/bin/bash

curl -X POST -H 'Content-type: application/json' \
--data '{"text": ":zap: *Test message from Jenkins user shell*"}' \
'https://hooks.slack.com/services/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'
