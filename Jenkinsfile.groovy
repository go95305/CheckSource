node {
    def scmVars = checkout scm
    def values = scmVars.GIT_BRANCH.tokenize('/')
    String branchName = values[1]

    try {
        clone(branchName)
        build()
        buildImage()
        pushImage()
        deploy()
    } catch (env) {
        throw env
    }
}

def clone(String branch) {
    stage('Git Clone') {
        echo 'Clone ' + branch
        git branch: branch, credentialsId: "${CREDENTIALIS_ID}", url: "${GIT_URL}"
    }
}

def build() {
    stage('Build') {
        sh 'cd frontend && yarn install'
        sh 'cd frontend && yarn build'
        sh 'rm -rf ./backend/checksource/src/test'
        sh "chmod +x ./backend/checksource/gradlew"
        sh 'rm -rf ./backend/checksource/build/libs/*'
        sh "cd ./backend/checksource && ./gradlew build"
    }
}

def buildImage() {
    stage('BuildImage') {
        sh "docker build -t frontend ./frontend/."
        sh "docker build -t backend ./backend/checksource/."
    }
}

def pushImage() {
    stage('pushImage') {
        sh 'docker image prune'

        sh 'rm  ~/.dockercfg || true'
        sh 'rm ~/.docker/config.json || true'

        docker.withRegistry('https://378668795069.dkr.ecr.ap-northeast-2.amazonaws.com', 'ecr:ap-northeast-2:again09-ecr-connector') {
            sh "docker tag frontend:latest 378668795069.dkr.ecr.ap-northeast-2.amazonaws.com/frontend:latest"
            sh "docker push 378668795069.dkr.ecr.ap-northeast-2.amazonaws.com/frontend:latest"
            sh "docker tag backend:latest 378668795069.dkr.ecr.ap-northeast-2.amazonaws.com/backend:latest"
            sh "docker push 378668795069.dkr.ecr.ap-northeast-2.amazonaws.com/backend:latest"
        }
    }
}

def deploy() {
    stage('Deploy') {
        sh "docker run -itd --name backend -p 8080:8080 -u root backend"
        sh "docker run -itd --name frontend -p 80:80 -u root frontend"

    }
}