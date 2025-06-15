pipeline {
    agent {
        label 'master'
    }

    stages {
        stage('Push Docker Image') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'dockerhub-creds',
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) {
                    sh '''
                        echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin
                        docker tag shopping-cart:latest $DOCKER_USER/shopping-cart:latest
                        docker push $DOCKER_USER/shopping-cart:latest
                    '''
                }
            }
        }
    }

    post {
        success {
            withCredentials([string(credentialsId: 'slack-webhook-shopping', variable: 'SLACK_WEBHOOK')]) {
                sh """
                    curl -X POST -H 'Content-type: application/json' \\
                    --data '{ "text": ":rocket: *Build #${BUILD_NUMBER}* Completed successfully on *master node*." }' \\
                    $SLACK_WEBHOOK
                """
            }
        }
        failure {
            withCredentials([string(credentialsId: 'slack-webhook-shopping', variable: 'SLACK_WEBHOOK')]) {
                sh """
                    curl -X POST -H 'Content-type: application/json' \\
                    --data '{ "text": ":x: *Build #${BUILD_NUMBER}* FAILED on *master node*. Check logs." }' \\
                    $SLACK_WEBHOOK
                """
            }
        }
    }
}
