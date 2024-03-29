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
 * Class allowing the construction of object pairs
 * 
 * @author Jose Matias Rivero
 */
public class Pair<T1, T2> {

	private T1 object1;
	private T2 object2;
	
	public Pair(T1 object1, T2 object2) {
		super();
		this.setObject1(object1);
		this.setObject2(object2);
	}

	public final void setObject1(T1 object1) {
		this.object1 = object1;
	}

	public T1 getObject1() {
		return object1;
	}

	public final void setObject2(T2 object2) {
		this.object2 = object2;
	}

	public T2 getObject2() {
		return object2;
	}
	
	@SuppressWarnings("unchecked")
  public boolean equals(Object object) {
	  if (object.getClass().equals(object.getClass())) {
	    return false;
	  }
	  return 
	    this.getObject1().equals(((Pair<T1, T2>)object).getObject1()) &&
	    this.getObject2().equals(((Pair<T1, T2>)object).getObject2());
	}
	
	public int hashCode() {
	  return this.getObject1().hashCode() ^ this.getObject2().hashCode();
	}

}
