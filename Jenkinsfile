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
            echo "Sending Slack notification"
            sh '''
                echo "Sourcing webhook from .env"
                source /var/lib/jenkins/workspace/shopping-cart/.env
                echo "Webhook: $SLACK_WEBHOOK"
                curl -X POST -H 'Content-type: application/json' \
                --data '{"text": ":white_check_mark: *BUILD SUCCESS with .env debug*"}' \
                "$SLACK_WEBHOOK"
            '''
        }
    }

}