pipeline{
	agent any
	tools {
	maven "MAVEN3"
	jdk "OracleJDK11"
	}
	stages {
		stage('Fetch Code') {
			steps {
				git branch: 'main', url: 'https://github.com/devopshydclub/vprofile-project.git'
			}
		}
		stage('Build'){
				steps{
					sh 'mvn install -DskipTests'
				}

				post{
					success {
						echo 'Archiving artifacts now.'
						archiveArtifacts artifacts: '**/*.war'
					}
				}
		}
		stage('UNIT TESTS'){
			steps{
				sh 'mvn test'
			}
			}
		stage('Checkstyle Analysis'){
			steps {
				sh 'mvn checkstyle:checkstyle'
			}
			}
		stage('Sonar Analysis'){
		environment{
			scannerHome = tool 'Sonar'
		}
			steps {
				withSonarQubeEnv('sonar'){
					sh '''${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=vprofile \
					  -Dsonar.projectName=vprofile \
					  -Dsonar.projectVersion=1.0 \
					  -Dsonar.sources=src/ \
					  -Dsonar.java.binaries=target/test-classes/com/visualpathit/account/controllerTest/ \
					  -Dsonar.jacoco.reportsPath=target/jacoco.exec \
					  -Dsonar.junit.reportsPath=target/surefire-reports/ \
  					  -Dsonar.java.checkstyle.reportPaths=target/checkstyle-result.xml'''
				}
			}
			}
		stage('Quality Gate'){
		    steps{
		        timeout(time:1,unit:'HOURS'){
		           waitForQualityGate abortPipeline:true   
		        }
		    }
		}
		
	}
    post {
        always {
            // Email Notification
            emailext subject: "Jenkins Build - ${currentBuild.result} - ${env.JOB_NAME}",
                      body: """
                        ${currentBuild.result == 'FAILURE' ? 'Build failed.' : 'Build successful.'}
                        
                        SonarQube Analysis Results:
                        - SonarQube URL: ${SONAR_SERVER}
                        
                        For more SonarQube details:
                        - Username: ${SONAR_USERNAME}
                        - Password: ${SONAR_PASSWORD}
                        
                        ${currentBuild.result == 'FAILURE' ? "\n**Build Log:**\n${BUILD_LOG}" : ''}
                        """,
                      to: 'redtius@gmail.com'
        }
    }

	}