pipeline {

    agent any

    /*
     * ============================================================
     * Selenium Grid Configuration
     * ============================================================
     *
     * Selenium Grid currently contains Chrome node only.
     *
     * Therefore:
     *   Jenkins + remote execution = Chrome Selenium Grid
     *
     * Firefox/Edge Grid nodes can be added later.
     *
     * Local execution can support Chrome, Firefox and Edge.
     */

    parameters {

        choice(
            name: 'TEST_TYPE',
            choices: [
                'TESTNG',
                'CUCUMBER'
            ],
            description: 'Select which test framework to execute'
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
            description: 'Cucumber tag. Used only when TEST_TYPE=Cucumber.'
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

                    echo "================================"
                    echo "TEST TYPE   : ${params.TEST_TYPE}"
                    echo "BROWSER     : ${params.BROWSER}"
                    echo "ENVIRONMENT : ${params.ENVIRONMENT}"
                    echo "HEADLESS    : ${params.HEADLESS}"
                    echo "================================"

                    if (params.TEST_TYPE == 'TESTNG') {

                        echo "Executing TestNG tests..."
                        echo "Cucumber tag will NOT be used."

                        bat """
                            mvn clean test ^
                            -Dexecution=remote ^
                            -Dbrowser=${params.BROWSER} ^
                            -Denv=${params.ENVIRONMENT} ^
                            -Dheadless=${params.HEADLESS} ^
                            -Dtest.suite.file=testng.xml
                        """

                    } else if (params.TEST_TYPE == 'CUCUMBER') {

                        echo "Executing Cucumber tests..."
                        echo "Cucumber Tag: ${params.CUCUMBER_TAG}"

                        bat """
                            mvn clean test ^
                            -Dexecution=remote ^
                            -Dbrowser=${params.BROWSER} ^
                            -Denv=${params.ENVIRONMENT} ^
                            -Dheadless=${params.HEADLESS} ^
                            -Dtest.suite.file=cucumber.xml ^
                            -Dcucumber.filter.tags="${params.CUCUMBER_TAG}"
                        """
                    }
                }
            }
        }
    }

    post {

        always {

            /*
             * ====================================================
             * TestNG Reports
             * ====================================================
             */

            script {

                if (params.TEST_TYPE == 'TESTNG') {

                    echo "Archiving TestNG results..."

                    junit(
                        allowEmptyResults: true,
                        testResults: 'target/surefire-reports/*.xml'
                    )

                    archiveArtifacts(
                        artifacts: 'target/screenshots/**/*',
                        allowEmptyArchive: true
                    )

                    archiveArtifacts(
                        artifacts: 'target/reports/extent-report/**/*',
                        allowEmptyArchive: true
                    )

                    archiveArtifacts(
                        artifacts: 'target/logs/*',
                        allowEmptyArchive: true
                    )

                } else if (params.TEST_TYPE == 'CUCUMBER') {

                    /*
                     * ====================================================
                     * Cucumber Reports
                     * ====================================================
                     */

                    echo "Archiving Cucumber results..."

                    archiveArtifacts(
                        artifacts: 'target/screenshots/**/*',
                        allowEmptyArchive: true
                    )

                    archiveArtifacts(
                        artifacts: 'target/cucumber-reports/cucumber.html',
                        allowEmptyArchive: true
                    )

                    archiveArtifacts(
                        artifacts: 'target/logs/*',
                        allowEmptyArchive: true
                    )
                }
            }
        }

        success {

            echo 'Automation execution PASSED.'
        }

        failure {

            echo 'Automation execution FAILED.'
        }
    }
}
