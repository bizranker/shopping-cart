pipeline {
    agent { label 'slave1' }

    environment {
        SLACK_WEBHOOK = 'https://hooks.slack.com/services/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'
    }

    stages {
        stage('Slack Notification') {
            steps {
                script {
                    // Send Slack test message before Maven build starts
                    sh '''
                        curl -X POST -H 'Content-type: application/json' \
                        --data '{"text": ":zap: *Test message from Jenkins before Maven build*"}' \
                        "$SLACK_WEBHOOK"
                    '''
                }
            }
        }

        stage('Build') {
            steps {
                echo "Running build step..."
                sh '/var/lib/jenkins/tools/hudson.tasks.Maven_MavenInstallation/maven3/bin/mvn clean package -DskipTests=true'
            }
        }
    }

    post {
        success {
            echo "Build success - sending final Slack message"
            sh '''
                curl -X POST -H 'Content-type: application/json' \
                --data '{"text": ":white_check_mark: *BUILD SUCCESS from Jenkins*"}' \
                "$SLACK_WEBHOOK"
            '''
        }
    }
}
