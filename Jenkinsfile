pipeline {
    agent {
        label 'master'
    }

    environment {
        SLACK_WEBHOOK = credentials('slack-webhook-shopping')
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
    script {
      sh """
      curl -X POST -H 'Content-type: application/json' \\
      --data '{
        "username": "BuildBot",
        "icon_emoji": ":classical_building:",
        "text": "*🏛️ Victory is ours!*\n*Project:* shopping-cart\n*Build:* #${env.BUILD_NUMBER}\n*Time:* ${new Date().format("EEE MMM dd hh:mm:ss a z yyyy")}\n:white_check_mark: *Status:* SUCCESS\\n<${env.BUILD_URL}|View Build Details>"
      }' ${SLACK_WEBHOOK}
      """
    }
  }
}



        failure {
            sh """
            set +e
            LAST_LINES=\$(tail -n 20 \$WORKSPACE/target/surefire-reports/*.txt 2>/dev/null | tr -d '\\`')
            curl -X POST -H 'Content-type: application/json' \
            --data "{
                \\"username\\": \\"BuildBot\\",
                \\"icon_emoji\\": \\":warning:\\",
                \\"text\\": \\":warning: Build failed\\n*Project:* shopping-cart\\n*Build:* #${env.BUILD_NUMBER}\\n*Last log lines:* \\\$LAST_LINES\\n:boom: *Status:* FAILURE\\"
            }" ${SLACK_WEBHOOK}
            """
        }
    }   
}