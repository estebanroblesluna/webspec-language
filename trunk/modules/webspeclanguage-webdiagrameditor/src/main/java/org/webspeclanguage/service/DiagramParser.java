package org.webspeclanguage.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.webspeclanguage.api.Diagram;
import org.webspeclanguage.api.Generator;
import org.webspeclanguage.api.Interaction;
import org.webspeclanguage.api.Transition;
import org.webspeclanguage.impl.core.DiagramImpl;
import org.webspeclanguage.impl.core.InteractionImpl;
import org.webspeclanguage.impl.core.NavigationImpl;
import org.webspeclanguage.impl.core.RichBehaviorImpl;
import org.webspeclanguage.impl.generator.OneOfArrays;
import org.webspeclanguage.impl.generator.OneOfNumbers;
import org.webspeclanguage.impl.generator.OneOfStrings;
import org.webspeclanguage.impl.generator.RandomStringGenerator;
import org.webspeclanguage.impl.generator.UniformNumberGenerator;
import org.webspeclanguage.impl.widget.Button;
import org.webspeclanguage.impl.widget.CheckBox;
import org.webspeclanguage.impl.widget.ComboBox;
import org.webspeclanguage.impl.widget.Container;
import org.webspeclanguage.impl.widget.Label;
import org.webspeclanguage.impl.widget.Link;
import org.webspeclanguage.impl.widget.ListBox;
import org.webspeclanguage.impl.widget.ListOfContainer;
import org.webspeclanguage.impl.widget.Panel;
import org.webspeclanguage.impl.widget.RadioButton;
import org.webspeclanguage.impl.widget.TextField;
import org.webspeclanguage.impl.widget.Widget;
import org.webspeclanguage.userstories.cropping.CroppingInfo;


/**
 * A parser for {@link Diagram} json representation
 * 
 * @author Esteban Robles Luna
 */
@SuppressWarnings("rawtypes")
public class DiagramParser {

  private Diagram diagram;
  private Map<String, CroppingInfo> croppingMap;
  private Map<Integer, Interaction> interactionMap;
  
  public ParsingResult parse(String jsonRepresentation) {
    this.croppingMap = new HashMap<String, CroppingInfo>();
    this.interactionMap = new HashMap<Integer, Interaction>();
    
    try {
      JSONObject root = new JSONObject(jsonRepresentation);
      this.basicParse(root);
      
      ParsingResult result = new ParsingResult(this.diagram, this.croppingMap);
      return result;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
  
  @SuppressWarnings("unchecked")
  private <T> T basicParse(JSONObject anObject) throws JSONException {
    Object result = null;
    
    String figureType = anObject.getString("figureType");
    
    if (figureType.equals("WebSpecDrawing")) {
      result = this.parseDiagram(anObject); //DONE
    } else if (figureType.equals("InteractionFigure")) {
      result = this.parseInteraction(anObject); //DONE
    } else if (figureType.equals("NavigationFigure")) {
      result = this.parseTransition(anObject); //DONE
    } else if (figureType.equals("RichBehaviorFigure")) {
      result = this.parseTransition(anObject); //DONE
    } else if (figureType.equals("ButtonFigure")) {
      result = this.parseWidget(anObject, new Button()); //DONE
    } else if (figureType.equals("CheckBoxFigure")) {
      result = this.parseWidget(anObject, new CheckBox()); //DONE
    } else if (figureType.equals("ComboBoxFigure")) {
      result = this.parseWidget(anObject, new ComboBox()); //DONE
    } else if (figureType.equals("LabelFigure")) {
      result = this.parseWidget(anObject, new Label()); //DONE
    } else if (figureType.equals("LinkFigure")) {
      result = this.parseWidget(anObject, new Link()); //DONE
    } else if (figureType.equals("ListBoxFigure")) {
      result = this.parseWidget(anObject, new ListBox()); //DONE
    } else if (figureType.equals("RadioButtonFigure")) {
      result = this.parseWidget(anObject, new RadioButton()); //DONE
    } else if (figureType.equals("TextFieldFigure")) {
      result = this.parseWidget(anObject, new TextField()); //DONE
    } else if (figureType.equals("PanelContainerFigure")) {
      result = this.parseContainer(anObject, new Panel()); //DONE
    } else if (figureType.equals("ListOfContainerFigure")) {
      result = this.parseContainer(anObject, new ListOfContainer()); //DONE
    } else if (figureType.equals("OneOfManyStringsFigure")) {
      result = this.parseGenerator(anObject); //DONE
    } else if (figureType.equals("OneOfManyNumbersFigure")) {
      result = this.parseGenerator(anObject); //DONE
    } else if (figureType.equals("OneOfManyArraysFigure")) {
      result = this.parseGenerator(anObject); //DONE
    } else if (figureType.equals("RandomStringFigure")) {
      result = this.parseGenerator(anObject); //DONE
    } else if (figureType.equals("UniformDistributionFigure")) {
      result = this.parseGenerator(anObject); //DONE
    }
    
    return (T) result;
  }

  private Container parseContainer(JSONObject anObject, Container container) throws JSONException {
    String name = anObject.getJSONObject("model").getString("Name");
    String location = anObject.getJSONObject("model").getString("Location");

    container.setName(name);
    container.setLocation(location);
    
    return container;
  }

  private Generator parseGenerator(JSONObject anObject) throws JSONException {
    String figureType = anObject.getString("figureType");
    String name = anObject.getJSONObject("model").getString("Name");

    Generator result = null;
    if (figureType.equals("OneOfManyStringsFigure")) {
      String strings = anObject.getJSONObject("model").getString("Strings");

      String[] values = strings.split(",");
      for (int i = 0; i < values.length; i++) {
        String string = values[i];
        string = string.trim();
        string = string.substring(1, string.length() - 2); // remove "
        values[i] = string;
      }
      
      result = new OneOfStrings(name, values);
      
    } else if (figureType.equals("OneOfManyNumbersFigure")) {
      String strings = anObject.getJSONObject("model").getString("Numbers");
      String[] valuesAsString = strings.split(",");
      BigDecimal[] values = new BigDecimal[valuesAsString.length];
      
      for (int i = 0; i < valuesAsString.length; i++) {
        String value = valuesAsString[i];
        values[i] = new BigDecimal(value.trim());
      }
      result = new OneOfNumbers(name, values);

    } else if (figureType.equals("OneOfManyArraysFigure")) {
      //TODO
      result = new OneOfArrays(name);

    } else if (figureType.equals("RandomStringFigure")) {
      String minLengthAsString = anObject.getJSONObject("model").getString("Min length");
      String maxLengthAsString = anObject.getJSONObject("model").getString("Max length");
  
      int minLength = Integer.valueOf(minLengthAsString);
      int maxLength = Integer.valueOf(maxLengthAsString);
      
      result = new RandomStringGenerator(name, minLength, maxLength);
    
    } else if (figureType.equals("UniformDistributionFigure")) {
      String minNumberAsString = anObject.getJSONObject("model").getString("Min number");
      String maxNumberAsString = anObject.getJSONObject("model").getString("Max number");

      int min = Integer.valueOf(minNumberAsString);
      int max = Integer.valueOf(maxNumberAsString);

      result = new UniformNumberGenerator(name, min, max);
    }
    
    return result;
  }

  private Widget parseWidget(JSONObject anObject, Widget widget) throws JSONException {
    String name = anObject.getJSONObject("model").getString("Name");
    String location = anObject.getJSONObject("model").getString("Location");

    widget.setName(name);
    widget.setLocation(location);
    
    return widget;
  }

  private Transition parseTransition(JSONObject anObject) throws JSONException {
    this.computeBoundingBox(anObject);

    String figureType = anObject.getString("figureType");

    String name = anObject.getJSONObject("model").getString("Name");
    String preconditions = anObject.getJSONObject("model").getString("Precondition");
    String actions = anObject.getJSONObject("model").getString("Actions");

    Integer sourceId = anObject.getInt("source");
    Integer targetId = anObject.getInt("target");
    
    Interaction source = this.interactionMap.get(sourceId);
    Interaction target = this.interactionMap.get(targetId);

    Transition transition = null;
    
    if (figureType.equals("NavigationFigure")) {
      NavigationImpl navigation = new NavigationImpl(source, target);
      navigation.setName(name);
      transition = navigation;
    } else if (figureType.equals("RichBehaviorFigure")) {
      RichBehaviorImpl rich = new RichBehaviorImpl(source, target);
      rich.setName(name);
      transition = rich;
    }
    
    transition.setActions(actions);
    transition.setPrecondition(preconditions);
    
    return transition;
  }

  private Interaction parseInteraction(JSONObject anObject) throws JSONException {
    this.computeBoundingBox(anObject);
    
    String name = anObject.getJSONObject("model").getString("Name");
    String location = anObject.getJSONObject("model").getString("Location");
    String invariant = anObject.getJSONObject("model").getString("Invariant");
    String startingAsString = anObject.getJSONObject("model").getString("Starting");

    boolean starting = false;
    try {
      starting = Boolean.valueOf(startingAsString);
    } catch (Exception e) {
      //Do nothing
    }
    
    Interaction interaction = new InteractionImpl(name, starting);
    interaction.setLocation(location);
    interaction.setInvariant(invariant);
    
    List subfigures = this.parseSubfigures(anObject);
    
    if (subfigures.size() == 1 && subfigures.get(0) instanceof Container) {
      Container container = (Container) subfigures.get(0);
      for (Widget widget : container.getWidgets()) {
        interaction.getRoot().addWidget(widget);
      }
    }

    Integer id = anObject.getInt("id");
    this.interactionMap.put(id, interaction);
    
    return interaction;
  }

  private Diagram parseDiagram(JSONObject anObject) throws JSONException {
    Diagram diagram = new DiagramImpl("");
    this.diagram = diagram;
    
    List subfigures = this.parseSubfigures(anObject);
    
    for (Object o : subfigures) {
      if (o instanceof Interaction) {
        diagram.addInteraction((Interaction) o);
      } else if (o instanceof Generator) {
        diagram.addGenerator((Generator) o);
      }
    }
    
    return diagram;
  }
  
  @SuppressWarnings("unchecked")
  private List parseSubfigures(JSONObject anObject) throws JSONException {
    List subfigures = new ArrayList();
    if (anObject.has("subfigures")) {
      JSONArray array = anObject.getJSONArray("subfigures");
      for (int i = 0; i < array.length(); i++) {
        JSONObject object = array.getJSONObject(i);
        Object o = this.basicParse(object);
        if (o != null) {
          subfigures.add(o);
        }
      }
    }
    return subfigures;
  }

  private void computeBoundingBox(JSONObject anObject) throws JSONException {
    if (anObject.has("model")) {
      String name = anObject.getJSONObject("model").getString("Name");
      CroppingInfo info = this.parseFrame(anObject);
      this.croppingMap.put(name, info);
    }
  }

  private CroppingInfo parseFrame(JSONObject anObject) throws JSONException {
    int x = anObject.getInt("x");
    int y = anObject.getInt("y");
    int width = anObject.getInt("width");
    int height = anObject.getInt("height");

    return new CroppingInfo(x, y, width, height);
  }
}
