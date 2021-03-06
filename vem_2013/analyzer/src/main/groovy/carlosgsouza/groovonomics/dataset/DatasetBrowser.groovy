package carlosgsouza.groovonomics.dataset;

import java.text.SimpleDateFormat

import carlosgsouza.groovonomics.common.FileHandler

public class DatasetBrowser {
	GitHubWebClient gitHubWebClient
	FileHandler fileHandler
	Human human
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd")
	
	int year, month
	
	public DatasetBrowser(year, month) {
		this.year = year
		this.month = month
	}
	
	public DatasetBrowser(baseDir, year, month) {
		this.year = year
		this.month = month
		
		fileHandler = new FileHandler(baseDir, "$year-$month")
		gitHubWebClient = new GitHubWebClient(fileHandler)
		human = new Human(fileHandler)
	}
	
	def listProjectsCreatedAt(String dateStr) {
		fileHandler.log(dateStr)
		
		int attempt = 1
		
		while(attempt <= 3) {
			try {
				def projects = gitHubWebClient.searchProjects(dateStr, "groovy")
				projects.each { fileHandler.output(it) }
				
				break
			} catch(e) {
				fileHandler.logError("$dateStr | ${attempt} / 3 | $e.message")
				e.printStackTrace()
				
				attempt++
				
				if(attempt > 3) {
					fileHandler.logError("$dateStr | It wasn't possible to get the projects today")
				} else {
					human.thinkFor(60)
				}
			}
		}
	}
	
	def listAllGroovyProjectsToDate() {
		(1..31).each { day ->
			listProjectsCreatedAt formatDate(year, month, day)
			human.think()
		}
	}
	
	def formatDate(y, m, d) {
		m = (m < 10) ? "0$m" : "$m"
		d = (d < 10) ? "0$d" : "$d"
		
		"$y-$m-$d"
	}
	
	public static void main(String[] args) {
		(2008..2013).each { y ->
			(1..12).each { m ->
				if( (y != 2008 || m >= 3) && (y != 2013 || m < 5) ) {
					new DatasetBrowser(args[0], y, m).listAllGroovyProjectsToDate()
				}
			}
		}
	}
}
