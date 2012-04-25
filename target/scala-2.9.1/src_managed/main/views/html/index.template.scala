
package views.html

import play.templates._
import play.templates.TemplateMagic._

import play.api.templates._
import play.api.templates.PlayMagic._
import models._
import controllers._
import java.lang._
import java.util._
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import play.api.i18n._
import play.api.templates.PlayMagicForJava._
import play.mvc._
import play.data._
import play.api.data.Field
import com.avaje.ebean._
import play.mvc.Http.Context.Implicit._
import views.html._
/**/
object index extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template1[String,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(message: String):play.api.templates.Html = {
        _display_ {

Seq(format.raw/*1.19*/("""

"""),_display_(Seq(/*3.2*/main("OSF - Proof of Concept")/*3.32*/ {_display_(Seq(format.raw/*3.34*/("""
    
	<h1>OSF - Proof of Concept</h1>
	<h2>
		Welcome to the proof of concept application for the Play Framework.
	</h2>
	<p>The application has been developped by Valentin Cl&eacute;ment and Adrien Nicolet</p>
    
    <h3>Stateless programming</h3>
	<p>
		The goal of this part is to demonstrates how the stateless layer can be used upper the 
		business services exposed by Lotaris. Documentation about Java Cache on the playframework
		 website: <a href="http://www.playframework.org/documentation/2.0/JavaCache" target="blank">
		 http://www.playframework.org/documentation/2.0/JavaCache</a>
 	</p>
	<a href="#">Start the demonstration</a><br/>
	<a href="#">Link 1</a><br/>
	<a href="#">Link 2</a><br/>
	
	
	<h3>Live code modification</h3>
	<p>
		The goal of this part is to demonstrates how the play framework uses the on fly compilation,
		 in order to avoid a rebuild and deployment for any change on the application.
	 </p>
	<a href="#">Start the demonstration</a><br/>
	<a href="#">Link 1</a><br/>
	<a href="#">Link 2</a><br/>
    
""")))})))}
    }
    
    def render(message:String) = apply(message)
    
    def f:((String) => play.api.templates.Html) = (message) => apply(message)
    
    def ref = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Apr 25 13:48:04 CEST 2012
                    SOURCE: /Users/adriennicolet/PlayProjects/proofofconcept/app/views/index.scala.html
                    HASH: 0fa46b78f1b26eb20518ad7aa6603f6dd5482fc8
                    MATRIX: 755->1|844->18|876->21|914->51|948->53
                    LINES: 27->1|30->1|32->3|32->3|32->3
                    -- GENERATED --
                */
            