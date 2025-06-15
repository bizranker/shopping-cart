pipeline {
    agent { label 'master' }

    stages {
        stage('Test') {
            steps {
                echo 'Running test stage...'
            }
        }
    }

    post {
        success {
            sh 'echo "Testing Jenkins post build command"'
            sh 'curl -X POST -H "Content-type: application/json" --data "{\"text\":\"Test message from Jenkins\"}" $SLACK_WEBHOOK'
        }
    }
}