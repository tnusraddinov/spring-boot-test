pipeline{
    agent any

    stages {

        stage('build'){
            steps {
                echo 'build ....'
                sh 'pwd'
                sh 'ls -lah'
            }
        }

        stage('test'){
            steps {
                echo 'test ....'
            }
        }

        stage('deploy'){
            steps {
                echo 'deploy ....'
            }
        }

    }
}
