node {
    def scmVars = checkout scm
    def values = scmVars.GIT_BRANCH.tokenize('/')
    String branchName = values[1]

    try {
        clone(branchName)
        build()
        buildImage()
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
	sh 'rm -rf ./frontend/dist'
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

def deploy() {
    stage('Deploy') {
        sh "docker stop backend"
        sh "docker rm backend"
        sh "docker stop frontend"
        sh "docker rm frontend"

	sh "docker image prune"

        sh "docker run -itd --name backend -p 8080:8080 -u root backend:backend"
        sh "docker run -itd --name frontend -p 80:80 -u root frontend:frontend"

    }
}
