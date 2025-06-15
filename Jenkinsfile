pipeline {
    agent any

    environment {
        MAVEN_OPTS = '-Dmaven.repo.local=.m2/repository'
    }

    tools {
        maven 'Maven 3.8.6'
        jdk 'Java 17'
    }

    stages {
        stage('Git Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Compile Code') {
            steps {
                sh 'mvn compile'
            }
        }

        stage('OWASP Dependency Check') {
            steps {
                sh 'mvn org.owasp:dependency-check-maven:check'
            }
        }

        stage('SonarQube Analysis') {
            environment {
                SONARQUBE_ENV = credentials('sonarqube-token')
            }
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh 'mvn sonar:sonar'
                }
            }
        }

        stage('Run JUnit Tests') {
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
                sh 'docker build -f docker/Dockerfile -t shopping-cart:latest .'
            }
        }

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
                slackSend(
                    channel: '#monita-bizranker',
                    color: 'good',
                    message: """\
        *✅ Build #${env.BUILD_NUMBER} Completed*
        *📦 Project:* shopping-cart
        *🕒 Time:* ${new Date()}
        *🟢 Status:* SUCCESS
        🔗 <${env.BUILD_URL}|View Build Details>
        """
                )
            }
            failure {
                slackSend(
                    channel: '#monita-bizranker',
                    color: 'danger',
                    message: """\
        *❌ Build #${env.BUILD_NUMBER} Failed*
        *📦 Project:* shopping-cart
        *🕒 Time:* ${new Date()}
        *🔴 Status:* FAILURE
        🔗 <${env.BUILD_URL}|Investigate Build Logs>
        """
                )
            }
        }


}
