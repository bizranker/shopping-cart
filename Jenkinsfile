pipeline {
    agent { label 'master' }

    tools {
        maven 'maven3'
        jdk 'jdk17'
    }

    environment {
        PATH = "${tool 'maven3'}/bin:${env.PATH}"
    }

    stages {
        stage('Tool Check') {
            steps {
                echo "Tools installed and environment configured."
            }
        }

        stage('Git Checkout') {
            steps {
                git branch: 'main',
                    credentialsId: 'git-cred-bizranker-shopping-cart',
                    url: 'https://github.com/bizranker/shopping-cart.git'

        }

        stage('COMPILE') {
            steps {
                sh 'mvn clean compile -DskipTests=True'
            }
        }

        stage('OWASP Scan') {
            steps {
                dependencyCheck additionalArguments: '--scan .', odcInstallation: 'DP'
            }
            }
        }
    }
}