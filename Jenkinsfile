pipeline {

    agent any

    parameters {

        choice(
            name: 'TEST_TYPE',
            choices: ['ALL', 'TESTNG', 'CUCUMBER'],
            description: 'Select which tests to execute'
        )

        booleanParam(
            name: 'HEADLESS',
            defaultValue: true,
            description: 'Run browser in headless mode'
        )

        choice(
            name: 'CUCUMBER_TAG',
            choices: ['@smoke', '@regression', '@negative'],
            description: 'Cucumber tag to execute'
        )
    }

    stages {

        stage('Environment Check') {

            steps {

                bat '''
                    echo ==============================
                    echo TEST_TYPE=%TEST_TYPE%
                    echo HEADLESS=%HEADLESS%
                    echo CUCUMBER_TAG=%CUCUMBER_TAG%
                    echo ==============================

                    echo JAVA_HOME=%JAVA_HOME%

                    java -version

                    mvn -version
                '''
            }
        }

        stage('Run Tests') {

            steps {

                script {

                    if (params.TEST_TYPE == 'ALL') {

                        bat """
                            mvn clean test -Dheadless=${params.HEADLESS} -Dcucumber.filter.tags=${params.CUCUMBER_TAG}
                        """

                    } else if (params.TEST_TYPE == 'TESTNG') {

                        bat """
                            mvn clean test -Dheadless=${params.HEADLESS}
                        """

                    } else if (params.TEST_TYPE == 'CUCUMBER') {

                        bat """
                            mvn clean test -Dheadless=${params.HEADLESS} -Dcucumber.filter.tags=${params.CUCUMBER_TAG}
                        """
                    }
                }
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

        success {
            echo 'Automation execution PASSED.'
        }

        failure {
            echo 'Automation execution FAILED.'
        }
    }
}