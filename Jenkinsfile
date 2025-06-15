// Long-Term Jenkinsfile Summary for shopping-cart

pipeline {
  agent { label 'master' }

  environment {
    SLACK_WEBHOOK = credentials('shopping-cart-slack-webhook')
    REGISTRY_CREDENTIALS = credentials('dockerhub-cred')
    DOCKER_IMAGE = 'bizranker/shopping-cart:latest'
  }

  stages {
    stage('Checkout') {
      steps {
        git credentialsId: 'github-cred', url: 'https://github.com/brianbills21/shopping-cart.git'
      }
    }

    stage('Compile') {
      steps {
        sh 'mvn clean compile'
      }
    }

    stage('OWASP Dependency Check') {
      steps {
        sh 'mvn org.owasp:dependency-check-maven:check -DskipTests'
      }
    }

    stage('Run Unit Tests') {
      steps {
        sh 'mvn test'
      }
      post {
        always {
          junit '**/target/surefire-reports/*.xml'
        }
      }
    }

    stage('Build Docker Image') {
      steps {
        sh 'docker build -t $DOCKER_IMAGE .'
      }
    }

    stage('Push Docker Image') {
      steps {
        withCredentials([usernamePassword(credentialsId: 'dockerhub-cred', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
          sh '''
            echo "$PASSWORD" | docker login -u "$USERNAME" --password-stdin
            docker push $DOCKER_IMAGE
          '''
        }
      }
    }
  }

  post {
    success {
      script {
        sh """
          curl -X POST -H 'Content-type: application/json' --data '{
            "username": "BuildBotus Maximus",
            "icon_url": "https://upload.wikimedia.org/wikipedia/commons/8/8e/Roman_Senate_building.png",
            "text": ":white_check_mark: *Build succeeded* for *shopping-cart* on the Master node. Ave Jenkins!"
          }' $SLACK_WEBHOOK
        """
      }
    }

    failure {
      script {
        sh """
          curl -X POST -H 'Content-type: application/json' --data '{
            "username": "BuildBotus Maximus",
            "icon_url": "https://upload.wikimedia.org/wikipedia/commons/8/8e/Roman_Senate_building.png",
            "text": ":x: *Build failed* for *shopping-cart*. The Senate is displeased. Investigate with haste!"
          }' $SLACK_WEBHOOK
        """
      }
    }
  }
}