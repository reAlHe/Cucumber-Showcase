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
                    def changeDetected = false
                    def changeSet = currentBuild.changeSets
                    for (int i = 0; i < changeSet.size(); i++) {
                        for (int j = 0; j < changeSet[i].items.length; j++) {
                            changeDetected = true
                        }
                    }

                    if (changeDetected) {
                        echo 'Running high prioritized tests'
                        sh './gradlew clean test -Pcucumber.tags="@firefox"'
                        writeFile file: FLAG_FILE, text: 'true'
                    }
                }
            }
        }
    }
}