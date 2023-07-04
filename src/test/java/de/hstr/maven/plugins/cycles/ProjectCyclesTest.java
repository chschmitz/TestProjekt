package de.hstr.maven.plugins.cycles;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.IOException;

import de.hstr.maven.plugins.cycles.analyzer.ComponentAnalysis;

import org.junit.Test;

import com.google.common.base.Predicate;

public class ProjectCyclesTest {
    /**
     * Eat your own dog food - make sure that this project does not have cycles itself.
     * @throws IOException 
     */
    @Test
    public void testNoCycles() throws IOException {
        Predicate<String> nameFilter = new Predicate<String>() {

            @Override
            public boolean apply(String pkgName) {
                // Exclude cycle we've introduced for testing
                return pkgName.startsWith(ProjectCyclesTest.class.getPackage().getName()) 
                        && !pkgName.startsWith("de.hstr.maven.plugins.cycles.graph.sink") 
                        && !pkgName.startsWith("de.hstr.maven.plugins.cycles.graph.source");
            }
        };
        
        ComponentAnalysis analysis = new ComponentAnalysis(nameFilter,
                Integer.MAX_VALUE,
                new File("target/classes"), new File("target/test-classes"));
        assertThat(analysis.hasNonTrivialComponents(), is(false));
    }
}
