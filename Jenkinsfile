pipeline {
    agent { label 'master' }

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
                echo "Notifying Slack of SUCCESS (via .env)"
                ./scripts/notify_slack.sh SUCCESS "$JOB_NAME" "$BUILD_NUMBER" "$BUILD_URL"
            '''
        }

        failure {
            sh '''
                echo "Notifying Slack of FAILURE (via .env)"
                ./scripts/notify_slack.sh FAILURE "$JOB_NAME" "$BUILD_NUMBER" "$BUILD_URL"
            '''
        }
    }
}
