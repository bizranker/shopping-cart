pipeline {
    agent { label 'master' }

    tools {
        maven 'Maven'
        jdk 'JDK17'
    }

    environment {
        PATH = "${tool 'Maven'}/bin:${env.PATH}"
    }

    stages {
        stage('Declarative: Tool Install') {
            steps {
                echo "Tools installed."
            }
        }

        stage('Git Checkout') {
            steps {
                git credentialsId: 'git-cred-bizranker-shopping-cart', url: 'https://github.com/bizranker/shopping-cart.git'
            }
        }

        stage('COMPILE') {
            steps {
                sh 'mvn clean compile -DskipTests=True'
            }
        }

        stage('OWASP Scan') {
            steps {
                dependencyCheck additionalArguments: '', odcInstallation: 'DP', outdir: '', scanpath: ''
            }
        }
    }
}
