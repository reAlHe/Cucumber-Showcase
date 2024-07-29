pipeline {
    agent any
    environment {
        FLAG_FILE = '/var/jenkins_home/shared/scm_change_detected.flag'
    }
    triggers {
        pollSCM('H/5 * * * *') // Checks for SCM changes every 5 minutes
    }
    stages {
        stage('SCM Change Detection') {
            steps {
                script {
                        echo 'Running high prioritized tests'
                        sh './gradlew clean test -Pcucumber.tags="@firefox" -d'
                        writeFile file: FLAG_FILE, text: 'true'
                }
            }
        }
    }
}