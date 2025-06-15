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
            sh "sudo /var/lib/jenkins/slack_notify.sh success 'All stages completed' ${env.BUILD_NUMBER} shopping-cart ${env.BUILD_URL}"
        }
        failure {
            sh """
                set +e
                LAST_LINES=\$(tail -n 20 \$WORKSPACE/target/surefire-reports/*.txt 2>/dev/null | tr -d '\`')
                sudo /var/lib/jenkins/slack_notify.sh failure "\$LAST_LINES" ${env.BUILD_NUMBER} shopping-cart ${env.BUILD_URL}
            """
        }

    }


}
