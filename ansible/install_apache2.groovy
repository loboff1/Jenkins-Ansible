pipeline {
    agent any

    stages {
    
    stage('Clean workspace') {
            steps {
                CleanWs()
            }
        stage('Check Git') {
            steps {
                git branch: 'main', url: "git@github.com:loboff1/ansible.git", credentialsId: 'github_key'
            }
        }
        stage('Deploy Apache2') {
            steps {
                withCredentials([file(credentialsId: 'vault_token', variable: 'VAULT_TOKEN')]) {
        ansiblePlaybook playbook: 'ansible/playbook-roles.yaml', inventory: 'ansible/hosts', sudoUser: null, extras: "--vault-password-file ${VAULT_TOKEN}"
        }
        
        stage('Test') {
            steps {
                sh 'apache2 --version > apache2.txt'
            }
            }
        }
    }
}
