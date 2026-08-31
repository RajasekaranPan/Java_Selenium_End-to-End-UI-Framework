pipeline {

    agent any

    parameters {

        choice(
            name: 'TEST_TYPE',
            choices: [
                'ALL',
                'TESTNG',
                'CUCUMBER_SMOKE',
                'CUCUMBER_REGRESSION',
                'CUCUMBER_NEGATIVE'
            ],
            description: 'Select the test suite to execute'
        )

        booleanParam(
            name: 'HEADLESS',
            defaultValue: true,
            description: 'Run browser in headless mode'
        )
    }

    stages {

        stage('Environment Check') {

            steps {

                bat '''
                    echo ==============================
                    echo TEST TYPE : %TEST_TYPE%
                    echo HEADLESS   : %HEADLESS%
                    echo JAVA_HOME  : %JAVA_HOME%
                    echo ==============================

                    java -version
                    mvn -version
                '''
            }
        }

        stage('Run Tests') {

            steps {

                script {

                    switch (params.TEST_TYPE) {

                        case 'TESTNG':

                            bat """
                                mvn clean test -Dheadless=${params.HEADLESS}
                            """

                            break


                        case 'CUCUMBER_SMOKE':

                            bat """
                                mvn clean test -Dheadless=${params.HEADLESS} -Dcucumber.filter.tags=@smoke
                            """

                            break


                        case 'CUCUMBER_REGRESSION':

                            bat """
                                mvn clean test -Dheadless=${params.HEADLESS} -Dcucumber.filter.tags=@regression
                            """

                            break


                        case 'CUCUMBER_NEGATIVE':

                            bat """
                                mvn clean test -Dheadless=${params.HEADLESS} -Dcucumber.filter.tags=@negative
                            """

                            break


                        case 'ALL':

                            bat """
                                mvn clean test -Dheadless=${params.HEADLESS}
                            """

                            break
                    }
                }
            }
        }
    }

    post {

        always {

            echo 'Publishing test results...'

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
