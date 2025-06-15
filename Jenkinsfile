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
                    echo ">>> .env DEBUG: [SUCCESS block]"
                    echo "DOTENV_PATH=$DOTENV_PATH"
                    . "$DOTENV_PATH"
                    echo "SLACK_WEBHOOK=$SLACK_WEBHOOK"

                    curl -X POST -H 'Content-type: application/json' \
                    --data '{"text": ":white_check_mark: *BUILD SUCCESS with .env debug*"}' \
                    "$SLACK_WEBHOOK"
                '''
            }
            failure {
                sh '''
                    echo ">>> .env DEBUG: [FAILURE block]"
                    echo "DOTENV_PATH=$DOTENV_PATH"
                    . "$DOTENV_PATH"
                    echo "SLACK_WEBHOOK=$SLACK_WEBHOOK"

                    curl -X POST -H 'Content-type: application/json' \
                    --data '{"text": ":x: *BUILD FAILURE with .env debug*"}' \
                    "$SLACK_WEBHOOK"
                '''
            }
        }


}
