pipeline {
    agent { label 'master' }

    environment {
        SLACK_WEBHOOK = 'https://hooks.slack.com/services/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'
    }

    stages {
        stage('Build') {
            steps {
                echo "Running build step..."
                // Insert your real build logic here
            }
        }

        stage('Test') {
            steps {
                echo "Running tests..."
                // Uncomment this to simulate a failure and test Slack failure alert:
                // error("Intentional failure to test Slack alert")
            }
        }
    }

    post {
        success {
            sh '''
                ./scripts/notify_slack.sh SUCCESS "$JOB_NAME" "$BUILD_NUMBER" "$BUILD_URL" "$SLACK_WEBHOOK"
            '''
        }

        failure {
            sh '''
                ./scripts/notify_slack.sh FAILURE "$JOB_NAME" "$BUILD_NUMBER" "$BUILD_URL" "$SLACK_WEBHOOK"
            '''
        }
    }
}
