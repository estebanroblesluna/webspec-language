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
package org.webspeclanguage.mockupdd.utils;

/**
 * Represents a rectangle, expressed by the position of its 4 boundaries
 * 
 * @author Jose Matias Rivero
 */
public class Rectangle {

	private Integer left;
	private Integer right;
	private Integer top;
	private Integer bottom;

	public Rectangle(Integer left, Integer right, Integer top, Integer bottom) {
		super();
		this.setLeft(left);
		this.setRight(right);
		this.setTop(top);
		this.setBottom(bottom);
	}

	private void setLeft(Integer left) {
		this.left = left;
	}

	public Integer getLeft() {
		return left;
	}

	private void setRight(Integer right) {
		this.right = right;
	}

	public Integer getRight() {
		return right;
	}

	private void setTop(Integer top) {
		this.top = top;
	}

	public Integer getTop() {
		return top;
	}

	private void setBottom(Integer bottom) {
		this.bottom = bottom;
	}

	public Integer getBottom() {
		return bottom;
	}
	
	public Integer getWidth() {
		return this.getRight() - this.getLeft();
	}
	
	public Integer getHeight() {
		return this.getBottom() - this.getTop();
	}

}
