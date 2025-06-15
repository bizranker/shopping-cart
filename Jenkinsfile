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
            sh '''
                echo "Sending Slack message"
                curl -X POST -H 'Content-type: application/json' \
                --data '{"text": ":white_check_mark: *BUILD SUCCESS from Jenkins*"}' \
                'https://hooks.slack.com/services/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'
            '''
        }
    }



}