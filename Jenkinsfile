pipeline {
    agent any
    tools{
        maven 'Maven-3.8.8' 
    }
    stages{
        stage('Build Maven'){
            steps{
                 checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/Grigor99/spec']]) 
                 sh 'mvn clean install'
            }
        }
       
        stage('Build Docker Image'){
            steps{
                script{
                     sh 'docker build -t grigorr87/jenkins-automation .'
                }
            }
        }
        stage('Push Docker Image to DockerHUB'){
            steps{
                script{
                    withCredentials([string(credentialsId: 'dockerhubpwd', variable: 'dockerhubpwd')]) {
                    sh 'echo "$dockerhubpwd" | docker login --username grigorr87 --password-stdin'
} 
                    sh 'docker push grigorr87/jenkins-automation'
                    
                }
            }
        }
    }
}
