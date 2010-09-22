/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.webspeclanguage.base;

import org.webspeclanguage.action.ExpressionAction;
import org.webspeclanguage.action.LetVariable;
import org.webspeclanguage.expression.base.AbstractFunctionCallExpression;
import org.webspeclanguage.expression.base.AndExpression;
import org.webspeclanguage.expression.base.EqualsExpression;
import org.webspeclanguage.expression.base.Expression;
import org.webspeclanguage.expression.base.ExpressionType;
import org.webspeclanguage.expression.base.FunctionCallExpression;
import org.webspeclanguage.expression.base.GeneratorExpression;
import org.webspeclanguage.expression.base.NotExpression;
import org.webspeclanguage.expression.base.StringConstant;
import org.webspeclanguage.expression.base.VariableValue;
import org.webspeclanguage.expression.base.WidgetPropertyReference;
import org.webspeclanguage.expression.base.WidgetReference;
import org.webspeclanguage.generator.OneOfNumbers;
import org.webspeclanguage.generator.OneOfStrings;
import org.webspeclanguage.generator.UniformNumberGenerator;
import org.webspeclanguage.widget.Button;
import org.webspeclanguage.widget.Label;
import org.webspeclanguage.widget.ListOfContainer;
import org.webspeclanguage.widget.TextField;

/**
 * A factory for WebSpec examples
 * 
 * @author Esteban Robles Luna
 */
public final class WebSpecFactory {

  private WebSpecFactory() {}
  
  public static WebSpecDiagram getWebSpecDiagram() {
    WebSpecDiagram diagram = new WebSpecDiagram("i1");

    WebSpecInteraction i1 = new WebSpecInteraction("i1", diagram);
    i1.setLocation("http://www.google.com");

    Label usernameLabel = new Label();
    usernameLabel.setId("usernameLabel");
    i1.addWidget(usernameLabel);

    TextField username = new TextField();
    username.setId("username");
    i1.addWidget(username);

    TextField password = new TextField();
    i1.addWidget(password);

    Button button = new Button();
    button.setId("login");
    i1.addWidget(button);

    WebSpecInteraction i2 = new WebSpecInteraction("i2", diagram);

    Label errorMessage = new Label();
    errorMessage.setId("message");
    i2.addWidget(errorMessage);

    TextField username2 = new TextField();
    username2.setId("username");
    i2.addWidget(username2);

    TextField password2 = new TextField();
    i2.addWidget(password2);

    Button button2 = new Button();
    button2.setId("login");
    i2.addWidget(button2);

    WebSpecInteraction i3 = new WebSpecInteraction("i3", diagram);

    Label welcomeMessage = new Label();
    welcomeMessage.setId("message");
    i2.addWidget(welcomeMessage);

    WebSpecNavigation i1i2 = i1.navigateTo(i2);
    WebSpecNavigation i1i3 = i1.navigateTo(i3);
    WebSpecNavigation i2i3 = i2.navigateTo(i3);

    diagram.setStartingInteraction(i1);

    Expression i1Invariant = new EqualsExpression(new WidgetPropertyReference(
        usernameLabel, "value"), new StringConstant("Username"));
    i1.setInvariant(i1Invariant);

    i1i2.addAction(new LetVariable("username", new GeneratorExpression(
        "invalidUsernames"), ExpressionType.STRING));
    i1i2.addAction(new ExpressionAction(
        new FunctionCallExpression(
            AbstractFunctionCallExpression.TYPE, new WidgetReference(username),
            new VariableValue("username"))));
    i1i2.addAction(new ExpressionAction(
        new FunctionCallExpression(
            AbstractFunctionCallExpression.CLICK, new WidgetReference(button))));

    Expression i2Invariant = new EqualsExpression(new WidgetPropertyReference(
        errorMessage, "value"), new StringConstant("Username invalid"));
    i2.setInvariant(i2Invariant);

    i1i3.addAction(new LetVariable("username", new GeneratorExpression(
        "validUsernames"), ExpressionType.STRING));
    i1i3.addAction(new ExpressionAction(
        new FunctionCallExpression(
            AbstractFunctionCallExpression.TYPE, new WidgetReference(username),
            new VariableValue("username"))));
    i1i3.addAction(new ExpressionAction(
        new FunctionCallExpression(
            AbstractFunctionCallExpression.CLICK, new WidgetReference(button))));

    Expression i3Invariant = new EqualsExpression(new WidgetPropertyReference(
        welcomeMessage, "value"), new StringConstant("Welcome"));
    i3.setInvariant(i3Invariant);

    i2i3.addAction(new LetVariable("username", new GeneratorExpression(
        "validUsernames"), ExpressionType.STRING));
    i2i3.addAction(new ExpressionAction(
        new FunctionCallExpression(
            AbstractFunctionCallExpression.TYPE, new WidgetReference(username2),
            new VariableValue("username"))));
    i2i3.addAction(new ExpressionAction(
        new FunctionCallExpression(
            AbstractFunctionCallExpression.CLICK, new WidgetReference(button2))));
    return diagram;
  }

  public static WebSpecDiagram getAmazonExample() {
    WebSpecDiagram diagram = new WebSpecDiagram("Amazon example");

    diagram.addGenerator("productNames", new OneOfStrings("ipod", "iphone"));

    diagram.addGenerator("productIndexs", new OneOfNumbers(0, 2, 3, 4, 5, 6, 7,
        8, 9));

    diagram.addGenerator("relatedIndexs", new UniformNumberGenerator(1, 3));

    WebSpecInteraction i1 = new WebSpecInteraction("Home", diagram);
    i1.setTitle("\"Amazon\"");
    i1.setLocation("http://www.amazon.com");
    diagram.setStartingInteraction(i1);

    TextField searchTextField = new TextField();
    searchTextField.setId("twotabsearchtextbox");
    i1.addWidget(searchTextField);

    Button searchButton = new Button();
    searchButton.setLocation("//div[@id='navGoButton']/input");
    i1.addWidget(searchButton);

    WebSpecInteraction i2 = new WebSpecInteraction("Search results", diagram);

    TextField searchTextFieldResult = new TextField();
    searchTextFieldResult.setLocation("//input[@id='twotabsearchtextbox']");
    i2.addWidget(searchTextFieldResult);

    ListOfContainer widgetsList = new ListOfContainer();
    widgetsList.setIndexVariable("productIndex");
    i2.addWidget(widgetsList);

    Label searchItemTitle = new Label();
    searchItemTitle
        .setLocation("//div[@id='result_${productIndex}']/div[3]/div[1]/a");
    widgetsList.addWidget(searchItemTitle);

    WebSpecNavigation i1i2 = i1.navigateTo(i2);

    i1i2.addAction(new LetVariable("productName", new GeneratorExpression(
        "productNames"), ExpressionType.STRING));
    i1i2.addAction(new ExpressionAction(
        new FunctionCallExpression(
            AbstractFunctionCallExpression.TYPE, new WidgetReference(searchTextField),
            new VariableValue("productName"))));
    i1i2.addAction(new ExpressionAction(
        new FunctionCallExpression(
            AbstractFunctionCallExpression.CLICK, new WidgetReference(searchButton))));

    Expression i2Invariant = new EqualsExpression(new WidgetPropertyReference(
        searchTextFieldResult, "value"), new VariableValue("productName"));
    i2.setInvariant(i2Invariant);

    WebSpecInteraction i3 = new WebSpecInteraction("Product detail", diagram);

    Label titleLabel = new Label();
    titleLabel.setLocation("//span[@id='btAsinTitle']");
    i3.addWidget(titleLabel);

    Expression i3Invariant = new FunctionCallExpression("isTextPresent", new VariableValue("productName"));
    i3.setInvariant(i3Invariant);

    WebSpecNavigation i2i3 = i2.navigateTo(i3);
    i2i3.addAction(new LetVariable("productIndex", new GeneratorExpression(
        "productIndexs"), ExpressionType.NUMBER));
    i2i3
        .addAction(new ExpressionAction(
            new FunctionCallExpression(
                AbstractFunctionCallExpression.CLICK, new WidgetReference(
                    searchItemTitle))));

    return diagram;
  }

  public static WebSpecDiagram getAmazonExample2() {
    WebSpecDiagram diagram = new WebSpecDiagram("Amazon example2");

    diagram.addGenerator("productNames", new OneOfStrings("ipod", "iphone"));

    diagram.addGenerator("productIndexs", new OneOfNumbers(0, 2, 3, 4, 5, 6, 7,
        8, 9));

    diagram.addGenerator("relatedIndexs", new UniformNumberGenerator(1, 3));

    WebSpecInteraction homeInteraction = new WebSpecInteraction("Home", diagram);
    homeInteraction.setLocation("http://www.amazon.com");
    WebSpecInteraction searchResultsInteraction = new WebSpecInteraction(
        "Search results", diagram);
    WebSpecInteraction productDetailInteraction = new WebSpecInteraction(
        "Product detail", diagram);
    WebSpecInteraction productAddedToSCInteraction = new WebSpecInteraction(
        "Product added to shopping cart", diagram);
    WebSpecInteraction relatedProductAddedToSCInteraction = new WebSpecInteraction(
        "Related product added to shopping cart", diagram);
    WebSpecInteraction home2Interaction = new WebSpecInteraction("Home 2",
        diagram);

    diagram.setStartingInteraction(homeInteraction);

    // HOME
    TextField searchField = homeInteraction
        .createTextFieldWithId("twotabsearchtextbox");
    Button searchButton = homeInteraction
        .createButtonWithLocation("//div[@id='navGoButton']/input");

    // SEARCH RESULTS
    TextField searchTextFieldResult = searchResultsInteraction
        .createTextFieldWithLocation("//input[@id='twotabsearchtextbox']");
    ListOfContainer widgetsList = searchResultsInteraction
        .createListOfContainer("productIndex");
    Label searchItemTitle = widgetsList
        .createLabelWithLocation("//div[@id='result_${productIndex}']/div[3]/div[1]/a");

    // PRODUCT DETAIL
    Label priceLabel = productDetailInteraction
        .createLabelWithLocation("//div[@id='priceBlock']/table/tbody/tr[2]/td[2]/b[1]");
    Button addToCartButton = productDetailInteraction
        .createButtonWithLocation("//span[@id='addToCartSpan']/input[@id='']");
    Button homeButton = productDetailInteraction
        .createButtonWithLocation("//span[@id='navLogoPrimary']");

    // PRODUCT ADDED TO SC
    ListOfContainer listOfRelatedProducts = productAddedToSCInteraction
        .createListOfContainer("relatedProductIndex");
    Label relatedProductPriceLabel = listOfRelatedProducts
        .createLabelWithLocation("//html/body/table[2]/tbody/tr/td[1]/span/table/tbody/tr[5]/td[${relatedProductIndex}]/span[2]");
    Button relatedProductButton = productAddedToSCInteraction
        .createButtonWithLocation("//html/body/table[2]/tbody/tr/td[1]/span/table/tbody/tr[7]/td[${relatedProductIndex}]/a/img");

    // INVARIANT HOME
    Expression homeInteractionInvariant = new NotExpression(new FunctionCallExpression("isTextPresent", 
        new StringConstant("You looked at")));
    homeInteraction.setInvariant(homeInteractionInvariant);

    // INVARIANT SEARCH RESULTS
    Expression searchResultsInteractionInvariant = new EqualsExpression(
        new WidgetPropertyReference(searchTextFieldResult, "value"),
        new VariableValue("productName"));
    searchResultsInteraction.setInvariant(searchResultsInteractionInvariant);

    // INVARIANT PRODUCT DETAIL
    Expression productDetailInteractionInvariant = new FunctionCallExpression("isTextPresent", 
        new VariableValue("productName"));
    productDetailInteraction.setInvariant(productDetailInteractionInvariant);

    // INVARIANT PRODUCT ADDED TO SC
    Expression productAddedToSCInteractionInvariant = new FunctionCallExpression("isTextPresent", 
        new VariableValue("price"));
    productAddedToSCInteraction
        .setInvariant(productAddedToSCInteractionInvariant);

    // INVARIANT RELATED PRODUCT ADDED TO SC
    Expression relatedProductAddedToSCInteractionInvariant = new AndExpression(
            new FunctionCallExpression("isTextPresent", new VariableValue("price")), new FunctionCallExpression("isTextPresent", 
            new VariableValue("relatedPrice")));
    relatedProductAddedToSCInteraction
        .setInvariant(relatedProductAddedToSCInteractionInvariant);

    // INVARIANT HOME2
    Expression home2InteractionInvariant = new FunctionCallExpression("isTextPresent", new StringConstant(
        "You looked at"));
    home2Interaction.setInvariant(home2InteractionInvariant);

    // HOME->SEARCH RESULTS
    WebSpecNavigation homeToSearchResults = homeInteraction
        .navigateTo(searchResultsInteraction);
    homeToSearchResults.addAction(new LetVariable("productName",
        new GeneratorExpression("productNames"), ExpressionType.STRING));
    homeToSearchResults.addAction(new ExpressionAction(
        new FunctionCallExpression(
            AbstractFunctionCallExpression.TYPE, new WidgetReference(searchField),
            new VariableValue("productName"))));
    homeToSearchResults.addAction(new ExpressionAction(
        new FunctionCallExpression(
            AbstractFunctionCallExpression.CLICK, new WidgetReference(searchButton))));

    // SEARCH RESULTS->PRODUCT DETAIL
    WebSpecNavigation searchResultsToProductDetail = searchResultsInteraction
        .navigateTo(productDetailInteraction);
    searchResultsToProductDetail.addAction(new LetVariable("productIndex",
        new GeneratorExpression("productIndexs"), ExpressionType.NUMBER));
    searchResultsToProductDetail.addAction(new LetVariable("productName",
        new WidgetReference(searchItemTitle), ExpressionType.STRING));
    searchResultsToProductDetail
        .addAction(new ExpressionAction(
            new FunctionCallExpression(
                AbstractFunctionCallExpression.CLICK, new WidgetReference(
                    searchItemTitle))));

    // PRODUCT DETAIL->PRODUCT ADDED TO SC
    WebSpecNavigation productDetailToProductAddedToSC = productDetailInteraction
        .navigateTo(productAddedToSCInteraction);
    productDetailToProductAddedToSC.addAction(new LetVariable("price",
        new WidgetReference(priceLabel), ExpressionType.STRING));
    productDetailToProductAddedToSC
        .addAction(new ExpressionAction(
            new FunctionCallExpression(
                AbstractFunctionCallExpression.CLICK, new WidgetReference(
                    addToCartButton))));

    // PRODUCT DETAIL->HOME 2
    WebSpecNavigation productDetailToHome = productDetailInteraction
        .navigateTo(home2Interaction);
    productDetailToHome.addAction(new ExpressionAction(
        new FunctionCallExpression(
            AbstractFunctionCallExpression.CLICK, new WidgetReference(homeButton))));

    // PRODUCT ADDED TO SC->RELATED PRODUCT ADDED TO SC
    WebSpecNavigation productAddedToRelatedAdded = productAddedToSCInteraction
        .navigateTo(relatedProductAddedToSCInteraction);
    productAddedToRelatedAdded.addAction(new LetVariable("relatedProductIndex",
        new GeneratorExpression("relatedIndexs"), ExpressionType.NUMBER));
    productAddedToRelatedAdded.addAction(new LetVariable("relatedPrice",
        new WidgetReference(relatedProductPriceLabel), ExpressionType.STRING));
    productAddedToRelatedAdded.addAction(new ExpressionAction(
        new FunctionCallExpression(
            AbstractFunctionCallExpression.CLICK, new WidgetReference(
                relatedProductButton))));

    return diagram;
  }

  @SuppressWarnings("unused")
  public static WebSpecDiagram getCycleExample() {
    WebSpecDiagram diagram = new WebSpecDiagram("Cycle example");

    WebSpecInteraction i1 = new WebSpecInteraction("i1", diagram);
    i1.setLocation("http://www.amazon.com");
    diagram.setStartingInteraction(i1);
    diagram.addInteraction(i1);

    WebSpecInteraction i2 = new WebSpecInteraction("i2", diagram);
    diagram.addInteraction(i2);

    WebSpecInteraction i3 = new WebSpecInteraction("i3", diagram);
    diagram.addInteraction(i3);

    WebSpecNavigation i1i2 = i1.navigateTo(i2);
    WebSpecNavigation i2i1 = i2.navigateTo(i1);

    WebSpecNavigation i1i3 = i1.navigateTo(i3);
    return diagram;
  }
}
