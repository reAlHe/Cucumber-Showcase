pipeline {
    agent any
    environment {
        FLAG_FILE = '/var/jenkins_home/shared/scm_change_detected.flag'
        DISPLAY = ':99'
    }
    triggers {
        pollSCM('H/1 * * * *') // Checks for SCM changes every minute
    }
    stages {
        stage('Start Xvfb') {
                    steps {
                        script {
                            sh 'Xvfb :99 -screen 0 1024x768x24 &'
                        }
                    }
                }
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
    post {
            always {
                script {
                    sh 'killall Xvfb'
                }
            }
    }
}