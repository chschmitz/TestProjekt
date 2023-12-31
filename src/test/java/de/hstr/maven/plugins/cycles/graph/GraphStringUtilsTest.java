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
package de.hstr.maven.plugins.cycles.graph;

import static de.hstr.maven.plugins.cycles.graph.GraphStringUtils.shorten;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class GraphStringUtilsTest {

    @Test
    public void testShortenEmpty() {
        assertThat(shorten("", true), is(""));
    }

    @Test
    public void testShorten() {
        assertThat(shorten("foo.bar.baz.quux.blurfl", true), is("f.b.b.q.blurfl"));
    }

    @Test
    public void testDontShorten() {
        assertThat(shorten("foo.bar.baz.quux.blurfl", false), is("foo.bar.baz.quux.blurfl"));
    }
}
