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
package org.webspeclanguage.metamock.utils;

/**
 * Class representing a point in 2D space
 * 
 * @author Jose Matias Rivero
 */
public class Point {

	private Integer x;
	private Integer y;

	public Point(Integer x, Integer y) {
		super();
		this.setX(x);
		this.setY(y);
	}

	private void setY(Integer y) {
		this.y = y;
	}

	public Integer getY() {
		return y;
	}

	private void setX(Integer x) {
		this.x = x;
	}

	public Integer getX() {
		return x;
	}
	
	public boolean isNear(Point point, Integer pixelTolerance) {
	  return
	    this.getX() - pixelTolerance <= point.getX() && point.getX() <= this.getX() + pixelTolerance && 
	    this.getY() - pixelTolerance <= point.getY() && point.getY() <= this.getY() + pixelTolerance;
	}
	
	@Override
	public boolean equals(Object point) {
	  if (!(point instanceof Point)) {
	    return false;
	  }
	  return 
	    this.getX().equals(((Point)point).getX()) &&
	    this.getY().equals(((Point)point).getY());
	}
	
	public int hashCode() {
	  return (this.getX() * 2) + ((this.getY() * 2) - 1);
	}
	
	public String toString() {
	  return "(" + this.getX() + ", " + this.getY() + ")";
	}

}
