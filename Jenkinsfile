pipeline {
    agent any

    stages {
        stage('Validation') {
            steps {
                echo 'validation  .....'
                sh 'mvn validate'
               
            }
        }
        stage('Compiling') {
            steps {
                echo 'compile  .....'
                sh 'mvn compile'
               
            }
        }
        stage('Testing') {
            steps {
                echo 'test  ....'
                sh 'mvn test'
               
            }
        }
        stage('Packaging') {
            steps {
                echo 'package  ....'
                sh 'mvn package'
            }
        }
    }
}
