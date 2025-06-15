pipeline {
    agent { label 'master' }

    environment {
        SLACK_WEBHOOK = 'https://hooks.slack.com/services/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'
    }

    stages {
        stage('Build') {
            steps {
                echo "Running build step..."
            }
        }

        stage('Test') {
            steps {
                echo "Running tests..."
            }
        }
    }

    post {
        success {
            sh '''
                echo "Notifying Slack of SUCCESS"
                ./scripts/notify_slack.sh SUCCESS "$JOB_NAME" "$BUILD_NUMBER" "$BUILD_URL" "$SLACK_WEBHOOK"
            '''
        }

        failure {
            sh '''
                echo "Notifying Slack of FAILURE"
                ./scripts/notify_slack.sh FAILURE "$JOB_NAME" "$BUILD_NUMBER" "$BUILD_URL" "$SLACK_WEBHOOK"
            '''
        }
    }
}
