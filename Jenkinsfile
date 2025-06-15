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
            sh '/var/lib/jenkins/slack_test.sh'
        }
        failure {
            sh '''
                curl -X POST -H 'Content-type: application/json' \
                --data '{"text": ":x: *BUILD FAILED from Jenkins*"}' \
                'https://hooks.slack.com/services/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'
            '''
        }
    }
}
