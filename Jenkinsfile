pipeline {
	agent any
	  stages {
			stage('Cloning Git') {
					  steps {
							   git([url: 'https://github.com/SaiKumarPavan/RssReader.git', branch: 'master'])
							}
						}
			stage('Building image') {
						steps{
							   script {
										  docker build -t rssfeed/latest .
									  }
							 }
					 }
			stage('Run Image') {
						steps{
								script {
										  docker run -it rssfeed/latest
				   }
					 }
	}
	}
}