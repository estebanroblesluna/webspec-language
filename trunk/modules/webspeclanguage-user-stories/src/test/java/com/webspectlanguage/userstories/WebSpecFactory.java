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
package com.webspectlanguage.userstories;

import org.webspeclanguage.api.Diagram;
import org.webspeclanguage.impl.core.DiagramImpl;
import org.webspeclanguage.impl.core.InteractionImpl;
import org.webspeclanguage.impl.core.NavigationImpl;
import org.webspeclanguage.impl.expression.core.NotEqualsExpression;
import org.webspeclanguage.impl.expression.core.StringConstant;
import org.webspeclanguage.impl.expression.core.WidgetPropertyReference;
import org.webspeclanguage.impl.generator.RandomStringGenerator;
import org.webspeclanguage.impl.widget.Button;
import org.webspeclanguage.impl.widget.Label;
import org.webspeclanguage.impl.widget.TextField;

/**
 * Factory class to provide a shopping cart example for tests.
 * 
 * @author cristian.cianfagna
 */
public class WebSpecFactory {

  public static Diagram getShoppingCartExample() {
    DiagramImpl diagram = new DiagramImpl("Diagram for a shopping cart");
    diagram.setCyclesAllowed(1);

    diagram.addGenerator(new RandomStringGenerator("stringGenerator", 10));

    InteractionImpl home = new InteractionImpl("Home");
    home.setMockupFile(WebSpecFactory.class.getResource("HomeMockup.png").getFile());
    home.setLocation("http://shoppingcartexample.com");

    TextField searchText = new TextField();
    searchText.setId("searchText");
    home.addWidget(searchText);

    Button button = new Button();
    button.setId("search");
    home.addWidget(button);

    InteractionImpl searchResults = new InteractionImpl("SearchResults");
    searchResults.setMockupFile(WebSpecFactory.class.getResource("SearchResults.png").getFile());

    Label productName = new Label();
    productName.setId("productName");
    searchResults.addWidget(productName);

    Label productPrice = new Label();
    productPrice.setId("productPrice");
    searchResults.addWidget(productPrice);

    Button button2 = new Button();
    button2.setId("addToCart");
    searchResults.addWidget(button2);

    InteractionImpl shoppingCart = new InteractionImpl("ShoppingCart");
    shoppingCart.setMockupFile(WebSpecFactory.class.getResource("ShoppingCart.png").getFile());

    Label productName1 = new Label();
    productName1.setId("productName");
    shoppingCart.addWidget(productName1);

    Label productPrice1 = new Label();
    productPrice1.setId("productPrice");
    shoppingCart.addWidget(productPrice1);

    InteractionImpl productDetail = new InteractionImpl("ProductDetail");
    productDetail.setMockupFile(WebSpecFactory.class.getResource("ProductDetail.png").getFile());

    Label productName2 = new Label();
    productName2.setId("productName");
    productDetail.addWidget(productName2);

    Label productPrice2 = new Label();
    productPrice2.setId("productPrice");
    productDetail.addWidget(productPrice2);

    Button button3 = new Button();
    button3.setId("addToCart");
    productDetail.addWidget(button3);

    NavigationImpl homeTOsearchResutlsNavigationImpl = (NavigationImpl) home.navigateTo(searchResults);
    homeTOsearchResutlsNavigationImpl.setName("search");
    homeTOsearchResutlsNavigationImpl.setPrecondition(
    		new NotEqualsExpression(
    				new WidgetPropertyReference(searchText, "text"),
    				new StringConstant(""))		
    );
    homeTOsearchResutlsNavigationImpl.setActions("type(Home.searchText, $stringGenerator$); click(Home.search)");

    NavigationImpl searchResultsTOproductDetailNavigationImpl = (NavigationImpl) searchResults.navigateTo(productDetail);
    searchResultsTOproductDetailNavigationImpl.setName("view product details1");
    searchResultsTOproductDetailNavigationImpl.setPrecondition("true");
    searchResultsTOproductDetailNavigationImpl.setActions("click(SearchResults.productName)");

    NavigationImpl searchResultsTOshoppingCartNavigationImpl = (NavigationImpl) searchResults.navigateTo(shoppingCart);
    searchResultsTOshoppingCartNavigationImpl.setName("add to cart1");
    searchResultsTOshoppingCartNavigationImpl.setPrecondition("true");
    searchResultsTOshoppingCartNavigationImpl.setActions("click(SearchResults.addToCart)");

    NavigationImpl productDetailTOshoppingCartNavigationImpl = (NavigationImpl) productDetail.navigateTo(shoppingCart);
    productDetailTOshoppingCartNavigationImpl.setName("add to cart2");
    productDetailTOshoppingCartNavigationImpl.setPrecondition("true");
    productDetailTOshoppingCartNavigationImpl.setActions("click(ProductDetail.addToCart)");

    NavigationImpl shoppingCartTOproductDetailNavigationImpl = (NavigationImpl) shoppingCart.navigateTo(productDetail);
    shoppingCartTOproductDetailNavigationImpl.setName("view product details2");
    shoppingCartTOproductDetailNavigationImpl.setPrecondition("true");
    shoppingCartTOproductDetailNavigationImpl.setActions("click(ShoppingCart.productName)");

    diagram.setStartingInteraction(home);
    diagram.addInteraction(home);
    diagram.addInteraction(searchResults);
    diagram.addInteraction(productDetail);
    diagram.addInteraction(shoppingCart);

    return diagram;
  }

}
