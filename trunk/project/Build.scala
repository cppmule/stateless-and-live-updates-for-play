import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "proofofconcept"
    val appVersion      = "1.0-SNAPSHOT"

    val appDependencies = Seq(
		"com.sun.jersey" % "jersey-client" % "1.8",
		"com.sun.jersey" % "jersey-core" % "1.8",
		"org.springframework" % "spring-core" % "2.5.4",
		"org.springframework" % "spring-context" % "2.5.4",
		"org.springframework" % "spring-aop" % "2.5.4",
		"org.springframework" % "spring-remoting" % "2.0.8"				
	)

    val main = PlayProject(appName, appVersion, appDependencies, mainLang = JAVA).settings(
      // Add your own project settings here      
    )

}
