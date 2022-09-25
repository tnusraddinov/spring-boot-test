pipeline{
    agent any

    stages {

        stage('Build'){
            steps {
                echo 'build ....'
                sh 'pwd'
                sh 'ls -lah'
                sh "./mvnw clean install -DskipTests"
            }
        }

        stage('Test'){
            steps {
                echo 'test ....'
                try {
                    sh "./mvnw test -Punit"
                } catch(err) {
                    echo "${err}"
                    throw err
                }
            }
        }

        stage('Deploy'){
            steps {
                echo 'deploy ....'

                sh "pid=\$(lsof -i:8989 -t); kill -TERM \$pid || kill -KILL \$pid"
                withEnv(['JENKINS_NODE_COOKIE=dontkill', 'myenv=hello from server']) {
                    sh 'nohup ./mvnw spring-boot:run -Dserver.port=8989 &'
                }
            }
        }

    }
}
