pipeline {
    agent { label 'master' }

    environment {
        DOTENV_PATH = '/var/lib/jenkins/workspace/shopping-cart/.env'
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
                    echo "Loading .env using dot command"
                    . "$DOTENV_PATH"
                    curl -X POST -H 'Content-type: application/json' \
                    --data '{"text": ":white_check_mark: *BUILD SUCCESS from Jenkins*"}' \
                    "$SLACK_WEBHOOK"
                '''
            }
            failure {
                sh '''
                    echo "Loading .env using dot command"
                    . "$DOTENV_PATH"
                    curl -X POST -H 'Content-type: application/json' \
                    --data '{"text": ":x: *BUILD FAILED from Jenkins*"}' \
                    "$SLACK_WEBHOOK"
                '''
            }
        }

}
