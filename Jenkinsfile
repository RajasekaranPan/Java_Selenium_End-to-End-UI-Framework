pipeline {

    agent any

    stages {

        stage('Environment Check') {
            steps {
                bat '''
                    echo JAVA_HOME=%JAVA_HOME%
                    where java
                    java -version
                    mvn -version
                '''
            }
        }

        stage('Run Tests') {
            steps {
                bat 'mvn clean test -Dheadless=true'
            }
        }
    }

    post {

        always {

            junit(
                testResults: 'target/surefire-reports/*.xml',
                allowEmptyResults: true
            )

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
    }
}