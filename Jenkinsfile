pipeline {

    agent any

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Environment Check') {
            steps {
                bat 'java -version'
                bat 'mvn -version'
                bat 'git --version'
            }
        }

        stage('Clean') {
            steps {
                bat 'mvn clean'
            }
        }

        stage('Run Tests') {
            steps {
                bat 'mvn test -Dheadless=true'
            }
        }
    }

    post {

        always {

            archiveArtifacts(
                artifacts: 'target/screenshots/**/*.png',
                allowEmptyArchive: true
            )

            archiveArtifacts(
                artifacts: 'target/reports/**/*.html',
                allowEmptyArchive: true
            )

            archiveArtifacts(
                artifacts: 'target/cucumber-reports/**/*.html',
                allowEmptyArchive: true
            )
        }

        success {
            echo 'Selenium automation execution PASSED.'
        }

        failure {
            echo 'Selenium automation execution FAILED.'
        }
    }
}