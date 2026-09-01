pipeline {

    agent any

    /*
     * Selenium Grid Configuration:
     *
     * Currently Selenium Grid is configured with Chrome node only.
     * Therefore, remote execution through Jenkins supports Chrome only.
     *
     * Local execution can support Chrome, Firefox and Edge.
     * Firefox/Edge Selenium Grid nodes can be added later if required.
     */

    parameters {

        choice(
            name: 'TEST_TYPE',
            choices: [
                'ALL',
                'TESTNG',
                'CUCUMBER'
            ],
            description: 'Select which tests to execute'
        )

        choice(
            name: 'BROWSER',
            choices: [
                'chrome'
            ],
            description: 'Browser to execute tests. Selenium Grid currently supports Chrome only.'
        )

        choice(
            name: 'ENVIRONMENT',
            choices: [
                'qa',
                'uat',
                'prod'
            ],
            description: 'Test environment'
        )

        booleanParam(
            name: 'HEADLESS',
            defaultValue: true,
            description: 'Run browser in headless mode'
        )

        choice(
            name: 'CUCUMBER_TAG',
            choices: [
                '@smoke',
                '@regression',
                '@negative'
            ],
            description: 'Cucumber tag to execute'
        )
    }

    environment {

        JAVA_HOME = 'C:\\Program Files\\Java\\jdk-21.0.12'

        PATH = "${JAVA_HOME}\\bin;${env.PATH}"
    }

    stages {

        stage('Environment Check') {

            steps {

                bat '''
                    echo ==============================
                    echo JAVA_HOME=%JAVA_HOME%
                    echo ==============================

                    java -version

                    echo ==============================
                    echo Maven Version
                    echo ==============================

                    mvn -version

                    echo ==============================
                    echo Selenium Grid
                    echo http://localhost:4444
                    echo Chrome Node Only
                    echo ==============================
                '''
            }
        }

        stage('Run Tests') {

            steps {

                script {

                    echo "Browser     : ${params.BROWSER}"
                    echo "Environment : ${params.ENVIRONMENT}"
                    echo "Headless    : ${params.HEADLESS}"
                    echo "Test Type   : ${params.TEST_TYPE}"

                    if (params.TEST_TYPE == 'TESTNG') {

                        bat """
                            mvn clean test ^
                            -Dexecution=remote ^
                            -Dbrowser=${params.BROWSER} ^
                            -Denv=${params.ENVIRONMENT} ^
                            -Dheadless=${params.HEADLESS} ^
                            -Dsurefire.suiteXmlFiles=testng.xml
                        """

                    } else if (params.TEST_TYPE == 'CUCUMBER') {

                        bat """
                            mvn clean test ^
                            -Dexecution=remote ^
                            -Dbrowser=${params.BROWSER} ^
                            -Denv=${params.ENVIRONMENT} ^
                            -Dheadless=${params.HEADLESS} ^
                            -Dcucumber.filter.tags=${params.CUCUMBER_TAG}
                        """

                    } else {

                        bat """
                            mvn clean test ^
                            -Dexecution=remote ^
                            -Dbrowser=${params.BROWSER} ^
                            -Denv=${params.ENVIRONMENT} ^
                            -Dheadless=${params.HEADLESS}
                        """
                    }
                }
            }
        }
    }

    post {

        always {

            junit(
                allowEmptyResults: true,
                testResults: 'target/surefire-reports/*.xml'
            )

            archiveArtifacts(
                artifacts: 'target/screenshots/**/*',
                allowEmptyArchive: true
            )

            archiveArtifacts(
                artifacts: 'target/extent-report/**/*',
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