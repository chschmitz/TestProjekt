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

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import edu.uci.ics.jung.graph.DirectedGraph;

public class GraphBuilderTest {
    private File baseDir;

    @Before
    public void setUp() {
        baseDir = new File(GraphBuilderTest.class.getResource(".").getFile());
    }
    
    @Test
    public void testBuildGraph() throws IOException {
        DirectedGraph<String, WeightedEdge> graph = GraphBuilder.buildPackageGraph(NameFilter.nameFilter("de.hstr.maven.plugins.cycles.graph"), 
                baseDir);
        assertThat(graph.getVertexCount(), is(3));
        assertThat(graph.getEdgeCount(), is(3));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testBuildGraphCollapse() throws IOException {
        DirectedGraph<String, WeightedEdge> graph = GraphBuilder.buildPackageGraph(NameFilter.nameFilter("de.hstr.maven.plugins.cycles.graph"), 
                7, baseDir);
        
        assertThat(graph.getVertexCount(), is(2));
        assertThat(graph.getEdgeCount(), is(2));
        
        Set<Double> actualWeights = new HashSet<Double>();
        for (WeightedEdge e: graph.getEdges()) {
            actualWeights.add(e.getWeight());
        }
        
        assertThat(actualWeights.size(), is(2));
        assertThat(actualWeights, hasItems(closeTo(1d, 1e-7), closeTo(2d, 1e-7)));
    }
}
