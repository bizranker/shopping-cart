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
                git credentialsId: 'e75529fa-7bec-4ad1-80d0-567ea93aeece', url: 'https://github.com/brianbills21/shopping-cart.git'
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
