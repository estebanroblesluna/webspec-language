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
package org.webspeclanguage.impl.core;

import org.webspeclanguage.api.Navigation;
import org.webspeclanguage.impl.expression.core.AndExpression;
import org.webspeclanguage.impl.expression.core.EqualsExpression;
import org.webspeclanguage.impl.expression.core.Expression;
import org.webspeclanguage.impl.expression.core.FunctionCallExpression;
import org.webspeclanguage.impl.expression.core.NotExpression;
import org.webspeclanguage.impl.expression.core.StringConstant;
import org.webspeclanguage.impl.expression.core.VariableValue;
import org.webspeclanguage.impl.expression.core.WidgetPropertyReference;
import org.webspeclanguage.impl.generator.OneOfNumbers;
import org.webspeclanguage.impl.generator.OneOfStrings;
import org.webspeclanguage.impl.generator.UniformNumberGenerator;
import org.webspeclanguage.impl.widget.Button;
import org.webspeclanguage.impl.widget.Label;
import org.webspeclanguage.impl.widget.ListOfContainer;
import org.webspeclanguage.impl.widget.TextField;

/**
 * A factory for WebSpec examples
 * 
 * @author Esteban Robles Luna
 */
public final class WebSpecFactory {

  private WebSpecFactory() {}
  
  public static DiagramImpl getWebSpecDiagram() {
    DiagramImpl diagram = new DiagramImpl("i1");

    InteractionImpl i1 = new InteractionImpl("i1");
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

    InteractionImpl i2 = new InteractionImpl("i2");

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

    InteractionImpl i3 = new InteractionImpl("i3");

    Label welcomeMessage = new Label();
    welcomeMessage.setId("welcomeMessage");
    i2.addWidget(welcomeMessage);

    Navigation i1i2 = i1.navigateTo(i2);
    Navigation i1i3 = i1.navigateTo(i3);
    Navigation i2i3 = i2.navigateTo(i3);

    diagram.setStartingInteraction(i1);
    diagram.addInteraction(i1);
    diagram.addInteraction(i2);
    diagram.addInteraction(i3);

    Expression i1Invariant = new EqualsExpression(new WidgetPropertyReference(
        usernameLabel, "value"), new StringConstant("Username"));
    i1.setInvariant(i1Invariant);
    
    i1i2.setActions("String username := $invalidUsernames$; type(i1.username, ${username}); click(i1.login)");
    
    Expression i2Invariant = new EqualsExpression(new WidgetPropertyReference(
        errorMessage, "value"), new StringConstant("Username invalid"));
    i2.setInvariant(i2Invariant);

    i1i3.setActions("String username := $validUsernames$; type(i1.username, ${username}); click(i1.login)");

    Expression i3Invariant = new EqualsExpression(new WidgetPropertyReference(
        welcomeMessage, "value"), new StringConstant("Welcome"));
    i3.setInvariant(i3Invariant);

    i2i3.setActions("String username := $validUsernames$; type(i1.username, ${username}); click(i1.login)");

    return diagram;
  }

  public static DiagramImpl getAmazonExample() {
    DiagramImpl diagram = new DiagramImpl("Amazon example");

    diagram.addGenerator(new OneOfStrings("productNames", "ipod", "iphone"));
    diagram.addGenerator(new OneOfNumbers("productIndexs", 0, 2, 3, 4, 5, 6, 7, 8, 9));
    diagram.addGenerator(new UniformNumberGenerator("relatedIndexs", 1, 3));

    InteractionImpl i1 = new InteractionImpl("Home");
    i1.setTitle("\"Amazon\"");
    i1.setLocation("http://www.amazon.com");
    diagram.setStartingInteraction(i1);
    diagram.addInteraction(i1);

    TextField searchTextField = new TextField();
    searchTextField.setId("twotabsearchtextbox");
    i1.addWidget(searchTextField);

    Button searchButton = new Button();
    searchButton.setLocation("//div[@id='navGoButton']/input");
    i1.addWidget(searchButton);

    InteractionImpl i2 = new InteractionImpl("SearchResults");
    diagram.addInteraction(i2);
    
    TextField searchTextFieldResult = new TextField();
    searchTextFieldResult.setName("searchTextField");
    searchTextFieldResult.setLocation("//input[@id='twotabsearchtextbox']");
    i2.addWidget(searchTextFieldResult);

    ListOfContainer widgetsList = new ListOfContainer();
    widgetsList.setIndexVariable("productIndex");
    i2.addWidget(widgetsList);

    Label searchItemTitle = new Label();
    searchItemTitle
        .setLocation("//div[@id='result_${productIndex}']/div[3]/div[1]/a");
    widgetsList.addWidget(searchItemTitle);
    
    Button searchButtonSearchResults = new Button();
    searchButtonSearchResults.setName("searchButton");
    searchButtonSearchResults.setLocation("//div[@id='navGoButton']/input");
    i2.addWidget(searchButtonSearchResults);

    Navigation i1i2 = i1.navigateTo(i2);

    i1i2.setActions("String productName := $productNames$; type(SearchResults.searchTextField, ${productName}); click(SearchResults.searchButton)");

    Expression i2Invariant = new EqualsExpression(new WidgetPropertyReference(
        searchTextFieldResult, "value"), new VariableValue("productName"));
    i2.setInvariant(i2Invariant);

    InteractionImpl i3 = new InteractionImpl("Product detail");

    Label titleLabel = new Label();
    titleLabel.setLocation("//span[@id='btAsinTitle']");
    i3.addWidget(titleLabel);

    Expression i3Invariant = new FunctionCallExpression("isTextPresent", new VariableValue("productName"));
    i3.setInvariant(i3Invariant);

    Navigation i2i3 = i2.navigateTo(i3);
    
    i2i3.setActions("Number productIndex := $productIndexs$; click(SearchResults.searchItemTitle)");

    return diagram;
  }

  public static DiagramImpl getAmazonExample2() {
    DiagramImpl diagram = new DiagramImpl("Amazon example2");

    diagram.addGenerator(new OneOfStrings("productNames", "ipod", "iphone"));
    diagram.addGenerator(new OneOfNumbers("productIndexs", 0, 2, 3, 4, 5, 6, 7, 8, 9));
    diagram.addGenerator(new UniformNumberGenerator("relatedIndexs", 1, 3));

    InteractionImpl homeInteraction = new InteractionImpl("Home");
    homeInteraction.setLocation("http://www.amazon.com");
    diagram.addInteraction(homeInteraction);

    InteractionImpl searchResultsInteraction = new InteractionImpl("SearchResults");
    diagram.addInteraction(searchResultsInteraction);

    InteractionImpl productDetailInteraction = new InteractionImpl("ProductDetail");
    diagram.addInteraction(productDetailInteraction);

    InteractionImpl productAddedToSCInteraction = new InteractionImpl("ProductAddedToSC");
    diagram.addInteraction(productAddedToSCInteraction);

    InteractionImpl relatedProductAddedToSCInteraction = new InteractionImpl("RelatedProductAddedToSC");
    diagram.addInteraction(relatedProductAddedToSCInteraction);

    InteractionImpl home2Interaction = new InteractionImpl("Home 2");
    diagram.addInteraction(home2Interaction);

    diagram.setStartingInteraction(homeInteraction);

    // HOME
    TextField searchField = homeInteraction.createTextFieldWithId("twotabsearchtextbox");
    Button searchButton = homeInteraction.createButtonWithLocation("//div[@id='navGoButton']/input");
    homeInteraction.addWidget(searchField);
    homeInteraction.addWidget(searchButton);
    searchField.setName("searchField");
    searchButton.setName("searchButton");
    
    // SEARCH RESULTS
    TextField searchTextFieldResult = searchResultsInteraction.createTextFieldWithLocation("//input[@id='twotabsearchtextbox']");
    ListOfContainer widgetsList = searchResultsInteraction.createListOfContainer("productIndex");
    Label searchItemTitle = widgetsList.createLabelWithLocation("//div[@id='result_${productIndex}']/div[3]/div[1]/a");
    searchResultsInteraction.addWidget(searchTextFieldResult);
    searchResultsInteraction.addWidget(widgetsList);
    searchResultsInteraction.addWidget(searchItemTitle);
    searchTextFieldResult.setName("searchTextFieldResult");
    widgetsList.setName("widgetsList");
    searchItemTitle.setName("searchItemTitle");
    
    
    // PRODUCT DETAIL
    Label priceLabel = productDetailInteraction.createLabelWithLocation("//div[@id='priceBlock']/table/tbody/tr[2]/td[2]/b[1]");
    Button addToCartButton = productDetailInteraction.createButtonWithLocation("//span[@id='addToCartSpan']/input[@id='']");
    Button homeButton = productDetailInteraction.createButtonWithLocation("//span[@id='navLogoPrimary']");
    productDetailInteraction.addWidget(priceLabel);
    productDetailInteraction.addWidget(addToCartButton);
    productDetailInteraction.addWidget(homeButton);
    priceLabel.setName("price");
    addToCartButton.setName("addToCart");
    homeButton.setName("home");
    
    // PRODUCT ADDED TO SC
    ListOfContainer listOfRelatedProducts = productAddedToSCInteraction.createListOfContainer("relatedProductIndex");
    Label relatedProductPriceLabel = listOfRelatedProducts.createLabelWithLocation("//html/body/table[2]/tbody/tr/td[1]/span/table/tbody/tr[5]/td[${relatedProductIndex}]/span[2]");
    Button relatedProductButton = productAddedToSCInteraction.createButtonWithLocation("//html/body/table[2]/tbody/tr/td[1]/span/table/tbody/tr[7]/td[${relatedProductIndex}]/a/img");
    productAddedToSCInteraction.addWidget(listOfRelatedProducts);
    productAddedToSCInteraction.addWidget(relatedProductPriceLabel);
    productAddedToSCInteraction.addWidget(relatedProductButton);
    listOfRelatedProducts.setName("listOfRelatedProducts");
    relatedProductPriceLabel.setName("relatedProductPrice");
    relatedProductButton.setName("relatedProduct");

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
    Navigation homeToSearchResults = homeInteraction.navigateTo(searchResultsInteraction);
    homeToSearchResults.setActions("String productName := $productNames$; type(Home.searchField, ${productName}); click(Home.searchButton)");
    
    // SEARCH RESULTS->PRODUCT DETAIL
    Navigation searchResultsToProductDetail = searchResultsInteraction.navigateTo(productDetailInteraction);
    searchResultsToProductDetail.setActions("Number productIndex := $productIndexs$; String productName := SearchResults.searchItemTitle.text; click(SearchResults.searchItemTitle)");

    // PRODUCT DETAIL->PRODUCT ADDED TO SC
    Navigation productDetailToProductAddedToSC = productDetailInteraction.navigateTo(productAddedToSCInteraction);
    productDetailToProductAddedToSC.setActions("String price := ProductDetail.price.text; click(ProductDetail.addToCart)");

    // PRODUCT DETAIL->HOME 2
    Navigation productDetailToHome = productDetailInteraction.navigateTo(home2Interaction);
    productDetailToHome.setActions("click(ProductDetail.home)");

    // PRODUCT ADDED TO SC->RELATED PRODUCT ADDED TO SC
    Navigation productAddedToRelatedAdded = productAddedToSCInteraction.navigateTo(relatedProductAddedToSCInteraction);
    productAddedToRelatedAdded.setActions("Number relatedProductIndex := $relatedIndexs$; String relatedPrice := ProductAddedToSC.relatedProductPrice.text; click(ProductAddedToSC.relatedProductButton)");
    
    return diagram;
  }

  @SuppressWarnings("unused")
  public static DiagramImpl getCycleExample() {
    DiagramImpl diagram = new DiagramImpl("Cycle example");

    InteractionImpl i1 = new InteractionImpl("i1");
    i1.setLocation("http://www.amazon.com");
    diagram.setStartingInteraction(i1);
    diagram.addInteraction(i1);

    InteractionImpl i2 = new InteractionImpl("i2");
    diagram.addInteraction(i2);

    InteractionImpl i3 = new InteractionImpl("i3");
    diagram.addInteraction(i3);

    Navigation i1i2 = i1.navigateTo(i2);
    Navigation i2i1 = i2.navigateTo(i1);
    
    Navigation i1i3 = i1.navigateTo(i3);
    return diagram;
  }
}
