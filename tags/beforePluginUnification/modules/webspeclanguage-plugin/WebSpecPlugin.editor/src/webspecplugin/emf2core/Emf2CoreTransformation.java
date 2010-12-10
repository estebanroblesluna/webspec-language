package webspecplugin.emf2core;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.webspeclanguage.api.Action;
import org.webspeclanguage.api.Generator;
import org.webspeclanguage.api.Interaction;
import org.webspeclanguage.api.Transition;
import org.webspeclanguage.impl.action.ActionParser;
import org.webspeclanguage.impl.action.ActionVisitor;
import org.webspeclanguage.impl.action.ExpressionAction;
import org.webspeclanguage.impl.action.LetVariable;
import org.webspeclanguage.impl.core.DiagramImpl;
import org.webspeclanguage.impl.core.DuplicatedGeneratorException;
import org.webspeclanguage.impl.core.ExistingInteractionException;
import org.webspeclanguage.impl.core.InteractionImpl;
import org.webspeclanguage.impl.core.InvalidStartingInteractionException;
import org.webspeclanguage.impl.core.NavigationImpl;
import org.webspeclanguage.impl.core.Path;
import org.webspeclanguage.impl.core.TransitionImpl;
import org.webspeclanguage.impl.expression.core.ArrayExpression;
import org.webspeclanguage.impl.expression.core.Expression;
import org.webspeclanguage.impl.expression.core.GeneratorExpression;
import org.webspeclanguage.impl.expression.parser.ParsingException;
import org.webspeclanguage.impl.expression.parser.WidgetNotFoundException;
import org.webspeclanguage.impl.expression.parser.lexer.LexerException;
import org.webspeclanguage.impl.expression.parser.parser.ParserException;
import org.webspeclanguage.impl.expression.typechecker.TypecheckingResult;
import org.webspeclanguage.impl.expression.utils.ExpressionUtils;
import org.webspeclanguage.impl.generator.OneOfArrays;
import org.webspeclanguage.impl.generator.OneOfNumbers;
import org.webspeclanguage.impl.generator.OneOfStrings;
import org.webspeclanguage.impl.generator.RandomStringGenerator;
import org.webspeclanguage.impl.generator.UniformNumberGenerator;
import org.webspeclanguage.impl.widget.Button;
import org.webspeclanguage.impl.widget.CheckBox;
import org.webspeclanguage.impl.widget.ComboBox;
import org.webspeclanguage.impl.widget.Container;
import org.webspeclanguage.impl.widget.ExistingWidgetException;
import org.webspeclanguage.impl.widget.Label;
import org.webspeclanguage.impl.widget.Link;
import org.webspeclanguage.impl.widget.ListBox;
import org.webspeclanguage.impl.widget.ListOfContainer;
import org.webspeclanguage.impl.widget.Panel;
import org.webspeclanguage.impl.widget.RadioButton;
import org.webspeclanguage.impl.widget.TextField;
import org.webspeclanguage.impl.widget.Widget;

import webspecplugin.webspecmodel.UniformNumberDistribution;

@SuppressWarnings("unchecked")
public class Emf2CoreTransformation {

	private List<String> errors;
	private List<String> warnings;
	private Map<webspecplugin.webspecmodel.Interaction, InteractionImpl> interactionConvertions;
	private Map<webspecplugin.webspecmodel.Transition, TransitionImpl> transitionConvertions;
	private DiagramImpl webSpecDiagram;
	private Set<String> generatorsDeclared;
	private Set<String> generatorsUsed;
	
	public TransformationResult transform(webspecplugin.webspecmodel.Diagram diagram) {
		this.setErrors(new ArrayList<String>());
		this.setWarnings(new ArrayList<String>());

		try {
			DiagramImpl webSpecDiagram = this.createDiagramFrom(diagram);
			this.typecheck(webSpecDiagram);
			if (this.getErrors().isEmpty()) {
				return new SuccessTransformation(webSpecDiagram, this.getWarnings());
			} else {
				return new TransformationErrors(this.getErrors(), this.getWarnings());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	private void typecheck(DiagramImpl webSpecDiagram) {
	  TypecheckingResult result = webSpecDiagram.typecheck();
	  for (Exception exception : result.getExceptions()) {
	    this.addError(exception.getMessage());
	  }
  }

  private DiagramImpl createDiagramFrom(webspecplugin.webspecmodel.Diagram diagram) {
    createDiagram(diagram);
    createGenerators(diagram);
    addActionsSetup(diagram);
		createInteractionsAndWidgets(diagram);
		createTransitions(diagram);
		createInteractionInvariants(diagram);
		createTransitionsPreconditionsAndActions(diagram);
		
		generatorsUsed = new HashSet<String>();

		computeGeneratorsUsedInNavigationPreconditions();
		computeGeneratorsUsedInInteractionInvariants();
		computeUndeclaredAndUnusedGenerators();

		if (webSpecDiagram.getStartingInteraction() != null) {
			Map<Path, Set<String>> undeclaredVariables = webSpecDiagram.getUndeclaredVariables();
			for (Path path : undeclaredVariables.keySet()) {
				Set<String> variables = undeclaredVariables.get(path);
				String pathName = path.getNameUsing("->");
				for (String variableName : variables) {
					this.addError("Variable " + variableName + " is undeclared in path " + pathName);
				}
			}
		}
		
		return webSpecDiagram;
	}

	private void computeUndeclaredAndUnusedGenerators() {
		Set generatorsUsedAsString = new HashSet();
		for (Object o : generatorsUsed) {
			GeneratorExpression generator = (GeneratorExpression) o;
			generatorsUsedAsString.add(generator.getGeneratorName());
		}
		
		Set undeclaredGenerators = new HashSet(generatorsUsedAsString);
		undeclaredGenerators.removeAll(generatorsDeclared);

		Set unusedGenerators = new HashSet(generatorsDeclared);
		unusedGenerators.removeAll(generatorsUsedAsString);

		for (Object o : undeclaredGenerators) {
			String generator = (String) o;
			this.addError("Generator " + generator + " is undeclared");
		}

		for (Object o : unusedGenerators) {
			String generator = (String) o;
			this.addWarning("Generator " + generator + " is unused");
		}
	}

	private void computeGeneratorsUsedInInteractionInvariants() {
		for (Interaction webSpecInteraction : interactionConvertions.values()) {
			if (webSpecInteraction.getInvariant() != null) {
				webSpecInteraction.getInvariant().getInstancesOfOn(
						GeneratorExpression.class, 
						generatorsUsed);
			}
		}
	}

	private void computeGeneratorsUsedInNavigationPreconditions() {
		for (Transition webSpecTransition : transitionConvertions.values()) {
			if (webSpecTransition.getPrecondition() != null) {
				webSpecTransition.getPrecondition().getInstancesOfOn(
						GeneratorExpression.class, 
						generatorsUsed);
			}
			
			for (Action action : webSpecTransition.getActions()) {
				action.accept(new ActionVisitor() {
					@Override
					public Object visitExpressionAction(ExpressionAction expressionAction) {
						expressionAction.getExpression().getInstancesOfOn(
								GeneratorExpression.class, 
								generatorsUsed);
						return null;
					}

					@Override
					public Object visitLetVariable(LetVariable letVariable) {
						letVariable.getExpression().getInstancesOfOn(
								GeneratorExpression.class, 
								generatorsUsed);
						return null;
					}
				});
			}
		}
	}

	private void createGenerators(webspecplugin.webspecmodel.Diagram diagram) {
		generatorsDeclared = new HashSet<String>();

		for (Object obj : diagram.getGenerators()) {
			Generator generator = (Generator) obj;
			
			Generator webSpecGenerator = null;
			
			try {
				if (generator instanceof webspecplugin.webspecmodel.OneOfNumbers) {
					webspecplugin.webspecmodel.OneOfNumbers generatorC = (webspecplugin.webspecmodel.OneOfNumbers) generator;
					String[] numbersAsStrings = generatorC.getNumbers() == null 
						? new String[] { }
						: generatorC.getNumbers().split(",");
					BigDecimal[] numbers = new BigDecimal[numbersAsStrings.length];
					for (int i = 0; i < numbersAsStrings.length; i++) {
						String string = numbersAsStrings[i];
						numbers[i] = new BigDecimal(string);
					}
					webSpecGenerator = new OneOfNumbers(generatorC.getName(), numbers);
					
				} else if (generator instanceof webspecplugin.webspecmodel.OneOfStrings) {
					webspecplugin.webspecmodel.OneOfStrings generatorC = (webspecplugin.webspecmodel.OneOfStrings) generator;
					List<String> strings = ExpressionUtils.strings(generatorC.getStrings());
					String[] astrings = new String[strings.size()];
					for (int i = 0; i < strings.size(); i++) {
						String string = strings.get(i);
						astrings[i] = string;
					}
					webSpecGenerator = new OneOfStrings(generatorC.getName(), astrings);
					
				} else if (generator instanceof webspecplugin.webspecmodel.UniformNumberDistribution) {
					webspecplugin.webspecmodel.UniformNumberDistribution generatorC = (UniformNumberDistribution) generator; 
					webSpecGenerator = new UniformNumberGenerator(generatorC.getName(), generatorC.getMin(), generatorC.getMax());
					
				} else if (generator instanceof webspecplugin.webspecmodel.StringGenerator) {
					webspecplugin.webspecmodel.StringGenerator generatorC = (webspecplugin.webspecmodel.StringGenerator) generator;
					webSpecGenerator = new RandomStringGenerator(generatorC.getName(), generatorC.getMaxLength());
					
				} else if (generator instanceof webspecplugin.webspecmodel.OneOfArray) {
					webspecplugin.webspecmodel.OneOfArray generatorC = (webspecplugin.webspecmodel.OneOfArray) generator; 
					List<String> arrays = ExpressionUtils.arrayStrings(generatorC.getArrays());
					ArrayExpression[] exps = new ArrayExpression[arrays.size()];
					for (int i = 0; i < exps.length; i++) {
						String arrayString = arrays.get(i);
						try {
							ArrayExpression arrExp = (ArrayExpression) ExpressionUtils.getExpression(
									arrayString, 
									webSpecDiagram);
							exps[i] = arrExp;
						} catch (ParsingException e) {
							if (e.getCause() instanceof LexerException) {
								LexerException lexE = (LexerException) e.getCause();
								this.addError("Error parsing generator: " + generatorC.getName() + " " + lexE.getMessage());
							} else {
								this.addError("Error parsing generator: " + generatorC.getName() + " " + e.getMessage());
							}
						} catch (ClassCastException e) {
							this.addError("All elements of the generator must be constant arrays");
						}
					}
					webSpecGenerator = new OneOfArrays(generatorC.getName(), exps);
				}
			} catch (Exception e) {
				this.addError("Error on generator: " + generator.getName());
			}
			
			try {
				webSpecDiagram.addGenerator(webSpecGenerator);
				generatorsDeclared.add(generator.getName());
			} catch (DuplicatedGeneratorException e) {
				this.addError("Generator " + generator.getName() + " is duplicated");
			}
		}
	}

	private void createTransitionsPreconditionsAndActions(webspecplugin.webspecmodel.Diagram diagram) {
		for (Object obj : diagram.getTransitions()) {
		  webspecplugin.webspecmodel.Transition transition = (webspecplugin.webspecmodel.Transition) obj;
			
			//precondition
			try {
				Expression precondition = ExpressionUtils.getExpression(
						transition.getPrecondition(), 
						webSpecDiagram);
				
				transitionConvertions.get(transition).setPrecondition(precondition);
			} catch (ParsingException e) {
				if (e.getCause() instanceof LexerException) {
					LexerException lexE = (LexerException) e.getCause();
					this.addError("Error in precondition of transition: " + transition.getSourceInteraction() + "->" + transition.getTargetInteraction() + " " + lexE.getMessage());
				} else if (e.getCause() instanceof ParserException) {
					ParserException parE = (ParserException) e.getCause();
					this.addError("Error in precondition of transition: " + transition.getSourceInteraction() + "->" + transition.getTargetInteraction() + " " + parE.getMessage());
				} else {
					this.addError("Error in precondition of transition: " + transition.getSourceInteraction() + "->" + transition.getTargetInteraction() + " " + e.getMessage());
				}
			} catch (WidgetNotFoundException e) {
				this.addError("Widget " + e.getWidgetName() + " in interaction " + e.getInteractionName() + " not found");
			}
			
			//actions
			try {
				List<Action> actions = ActionParser.getActions(
						transition.getActions(), 
						webSpecDiagram);
				if (actions != null) {
					TransitionImpl webSpecTransition = transitionConvertions.get(transition);
					for (Action action : actions) {
						webSpecTransition.addAction(action);
					}
				}
			} catch (ParsingException e) {
				if (e.getCause() instanceof LexerException) {
					LexerException lexE = (LexerException) e.getCause();
					this.addError("Error in actions of transition: " + transition.getSourceInteraction().getName() + "->" + transition.getTargetInteraction().getName() + " " + lexE.getMessage());
				} else if (e.getCause() instanceof ParserException) {
					ParserException parE = (ParserException) e.getCause();
					this.addError("Error in actions of transition: " + transition.getSourceInteraction().getName() + "->" + transition.getTargetInteraction().getName() + " " + parE.getMessage());
				} else {
					this.addError("Error in actions of transition: " + transition.getSourceInteraction().getName() + "->" + transition.getTargetInteraction().getName() + " " + e.getMessage());
				}
			} catch (WidgetNotFoundException e) {
				this.addError("Widget " + e.getWidgetName() + " in interaction " + e.getInteractionName() + " not found");
			}
		}
	}

	private void createInteractionInvariants(webspecplugin.webspecmodel.Diagram diagram) {
		for (Object obj : diagram.getInteractions()) {
		  webspecplugin.webspecmodel.Interaction interaction = (webspecplugin.webspecmodel.Interaction) obj;

			try {
				Expression invariant = ExpressionUtils.getExpression(
						interaction.getInvariant(), 
						webSpecDiagram);
				interactionConvertions.get(interaction).setInvariant(invariant);
			} catch (ParsingException e) {
				if (e.getCause() instanceof LexerException) {
					LexerException lexE = (LexerException) e.getCause();
					this.addError("Error in invariant of interaction: " + interaction.getName() + " " + lexE.getMessage());
				} else if (e.getCause() instanceof ParserException) {
					ParserException parE = (ParserException) e.getCause();
					this.addError("Error in invariant of interaction: " + interaction.getName() + " " + parE.getMessage());
				} else {
					this.addError("Error in invariant of interaction: " + interaction.getName() + " " + e.getMessage());
				}
			} catch (WidgetNotFoundException e) {
				this.addError("Widget " + e.getWidgetName() + " in interaction " + e.getInteractionName() + " not found");
			}
		}
	}

	private void createTransitions(webspecplugin.webspecmodel.Diagram diagram) {
		transitionConvertions = new HashMap<webspecplugin.webspecmodel.Transition, TransitionImpl>();
		
		for (Object obj : diagram.getTransitions()) {
		  webspecplugin.webspecmodel.Transition transition = (webspecplugin.webspecmodel.Transition) obj;
			
			InteractionImpl from = interactionConvertions.get(transition.getSourceInteraction());
			InteractionImpl to = interactionConvertions.get(transition.getTargetInteraction());
			
			NavigationImpl navigation = (NavigationImpl) from.navigateTo(to);
			transitionConvertions.put(transition, navigation);
		}
	}

	private void createInteractionsAndWidgets(webspecplugin.webspecmodel.Diagram diagram) {
		interactionConvertions = new HashMap<webspecplugin.webspecmodel.Interaction, InteractionImpl>();
		Interaction startingInteraction = null;
		for (Object obj : diagram.getInteractions()) {
		  webspecplugin.webspecmodel.Interaction interaction = (webspecplugin.webspecmodel.Interaction) obj;
			
			//create general data
			InteractionImpl webSpecInteraction = null;
			try {
				webSpecInteraction = new InteractionImpl(interaction.getName());
			} catch (IllegalArgumentException e) {
				webSpecInteraction = new InteractionImpl("");
			}
			webSpecInteraction.setLocation(interaction.getLocation());
			
			try {
	      webSpecInteraction.setTitle(interaction.getTitle());
			} catch (ParsingException e) {
	      if (e.getCause() instanceof LexerException) {
	        LexerException lexE = (LexerException) e.getCause();
	        this.addError("Error in title of interaction " + interaction.getName() + ": " + lexE.getMessage());
	      } else if (e.getCause() instanceof ParserException) {
	        ParserException parE = (ParserException) e.getCause();
	        this.addError("Error in title of interaction " + interaction.getName() + ": " + parE.getMessage());
	      } else {
	        this.addError("Error in title of interaction " + interaction.getName() + ": " + e.getMessage());
	      }
	    } catch (WidgetNotFoundException e) {
	      this.addError("Widget " + e.getWidgetName() + " in interaction title " + e.getInteractionName() + " not found");
	    }
	    
			webSpecInteraction.setMockupFile(interaction.getMockupFile());

			if (interaction.isStarting()) {
				if (startingInteraction == null) {
					startingInteraction = webSpecInteraction;
					try {
						webSpecDiagram.setStartingInteraction(startingInteraction);
					} catch (InvalidStartingInteractionException e) {
						this.addError("Starting interaction: " + startingInteraction.getName() + " must have a location");
					}
				} else {
					this.addError("Duplicate starting interaction " + webSpecInteraction.getName());
				}
			}

			//add to diagram
			try {
				webSpecDiagram.addInteraction(webSpecInteraction);
			} catch (ExistingInteractionException e) {
				this.addError("Duplicate interaction: " + e.getName());
			}

			
			//create widgets
			for (Object obj2 : interaction.getRoot().getWidgets()) {
			  webspecplugin.webspecmodel.Widget widget = (webspecplugin.webspecmodel.Widget) obj2;
				this.transformWidgetInto(widget, webSpecInteraction.getRoot(), webSpecInteraction);
			}
			
			interactionConvertions.put(interaction, webSpecInteraction);
		}
		
		if (webSpecDiagram.getStartingInteraction() == null) {
			this.addError("A diagram must have one and only one starting interaction");
		}
	}

	private void transformWidgetInto(webspecplugin.webspecmodel.Widget widget, Container container, Interaction webSpecInteraction) {
		Widget webspecWidget = null;
		
		if (widget instanceof webspecplugin.webspecmodel.Button) {
			webspecWidget = new Button();
		} else if (widget instanceof webspecplugin.webspecmodel.CheckBox) {
			webspecWidget = new CheckBox();
		} else if (widget instanceof webspecplugin.webspecmodel.ComboBox) {
			webspecWidget = new ComboBox();
		} else if (widget instanceof webspecplugin.webspecmodel.ListBox) {
			webspecWidget = new ListBox();
		} else if (widget instanceof webspecplugin.webspecmodel.RadioButton) {
			webspecWidget = new RadioButton();
		} else if (widget instanceof webspecplugin.webspecmodel.TextField) {
			webspecWidget = new TextField();
		} else if (widget instanceof webspecplugin.webspecmodel.Link) {
			webspecWidget = new Link();
		} else if (widget instanceof webspecplugin.webspecmodel.Label) {
			webspecWidget = new Label();
		} else if (widget instanceof webspecplugin.webspecmodel.ListOfContainer) {
			webspecWidget = new ListOfContainer();
		} else if (widget instanceof webspecplugin.webspecmodel.PanelContainer) {
			webspecWidget = new Panel();
		}

		webspecWidget.setLocation(widget.getLocation());
		webspecWidget.setName(widget.getName());
		try {
			container.addWidget(webspecWidget);
		} catch (ExistingWidgetException e) {
			this.addError("Duplicate widget in interaction " + webSpecInteraction.getName() + " named " + e.getName());
		}
		
		if (webspecWidget instanceof Container) {
			webspecplugin.webspecmodel.Container c = (webspecplugin.webspecmodel.Container) widget;
			for (Object o2 : c.getWidgets()) {
			  webspecplugin.webspecmodel.Widget w = (webspecplugin.webspecmodel.Widget) o2;
				this.transformWidgetInto(w, (Container) webspecWidget, webSpecInteraction);
			}
		}
	}
	
	private void createDiagram(webspecplugin.webspecmodel.Diagram diagram) {
		webSpecDiagram = new DiagramImpl(diagram.getName());
		webSpecDiagram.setCyclesAllowed(diagram.getCycleAllowed());
	}
	
	private void addActionsSetup(webspecplugin.webspecmodel.Diagram diagram) {
    //actions
    try {
      List<Action> actions = ActionParser.getActions(
          diagram.getActionsSetup(), 
          webSpecDiagram);
      if (actions != null) {
        for (Action action : actions) {
          webSpecDiagram.addActionSetup(action);
        }
      }
    } catch (ParsingException e) {
      if (e.getCause() instanceof LexerException) {
        LexerException lexE = (LexerException) e.getCause();
        this.addError("Error in actions setup of diagram: " + lexE.getMessage());
      } else if (e.getCause() instanceof ParserException) {
        ParserException parE = (ParserException) e.getCause();
        this.addError("Error in actions setup of diagram: " + parE.getMessage());
      } else {
        this.addError("Error in actions setup of diagram: " + e.getMessage());
      }
    } catch (WidgetNotFoundException e) {
      this.addError("Widget " + e.getWidgetName() + " in interaction " + e.getInteractionName() + " not found");
    }
	}

	private void addError(String string) {
		this.getErrors().add(string);
	}

	private void addWarning(String string) {
		this.getWarnings().add(string);
	}

	private List<String> getErrors() {
		return errors;
	}

	private void setErrors(List<String> errors) {
		this.errors = errors;
	}

	private List<String> getWarnings() {
		return warnings;
	}

	private void setWarnings(List<String> warnings) {
		this.warnings = warnings;
	}
}
