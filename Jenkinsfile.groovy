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
        sh "docker build --tag frontend:frontend ./frontend/."
        sh "docker build --tag backend:backend ./backend/checksource/."
    }
}

def pushImage() {
    stage('pushImage') {
        sh 'docker image prune'

        sh 'rm  ~/.dockercfg || true'
        sh 'rm ~/.docker/config.json || true'

        docker.withRegistry('https://378668795069.dkr.ecr.ap-northeast-2.amazonaws.com', 'ecr:ap-northeast-2:again09-ecr-connector') {
            sh "docker tag frontend:frontend 378668795069.dkr.ecr.ap-northeast-2.amazonaws.com/checksource:frontend"
            sh "docker push 378668795069.dkr.ecr.ap-northeast-2.amazonaws.com/checksource:frontend"
            sh "docker tag backend:backend 378668795069.dkr.ecr.ap-northeast-2.amazonaws.com/checksource:backend"
            sh "docker push 378668795069.dkr.ecr.ap-northeast-2.amazonaws.com/checksource:backend"

        }
    }
}

def deploy() {
    stage('Deploy') {
        sh "docker container stop backend"
        sh "docker container rm backend"
        sh "docker container stop frontend"
        sh "docker container rm frontend"
        sh "docker run -itd --name backend -p 8080:8080 -u root backend"
        sh "docker run -itd --name frontend -p 80:80 -u root frontend"
    }
}