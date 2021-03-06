package houseware.learn.testing.arquillian.shrinkwrap.test;

import houseware.learn.testing.ChromeTestUtils;
import houseware.learn.testing.FirefoxTestUtils;
import houseware.learn.testing.ITestShowcase;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.importer.ZipImporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.shrinkwrap.resolver.api.maven.MavenResolvedArtifact;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.net.URL;


/**
 * @author fphilip@houseware.es
 */
@Slf4j
@RunAsClient
@RunWith(Arquillian.class)
public class TestArquillianShowcaseTomcat8 extends ITestShowcase {

    @Deployment
    public static WebArchive createDeployment() throws IOException {


        File war  = Maven.resolver()
                      .resolve("org.primefaces:showcase:war:5.3")
                      .withoutTransitivity().asSingleFile();

        System.out.println("???"+war.getAbsolutePath());

        WebArchive archive = ShrinkWrap.create(ZipImporter.class, "showcase.war")
                .importFrom(war)
                .as(WebArchive.class);

        System.err.println(">>><" + archive.toString(true));
        //        log.info(archive.toString(true));
        return archive;
    }



    public String getHost() {
        return "http://localhost:8080/showcase";
    }

    @Override
    public WebDriver buildDriver() {
        return ChromeTestUtils.newDriver();
    }

}
