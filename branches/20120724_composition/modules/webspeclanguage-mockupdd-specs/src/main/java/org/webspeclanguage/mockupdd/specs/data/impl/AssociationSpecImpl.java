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

package org.webspeclanguage.mockupdd.specs.data.impl;

import org.webspeclanguage.mockupdd.specs.data.AssociationSpec;
import org.webspeclanguage.mockupdd.specs.data.ClassSpec;
import org.webspeclanguage.mockupdd.specs.data.MaximumCardinality;

/**
 * Represents an directed association to an {@link ClassSpec}
 * 
 * @author Jose Matias Rivero
 */
public class AssociationSpecImpl implements AssociationSpec {

	private ClassSpec destinationClass;
	private String associationName;
	private MaximumCardinality maximumCardinality;

	public AssociationSpecImpl(ClassSpec destinationClass,
			String associationName, MaximumCardinality maximumCardinality) {
		super();
		this.setDestinationClass(destinationClass);
		this.setAssociationName(associationName);
		this.setMaximumCardinality(maximumCardinality);
	}

	public ClassSpec getDestinationClass() {
		return destinationClass;
	}

	private void setDestinationClass(ClassSpec destinationClass) {
		this.destinationClass = destinationClass;
	}

	public String getAssociationName() {
		return associationName;
	}

	private void setAssociationName(String associationName) {
		this.associationName = associationName;
	}

	public MaximumCardinality getMaximumCardinality() {
		return maximumCardinality;
	}

	private void setMaximumCardinality(MaximumCardinality maximumCardinality) {
		this.maximumCardinality = maximumCardinality;
	}

}
