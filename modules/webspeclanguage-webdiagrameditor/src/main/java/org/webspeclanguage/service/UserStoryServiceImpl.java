package org.webspeclanguage.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.webspeclanguage.api.Diagram;
import org.webspeclanguage.userstories.UserStoryGenerationParameters;
import org.webspeclanguage.userstories.UserStoryGenerationResponse;
import org.webspeclanguage.userstories.UserStoryGenerator;
import org.webspeclanguage.userstories.cropping.CroppingInfo;

import com.common.model.User;

/**
 * A service that coordinates the generation of the user stories from the diagrams
 * 
 * @author Esteban Robles Luna
 */
public class UserStoryServiceImpl {

  private DiagramService diagramService;
  private Map<UserStoryOutput, UserStoryGenerator> userStoryMapping;
  private String diagramImageServiceURL;
  private String croppingServiceURL;
  private List<String> cssPaths;
  private List<String> jsPaths;
  private String imagesDirectoryPath;
  
  public UserStoryServiceImpl(
          UserStoryGenerator htmlGenerator, 
          UserStoryGenerator wordEnumeration, 
          UserStoryGenerator wordTabular, 
          DiagramService diagramService,
          String diagramImageServiceURL,
          String croppingServiceURL,
          List<String> cssPaths,
          List<String> jsPaths,
          String imagesDirectoryPath) {
    
    this.userStoryMapping = new HashMap<UserStoryOutput, UserStoryGenerator>();
    this.userStoryMapping.put(UserStoryOutput.HTML, htmlGenerator);
    this.userStoryMapping.put(UserStoryOutput.WORD_ENUMERATION, wordEnumeration);
    this.userStoryMapping.put(UserStoryOutput.WORD_TABULAR, wordTabular);
    
    this.diagramService = diagramService;
    this.diagramImageServiceURL = diagramImageServiceURL;
    this.croppingServiceURL = croppingServiceURL;
    this.cssPaths = cssPaths;
    this.jsPaths = jsPaths;
    this.imagesDirectoryPath = imagesDirectoryPath;
  }
  
  public byte[] generate(User user, long diagramId, UserStoryOutput output) throws GenerationException {
    org.webspeclanguage.model.Diagram readDiagram = this.diagramService.getDiagram(user, diagramId);

    UserStoryGenerator generator = this.getGenerator(output);
    ParsingResult parsingResult = this.getDiagram(user, readDiagram);
    Diagram diagram = parsingResult.getDiagram();
    Map<String, CroppingInfo> croppingMap = parsingResult.getBoundingBoxes();
    
    try {
      String diagramFilename = UUID.randomUUID().toString();
      File diagramFile = File.createTempFile(diagramFilename, ".tmp");
      FileUtils.writeByteArrayToFile(diagramFile, readDiagram.getImageBytes());
      
      UserStoryGenerationParameters userStoryGenerationParameters = 
      		this.getUserStoryGenerationParameters(output, diagram, String.valueOf(diagramId),
      				croppingMap, diagramFile, new Locale("en", "US"));
      
      UserStoryGenerationResponse response = generator.generate(userStoryGenerationParameters);
      
      String responseFilename = UUID.randomUUID().toString();
      File responseFile = File.createTempFile(responseFilename, ".tmp");
      String fullDirectory = responseFile.getAbsoluteFile().getParentFile().toString();
      File fullDirectoryAsFile = new File(fullDirectory);
      fullDirectoryAsFile.mkdir();
      response.generateResourcesIn(fullDirectory, responseFilename);
      
      
      String extension = output.getExtension();
      String finalFilename = new StringBuilder(fullDirectory).append("/")
              .append(responseFilename).append("." + extension).toString();
      File file = new File (finalFilename);
      FileInputStream fileIO = new FileInputStream(file);
      byte[] result = IOUtils.toByteArray(fileIO);

      return result;
    } catch (IOException e) {
      throw new GenerationException(e);
    } catch (Exception e) {
      throw new GenerationException(e);
    }
  }

  private UserStoryGenerationParameters getUserStoryGenerationParameters(
  		UserStoryOutput output, Diagram diagram,
  		String diagramId, Map<String, CroppingInfo> croppingMap, File diagramFile, Locale outputLocale) {
  	
		return new UserStoryGenerationParametersFactory(diagram, croppingMap, outputLocale,
				diagramFile, this.diagramImageServiceURL, this.croppingServiceURL,
				diagramId, this.cssPaths, this.jsPaths, this.imagesDirectoryPath).
				build(output);
	}

  private ParsingResult getDiagram(User user, org.webspeclanguage.model.Diagram diagram) {
    DiagramParser parser = new DiagramParser();
    ParsingResult result = parser.parse(diagram.getJsonRepresentation());
    return result;
  }

  private UserStoryGenerator getGenerator(UserStoryOutput output) {
    UserStoryGenerator generator = this.userStoryMapping.get(output);
    if (generator == null) {
      generator = this.userStoryMapping.get(UserStoryOutput.HTML);
    }
    return generator;
  }
}
