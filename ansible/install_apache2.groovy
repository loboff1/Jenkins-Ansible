pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: "git@github.com:loboff1/ansible.git", credentialsId: 'github_key'
            }
        }
        stage('Deploy') {
            steps {
                withCredentials([file(credentialsId: 'vault_token', variable: 'VAULT_TOKEN')]) {
        ansiblePlaybook playbook: 'ansible/playbook-roles.yaml', inventory: 'ansible/hosts', sudoUser: null, extras: "--vault-password-file ${VAULT_TOKEN}"
        }
            }
        }
    }
}
