pipeline {

    agent any

    tools {
    
        maven 'Maven'
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                bat 'mvn clean test'
            }
        }

    }

    post {

        always {
            archiveArtifacts artifacts: 'reports/**', fingerprint: true
        }

    }
}