pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Compiling shopping-cart app...'
                sh './mvnw clean package'
            }
        }

        // Add more stages here as needed
    }

    post {
        success {
            sh '''
                ./scripts/notify_slack.sh SUCCESS "$JOB_NAME" "$BUILD_NUMBER" "$BUILD_URL"
            '''
        }

        failure {
            sh '''
                ./scripts/notify_slack.sh FAILURE "$JOB_NAME" "$BUILD_NUMBER" "$BUILD_URL"
            '''
        }
    }
}
