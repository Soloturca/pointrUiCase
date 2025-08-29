pipeline {
    agent any

    environment {
        MAVEN_HOME = "C:\\apache-maven-3.9.11"  // adjust if Maven is installed elsewhere
        JAVA_HOME  = "C:\\Program Files\\Java\\jdk-11" // adjust if JDK is installed elsewhere
        PATH       = "${env.JAVA_HOME}\\bin;${env.MAVEN_HOME}\\bin;${env.PATH}"
    }

    stages {
        stage('Checkout') {
            steps {
                // Pull code from Git repository
                git branch: 'main', url: 'https://github.com/Soloturca/pointrUiCase.git'
            }
        }

        stage('Clean & Build') {
            steps {
                echo 'Cleaning and building project...'
                bat 'mvn clean compile'
            }
        }

        stage('Run Tests') {
            steps {
                echo 'Running Selenium TestNG tests...'
                bat 'mvn test -DsuiteXmlFile=TestNG/CIBuild.xml -Dtest=pointr'
            }
        }

        stage('Generate Allure Report') {
            steps {
                echo 'Generating Allure Report...'
                // Generate results
                bat 'mvn allure:report'
                // Open results in Jenkins
                allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
            }
        }
    }

    post {
        always {
            echo 'Cleaning up workspace...'
            cleanWs()
        }
        failure {
            echo 'Build failed!'
        }
        success {
            echo 'Build succeeded!'
        }
    }
}
