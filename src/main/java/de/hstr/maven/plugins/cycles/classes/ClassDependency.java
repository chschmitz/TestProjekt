/**
 * Copyright 1&1 Internet AG, https://github.com/1and1/
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.hstr.maven.plugins.cycles.classes;

import com.google.common.base.Objects;

/**
 * Represents a single class dependency.
 * 
 * @author chschmitz
 */
public final class ClassDependency {
    private String from;
    private String to;

    /**
     * @param from the depending class
     * @param to the class that <code>from</code> depends on
     */
    public ClassDependency(String from, String to) {
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(from, to);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        ClassDependency other = (ClassDependency) obj;
        return Objects.equal(from, other.from) && Objects.equal(to, other.to);
    }
    
    @Override
    public String toString() {
        return from + "->" + to;
    }
}
