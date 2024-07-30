pipeline {
    agent any
    environment {
        FLAG_FILE = "/var/jenkins_home/shared/trigger-all-tests.flag"
    }
    triggers {
        pollSCM('H/1 * * * *') // Checks for SCM changes every minute
    }
    stages {
        stage('SCM Change Detection') {
            steps {
                script {
                        echo 'Running high prioritized tests'
//                         sh './gradlew clean test -Pcucumber.tags="@firefox"'
                        sh './gradlew clean'
                        writeFile file: FLAG_FILE, text: 'true'
                }
            }
        }
    }
}